package com.pedroza.listadecompras_at.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pedroza.listadecompras_at.MainViewModel
import com.pedroza.listadecompras_at.R
import com.pedroza.listadecompras_at.data.AppData
import com.pedroza.listadecompras_at.viewmodel.*
import kotlinx.android.synthetic.main.fragment_adiciona_produto.*

class AdicionaProdutoFragment : Fragment() {

    //Uso lateinit para adiar a incialização das minhas variaveis para poder ser usada da forma correta no CreateView e no ViewCreated.
    private lateinit var adicionarProdutoViewModel: AdicionarProdutoViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_adiciona_produto, container, false)
        adicionarProdutoViewModel = ViewModelProvider(this, ViewModeFactory(AppData.getInstance()))
                .get(AdicionarProdutoViewModel::class.java)


        adicionarProdutoViewModel.let {
            it.status.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().popBackStack()
                }
            }
            it.message.observe(viewLifecycleOwner) {
                if (!it.isNullOrBlank()) {
                    Snackbar.make(ConstraintAddProdutoLayout, it, Snackbar.LENGTH_LONG).show()
                }
            }
        }

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.produto.observe(viewLifecycleOwner) {
            if (it != null) {
                inputNome.setText(it.nomeProduto)
                inputQuantidade.setText(it.quantidade.toString())
                inputValorProduto.setText(it.valor.toString())
                inputLinkProduto.setText(it.link)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cadastrarButton.setOnClickListener {
            adicionarProdutoViewModel.add(
                    inputNome.text.toString(),
                    inputQuantidade.text.toString().toInt(),
                    inputValorProduto.text.toString().toDouble(),
                    inputLinkProduto.text.toString(),
                    mainViewModel.produto.value
            )
        }
    }
}

