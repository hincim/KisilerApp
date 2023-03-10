package com.hakaninc.kisilerapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hakaninc.kisilerapp.viewmodel.PersonRecordViewModel

class PersonRecordViewModelFactory (var application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonRecordViewModel(application = application) as T
    }
}