package com.hakaninc.kisilerapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hakaninc.kisilerapp.repo.PersonsDaoRepo

class PersonRecordViewModel : ViewModel() {

    private var pRepo = PersonsDaoRepo()

    fun personRecord(name: String,tel:String){
        pRepo.personRecordRepo(name,tel)
    }
}