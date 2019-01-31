package com.mvvm.mvvmdagger.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.mvvmdagger.data.model.db.User
import com.mvvm.mvvmdagger.databinding.ItemUserBinding
import com.mvvm.mvvmdagger.ui.base.BaseViewHolder

class UserAdapter(private val userClickCallback: UserClickCallback?) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    internal var userList: List<User>? = null

    fun addItems(data: List<User>) {
        userList = data
        notifyDataSetChanged()
    }

    fun clearItems() {
        userList = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        binding.callback = userClickCallback

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return if (userList == null) 0 else userList!!.size
    }

    inner class UserViewHolder(private val mBinding: ItemUserBinding) : BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            val user = userList!![position]
            mBinding.user = user
            mBinding.executePendingBindings()
        }
    }
}