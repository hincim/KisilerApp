package com.hakaninc.kisilerapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.repo.PersonsDaoRepoRetrofit

// import com.hakaninc.kisilerapp.repo.PersonsDaoRepo

class PersonDetailViewModel(application: Application) : AndroidViewModel(application) {

    private var pRepo = PersonsDaoRepoRetrofit(application = application)

    fun personUpdate(id:Int, name: String,tel:String,oldName:String){
        pRepo.personUpdateRepo(id,name,tel,oldName)
    }
}