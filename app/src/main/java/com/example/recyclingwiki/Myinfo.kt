package com.example.recyclingwiki

import android.bluetooth.BluetoothAdapter.ERROR
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_myinfo.*
private lateinit var auth: FirebaseAuth
class Myinfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myinfo)

        auth = Firebase.auth

        notification_btn.setOnClickListener {
            val intent = Intent(this, Notification::class.java)
            startActivity(intent)
        }

        withdrawl_button.setOnClickListener {
            // 다이얼로그를 생성하기 위해 Builder 클래스 생성자를 이용해 줍니다.
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Withdrawl account")
                .setMessage("Do you really want to delete your account?")
                .setPositiveButton("YES",
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.e("Jaemin","SUCCESS")
                        auth.getCurrentUser()?.delete();
                        logout()
                    })
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
                    // 다이얼로그를 띄워주기
            builder.show()

        }

        logout_button.setOnClickListener{
            logout()
        }

        qna_button.setOnClickListener{
            val intent = Intent(this, QNA::class.java)
            startActivity(intent)
        }

    }

    private fun logout() {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}