package com.nadillla.assignmentweek9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_tambah_user.*

class TambahUserActivity : AppCompatActivity() {

    //declare firebase database
    private var db: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_user)

        initView()
        initFirebase()
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")



        // Read from the database
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue(String::class.java)!!
//                Log.d("TAG", "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w("TAG", "Failed to read value.", error.toException())
//            }
//        })

//        spinner.onItemSelectedListener = this
    }


    private fun initFirebase() {
        db = FirebaseDatabase.getInstance()
    }

    private fun initView() {
        btnAddUser.setOnClickListener {

            //get inputan user
            val name = edName.text.toString()
            val email = edEmail.text.toString()
            val hp = edHp.text.toString()
            val address = edAddress.text.toString()
            val job = edJob.text.toString()



            //masukin ke model


            //tambahkan pengecekan user
            val myRef = db?.getReference("Users")

            // get key
            val key = myRef?.push()?.key
            val user = User(name, email, hp, address, job)


            //insert ke db
            myRef?.child(key?: "")?.setValue(user)
            finish()


        }
    }

//    override fun onNothingSelected(parent: AdapterView<*>?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        TODO("Not yet implemented")
//    }
}