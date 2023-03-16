package com.hakaninc.kisilerapp.repo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.retrofit.APIUtils
//import com.hakaninc.kisilerapp.room.Database
import com.hakaninc.retrofit.CRUDAnswer
import com.hakaninc.retrofit.PersonsAnswer
import com.hakaninc.retrofit.PersonsDaoRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonsDaoRepoRetrofit(var application: Application) {

    private var personsListRepo = MutableLiveData<List<Persons>>()
    private var personsDaoRetrofit: PersonsDaoRetrofit

    init {
        personsDaoRetrofit = APIUtils.getPersonsDao()
        personsListRepo = MutableLiveData()
    }

    fun connectingToViewmodel() : MutableLiveData<List<Persons>>{
        return personsListRepo
    }

    fun getAllPersonsRepo() {

       personsDaoRetrofit.getAllPerson().enqueue(object : Callback<PersonsAnswer>{
           override fun onResponse(call: Call<PersonsAnswer>?, response: Response<PersonsAnswer>?) {
               personsListRepo.value = response?.body()?.persons
           }

           override fun onFailure(call: Call<PersonsAnswer>?, t: Throwable?) {}
       })
    }

    fun searchPersonRepo(searchWord: String){

        personsDaoRetrofit.searchPerson(searchWord).enqueue(object : Callback<PersonsAnswer>{
            override fun onResponse(call: Call<PersonsAnswer>?, response: Response<PersonsAnswer>?) {
                personsListRepo.value = response?.body()?.persons
            }

            override fun onFailure(call: Call<PersonsAnswer>?, t: Throwable?) {}
        })

    }

    fun personRecordRepo(name: String,tel:String) {

        personsDaoRetrofit.insertPerson(name, tel).enqueue(object : Callback<CRUDAnswer> {
            override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {

                Toast.makeText(application,name +" eklendi",Toast.LENGTH_SHORT).show()
                getAllPersonsRepo()
            }

            override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }
    fun personUpdateRepo(person_id:Int,person_name:String,person_tel:String,oldName:String){

        personsDaoRetrofit.updatePerson(person_id,person_name,person_tel).enqueue(object : Callback<CRUDAnswer>{
            override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {
                Toast.makeText(application,oldName +" güncellendi",Toast.LENGTH_SHORT).show()
                getAllPersonsRepo()
            }

            override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })

    }

    fun personDeleteRepo(person: Persons){
        personsDaoRetrofit.personDelete(person.person_id).enqueue(object : Callback<CRUDAnswer>{
            override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {
                Toast.makeText(application,person.person_name +" silindi",Toast.LENGTH_SHORT).show()
                getAllPersonsRepo()
            }

            override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })

    }
}