package com.hakaninc.kisilerapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.repo.PersonsDaoRepo

class HomePageViewModel : ViewModel() {

    private var pRepo = PersonsDaoRepo()
    var personsList = MutableLiveData<List<Persons>>()

    init {
        getAllPersons()
        personsList = pRepo.connectingToViewmodel()
    }

    private fun getAllPersons() {
        pRepo.getAllPersonsRepo()
    }

    fun searchPerson(searchWord: String){
        pRepo.searchPersonRepo(searchWord)
    }
    fun personDelete(id:Int){
        pRepo.personDeleteRepo(id)
    }
}