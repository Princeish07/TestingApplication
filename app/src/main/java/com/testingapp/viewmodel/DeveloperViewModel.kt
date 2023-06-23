package com.testingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


import com.testingapp.model.DeveloperModel
import com.testingapp.model.ListModelItem
import com.testingapp.repository.DeveloperRepository
import java.util.*


class DeveloperViewModel(application: Application) : AndroidViewModel(application) {
    private val mDeveloperRepository: DeveloperRepository

    val allDeveloper: LiveData<List<ListModelItem>>
        get() = mDeveloperRepository.getMutableLiveData()

    init {
        mDeveloperRepository = DeveloperRepository()
    }
}
