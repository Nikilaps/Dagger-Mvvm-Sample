package com.mvvm.mvvmdagger.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseModel {
    @SerializedName("success")
    @Expose
    var isSuccess: Boolean = true
    @SerializedName("data")
    @Expose
    var data: Any? = null
}
