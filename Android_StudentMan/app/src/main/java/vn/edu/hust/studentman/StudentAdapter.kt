package vn.edu.hust.studentman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(
  private val students: MutableList<StudentModel>,
  private val activity: MainActivity
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.layout_student_item, parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]

    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId

    holder.imageEdit.setOnClickListener {
      showEditStudentDialog(position)
    }

    holder.imageRemove.setOnClickListener {
      showDeleteStudentDialog(position)
    }
  }

  private fun showEditStudentDialog(position: Int) {
    val student = students[position]
    val dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_add_edit_student, null)
    val nameInput = dialogView.findViewById<EditText>(R.id.edit_student_name).apply {
      setText(student.studentName)
    }
    val idInput = dialogView.findViewById<EditText>(R.id.edit_student_id).apply {
      setText(student.studentId)
    }

    AlertDialog.Builder(activity)
      .setTitle("Sửa thông tin sinh viên")
      .setView(dialogView)
      .setPositiveButton("Lưu") { _, _ ->
        // Cập nhật trực tiếp vào danh sách sinh viên
        students[position] = student.copy(
          studentName = nameInput.text.toString(),
          studentId = idInput.text.toString()
        )
        notifyItemChanged(position)
      }
      .setNegativeButton("Hủy", null)
      .show()
  }


  private fun showDeleteStudentDialog(position: Int) {
    val removedStudent = students[position]
    AlertDialog.Builder(activity)
      .setTitle("Xóa sinh viên")
      .setMessage("Bạn có chắc chắn muốn xóa sinh viên này không?")
      .setPositiveButton("Xóa") { _, _ ->
        students.removeAt(position)
        notifyItemRemoved(position)

        Snackbar.make(activity.findViewById(R.id.main), "Đã xóa sinh viên", Snackbar.LENGTH_LONG)
          .setAction("Hoàn tác") {
            students.add(position, removedStudent)
            notifyItemInserted(position)
          }
          .setDuration(30000)
          .show()
      }
      .setNegativeButton("Hủy", null)
      .show()
  }
}
