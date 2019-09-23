package com.kasunthilina.ktfbintergration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val URL:String="https://dl.dropboxusercontent.com/s/6nt7fkdt7ck0lue/hotels.json"
    lateinit var listData:MutableList<Data>
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
                var userName:String?=it.userName
                var imageURL:String?=it.imageUrl

            }
        }.start()

        val etUsername:TextView=findViewById(R.id.tvUserNameMain)
        val recyclerView:RecyclerView=findViewById(R.id.recyclerView)
        val btnLogout:Button=findViewById(R.id.btnLogOut)
        listData=ArrayList()
        parseJSON()

        btnLogout.setOnClickListener{
            FacebookSdk.sdkInitialize(applicationContext)
            LoginManager.getInstance().logOut()
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

    private fun parseJSON() {
        val requestQueue=Volley.newRequestQueue(this@MainActivity)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            URL,
            null,
            Response.Listener { response ->
                    // Get the JSON array
                    val array = response.getJSONArray("data")
                    Log.d("MainActivity", "JSON ResponseBody :$array")
                    // Loop through the array elements
                    for (i in 0 until array.length()) {
                        // Get current json object
                        val jsonObject = array.getJSONObject(i)
                        //getting the image sub node
                        val image = jsonObject.getJSONObject("image")
                        val data = Data()
                        data.setTitle(jsonObject.getString("title"))
                        data.setDescription(jsonObject.getString("description"))
                        data.setAddress(jsonObject.getString("address"))
                        data.setPostcode(jsonObject.getString("postcode"))
                        data.setPhoneNumber(jsonObject.getString("phoneNumber"))
                        data.setLatitude(jsonObject.getString("latitude"))
                        data.setLongitude(jsonObject.getString("longitude"))

                        data.setImage(image.getString("medium"))
                        val temp = image.getString("medium")
                        Log.d("MainActivity", "###img: $temp")
                        listData.add(data)
                    }

                setupRecyclerView(listData)
            },
            Response.ErrorListener {
                // Do something when error occurred
            }
        )

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest)
    }

    private fun setupRecyclerView(listData: List<Data>) {

        val myAdapter = RecyclerViewAdapter(this,listData)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(myAdapter)
    }
}
