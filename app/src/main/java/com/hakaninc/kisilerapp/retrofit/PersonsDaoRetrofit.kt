package com.hakaninc.retrofit

import retrofit2.Call
import retrofit2.http.*

interface PersonsDaoRetrofit {

    @GET("kisiler/tum_kisiler.php")
    fun getAllPerson():Call<PersonsAnswer>

    @POST("kisiler/tum_kisiler_arama.php")
    @FormUrlEncoded
    fun searchPerson(
        @Field("kisi_ad")
        person_name: String): Call<PersonsAnswer>

    @POST("kisiler/delete_kisiler.php")
    @FormUrlEncoded
    fun personDelete(
        @Field("kisi_id")
        person_id: Int): Call<CRUDAnswer>

    @POST("kisiler/insert_kisiler.php")
    @FormUrlEncoded
    fun insertPerson(
        @Field("kisi_ad")
        person_name: String,
        @Field("kisi_tel")
        person_tel: String): Call<CRUDAnswer>

    @POST("kisiler/update_kisiler.php")
    @FormUrlEncoded
    fun updatePerson(
        @Field("kisi_id")
        person_id: Int,
        @Field("kisi_ad")
        person_name: String,
        @Field("kisi_tel")
        person_tel: String): Call<CRUDAnswer>
}