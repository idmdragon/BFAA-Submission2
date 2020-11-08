package com.example.githubapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubapp.R
import com.example.githubapp.model.UsersModel
import de.hdodenhof.circleimageview.CircleImageView

class SearchAdapter (private val listUser : ArrayList<UsersModel>): RecyclerView.Adapter<SearchAdapter.UserViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listUser.size


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val UserName = itemView.findViewById<TextView>(R.id.tvUsername)
        private val Avatar = itemView.findViewById<CircleImageView>(R.id.IvUser)
        private val Followers = itemView.findViewById<TextView>(R.id.tvFollowers)
        private val Following = itemView.findViewById<TextView>(R.id.tvFollowing)

        internal fun bind(user: UsersModel) {
            Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .apply(RequestOptions().override(55,55))
                    .into(Avatar)

            UserName.text = user.login
            Followers.text = user.followers_url
            Following.text = user.following_url

        }


    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UsersModel)
    }
}