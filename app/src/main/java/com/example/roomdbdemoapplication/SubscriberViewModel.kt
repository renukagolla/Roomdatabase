package com.example.roomdbdemoapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbdemoapplication.db.Subscriber
import com.example.roomdbdemoapplication.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(val repository: SubscriberRepository) : ViewModel() {
    val subscriber = repository.subscriber

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    var isUpdateORDelete = false
    private lateinit var subscriberUpdateOrdelete: Subscriber

    val saveORUpdateButtonText = MutableLiveData<String>()
    val clearORDeleteButtonText = MutableLiveData<String>()

    init {
        saveORUpdateButtonText.value = "SAVE"
        clearORDeleteButtonText.value = "CLEAR"
    }

    fun saveORupdate() {
        if (isUpdateORDelete) {
            subscriberUpdateOrdelete.name = inputName.value!!
            subscriberUpdateOrdelete.email = inputEmail.value!!
            update(subscriberUpdateOrdelete)
        } else {
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Subscriber(0, name, email))
            inputName.value = ""
            inputEmail.value = ""
        }
    }


    fun clearORdelete() {
        if (isUpdateORDelete) {
            clearData(subscriberUpdateOrdelete)
        } else {
            clearAll()
        }
    }

    fun insert(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(subscriber)
        }

    fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateData(subscriber)
        withContext(Dispatchers.Main) {
            inputName.value = ""
            inputEmail.value = ""
            isUpdateORDelete = false
            saveORUpdateButtonText.value = "Save"
            clearORDeleteButtonText.value = "Clear All"
        }
    }

    fun clearData(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletedata(subscriber)
            withContext(Dispatchers.Main) {
                inputName.value = ""
                inputEmail.value = ""
                isUpdateORDelete = false
                saveORUpdateButtonText.value = "Save"
                clearORDeleteButtonText.value = "Clear All"
            }
        }

    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUpdateDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateORDelete = true
        subscriberUpdateOrdelete = subscriber
        saveORUpdateButtonText.value = "Update"
        clearORDeleteButtonText.value = "Delete"
    }
}