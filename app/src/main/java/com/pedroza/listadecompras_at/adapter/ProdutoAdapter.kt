package com.pedroza.listadecompras_at.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.AbsListView
import androidx.recyclerview.widget.RecyclerView
import com.pedroza.listadecompras_at.R
import com.pedroza.listadecompras_at.model.Produto
import kotlinx.android.synthetic.main.fragment_list_produto.view.*
import kotlinx.android.synthetic.main.recycler_list_produto.view.*
import java.text.ParsePosition


/*
Meu adapter.
 */

class ProdutoAdapter(
        private val produtos: List<Produto>,
        private val actionClickButtonLink: (Produto) -> Unit,
        private val actionClickButtonPerfilPrduto: (Produto) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {


    class ProdutoViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewNomeProduto = ItemView.textRecycleNomeProduto
        val textViewQuantidade = ItemView.textViewRecyclerQuantidade
        //val textViewValor = ItemView.textViewRecyclerValor
        val btnProdutoLink = ItemView.btnRecyclerLink
        val btnPerfilProduto = ItemView.btnRecyclerProdutoPerfil
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType:Int) : ProdutoViewHolder{
        val view = LayoutInflater
                .from(parent.context)
                .inflate(
                R.layout.recycler_list_produto,
                parent,
                false
        )
        val produtoViewHolder = ProdutoViewHolder(view)
        return produtoViewHolder
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder,position: Int){
        val produto = produtos.get(position)

        holder.textViewNomeProduto.text = produto.nomeProduto
        holder.textViewQuantidade.text = produto.quantidade.toString()
        holder.btnProdutoLink.setOnClickListener{
            actionClickButtonLink(produto)
        }
        holder.btnPerfilProduto.setOnClickListener{
            actionClickButtonPerfilPrduto(produto)
        }
    }

    override fun getItemCount(): Int {
        return produtos.size
    }
}