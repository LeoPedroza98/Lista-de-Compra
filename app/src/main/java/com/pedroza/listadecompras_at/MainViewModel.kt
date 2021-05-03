package com.pedroza.listadecompras_at

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedroza.listadecompras_at.model.Produto

class MainViewModel : ViewModel(){

    private val _produto = MutableLiveData<Produto>()
    val produto: LiveData<Produto>
    get()= _produto

    fun selectProduto(produto: Produto?){
        _produto.value = produto
    }

    fun deleteProduto(){
        selectProduto(null)
    }
}