package com.mvvm.mvvmdagger.data.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.text.TextUtils
import android.util.Patterns
import io.reactivex.annotations.Nullable

@Entity(tableName = "users")
 data class User(
    @PrimaryKey var id:Long,
    @ColumnInfo(name = "username") var email:String,
    @Nullable var password:String=""){

    /**
     * Function used to validate user email and password.
     * @return true if valid user else false.
     */
    fun isValidUser(): Boolean {
        return !TextUtils.isEmpty(email)
                && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5
    }
}