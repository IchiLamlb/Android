package com.example.dslop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SinhVienAdapter(private var sinhVienList: List<SinhVien>) : RecyclerView.Adapter<SinhVienAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvHoTen: TextView = view.findViewById(R.id.tvHoTen)
        val tvMSSV: TextView = view.findViewById(R.id.tvMSSV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sinh_vien, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sinhVien = sinhVienList[position]
        holder.tvHoTen.text = sinhVien.hoTen
        holder.tvMSSV.text = sinhVien.mssv
    }

    override fun getItemCount(): Int = sinhVienList.size

    fun filterList(filteredList: List<SinhVien>) {
        sinhVienList = filteredList
        notifyDataSetChanged()
    }
}
