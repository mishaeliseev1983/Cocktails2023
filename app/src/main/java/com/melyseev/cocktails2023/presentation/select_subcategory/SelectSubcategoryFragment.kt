package com.melyseev.cocktails2023.presentation.select_subcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melyseev.cocktails2023.databinding.FragmentSelectSubcategoryBinding
import com.melyseev.cocktails2023.presentation.App
import com.melyseev.cocktails2023.presentation.SubcategoryResultUI
import com.melyseev.cocktails2023.presentation.main.ViewModuleFactory
import com.melyseev.cocktails2023.presentation.select_subcategory.recyclerview.SubcategoriesListAdapter
import javax.inject.Inject


class SelectSubcategoryFragment : Fragment() {

    private var _binding: FragmentSelectSubcategoryBinding? = null
    private val binding: FragmentSelectSubcategoryBinding
        get() = _binding
            ?: throw RuntimeException("Object FragmentSelectSubcategoryBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModuleFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SelectSubcategoryViewModel::class.java]
    }

    private val daggerApplicationComponent by lazy {
       // (requireActivity().application as App).component
        (requireActivity().application as App).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectSubcategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daggerApplicationComponent.inject(this)

        val subcategoriesListAdapter = SubcategoriesListAdapter()
        binding.rvSubcategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvSubcategory.adapter = subcategoriesListAdapter

        viewModel.observeProgress(this){
            binding.progress.visibility = it
        }

        viewModel.observeStateSubcategoryList(this){
            when (it) {
                is SubcategoryResultUI.Success -> {
                    subcategoriesListAdapter.change( it.list )
                }
                is SubcategoryResultUI.Failure -> {
                    binding.tvSelectSubcategory.text = it.message
                }
            }
        }


        viewModel.fetchListSubcategory()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SelectSubcategoryFragment()
    }
}