package com.example.recyclingwiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_category.*

class category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        paper_button.setOnClickListener{
            val intent = Intent(this, paper::class.java)
            startActivity(intent)
        }
        plastic_btn.setOnClickListener{
            val intent = Intent(this, pet::class.java)
            startActivity(intent)
        }
        can_button.setOnClickListener{
            val intent = Intent(this, metalcan::class.java)
            startActivity(intent)
        }
        vinyl_btn.setOnClickListener{
            val intent = Intent(this, vinyl::class.java)
            startActivity(intent)
        }
        styrofoam_btn.setOnClickListener{
            val intent = Intent(this, styrofoam::class.java)
            startActivity(intent)
        }
    }
}