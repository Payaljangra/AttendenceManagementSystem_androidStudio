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

class StudentRecyclerSummViewAdapter(private val context: Context, private val studentsSumm:List<StudentSummary>):RecyclerView.Adapter<StudentRecyclerSummViewAdapter.StudentSummViewHolder>() {
    override fun onBindViewHolder(holder: StudentSummViewHolder, position: Int) {
        holder.vstudentName.text=studentsSumm[position].name
        holder.vstudentReg.text=studentsSumm[position].regno
        holder.vpresents.text=studentsSumm[position].pCount.toString()
        holder.vabsents.text=studentsSumm[position].aCount.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentSummViewHolder {
        val layoutInflator=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView=layoutInflator.inflate(R.layout.student_attendence_summary,parent,false)
        return StudentSummViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return studentsSumm.size
    }
    inner class StudentSummViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val vstudentName=itemView.findViewById<TextView>(R.id.vname)
        val vstudentReg=itemView.findViewById<TextView>(R.id.vregno)
        val vpresents=itemView.findViewById<TextView>(R.id.vpresents)
        val vabsents=itemView.findViewById<TextView>(R.id.vabsents)
    }
}