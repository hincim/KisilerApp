package com.hakaninc.kisilerapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hakaninc.kisilerapp.viewmodel.PersonDetailViewModel

class PersonDetailViewModelFactory(var application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonDetailViewModel(application = application) as T
    }
}