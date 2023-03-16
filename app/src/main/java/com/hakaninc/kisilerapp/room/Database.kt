package com.hakaninc.kisilerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hakaninc.kisilerapp.entity.Persons

/*
@Database(entities = [Persons::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun personsDao(): PersonsDao

    companion object{
        var INSTANCE : com.hakaninc.kisilerapp.room.Database? = null

        fun databaseAccess(context: Context) : com.hakaninc.kisilerapp.room.Database?{

            if (INSTANCE == null){

                synchronized(Database::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        com.hakaninc.kisilerapp.room.Database::class.java
                        ,"directory.sqlite")
                        .createFromAsset("directory.sqlite")
                        .build()
                }
            }

            return INSTANCE
        }
    }
}*/
