package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.TiradasApp
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.Tirada
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
    ): View {
        _binding = FragmentTiradaListBinding.inflate(inflater, container, false)
        binding.composeListaTiradas.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MaterialTheme {
                    ListaTiradas(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    )
                }
            }
        }


        return binding.root
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ListaTiradas(modifier: Modifier = Modifier) {
        val listaTiradas = viewModel.obtenerTiradas.observeAsState(emptyList())
        LazyColumn(modifier) {
            stickyHeader {
                CabeceraListaTiradas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(bottom = 10.dp)
                )
            }
            items(listaTiradas.value.size) {
                RegistroTirada(modifier = Modifier.fillMaxSize(),listaTiradas.value[it])
            }
        }
    }

    @Composable
    private fun CabeceraListaTiradas(modifier: Modifier = Modifier) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.primary, // Cambiar el color de fondo de la cabecera
        ){
            Column {
                Row {
                    Text(
                        text = getString(R.string.id),
                        modifier = Modifier.weight(1.0f),
                    )
                    Text(
                        text = getString(R.string.fecha),
                        modifier = Modifier.weight(3.0f),
                    )
                    Text(
                        text = getString(R.string.dado_1),
                        modifier = Modifier.weight(1.0f),
                    )
                    Text(
                        text = getString(R.string.dado_2),
                        modifier = Modifier.weight(1.0f),
                    )

                }
                Divider()

            }
        }
    }







    @Composable
    private fun RegistroTirada(modifier: Modifier = Modifier, tirada: Tirada) {
        Row(modifier){
            Text(
                text = tirada.id.toString(),
                modifier = Modifier.weight(1.0f),
            )
            Text(
                text = tirada.fecha,
                modifier = Modifier.weight(3.0f),
            )
            Text(
                text = tirada.dado1.toString(),
                modifier = Modifier.weight(1.0f),
            )
            Text(
                text = tirada.dado2.toString(),
                modifier = Modifier.weight(1.0f),
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}