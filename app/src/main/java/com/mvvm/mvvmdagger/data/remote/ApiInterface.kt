package com.mvvm.mvvmdagger.data.remote

import com.mvvm.mvvmdagger.data.model.api.ResponseModel
import com.mvvm.mvvmdagger.data.model.db.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("5c5023f6330000e224c586f5")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<ResponseModel>

    @GET("5c5294723200006023855b4d")
    fun getUsers(): Observable<List<User>>
}