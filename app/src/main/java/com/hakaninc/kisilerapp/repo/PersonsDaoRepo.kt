package com.hakaninc.kisilerapp.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.room.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PersonsDaoRepo(application: Application) {

    private var personsListRepo = MutableLiveData<List<Persons>>()
    val db : Database

    init {
        db = Database.databaseAccess(application)!!
        personsListRepo = MutableLiveData()
    }

    fun connectingToViewmodel() : MutableLiveData<List<Persons>>{
        return personsListRepo
    }

    fun getAllPersonsRepo() {

        val job: Job = CoroutineScope(Dispatchers.Main).launch {

            personsListRepo.value = db.personsDao().getAllPersonsDatabase()
        }
    }

    fun searchPersonRepo(searchWord: String){

        val job: Job = CoroutineScope(Dispatchers.Main).launch {

            personsListRepo.value = db.personsDao().searchPersonDatabase(searchWord)
        }
    }

    fun personRecordRepo(name: String,tel:String){

        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val newPerson = Persons(0,name,tel)
            // FIXME: person_id önemli değil.
            db.personsDao().addPersonDatabase(persons = newPerson)
        }
    }
    fun personUpdateRepo(id:Int, name: String,tel:String){

        val job: Job = CoroutineScope(Dispatchers.Main).launch {

            val updatedPerson = Persons(id,name,tel)
            db.personsDao().updatePersonDatabase(persons = updatedPerson)
        }
    }

    fun personDeleteRepo(id:Int){

        val job: Job = CoroutineScope(Dispatchers.Main).launch {

            val deletedPerson = Persons(id,"","")
            db.personsDao().deletePersonDatabase(persons = deletedPerson)
            getAllPersonsRepo()
        }
    }
}