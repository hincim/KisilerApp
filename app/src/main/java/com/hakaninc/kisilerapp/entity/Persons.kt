package com.hakaninc.kisilerapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull


data class Persons(@SerializedName("kisi_id")
                   @Expose
                   var person_id:Int,
                   @SerializedName("kisi_ad")
                   @Expose
                   var person_name:String,
                   @SerializedName("kisi_tel")
                   @Expose
                   var person_tel:String)
