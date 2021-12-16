package com.example.recyclingwiki

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var data = listOf(
            "Jongno",
            "Jung",
            "Yongsan",
            "Seongdong",
            "Gwangjin",
            "Dongdaemun",
            "Jungnang",
            "Seongbuk",
            "Gangbuk",
            "Dobong",
            "Nowon",
            "Eunpyeong",
            "Seodaemun",
            "Mapo",
            "Yangcheon",
            "Gangseo",
            "Guro",
            "Geumcheon",
            "Yeongdeungpo",
            "Dongjak",
            "Gwanak",
            "Seocho",
            "Gangnam",
            "Songpa",
            "Gangdong"
        )

        var city = ""
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        login_city_spinner.adapter = adapter

        login_city_spinner.setSelection(1)

        login_city_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                city = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        login_signup_btn.setOnClickListener{
            val email = signup_email_edittext.text.toString()
            val password = signup_password_edittext.text.toString()
            val name = signup_name_edittext.text.toString()
            val age = signup_age_edittext.text.toString()

            val intent = Intent()
            intent.putExtra("name",name)
            intent.putExtra("email",email)
            intent.putExtra("password",password)
            intent.putExtra("age",age)
            intent.putExtra("city",city)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }


}

