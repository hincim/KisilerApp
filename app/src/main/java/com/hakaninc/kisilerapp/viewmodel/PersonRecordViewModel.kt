package com.hakaninc.kisilerapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.hakaninc.kisilerapp.repo.PersonsDaoRepo

class PersonRecordViewModel(application: Application) : AndroidViewModel(application) {

    private var pRepo = PersonsDaoRepo(application = application)

    fun personRecord(name: String,tel:String){
        pRepo.personRecordRepo(name,tel)
    }
}