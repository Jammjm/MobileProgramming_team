package com.example.recyclingwiki

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_qna.*

class QNA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qna)

        call_btn.setOnClickListener{
            val mobilenum = "02-970-0000"
            // dialPhoneNumber creates action dial intent
            dialPhoneNumber(mobilenum)
        }

        email_btn.setOnClickListener{
            val email = "recycle@rec.or.kr"
            // dialPhoneNumber creates action dial intent
            composeEmail(email)
        }


    }

    private fun dialPhoneNumber(mobilenum: String) {

        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$mobilenum")
        }
        startActivity(intent)

    }
    // For intent email
    private fun composeEmail(address: String) {
        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        email.putExtra(Intent.EXTRA_SUBJECT, "subject")
        email.putExtra(Intent.EXTRA_TEXT, "message")
        email.type = "message/rfc822"
        startActivity(Intent.createChooser(email, "Choose an Email client :"))
    }


}