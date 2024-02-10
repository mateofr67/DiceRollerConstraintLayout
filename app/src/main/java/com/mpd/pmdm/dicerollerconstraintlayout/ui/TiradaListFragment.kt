package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpd.pmdm.dicerollerconstraintlayout.TiradasApp
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentTiradaListBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TiradaViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TiradaViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class TiradaListFragment : Fragment() {

    private var _binding: FragmentTiradaListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TiradaViewModel by activityViewModels {
        TiradaViewModelFactory((activity?.application as TiradasApp).appRepository)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTiradaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.layoutManager = LinearLayoutManager(context)
        val adapter = TiradasRecyclerViewAdapter()
        binding.list.adapter = adapter

        viewModel.obtenerTiradas.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}