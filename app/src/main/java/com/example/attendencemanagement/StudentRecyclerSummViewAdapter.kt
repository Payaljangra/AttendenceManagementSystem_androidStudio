package com.example.attendencemanagement

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.Date

class StudentRecyclerViewAdapter(private val context: Context,private val students:MutableList<Student>):RecyclerView.Adapter<StudentRecyclerViewAdapter.StudentViewHolder>() {
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.vstudentName.text=students[position].name
        holder.vstudentReg.text=students[position].regno
        val attDate= Date()

        holder.vattDate.text=DateFormat.getDateInstance().format(attDate)

        val statusAdap = ArrayAdapter<String>(context, R.layout.spinner_item, arrayOf("present","absent"))
        holder.vAttStatusSpin.adapter=statusAdap
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val layoutInflator=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView=layoutInflator.inflate(R.layout.student_listitem,parent,false)
        return StudentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return students.size
    }
    inner class StudentViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val vstudentName=itemView.findViewById<TextView>(R.id.vname)
        val vstudentReg=itemView.findViewById<TextView>(R.id.vregno)
        val vattDate=itemView.findViewById<TextView>(R.id.vdate)
        val vAttStatusSpin=itemView.findViewById<Spinner>(R.id.vspinner)
    }
}