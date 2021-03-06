package com.example.recyclingwiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        imageButton1.setOnClickListener{
            val intent = Intent(this, category::class.java)
            startActivity(intent)
        }
        imageButton2.setOnClickListener{
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
        imageButton3.setOnClickListener{
            val intent = Intent(this, Googlemap::class.java)
            startActivity(intent)

        }
        imageButton4.setOnClickListener{
            val intent = Intent(this, Myinfo::class.java)
            startActivity(intent)

        }
    }

}