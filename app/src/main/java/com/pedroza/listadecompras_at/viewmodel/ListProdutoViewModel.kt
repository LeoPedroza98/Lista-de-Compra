package com.pedroza.listadecompras_at.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.pedroza.listadecompras_at.model.Produto
import com.pedroza.listadecompras_at.data.*
/*
Criado as ViewModel nesse caso meu ListProduto herda minha classe AppData
onde eu armazeno algumas funções e armazeno o meu objeto produto.
Eu inicio uma variavel privada produtos que leva o LiveData com o get para inicio também da minha List de Produto.
Errata: Esqueceu de inicializar o Init passando o value dos produtos = Corrigido.
 */
class ListProdutoViewModel(appData: AppData ) : ViewModel() {
    //Utilizei o MutableLiveData aqui para exibir minha lista
    //Inicio o produtos.value e passso a função Tprodutos herdado do meu appData para mostrar o produto em minha lista.

    private val _produtos = MutableLiveData<List<Produto>>()
    val produtos: LiveData<List<Produto>>
    get() = _produtos

    init {
        _produtos.value = appData.tProdutos()
    }
}