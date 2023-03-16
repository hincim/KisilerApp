package com.hakaninc.kisilerapp.room

import androidx.room.*
import com.hakaninc.kisilerapp.entity.Persons

/*
@Dao
interface PersonsDao {

    @Query("SELECT * FROM persons")
    suspend fun getAllPersonsDatabase() : List<Persons>

    @Insert
    suspend fun addPersonDatabase(persons: Persons)

    @Delete
    suspend fun deletePersonDatabase(persons: Persons)

    @Update
    suspend fun updatePersonDatabase(persons: Persons)

    @Query("SELECT * FROM persons WHERE person_name like'%' || :search || '%'")
    suspend fun searchPersonDatabase(search: String) : List<Persons>

}*/
