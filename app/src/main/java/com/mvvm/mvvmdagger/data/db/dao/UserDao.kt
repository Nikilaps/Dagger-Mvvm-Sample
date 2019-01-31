package com.mvvm.mvvmdagger.data.db.dao

import android.arch.persistence.room.*
import com.mvvm.mvvmdagger.data.model.db.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Delete
    fun delete(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<User>>

}
