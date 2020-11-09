package com.nadillla.assignmentweek9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initFirebase()
        initView()
    }

    private fun initFirebase() {

        auth = FirebaseAuth.getInstance()
    }


    private fun initView() {

        btnSignUp.setOnClickListener {
            val email = edEmailrg.text.toString()
            val password = edPasswdrg.text.toString()
            val passConf = edPasswdConfirm.text.toString()

            if (email.isEmpty()) {
                edEmailrg.error = "Email tidak bole kosong"
            } else if (password.isEmpty()) {
                edPasswdrg.error = "Password tidak boleh kosong"
            } else if (passConf.isEmpty()) {
                edPasswdConfirm.error = "Password tidak boleh kosong"
            } else if (passConf != password) {
                edPasswdConfirm.error = "Password tidak sama"
            } else {
                actionRegister()
            }
        }
    }

    private fun actionRegister() {
        auth.createUserWithEmailAndPassword(edEmailrg.text.toString(), edPasswdrg.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "actionRegister: createUserWithEmail:success")
                    val user = auth.currentUser
                    finish()

                } else {
                    Log.w("Tag", "actionRegister: ", task.exception)

                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }

            }
    }
}