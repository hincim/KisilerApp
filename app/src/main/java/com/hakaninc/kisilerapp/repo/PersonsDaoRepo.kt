package com.hakaninc.kisilerapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hakaninc.kisilerapp.entity.Persons

class PersonsDaoRepo {

    private var personsListRepo = MutableLiveData<List<Persons>>()

    init {
        personsListRepo = MutableLiveData()
    }

    fun connectingToViewmodel() : MutableLiveData<List<Persons>>{
        return personsListRepo
    }

    fun getAllPersonsRepo() {
        val list = mutableListOf<Persons>()
        val p1 = Persons(1,"Hakan","11111")
        list.add(p1)
        personsListRepo.value = list
    }

    fun searchPersonRepo(searchWord: String){
        Log.e("Search",searchWord)
    }

    fun personRecordRepo(name: String,tel:String){
        Log.e("Record","$name - $tel")
    }
    fun personUpdateRepo(id:Int, name: String,tel:String){
        Log.e("Update","$id - $name - $tel")
    }

    fun personDeleteRepo(id:Int){
        Log.e("Delete",id.toString())
    }
}