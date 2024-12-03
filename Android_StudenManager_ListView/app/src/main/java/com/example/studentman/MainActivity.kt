package com.example.studentman

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: ArrayAdapter<StudentModel>
    private val students = mutableListOf(
            StudentModel("Nguyễn Văn An", "001"),
            StudentModel("Trần Thị Bình", "002"),
            StudentModel("Lê Minh Cường", "003"),
            StudentModel("Phạm Thị Duyên", "004"),
            StudentModel("Đặng Ngọc Hiền", "005"),
            StudentModel("Vũ Minh Hoàng", "006"),
            StudentModel("Nguyễn Thị Lan", "007"),
            StudentModel("Lý Hải Long", "008"),
            StudentModel("Hoàng Thị Mai", "009"),
            StudentModel("Bùi Minh Quân", "010"),
            StudentModel("Ngô Hoàng Sơn", "011"),
            StudentModel("Lê Quang Tín", "012"),
            StudentModel("Trương Hữu Trí", "013"),
            StudentModel("Phan Thị Tuyết", "014"),
            StudentModel("Đỗ Minh Tuấn", "015"),
            StudentModel("Lê Ngọc Vân", "016"),
            StudentModel("Nguyễn Hoàng Anh", "017"),
            StudentModel("Phạm Thị Kim", "018"),
            StudentModel("Hồ Minh Khôi", "019"),
            StudentModel("Dương Thị Thu", "020")

    )

    private var selectedStudentPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListView()
    }

    private fun setupListView() {
        val listView: ListView = findViewById(R.id.list_view_students)
        studentAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            students
        )
        listView.adapter = studentAdapter
        registerForContextMenu(listView)

        listView.setOnItemLongClickListener { _, _, position, _ ->
            selectedStudentPosition = position
            false // Return false to allow context menu to show
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_new -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.student_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        return when (item.itemId) {
            R.id.menu_edit -> {
                val intent = Intent(this, EditStudentActivity::class.java).apply {
                    putExtra("STUDENT", students[position])
                    putExtra("POSITION", position)
                }
                startActivityForResult(intent, EDIT_STUDENT_REQUEST_CODE)
                true
            }
            R.id.menu_remove -> {
                students.removeAt(position)
                studentAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            when (requestCode) {
//                ADD_STUDENT_REQUEST_CODE -> {
//                    val newStudent = data?.getParcelableExtra<StudentModel>("NEW_STUDENT")
//                    newStudent?.let {
//                        students.add(it)
//                        studentAdapter.notifyDataSetChanged()
//                    }
//                }
//                EDIT_STUDENT_REQUEST_CODE -> {
//                    val updatedStudent = data?.getParcelableExtra<StudentModel>("UPDATED_STUDENT")
//                    val position = data?.getIntExtra("POSITION", -1)
//                    if (updatedStudent != null && position != null && position != -1) {
//                        students[position] = updatedStudent
//                        studentAdapter.notifyDataSetChanged()
//                    }
//                }
//            }
//        }
//    }
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK) {
        when (requestCode) {
            ADD_STUDENT_REQUEST_CODE -> {
                // Lấy dữ liệu từ Intent
                val studentName = data?.getStringExtra("STUDENT_NAME")
                val studentId = data?.getStringExtra("STUDENT_ID")

                // Tạo đối tượng StudentModel
                if (studentName != null && studentId != null) {
                    val newStudent = StudentModel(studentName, studentId)
                    students.add(newStudent)
                    studentAdapter.notifyDataSetChanged()
                }
            }
            EDIT_STUDENT_REQUEST_CODE -> {
                val updatedStudent = data?.getParcelableExtra<StudentModel>("UPDATED_STUDENT")
                val position = data?.getIntExtra("POSITION", -1)
                if (updatedStudent != null && position != null && position != -1) {
                    students[position] = updatedStudent
                    studentAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}

    companion object {
        const val ADD_STUDENT_REQUEST_CODE = 1
        const val EDIT_STUDENT_REQUEST_CODE = 2
    }
}