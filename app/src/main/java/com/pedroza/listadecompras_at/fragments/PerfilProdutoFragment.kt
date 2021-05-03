package com.pedroza.listadecompras_at.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pedroza.listadecompras_at.MainViewModel
import com.pedroza.listadecompras_at.R
import com.pedroza.listadecompras_at.data.AppData
import com.pedroza.listadecompras_at.viewmodel.PerfilProduto
import com.pedroza.listadecompras_at.viewmodel.ViewModeFactory
import kotlinx.android.synthetic.main.fragment_perfil_produto.*
import kotlinx.android.synthetic.main.recycler_list_produto.*

/*
Fragment criado para o perfilProduto, guarda informações mais detalhadas sobre o produto como valor do produto
 */

class PerfilProdutoFragment : Fragment() {
    private lateinit var perfilProduto: PerfilProduto
    private lateinit var mainViewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDeleteProduto.setOnClickListener {
            var produtos = mainViewModel.produto.value
            perfilProduto.delete(produtos!!)
        }

        btnEditProduto.setOnClickListener {
            findNavController().navigate(R.id.adicionaProdutoFragment2)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_perfil_produto, container, false)

        perfilProduto = ViewModelProvider(this,ViewModeFactory(AppData.getInstance()))
                .get(PerfilProduto::class.java)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.produto.observe(viewLifecycleOwner){
            if(it != null){
                textViewProduto.text = it.nomeProduto
                textoViewQuantidade.text = it.quantidade.toString()
                textViewValor.text = it.valor.toString()
            }else if(!perfilProduto.status.value!!){
                Snackbar.make(
                        ConstraintPerfilProduto,
                        "Opa...Seleciona um produto",
                        Snackbar.LENGTH_LONG
                ).show()

            }
        }

        perfilProduto.message.observe(viewLifecycleOwner){
            if(!it.isNullOrBlank()){
                Snackbar.make(
                        ConstraintPerfilProduto,
                        it,
                        Snackbar.LENGTH_LONG
                ).show()
            }
        }

        perfilProduto.status.observe(viewLifecycleOwner){
            if(it){
                mainViewModel.deleteProduto()
                findNavController().popBackStack()
            }
        }
        return view
    }
}