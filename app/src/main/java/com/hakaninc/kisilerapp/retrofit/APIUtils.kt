package com.hakaninc.kisilerapp.retrofit

import com.hakaninc.retrofit.PersonsDaoRetrofit

class APIUtils {

    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getPersonsDao() : PersonsDaoRetrofit{

            return RetrofitClient.getClient(BASE_URL).create(PersonsDaoRetrofit::class.java)
        }
    }
}