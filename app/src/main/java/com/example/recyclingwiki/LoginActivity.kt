package com.example.recyclingwiki

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private companion object {
        private val TAG = "Jaemin"
        private const val RC_GOOGLE_SIGN_IN = 2049
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(this, gso)
        google_signin_button.setOnClickListener {
            val signInIntent = client.signInIntent
            startActivityForResult(signInIntent, LoginActivity.RC_GOOGLE_SIGN_IN)
        }


        login_signup_btn.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivityForResult(intent,100)
        }

        login_signin_btn.setOnClickListener {
            signIn(login_email_edittext.text.toString(), signup_password_edittext.text.toString())
        }
    }

    fun login_sucess() {


        //var ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        var df = FirebaseDatabase.getInstance()
        Log.d("Jaemin", "$df")
        df.getReference("Users").child(auth.uid.toString()).get().addOnSuccessListener { result ->
            Log.d("Jaemin", "$result")
        }


        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun createAccount(email: String, password: String, name:String, city: String, age: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = Firebase.auth.currentUser
                        var df = FirebaseFirestore.getInstance()
                        val hashMap = hashMapOf<String, Any>(
                            "name" to name,
                            "city" to city,
                            "age" to age,
                            "isuser" to 1
                        )

                        df.collection("users")
                            .add(hashMap)
                            .addOnSuccessListener {
                                Log.d(TAG, "Added document with ID ${it.id}")
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error adding document $exception")
                            }
                        Toast.makeText(
                            this, "Created account successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            this, "Create account failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun signIn(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        login_sucess()
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == LoginActivity.RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(LoginActivity.TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(LoginActivity.TAG, "Google sign in failed", e)
            }
        }
        if(resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    val name = data?.getStringExtra("name").toString()
                    val city = data?.getStringExtra("city").toString()
                    val age = data?.getStringExtra("age").toString()
                    val email = data?.getStringExtra("email").toString()
                    val password = data?.getStringExtra("password").toString()

                    createAccount(email, password, name, city, age)

                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(LoginActivity.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(LoginActivity.TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {

    }
}