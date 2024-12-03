package com.example.a1

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    // Hàm tạo màu ngẫu nhiên
    private fun getRandomColor(): Int {
        val rnd = Random()
        return android.graphics.Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    class EmailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: TextView = view.findViewById(R.id.avatar)
        val sender: TextView = view.findViewById(R.id.sender)
        val subject: TextView = view.findViewById(R.id.subject)
        val time: TextView = view.findViewById(R.id.time)
        val starIcon: ImageView = view.findViewById(R.id.starIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.email_item, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]

        // Cập nhật thông tin cho từng email
        holder.avatar.text = email.sender.first().toString()  // Lấy chữ cái đầu tiên của người gửi
        holder.sender.text = email.sender
        holder.subject.text = email.subject
        holder.time.text = email.time

        // Thiết lập màu nền cho avatar chỉ một lần
        if (email.avatarColor == 0) {
            email.avatarColor = getRandomColor() // Tạo màu ngẫu nhiên nếu chưa có màu
        }

        // Tạo Drawable hình tròn
        val shapeDrawable = GradientDrawable()
        shapeDrawable.shape = GradientDrawable.OVAL // Thiết lập hình tròn
        shapeDrawable.setColor(email.avatarColor) // Thiết lập màu nền từ thuộc tính avatarColor
        shapeDrawable.setSize(40, 40) // Kích thước của hình tròn
        holder.avatar.background = shapeDrawable // Đặt background cho avatar

        // Thiết lập icon sao
        if (email.isStarred) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_star_border)
        }

        // Sự kiện click cho icon sao
        holder.starIcon.setOnClickListener {
            email.isStarred = !email.isStarred
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = emailList.size
}
