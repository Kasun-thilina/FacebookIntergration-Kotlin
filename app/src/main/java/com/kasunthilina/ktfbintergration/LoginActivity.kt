package com.kasunthilina.ktfbintergration

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.bumptech.glide.Glide
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONObject
import java.security.MessageDigest
import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var loginButton: LoginButton
    lateinit var callbackManager: CallbackManager
    lateinit var txtUsername:TextView
    lateinit var imgUserProfile:CircleImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        /*var packageInfo=packageManager.getPackageInfo("com.kasunthilina.ktfbintergration",
            PackageManager.GET_SIGNATURES)
        for (Signature in packageInfo.signatures)
        {
            var md=MessageDigest.getInstance("SHA")
            md.update(Signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))

        }*/
        loginButton = findViewById(R.id.login_button)
        imgUserProfile = findViewById(R.id.imgUserProfile)
        txtUsername = findViewById(R.id.tvUserName)
        checkLoginStatus()
        callbackManager = CallbackManager.Factory.create()
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"))
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(error: FacebookException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: LoginResult?) {
                val mainActivityIntent = Intent(baseContext, MainActivity::class.java)
                val handler = Handler()
                var activityRunnable = Runnable {
                    startActivity(mainActivityIntent)
                }
                handler.postDelayed(activityRunnable, 1500)


            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    var accessTokenTracker =object :AccessTokenTracker(){
        override fun onCurrentAccessTokenChanged(
            oldAccessToken: AccessToken?,
            currentAccessToken: AccessToken?
        ) {
            if (currentAccessToken==null)
            {
                txtUsername.setText("")
                imgUserProfile.setImageResource(0)
                Toast.makeText(applicationContext, "User Logged Out", Toast.LENGTH_LONG).show()
            }
            else
            {
                loadProfile(currentAccessToken)
            }
        }
    }

    private fun loadProfile(currentAccessToken: AccessToken) {
        val db= Room.databaseBuilder(applicationContext,Database::class.java,
            "user_database").build()
        val data=DataEntity()
        val graphRequest= GraphRequest.newMeRequest(currentAccessToken,object :GraphRequest.GraphJSONObjectCallback{
            override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {
                var fName=`object`!!.getString("first_name")
                var lName=`object`.getString("last_name")
                var email=`object`.getString("email")
                var id=`object`.getString("id")

                val imageURL = "http://graph.facebook.com/$id/picture?type=normal"
                val username = "$fName $lName"

                //Saving the acquired data to the Room DB
                data.colID=1
                data.userName=username
                Log.i("LoginActivity", "Name: $username")
                data.imageUrl=imageURL
                Thread {
                    Log.i("LoginActivity", "Thread")
                    db.dbDao().saveData(data)

                    db.dbDao().getData().forEach {
                        Log.i("#RoomDB", "Name: ${it.userName}")
                        Log.i("#RoomDB", "URL: ${it.imageUrl}")
                    }
                }.start()

                Glide.with(this@LoginActivity).load(imageURL).into(imgUserProfile)
            }

        })

        val parameterBundle=Bundle()
        parameterBundle.putString("fields","first_name,last_name,email,id")
        graphRequest.setParameters(parameterBundle)
        graphRequest.executeAsync()
    }


    private fun checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken()!=null){
            loadProfile(AccessToken.getCurrentAccessToken())
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }
    }

}