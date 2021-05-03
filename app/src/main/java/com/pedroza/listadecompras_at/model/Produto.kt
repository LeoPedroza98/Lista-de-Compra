package com.pedroza.listadecompras_at.model

/*
Começo: Model criado para Classe produto.
 */
class Produto (

    var nomeProduto : String,
    var quantidade:Int,
    var valor: Double,
    var link: String
){
    override fun toString():String{
        return "$nomeProduto" + "$quantidade" + "$valor"
    }
}