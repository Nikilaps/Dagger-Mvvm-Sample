package com.mvvm.mvvmdagger.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.mvvm.mvvmdagger.data.model.db.User
import com.mvvm.mvvmdagger.ui.home.UserAdapter

@BindingAdapter("adapter")
fun addUserItems(recyclerView: RecyclerView, user: List<User>?) {
    val adapter = recyclerView.adapter as UserAdapter?
    if (adapter != null) {
        adapter.clearItems()
        adapter.addItems(user!!)
    }
}

