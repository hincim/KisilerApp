package com.hakaninc.kisilerapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.repo.PersonsDaoRepo

class HomePageViewModel(application: Application) : AndroidViewModel(application) {

    private var pRepo = PersonsDaoRepo(application = application)
    var personsList = MutableLiveData<List<Persons>>()

    init {
        getAllPersons()
        personsList = pRepo.connectingToViewmodel()
    }

    fun getAllPersons() {
        pRepo.getAllPersonsRepo()
    }

    fun searchPerson(searchWord: String){
        pRepo.searchPersonRepo(searchWord)
    }
    fun personDelete(id:Int){
        pRepo.personDeleteRepo(id)
    }
}