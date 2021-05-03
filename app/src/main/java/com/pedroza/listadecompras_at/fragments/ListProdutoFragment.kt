package com.pedroza.listadecompras_at.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pedroza.listadecompras_at.MainViewModel
import com.pedroza.listadecompras_at.R
import com.pedroza.listadecompras_at.adapter.ProdutoAdapter
import com.pedroza.listadecompras_at.data.AppData
import com.pedroza.listadecompras_at.model.Produto
import com.pedroza.listadecompras_at.viewmodel.ListProdutoViewModel
import com.pedroza.listadecompras_at.viewmodel.ViewModeFactory
import kotlinx.android.synthetic.main.fragment_list_produto.*

/*
Fragment feito para reproduzir algumas situações nos meu aplicativo todas elas utilizando viewLife.
 */
class ListProdutoFragment : Fragment() {

    private  lateinit var listProdutoViewModel: ListProdutoViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_list_produto, container, false)
        listProdutoViewModel = ViewModelProvider(this,ViewModeFactory(AppData.getInstance()))
                .get(ListProdutoViewModel::class.java)

        listProdutoViewModel.produtos.observe(viewLifecycleOwner){
            if(!it.isNullOrEmpty()){
                val produtoAdapter = ProdutoAdapter(it,this::actionClickButtonLink,this::actionClickButtonPerfilPrduto)
                listViewProduto.adapter = produtoAdapter
                listViewProduto.layoutManager = LinearLayoutManager(requireContext())
            }else{
                Snackbar.make(
                        ConstraintLayoutListProduto,
                        "Nenhum Produto",
                        Snackbar.LENGTH_LONG
                ).show()
            }
        }
        mainViewModel = ViewModelProvider(requireActivity())
                .get(MainViewModel::class.java)
        return view
    }


    //Minha Itent pega o link do produto e encaminha para alguma pagina da web aonde o produto está.
    //Ao clicar no buttonLink presente na minha list ela encaminha.
    private fun actionClickButtonLink(produto:Produto) {
        var intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(produto?.link)
        )
        startActivity(intent)
    }
    //Action criada para ir ao meu perfil do produto
    private fun actionClickButtonPerfilPrduto(produto: Produto) {
        mainViewModel.selectProduto(produto)
        findNavController().navigate(R.id.perfilProdutoFragment2)
    }
    //ViewCreated para inicializar com setClickListener o meu floatingButton
    // Ele encaminha para a minha fragment adicionarProduto onde crio o meu produto para lista.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButtonAddProduto.setOnClickListener {
            mainViewModel.selectProduto(null)
            findNavController().navigate(R.id.adicionaProdutoFragment2)
        }
    }

}