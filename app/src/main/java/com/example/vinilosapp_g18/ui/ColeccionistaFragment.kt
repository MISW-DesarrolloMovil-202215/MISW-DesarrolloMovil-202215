package com.example.vinilosapp_g18.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp_g18.R
import com.example.vinilosapp_g18.databinding.ColeccionistaFragmentBinding
import com.example.vinilosapp_g18.models.Album
import com.example.vinilosapp_g18.models.Coleccionista
import com.example.vinilosapp_g18.ui.adapters.ColeccionistaAdapter
import com.example.vinilosapp_g18.viewmodels.AlbumViewModel
import com.example.vinilosapp_g18.viewmodels.ColeccionistaViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ColeccionistaFragment : Fragment() {
    private var _binding: ColeccionistaFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ColeccionistaViewModel
    private var viewModelAdapter: ColeccionistaAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ColeccionistaFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ColeccionistaAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.coleccionistaRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        // activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, ColeccionistaViewModel.Factory(activity.application)).get(
            ColeccionistaViewModel::class.java)
        viewModel.coleccionistas.observe(viewLifecycleOwner, Observer<List<Coleccionista>> {
            it.apply {
                viewModelAdapter!!.colecionista = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}