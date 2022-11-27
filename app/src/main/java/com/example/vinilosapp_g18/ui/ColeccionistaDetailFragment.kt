package com.example.vinilosapp_g18.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp_g18.R
import com.example.vinilosapp_g18.databinding.ColeccionistaDetailFragmentBinding
import com.example.vinilosapp_g18.models.Coleccionista
import com.example.vinilosapp_g18.ui.adapters.ColeccionistaDetailAdapter
import com.example.vinilosapp_g18.viewmodels.ColeccionistaDetailViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [ColeccionistaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ColeccionistaDetailFragment : Fragment() {
    private var _binding: ColeccionistaDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ColeccionistaDetailViewModel
    private var viewModelAdapter: ColeccionistaDetailAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ColeccionistaDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ColeccionistaDetailAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.commentsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        //activity.actionBar?.title = getString(R.string.title_comments)
        val args: ColeccionistaDetailFragmentArgs by navArgs()
        //Log.d("Args", args.coleccionistaId.toString())
        viewModel = ViewModelProvider(this, ColeccionistaDetailViewModel.Factory(activity.application, args.coleccionistaId)).get(
            ColeccionistaDetailViewModel::class.java)
        viewModel.coleccionista.observe(viewLifecycleOwner, Observer<List<Coleccionista>> {
            it.apply {
                viewModelAdapter!!.coleccionista = this
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