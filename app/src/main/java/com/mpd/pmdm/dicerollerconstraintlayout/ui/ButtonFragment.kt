package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.TiradasApp
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel.TwoDicesFactory
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentButtonBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TiradaViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TiradaViewModelFactory
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale


class ButtonFragment : Fragment() {
    private val formato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    private var _binding : FragmentButtonBinding? = null
    private val binding get() = _binding!!

    private val dados: TwoDicesViewModel by activityViewModels<TwoDicesViewModel>{
       TwoDicesFactory()
    }

    private val viewModel: TiradaViewModel by activityViewModels {
        TiradaViewModelFactory((activity?.application as TiradasApp).appRepository)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_button,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.twoDicesViewModel = dados
        binding.btnLanzar.setOnClickListener {
            dados.rollDices()
            (binding.twoDicesViewModel)?.apply {
                dado1.value?.let { it1 ->
                    dado2.value?.let { it2 ->
                        val currentTimestamp = Timestamp(System.currentTimeMillis())
                        val fecha = formato.format(currentTimestamp)
                        viewModel.insertarTirada(
                            fecha, it1, it2
                        )

                    }
                }
            }


            mostrarToast(getString(R.string.tirada_registrada))

        }

        binding.btnLimpiar.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle(getString(R.string.confirmacion_borrado_title))
            alertDialogBuilder.setMessage(getString(R.string.confirmacion_borrado_mensaje))
            alertDialogBuilder.setCancelable(false)

            alertDialogBuilder.setPositiveButton(getString(R.string.confirmacion_borrado_aceptar)) { dialog, _ ->
                viewModel.borrarTiradas()
                mostrarToast(getString(R.string.tiradas_borrado_exito_mensaje))
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton(getString(R.string.confirmacion_borrado_cancelar)) { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }


        binding.lifecycleOwner = viewLifecycleOwner
    }



    private fun mostrarToast(mensaje: String) {
        Toast.makeText(activity,mensaje, Toast.LENGTH_SHORT).show()
    }



}
