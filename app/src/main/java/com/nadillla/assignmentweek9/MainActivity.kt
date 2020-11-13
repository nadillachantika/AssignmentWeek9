package com.nadillla.assignmentweek9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.net.UnknownServiceException

class MainActivity : AppCompatActivity() {

    private var db : FirebaseDatabase? = null

    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initFirebase()
        initView()
        getdata()

    }

    private fun getdata() {
        // [START write_message]
        // Write a message to the database
        val db = Firebase.database
        val myRef = db?.getReference("Users")

        val dataUser = ArrayList<User>()

        // [START read_message]
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
              //  val value = dataSnapshot.getValue(String::class.java)!!
                //Log.d("TAG", "Value is: $value")


                for(datas in dataSnapshot.children){

                    val key = datas.key

                    //get per masing2 field
                    val nama = datas.child("name").value.toString()
                    val job = datas.child("job").value.toString()
                    val hp = datas.child("hp").value.toString()
                    val email = datas.child("email").value.toString()
                    val address = datas.child("address").value.toString()

                    val user = User(nama,email,hp,address,job,key )
                    dataUser.add(user)
                    
                    showData(dataUser)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
        // [END read_message]    }
    }

    override fun onResume() {
        super.onResume()
        getdata()
    }

    private fun showData(dataUser: ArrayList<User>) {
        listUser.adapter= UserAdapter(dataUser, object :UserAdapter.onItemDelete{
            override fun user(user: User) {

                var ref = db?.getReference("Users")
                ref?.child(user.key ?: "")?.removeValue()
                getdata()
            }

        })

    }

    private fun initView() {
//        var user = auth?.currentUser
//        user.let{
//            tvHello.text = "Email : " +user?.email
//        }

        btnAdd.setOnClickListener{
            startActivity(Intent(this,TambahUserActivity::class.java))
        }


    }

    private fun initFirebase(){
        auth = FirebaseAuth.getInstance()


    }
}