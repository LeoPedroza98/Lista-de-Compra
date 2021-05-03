package com.pedroza.listadecompras_at.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedroza.listadecompras_at.data.AppData
import com.pedroza.listadecompras_at.model.Produto
import com.pedroza.listadecompras_at.model.Produto.*
/*
Aqui é uma outra viewModel mas aqui é focada em adicionar o produto em minha list incializada em minha outra viewModel,
Inicio herdando uma variavel instance que é basicamente uma instancia do meu appData por isso o nome instance.
 */
class AdicionarProdutoViewModel(var instance:AppData) : ViewModel() {

    //Criei variaveis message e status para tratamento e confirmação;
    //Se meu aplicativo adiciona ou não o produto em minha list.
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>get() = _message

    private val _status = MutableLiveData<Boolean>()
    val status:LiveData<Boolean>get()=_status


    init {
        _message.value=null
        _status.value=false
    }

    fun add(nomeProduto:String, quantidade:Int, valor:Double, link:String,produto: Produto?){
        if (produto == null){
            instance.add(Produto(nomeProduto, quantidade, valor, link))
        }else{
            instance.edit(nomeProduto, quantidade, valor, link,produto)
        }
        if (true){
            _status.value = true
            _message.value = "Sucesso.."
        }
    }
}