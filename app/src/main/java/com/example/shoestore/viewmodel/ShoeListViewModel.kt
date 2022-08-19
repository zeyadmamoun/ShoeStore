package com.example.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var tempShoeList = mutableListOf<Shoe>()

    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedIn : LiveData<Boolean>
        get() = _loggedIn

    init {
        Timber.i("ViewModel created")
        _shoeList.value = mutableListOf()
        _loggedIn.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }

    fun addShoe(name: String?,size: Double,company: String?,description: String?){
        when{
            name == null || name.isBlank() -> return
            company == null || company.isBlank()-> return
            description == null || description.isBlank() -> return
            size == 0.0 -> return
            else -> {
                val newShoe = Shoe(name,size,company,description)
                tempShoeList.add(newShoe)
                _shoeList.value = tempShoeList
            }
        }
    }

    fun login(){
        _loggedIn.value = true
    }

    fun onLogout(){
        tempShoeList.clear()
        _shoeList.value = tempShoeList
    }
}