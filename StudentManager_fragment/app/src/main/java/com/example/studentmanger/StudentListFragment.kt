package com.example.studentmanger

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        val listView: ListView = view.findViewById(R.id.list_view_students)

        studentAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            students
        )
        listView.adapter = studentAdapter

        // Đăng ký context menu
        registerForContextMenu(listView)

        // Xử lý sự kiện click để chỉnh sửa sinh viên
        listView.setOnItemClickListener { _, _, position, _ ->
            val action = StudentListFragmentDirections
                .actionStudentListFragmentToEditStudentFragment(
                    students[position],
                    position
                )
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lắng nghe sinh viên mới
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<StudentModel>("new_student")
            ?.observe(viewLifecycleOwner) { newStudent ->
                students.add(newStudent)
                studentAdapter.notifyDataSetChanged()
            }

        // Lắng nghe sinh viên đã chỉnh sửa
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Pair<Int, StudentModel>>("updated_student")
            ?.observe(viewLifecycleOwner) { (position, updatedStudent) ->
                students[position] = updatedStudent
                studentAdapter.notifyDataSetChanged()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_new -> {
                // Điều hướng đến AddStudentFragment
                findNavController().navigate(R.id.action_studentListFragment_to_addStudentFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.student_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        return when (item.itemId) {
            R.id.menu_edit -> {
                val action = StudentListFragmentDirections
                    .actionStudentListFragmentToEditStudentFragment(
                        students[position],
                        position
                    )
                findNavController().navigate(action)
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
}