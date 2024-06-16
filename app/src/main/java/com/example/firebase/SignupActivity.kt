package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase.model.UserModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etFullName: EditText=findViewById(R.id.etFullName)
        val etCountry: EditText=findViewById(R.id.etCountry)
        val etEmail: EditText=findViewById(R.id.etEmail)
        val etPassword: EditText=findViewById(R.id.etPassword)
        val btnSave: Button =findViewById(R.id.btnSave)

        val db =FirebaseFirestore.getInstance()
        val auth= FirebaseAuth.getInstance()


        btnSave.setOnClickListener{
            val fullName=etFullName.text.toString()
            val country=etCountry.text.toString()
            val email=etEmail.text.toString()
            val password=etPassword.text.toString()


            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        val user: FirebaseUser? = auth.currentUser
                        val uid =user?.uid
                        val userModel = UserModel(fullName,country,email,uid)

                        db.collection("users")
                            .add(userModel)
                            .addOnSuccessListener {
                                Snackbar.make(
                                    findViewById(android.R.id.content)
                                    ,"Usuario registrado"
                                    ,Snackbar.LENGTH_LONG
                                ).show()
                                CleanCampos(etFullName,etCountry,etEmail,etPassword)
                                //redirigir a la actividad SigninActivity
                                val intent = Intent(this@SignupActivity, SigninActivity::class.java)
                                startActivity(intent)




                            }.addOnFailureListener{error->
                                Snackbar.make(
                                    findViewById(android.R.id.content)
                                    ,"Error al registrar usuario: ${error.message}"
                                    ,Snackbar.LENGTH_LONG
                                ).show()
                                CleanCampos(etFullName,etCountry,etEmail,etPassword)
                            }


                    }
                }
        }

    }
}

fun CleanCampos(etFullName: EditText, etCountry: EditText, etEmail: EditText, etPassword: EditText){
    etFullName.text.clear()
    etCountry.text.clear()
    etEmail.text.clear()
    etPassword.text.clear()
}