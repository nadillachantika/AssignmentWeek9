package com.nadillla.assignmentweek9

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(val data : ArrayList<User>, val itemClick : onItemDelete): RecyclerView.Adapter<UserAdapter.UserHolder>() {
    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(get: User) {
            itemView.txtNama.text = get.name
            itemView.txtJob.text=get.job


                //action klik
            itemView.setOnClickListener{
                val intent = Intent(itemView.context,UpdateUserActivity::class.java)
                intent.putExtra("data",get)
                itemView.context.startActivity(intent)


            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))


    override fun getItemCount() =data.size


    override fun onBindViewHolder(holder: UserAdapter.UserHolder, position: Int) {

        holder.bind(data.get(position))
        holder.itemView.btnDelete.setOnClickListener {
            itemClick.user(data.get(position))
        }

    }
    interface onItemDelete{
        fun user(user : User)

    }
}