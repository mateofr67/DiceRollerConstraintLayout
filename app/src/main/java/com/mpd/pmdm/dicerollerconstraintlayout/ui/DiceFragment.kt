package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDiceBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel

class DiceFragment : Fragment() {

    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!
    private val dados: TwoDicesViewModel by activityViewModels<TwoDicesViewModel> {
        TwoDicesViewModel.TwoDicesFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dice, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.twoDicesViewModel = dados
        dados.dado1.observe(viewLifecycleOwner) {
            actualizarDados()
        }
        dados.dado2.observe(viewLifecycleOwner) {
            actualizarDados()
        }
        binding.lifecycleOwner = viewLifecycleOwner
    }

    // Función para actualizar la imagen de los dados según su valor.
    private fun actualizarDados() {
        val imgDiceResource = when (dados.dado1.value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_6
        }

        val imgDiceResource2 = when (dados.dado2.value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_6
        }
        binding.ivDice1.setImageResource(imgDiceResource)
        binding.ivDice2.setImageResource(imgDiceResource2)


    }


}








