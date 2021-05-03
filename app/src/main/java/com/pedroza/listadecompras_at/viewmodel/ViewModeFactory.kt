package com.pedroza.listadecompras_at.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pedroza.listadecompras_at.data.AppData
import java.lang.IllegalArgumentException

class ViewModeFactory (var appData: AppData): ViewModelProvider.Factory{
    //Utilizo aqui o ViewModelProvide.Facotry para instanciar as ViewModels;
    // com a ligação do meu appData classe utilizada para ter funções relacionadas a listar,criar e perfil do meu produto.
    override fun <T : ViewModel?>create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(ListProdutoViewModel::class.java)){
            return ListProdutoViewModel(appData) as T
        }

        if (modelClass.isAssignableFrom(AdicionarProdutoViewModel::class.java))
            return AdicionarProdutoViewModel(appData) as T

        if (modelClass.isAssignableFrom(PerfilProduto::class.java)){
            return PerfilProduto(appData) as T
        }

        throw IllegalArgumentException("Illegal Argument")
    }

}