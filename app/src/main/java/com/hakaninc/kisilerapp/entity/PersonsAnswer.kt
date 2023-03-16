package com.hakaninc.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.hakaninc.kisilerapp.entity.Persons

data class PersonsAnswer(

    @SerializedName("kisiler")
    @Expose
    var persons: List<Persons>,
    @SerializedName("success")
    @Expose
    var success: Int
)
