package com.example.attendencemanagement


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import java.util.Date

class Database(context: Context, dbName: String, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, dbName, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val studetnTableQuery="create table if not exists student(" +
                "name varchar(30) not null," +
                "regno varchar(10) primary key);"

        val attendenceTableQuery= "create table if not exists attendence(" +
                "id varchar(10) not null," +
                "pdate date default(CURRENT_DATE)," +
                "ispresent boolean default 0,"+
                "unique(id,pdate),"+
                "FOREIGN KEY (id) REFERENCES student(regno));"
        val studentsRawQuery= """insert into student(name, regno) values('Quincy', 14970111),
                                ('payal', 12016203),
                                ('pranjal bhalla', 12016204),
                                ('V. sai', 12016205),
                                ('Harriette', 14186325),
                                ('Arman', 14355543),
                                ('Hewie', 13381753),
                                ('Adelind', 13061992),
                                ('Kliment', 13960863),
                                ('Lane', 13458994),
                                ('Carina', 12049607),
                                ('Terrijo', 14906665),
                                ('Rickert', 14282596),
                                ('Zorine', 14264324),
                                ('Meier', 12833192),
                                ('Julietta', 12800728),
                                ('Wanids', 14830379),
                                ('Myrna', 13191372),
                                ('Terrill', 14854946),
                                ('Mortie', 12277651),
                                ('Currey', 14841260),
                                ('Conney', 14169145),
                                ('Jayme', 13875193),
                                ('Sunny', 12424906),
                                ('Marten', 13713976),
                                ('Stephan', 13978980),
                                ('Kyle', 13854479),
                                ('Booth', 12134507),
                                ('Lauren', 13580387),
                                ('Jamey', 13298836),
                                ('Gabrila', 14222595),
                                ('Casey', 12023994),
                                ('Sigfried', 14278616),
                                ('Marje', 13980992),
                                ('Penny', 13374547),
                                ('Arlin', 14487490),
                                ('Celinda', 14828267),
                                ('Inglebert', 13528022),
                                ('Marketa', 13027064),
                                ('Silvano', 12962840),
                                ('Winnifred', 14563673),
                                ('Sarette', 14384139),
                                ('Vale', 14325476),
                                ('Rafa', 14236971),
                                ('Cynthia', 13623987),
                                ('Frederic', 12958823),
                                ('Godfry', 13196091),
                                ('Iseabal', 13538266),
                                ('Lesya', 12381428),
                                ('Magnum', 14387155),
                                ('Courtenay', 13883481),
                                ('Jard', 12127080),
                                ('Slade', 12696307),
                                ('Daphne', 13581512),
                                ('Simona', 14046801),
                                ('Eryn', 13522005),
                                ('Devan', 12121779),
                                ('Kathy', 12796046),
                                ('Sande', 13742130),
                                ('Tanhya', 14076239),
                                ('Marcus', 14098582),
                                ('Rosalynd', 13875412),
                                ('Fair', 13185333),
                                ('Sergeant', 13593438),
                                ('Blondell', 12521813),
                                ('Ellery', 12491236),
                                ('Shela', 14198505),
                                ('Mervin', 14329744),
                                ('Venus', 14994047),
                                ('Perla', 13489969),
                                ('Sly', 13143097),
                                ('Chet', 14235755),
                                ('Bellina', 14280028),
                                ('Vivi', 12244951),
                                ('Anselm', 12937447),
                                ('Giuditta', 13587333),
                                ('Georgette', 12025961),
                                ('Aime', 14259571),
                                ('Gwendolin', 12832726),
                                ('Joella', 12679234),
                                ('Noak', 14106737),
                                ('Ferd', 14747076),
                                ('Sybilla', 13373455),
                                ('Cherie', 12866602),
                                ('Angie', 13372478),
                                ('Koenraad', 14307388),
                                ('Alistair', 12585253),
                                ('Ab', 13603425),
                                ('Merrie', 13232338),
                                ('Marje', 13400499),
                                ('Craggie', 14435616),
                                ('Ennis', 14125876),
                                ('Livia', 13756182),
                                ('Huntley', 13245980),
                                ('Milicent', 14765962),
                                ('Consalve', 13752996),
                                ('Marieann', 14097923),
                                ('Kikelia', 14584924),
                                ('Carr', 13180798),
                                ('Griselda', 14154354),
                                ('Emmett', 13069929),
                                ('Mendie', 13593248),
                                ('Rickey', 14451265)"""
        db?.execSQL(studetnTableQuery)
        db?.execSQL(attendenceTableQuery)
        db?.execSQL(studentsRawQuery)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun markAttendence(seq:Sequence<Pair<String,Int>>){
        val db:SQLiteDatabase=writableDatabase
        try{
            seq.forEach {
                val cv=ContentValues()
                cv.put("id",it.first)
                cv.put("ispresent",it.second)
                val res=db.insert("attendence",null,cv)!=-1L
                if(!res)
                    throw Exception("Failed to mark attendence! Try again.")
            }
        }finally {
            db.close()
        }
    }

    fun getStudents(): Cursor {
        val db: SQLiteDatabase = readableDatabase

        return db.rawQuery("select name,regno from student", null)
    }

    fun getAttSummary():Cursor{
        val db: SQLiteDatabase = readableDatabase

        return db.rawQuery("""
            select name, regno, ispresent, count(*) from student, attendence
            where regno = id
            group by regno, ispresent
        """, null)
    }
}