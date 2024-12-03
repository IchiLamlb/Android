
package com.example.a1
data class Email(
    val sender: String,
    val subject: String,
    val time: String,
    var isStarred: Boolean = false,
    var avatarColor: Int = 0 // Thêm avatarColor để lưu màu của avatar
)
