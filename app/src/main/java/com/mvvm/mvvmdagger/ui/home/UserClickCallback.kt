package com.mvvm.mvvmdagger.ui.home

import com.mvvm.mvvmdagger.data.model.db.User


interface UserClickCallback {
    fun onClick(user: User)
}