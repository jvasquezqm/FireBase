package com.example.firebase.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.firebase.database.CustomerEntity
import com.example.firebase.database.CustomerRepository

class RegisterViewModel(application: Application)
    : AndroidViewModel(application) {
        private var repository = CustomerRepository(application)

    fun saveCustomer(customerEntity: CustomerEntity){
        repository.insert(customerEntity)}

}