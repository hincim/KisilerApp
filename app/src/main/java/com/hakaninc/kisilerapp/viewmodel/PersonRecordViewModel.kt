package com.hakaninc.kisilerapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// import com.hakaninc.kisilerapp.repo.PersonsDaoRepo
import com.hakaninc.kisilerapp.repo.PersonsDaoRepoRetrofit
import com.hakaninc.retrofit.CRUDAnswer

class PersonRecordViewModel(application: Application) : AndroidViewModel(application) {

    private var pRepo = PersonsDaoRepoRetrofit(application = application)
    var answer = MutableLiveData<String>()
    private var pRepoRetrofit = PersonsDaoRepoRetrofit(application)

    fun personRecord(name: String,tel:String){
        pRepo.personRecordRepo(name,tel)
    }
}