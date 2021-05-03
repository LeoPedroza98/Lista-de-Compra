package com.pedroza.listadecompras_at.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedroza.listadecompras_at.data.AppData
import com.pedroza.listadecompras_at.model.Produto

/*
Ideia de ultima hora criar um perfil do produto aonde leva questões como função de editar e deletar e ter mais algumas informações como valor do produto.
 */

class PerfilProduto (var AppData: AppData): ViewModel() {
    //Criei variaveis message e status somente para tratamento
    // e ter a confirmação de que o delete está funcionando, feito com LiveData utilizando o metodo get herdado.
    private val _message = MutableLiveData<String>()

    val message: LiveData<String> get() = _message

    private val _status = MutableLiveData<Boolean>()

    val status : LiveData<Boolean> get() = _status

    init{
        _message.value= null
        _status.value =false
    }

    fun delete(produto: Produto){
        _message.value = "Ok...produto está sendo deletado"
        AppData.delete(produto)
        _message.value = "Delete completo"
        _status.value=true
    }
}