package com.kasunthilina.ktfbintergration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    val URL:String="https://dl.dropboxusercontent.com/s/6nt7fkdt7ck0lue/hotels.json"
    lateinit var listData:List<Data>
    lateinit var recyclerView: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db= Room.databaseBuilder(applicationContext,Database::class.java,
            "user_database").build()

        Thread{
            db.dbDao().getData().forEach{
                Log.d("#RoomDB","Name: ${it.userName}")
                Log.d("#RoomDB","URL: ${it.imageUrl}")
            }
        }.start()

        var etUsername:TextView=findViewById(R.id.tvUserNameMain)
        var recyclerView:RecyclerView=findViewById(R.id.recyclerView)
        var btnLogout:Button=findViewById(R.id.btnLogOut)
        listData=ArrayList()

    }
}
