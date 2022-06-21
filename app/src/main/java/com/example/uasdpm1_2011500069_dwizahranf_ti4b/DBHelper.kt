package com.example.uasdpm1_2011500069_dwizahranf_ti4b

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context): SQLiteOpenHelper(context, "campuss",null,1){
    var kdMatkul=""
    var nmMatkul=""
    var sks = 0
    var sifat=""

    private val tabel = "lecturer"
    private var sql= ""

    override fun onCreate(db: SQLiteDatabase?) {
        sql = """create table $tabel  (
            |NIDN char (10) primary key,
            |nama_dosen varchar (50) not null,
            |Jabatan integer not null,
            |golongan_pangkat varchar(30) not null,
            |Pendidikan char(2) not null,
            |Keahlian varchar(30) not null,
            |Program_Studi varchar
            )
        """.trimMargin()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        sql = "drop table if exist $tabel"
        db?.execSQL(sql)
    }
    fun simpan(): Boolean{
        val db = writableDatabase
        val cv = ContentValues()
        with(cv) {
            put("kdMatkul", kdMatkul)
            put("nm_matkul", nmMatkul)
            put("sks", sks)
            put("sifat", sifat)
        }
        val cmd = db.insert(tabel, null, cv)
        db.close()
        return cmd != -1L

    }

    fun ubah (kode: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        with(cv){
            put("nm_matkul", nmMatkul)
            put("sks",sks)
            put("sifat",sifat)
        }
        val cmd = db.update(tabel,cv,"kdMatkul=?", arrayOf(kode))
        db.close()
        return cmd!=-1
    }
    fun hapus (kode:String): Boolean{
        val db = writableDatabase
        val cmd = db.delete(tabel,"kdMatkul=?", arrayOf(kode))
        return  cmd !=-1
    }

    fun tampil(): Cursor {
        val db = writableDatabase
        val reader = db.rawQuery("select * from $tabel", null)
        return reader
    }
}
