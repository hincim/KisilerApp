package com.hakaninc.kisilerapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "persons")
data class Persons(@PrimaryKey(autoGenerate = true)
                   @ColumnInfo(name = "person_id")
                   @NotNull
                   val person_id : Int,
                   @ColumnInfo(name = "person_name")
                   @NotNull
                   val person_name : String,
                   @ColumnInfo(name = "person_tel")
                   @NotNull
                   val person_tel : String)