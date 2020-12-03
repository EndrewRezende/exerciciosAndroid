package agenda.training.week_4

import agenda.training.week_4.R.id.*
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.week_4.Student
import kotlinx.android.synthetic.main.layout_card.view.*

class StudentAdapter (val students: MutableList<Student>): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    private var toastGlobal: Toast? = null
    var onClickItem: ((student: Student) -> Unit)? = null
    var onClickEdit: ((student: Student) -> Unit)? = null
    var alertDialog: ((student: Student) -> Unit)? = null
    var emptyView: ((int: Int) -> Unit)? =null
    var lastPositionEdited: Int = -1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        PopupMenu.OnMenuItemClickListener {
        val studentName: TextView = itemView.tv_student_name
        val studentCourse: TextView = itemView.tv_course_name
        var popupView: TextView = itemView.tv_option_menu
        init {
            toastGlobal?.cancel()
            itemView.setOnClickListener{
                val student = students[adapterPosition]
                onClickItem?.invoke(student)
            }
            popupView.setOnClickListener {
                showPopupMenu(itemView)
            }
        }
        private fun showPopupMenu(itemView: View) {
            val popupMenu = PopupMenu(itemView.context, popupView)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()
        }
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            val student = students[adapterPosition]
            when(item!!.itemId){
                action_edit -> onClickEdit?.invoke(student)
                else -> {
                    alertDialog?.invoke(student)
                }
            }
            return false
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = students[position]
        holder.studentName.text = currentItem.name
        holder.studentCourse.text = currentItem.course
    }
    override fun getItemCount() = students.size

    fun addItem(student: Student?){
        if (student != null){
            students.add(student)
            notifyItemInserted(students.lastIndex)
        }
    }
    fun changeItem(student: Student?){
        if (student != null){
           val position = students.indexOfFirst { it.id == student.id }
            if (position != -1){
                students[position] = student
                notifyItemChanged(position)
            }
        }
    }

    fun removeItem(student: Student){
        CourseDataBase.studentsPreRegistered.remove(student)
        notifyDataSetChanged()
    }
}
