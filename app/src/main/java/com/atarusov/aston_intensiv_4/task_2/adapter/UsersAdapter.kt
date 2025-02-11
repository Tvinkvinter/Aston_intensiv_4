package com.atarusov.aston_intensiv_4.task_2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.atarusov.aston_intensiv_4.R
import com.atarusov.aston_intensiv_4.databinding.UserItemBinding
import com.atarusov.aston_intensiv_4.task_2.model.User
import com.atarusov.aston_intensiv_4.task_2.utils.UsersDiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL

class UsersAdapter(
    private val onItemClickListener: (User) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var users = listOf<User>()
        set(value) {
            val diffUtil = UsersDiffUtil(field, value)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = users[position]
        with(holder as UsersViewHolder) {
            if (user.imageUri == null)
                binding.imgAvatar.setImageResource(R.drawable.ic_default_avatar_48)
            else Glide.with(holder.itemView)
                .load(user.imageUri)
                .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
                .transform(FitCenter(), CircleCrop())
                .into(binding.imgAvatar)

            binding.tvNameLastname.text = "${user.name} ${user.lastname}"
            binding.tvPhoneNumber.text = user.phoneNumber
            itemView.setOnClickListener {
                onItemClickListener.invoke(user)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    inner class UsersViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}