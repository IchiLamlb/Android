package com.example.a1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val emailList = listOf(
            Email("Lê Lâm", "BTVN Android", "10:34 PM"),
            Email("Edurila.com", "Learn Web Designing for only $19!", "2:34 PM"),
            Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM"),
            Email("Tuto.com", "8h de formation gratuite et les nouveau...", "11:04 AM"),
            Email("Support", "Société Ovh : suivi de vos services - hp...", "10:26 AM"),
            Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM")
        )

        val adapter = EmailAdapter(emailList)
        recyclerView.adapter = adapter
    }
}
