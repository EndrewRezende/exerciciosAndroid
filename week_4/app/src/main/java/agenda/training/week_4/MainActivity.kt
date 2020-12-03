package agenda.training.week_4

import agenda.training.week_4.CourseDataBase.studentsPreRegistered
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.week_4.Student
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.view.*

const val DATA_KEY = "Data"
const val RECYCLER_ADD = 1
const val RECYCLER_EDIT = 2

class MainActivity : AppCompatActivity() {

    private val adapterRecyclerView = StudentAdapter(studentsPreRegistered)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapterActivation()


        student_recycler_view.adapter = adapterRecyclerView

        /** Intent & Results **/

        floating_button.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, RECYCLER_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RECYCLER_RESULT){
            val student = data?.getParcelableExtra<Student>(REGISTER_RESULT_STUDENT)
            if (requestCode == RECYCLER_ADD){
                if (adapterRecyclerView.students.isNotEmpty()){
                    student_recycler_view.visibility = View.VISIBLE
                    tv_empty_warning.visibility = View.GONE
                }
                adapterRecyclerView.addItem(student)
                student_recycler_view.layoutManager!!.scrollToPosition(adapterRecyclerView.itemCount - 1)
            }
            if (requestCode == RECYCLER_EDIT){
                adapterRecyclerView.changeItem(student)
            }
        }
    }

    private fun emptyValidation(){
        student_recycler_view.visibility = View.GONE
        tv_empty_warning.visibility = View.VISIBLE
    }

    private fun adapterActivation(){
        adapterRecyclerView.onClickEdit = { student ->
            val intent = Intent(this, RegisterActivity::class.java).apply {
                putExtra(DATA_KEY, student)
            }
            startActivityForResult(intent, RECYCLER_EDIT)
        }
        adapterRecyclerView.emptyView = {int ->
            emptyValidation()
        }

        adapterRecyclerView.alertDialog = {student ->
            alertDialog(student)
        }
        adapterRecyclerView.onClickItem = {student ->
            bottomSheetCard(student)
        }
    }

    /** Alert & Cards **/

    private fun alertDialog(student: Student){
        val alertBuild = AlertDialog.Builder(this)
        alertBuild.setTitle("Deseja realmente excluir este aluno?")
        alertBuild.setNegativeButton("Não"){dialog, which ->
            dialog.cancel()
        }
        alertBuild.setPositiveButton("Sim") {dialog, which ->
            adapterRecyclerView.removeItem(student)
            if (adapterRecyclerView.students.isEmpty()){
                student_recycler_view.visibility = View.GONE
                tv_empty_warning.visibility = View.VISIBLE
            }
        }
        val dialog: AlertDialog = alertBuild.create()
        dialog.show()
    }

    private fun bottomSheetCard(student: Student){
        val sheetView = layoutInflater.inflate(R.layout.layout_bottom_sheet, null, true)

        val sheetDialogName: TextView = sheetView.tvSheetName
        val sheetDialogAddress: TextView = sheetView.tvSheetAddressDisplay
        val sheetDialogPhone: TextView = sheetView.tvSheetPhoneDisplay
        val sheetDialogEmail: TextView = sheetView.tvSheetEmailDisplay
        val sheetDialogCourse: TextView = sheetView.tvSheetCourseDisplay
        val sheetDialogShift: TextView = sheetView.tvSheetShiftDisplay
        val dialogSheet = BottomSheetDialog(this)

        sheetDialogName.text = student.name
        sheetDialogAddress.text = student.address
        sheetDialogPhone.text = student.phoneNumber
        sheetDialogEmail.text = student.email
        sheetDialogCourse.text = student.course
        sheetDialogShift.text = student.shift

        dialogSheet.setContentView(sheetView)
        dialogSheet.show()
    }
}