package com.example.attendencemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AttendenceRecordActivity : AppCompatActivity() {
    private lateinit var studentSummaryrv:RecyclerView
    private lateinit var db:Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence_record)

        studentSummaryrv=findViewById(R.id.studentSummaryrv)
        studentSummaryrv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        db=Database(this,"STUATT",null,1)

        val studentSummCursor=db.getAttSummary()
        val attMap= mutableMapOf<String,Triple<String,Int,Int>>() //triple name pcount acount
        while(studentSummCursor.moveToNext()){
            val name= studentSummCursor.getString(0)
            val regno= studentSummCursor.getString(1)
            val ispresent= studentSummCursor.getInt(2)
            val count=studentSummCursor.getInt(3)
            if(attMap[regno]!=null){
                val ex=attMap[regno]!!
                if(ispresent==0){
                    attMap[regno]=Triple(name,ex.second,ex.third+count)
                }else{
                    attMap[regno]=Triple(name,ex.second+count,ex.third)
                }
            }else{
                if(ispresent==0){
                    attMap[regno]=Triple(name,0,count)
                }else{
                    attMap[regno]=Triple(name,count,0)
                }
            }
        }
        val attSumm= attMap.map { entry -> StudentSummary(entry.value.first,entry.key,entry.value.second,entry.value.third) }
        Log.d("ATT",attSumm.size.toString())
        studentSummaryrv.adapter=StudentRecyclerSummViewAdapter(this,attSumm)

    }
}