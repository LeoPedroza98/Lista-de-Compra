package com.pedroza.listadecompras_at.data

import com.pedroza.listadecompras_at.model.Produto
import com.pedroza.listadecompras_at.viewmodel.ListProdutoViewModel
/*
Classe criada somente para guarda meu objeto produto e algumas funções como listar todos os produtos ,adicionar e deletear
 */
class AppData {

    //Lista do meus produtos mockada...
    private var produtos:MutableList<Produto> = mutableListOf(
            Produto("Arroz Tio João",2,11.48,"https://m.extra.com.br/arroz-tio-joao-100-graos-nobres-2kg-1502954078.html?IdSku=1502954078"),
            Produto("Feijão Carioca",2,10.50,"https://www.paodeacucar.com/produto/181919/feijao-carioca-tipo-1-nene-pacote-1kg?storeId=501&&utm_source=Google&utm_medium=Shopping&ef_id=Cj0KCQjwgtWDBhDZARIsADEKwgNosTlxEUH7b6NLWJ_lwEPRIA7tXOYtse9Ojh0tyL4nNEPh1P9tJ7EaAimKEALw_wcB:G:s&s_kwcid=AL!912!3!483262915353!!!u!305328391146!&utm_source=Google&utm_medium=Shopping&utm_campaign=Shopping-Inteligente-SP&gclid=Cj0KCQjwgtWDBhDZARIsADEKwgNosTlxEUH7b6NLWJ_lwEPRIA7tXOYtse9Ojh0tyL4nNEPh1P9tJ7EaAimKEALw_wcB"),
            Produto("Carne Filé Mingon",1,32.99,"https://www.superpaguemenos.com.br/file-mignon-bovino-resfriado-kg/p"),
            Produto("Batata Palha",2,5.79,"https://www.superpaguemenos.com.br/batata-palha-yoki-105g-tradicional/p")
    )

    //função que retorna toda lista de produtos...
    fun tProdutos():List<Produto>{
        return produtos
    }

    //função que adiciona produtos na lista
    fun add(produto: Produto){
        produtos.add(produto)
    }

    //Função que deleta
    fun delete(produto: Produto){
        produtos.remove(produto)
    }

    //Função que edita o produto
    fun edit(nomeProduto:String,quantidade:Int,valor:Double,link:String,produto: Produto){
        var index = produtos.indexOf(produto)
        produto.nomeProduto = nomeProduto
        produto.quantidade = quantidade
        produto.valor = valor
        produto.link = link
        produtos[index] = produto
    }

    //Em java eu usaria o Static mas android descobri que tenho que usar o Companion
    // pra declarar a criação da minha instancia appData nele eu trato se essa instance for null.
    companion object{
        private var instance: AppData? = null
        fun getInstance(): AppData{
            if (instance == null) {
                instance = AppData()
            }
            return instance as AppData
        }
    }
}