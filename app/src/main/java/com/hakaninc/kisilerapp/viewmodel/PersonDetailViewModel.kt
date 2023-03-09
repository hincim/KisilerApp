package com.hakaninc.kisilerapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hakaninc.kisilerapp.repo.PersonsDaoRepo

class PersonDetailViewModel : ViewModel() {

    private var pRepo = PersonsDaoRepo()

    fun personUpdate(id:Int, name: String,tel:String){
        pRepo.personUpdateRepo(id,name,tel)
    }
}