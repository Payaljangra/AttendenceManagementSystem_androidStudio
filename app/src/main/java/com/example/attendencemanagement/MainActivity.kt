package com.example.attendencemanagement

import android.content.Intent
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var rv:RecyclerView
    private lateinit var db:Database
    private lateinit var btnMarkAtt:Button
    private lateinit var vshowrec:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv=findViewById(R.id.rv)
        vshowrec=findViewById(R.id.vshowrec)
        rv.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        btnMarkAtt=findViewById(R.id.btnMarkAtt)
        db= Database(this,getString(R.string.DB_NAME),null,1)
        /**/
        val studentsCur= db.getStudents()
        val students= mutableListOf<Student>()
        while(studentsCur.moveToNext()){
            students.add(Student(studentsCur.getString(0),studentsCur.getString(1)))
        }

        val rvadapter=StudentRecyclerViewAdapter(this, students)
        /**/
        rv.adapter=rvadapter

        btnMarkAtt.setOnClickListener{
            val studentAttStatuses=rv.children.map {
                val vreg=it.findViewById<TextView>(R.id.vregno)
                val vstatus=it.findViewById<Spinner>(R.id.vspinner)
                return@map  (vreg.text.toString() to if(vstatus.selectedItem=="present") 1 else 0)
            }
            try{
                db.markAttendence(studentAttStatuses)
                Toast.makeText(this,"Attendence marked...",Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                Toast.makeText(this,"Attendence already marked...",Toast.LENGTH_SHORT).show()
            }
        }
        vshowrec.setOnClickListener{
            val i=Intent(this,AttendenceRecordActivity::class.java)
            startActivity(i)
        }
    }
}