package com.example.dslop
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var sinhVienAdapter: SinhVienAdapter
    private lateinit var sinhVienList: MutableList<SinhVien>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo danh sách sinh viên
        sinhVienList = mutableListOf(
            SinhVien("Bui Minh Khoa", "20190001"),
            SinhVien("Bui Thi Lan", "20190002"),
            SinhVien("Chu Hoang Son", "20200001"),
            SinhVien("Dang Thi Phuong", "20190003"),
            SinhVien("Do Minh Tam", "20220001"),
            SinhVien("Do Thi Mai", "20210001"),
            SinhVien("Hoang Anh Tuan", "20230001"),
            SinhVien("Hoang Van Thao", "20220002"),
            SinhVien("Huynh Thi Hanh", "20190004"),
            SinhVien("Le Thi Hoa", "20240001"),
            SinhVien("Le Van Thanh", "20240002"),
            SinhVien("Nguyen Anh Tuan", "20230002"),
            SinhVien("Nguyen Hoang Hai", "20200002"),
            SinhVien("Nguyen Minh Chinh", "20190005"),
            SinhVien("Nguyen Thi Bich Ngoc", "20220003"),
            SinhVien("Pham Thanh Long", "20210002"),
            SinhVien("Pham Van Hieu", "20190006"),
            SinhVien("Trinh Hoang Vu", "20220004"),
            SinhVien("Trinh Minh Hai", "20230003"),
            SinhVien("Tran Hoang Minh", "20230004"),
            SinhVien("Tran Thi Kim Anh", "20190007"),
            SinhVien("Tran Van Khoa", "20210003"),
            SinhVien("Vo Hoang Dat", "20190008"),
            SinhVien("Vu Thi Ngoc", "20200003"),
            SinhVien("Vu Van Thanh", "20210004"),
            SinhVien("Ngo Thi Mai", "20200004"),
            SinhVien("Ly Hoang Anh", "20240003"),
            SinhVien("Duong Minh Duy", "20220005"),
            SinhVien("Dang Minh Tuan", "20240004"),
            SinhVien("Nguyen Minh Tam", "20200005"),
            SinhVien("Pham Hoang Tuan", "20230005"),
            SinhVien("Nguyen Thi Hai", "20240005"),
            SinhVien("Le Thi Cam", "20210005"),
            SinhVien("Bui Van Tam", "20220006"),
            SinhVien("Nguyen Thi Hong", "20190009"),
            SinhVien("Dang Minh Hoa", "20240006"),
            SinhVien("Hoang Thi Lan", "20210006"),
            SinhVien("Hoang Minh Tuan", "20240007"),
            SinhVien("Le Minh Quang", "20230006"),
            SinhVien("Vu Minh Tuan", "20190010"),
            SinhVien("Pham Minh Tuan", "20200006"),
            SinhVien("Trinh Van An", "20240008"),
            SinhVien("Tran Hoang Son", "20240009"),
            SinhVien("Bui Minh Thanh", "20200007"),
            SinhVien("Nguyen Hoang Anh", "20210007"),
            SinhVien("Vo Minh Hoa", "20220007"),
            SinhVien("Nguyen Thi Quyen", "20200008"),
            SinhVien("Vu Thi Thanh", "20220008"),
            SinhVien("Le Van Hai", "20230007"),
            SinhVien("Trinh Thi Nhung", "20240010"),
            SinhVien("Nguyen Thanh Nam", "20240011"),
            SinhVien("Bui Van Khoa", "20200009"),
            SinhVien("Dang Hoang Nam", "20230008"),
            SinhVien("Nguyen Hoang Vu", "20190011"),
            SinhVien("Hoang Minh Khai", "20210008"),
            SinhVien("Tran Thi Cam", "20220009"),
            SinhVien("Nguyen Van Khoa", "20240012"),
            SinhVien("Pham Thanh Hoa", "20200010"),
            SinhVien("Nguyen Thi Kim", "20220010"),
            SinhVien("Le Thi Hien", "20240013"),
            SinhVien("Vo Van Cuong", "20200011"),
            SinhVien("Vu Minh Hoa", "20230009"),
            SinhVien("Bui Hoang Tu", "20210009"),
            SinhVien("Nguyen Anh Duong", "20200012"),
            SinhVien("Trinh Minh Thang", "20210010"),
            SinhVien("Hoang Van Duy", "20240014"),
            SinhVien("Tran Minh Chinh", "20220011"),
            SinhVien("Pham Minh Khoa", "20240015"),
            SinhVien("Le Hoang Tu", "20190012"),
            SinhVien("Vu Van An", "20230010"),
            SinhVien("Vu Minh Cuong", "20220012"),
            SinhVien("Nguyen Thi Hoai", "20190013"),
            SinhVien("Trinh Van Long", "20240016"),
            SinhVien("Do Minh Hai", "20210011"),
            SinhVien("Vo Hoang Hoa", "20200013"),
            SinhVien("Ly Thi Lan", "20240017"),
            SinhVien("Nguyen Thi Huong", "20210012"),
            SinhVien("Bui Minh Tu", "20220013"),
            SinhVien("Hoang Van Tai", "20240018"),
            SinhVien("Nguyen Thanh Liem", "20210013"),
            SinhVien("Dang Van Hoang", "20220014"),
            SinhVien("Tran Thi Hoa", "20240019"),
            SinhVien("Pham Hoang Thao", "20200014"),
            SinhVien("Nguyen Hoang Cuong", "20240020"),
            SinhVien("Le Van Quang", "20220015"),
            SinhVien("Tran Minh Phuc", "20240021"),
            SinhVien("Vu Van Tan", "20200015"),
            SinhVien("Dang Minh Khoa", "20230011"),
            SinhVien("Vo Van Tai", "20220016"),
            SinhVien("Le Minh Thang", "20210014"),
            SinhVien("Tran Hoang Ha", "20240022"),
            SinhVien("Vu Minh Hieu", "20230012"),
            SinhVien("Bui Thi Hoang", "20200016"),
            SinhVien("Nguyen Hoang Duy", "20240023"),
            SinhVien("Vu Van Hanh", "20200017"),
            SinhVien("Tran Thi Tien", "20210015"),
            SinhVien("Dang Hoang Minh", "20240024"),
            SinhVien("Nguyen Thi Hoa", "20230013"),
            SinhVien("Trinh Minh Duong", "20240025"),
            SinhVien("Vo Thi Ha", "20210016"),
            SinhVien("Nguyen Thi Mai", "20200018"),
            SinhVien("Hoang Van An", "20240026"),
            SinhVien("Bui Thi Kim", "20230014"),
            SinhVien("Nguyen Minh Khoa", "20240027"),
            SinhVien("Tran Van An", "20200019"),
            SinhVien("Vu Minh Hoa", "20240028"),
            SinhVien("Ly Thi Mai", "20220017"),
            SinhVien("Do Minh Quan", "20200020"),
            SinhVien("Nguyen Hoang Hanh", "20240029"),
            SinhVien("Pham Van Hoa", "20210017"),
            SinhVien("Le Van Thanh", "20240030"),
            SinhVien("Nguyen Hoang Hiep", "20230015")

            )

        // Thiết lập RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        sinhVienAdapter = SinhVienAdapter(sinhVienList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = sinhVienAdapter

        // Thiết lập ô tìm kiếm
        val etSearch: EditText = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filteredList = if (text.length > 2) {
            sinhVienList.filter {
                it.hoTen.contains(text, ignoreCase = true) || it.mssv.contains(text)
            }
        } else {
            sinhVienList
        }
        sinhVienAdapter.filterList(filteredList)
    }
}
