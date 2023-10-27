package com.programmer.challenge5_ma.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programmer.challenge5_ma.database.CartDatabase
import com.programmer.challenge5_ma.repository.CartRepository


class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            val cartDao = CartDatabase.getDatabase(application).cartItemDao()
            val repository = CartRepository(cartDao)
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
