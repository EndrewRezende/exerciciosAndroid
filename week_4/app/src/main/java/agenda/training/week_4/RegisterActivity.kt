package agenda.training.week_4

import agenda.training.week_4.CourseDataBase.afternoonShift
import agenda.training.week_4.CourseDataBase.courses
import agenda.training.week_4.CourseDataBase.morningShift
import agenda.training.week_4.CourseDataBase.nightShift
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.week_4.Student

import kotlinx.android.synthetic.main.activity_register.*
import kotlin.random.Random

const val RECYCLER_RESULT = 2
const val REGISTER_RESULT_STUDENT = "resultStudent"

class RegisterActivity : AppCompatActivity() {

    var studentData: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        studentData = intent.getParcelableExtra<Student>(DATA_KEY)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        if (studentData != null) {
            etNameInput.setText(studentData?.name)
            etAddressInput.setText(studentData?.address)
            etPhoneInput.setText(studentData?.phoneNumber)
            etEmailInput.setText(studentData?.email)
            spinner.setSelection(adapter.getPosition(studentData?.course))
            radioButtonCheck()
        }

        setSupportActionBar(toolbar_register)

        toolbar_register.setNavigationOnClickListener {
            finish()
        }
    }

    private fun inputValidation() {
        if (etNameInput.text.isEmpty() || etAddressInput.text.isEmpty() || etPhoneInput.text.isEmpty() || etEmailInput.text.isEmpty()) {
            emptyInputAlert()
        } else {
            includeStudentResult()
        }
    }

    private fun emptyInputAlert() {
        val alertBuild = AlertDialog.Builder(this)
        alertBuild.setTitle("Todos os campos devem ser preenchidos")
        alertBuild.setNegativeButton("OK") { dialog, _ ->
            dialog.cancel()
        }
        val dialog: AlertDialog = alertBuild.create()
        dialog.show()
    }

    private fun includeStudentResult() {
        val coursePosition = spinner.selectedItemPosition
        val course = courses[coursePosition]
        val intent = Intent()
        val studentID = studentData?.id ?: Random.nextInt()
        studentData = Student(
            studentID, etNameInput.text.toString(), etAddressInput.text.toString(),
            etPhoneInput.text.toString(), etEmailInput.text.toString(), course, shiftSelection()
        )
        intent.putExtra(REGISTER_RESULT_STUDENT, studentData)
        setResult(RECYCLER_RESULT, intent)
        finish()
    }

    private fun shiftSelection(): String {
        return when {
            rbMorning.isChecked -> morningShift

            rbAfternoon.isChecked -> afternoonShift

            else -> nightShift
        }
    }

    private fun radioButtonCheck() {
        val studentData = intent.getParcelableExtra<Student>(DATA_KEY)
        when (studentData?.shift) {
            morningShift -> {
                rgButtonGroup.check(R.id.rbMorning)
            }
            afternoonShift -> {
                rgButtonGroup.check(R.id.rbAfternoon)
            }
            nightShift -> {
                rgButtonGroup.check(R.id.rbNight)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId
        when (itemView) {
            R.id.btn_toolbar_check -> inputValidation()
        }
        return false
    }
}
