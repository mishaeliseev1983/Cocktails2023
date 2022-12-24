package com.melyseev.cocktails2023.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melyseev.cocktails2023.databinding.FragmentCocktailsListBinding
import com.melyseev.cocktails2023.presentation.list_cocktails.CocktailResultUI
import com.melyseev.cocktails2023.presentation.list_cocktails.recyclerview.CocktailsListAdapter
import com.melyseev.cocktails2023.presentation.list_subcategories.recyclerview.SubcategoryListAdapter
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryResultUI
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryUI
import com.melyseev.cocktails2023.presentation.list_subcategories.recyclerview.SubcategoryUIClick
import kotlinx.coroutines.delay
import javax.inject.Inject

class CocktailsListFragment : Fragment() {

    private var _binding: FragmentCocktailsListBinding? = null
    private val binding: FragmentCocktailsListBinding
        get() = _binding ?: throw RuntimeException("Object FragmentCocktailsListBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModuleFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CocktailsListViewModel::class.java]
    }

    private val daggerApplicationComponent by lazy {
        (requireActivity().application as App).component
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daggerApplicationComponent.inject( this )
        binding.tvCategory.text = CocktailsListViewModel.CATEGORY


        val subcategoryUIClick = object : SubcategoryUIClick {
            override fun click(subcategoryUI: SubcategoryUI) {

                println("qwer ${CocktailsListViewModel.CATEGORY}")
                binding.tvCategory.text = CocktailsListViewModel.CATEGORY
                CocktailsListViewModel.SUBCATEGORY = subcategoryUI.title

                viewModel.fetchListSubcategory()
                viewModel.fetchListCocktails()
            }
        }
        val subcategoryListAdapter = SubcategoryListAdapter(subcategoryUIClick)
        binding.recyclerViewSubcategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSubcategory.adapter = subcategoryListAdapter



        val cocktailListAdapter = CocktailsListAdapter()
        binding.recyclerViewCocktails.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewCocktails.adapter = cocktailListAdapter

        viewModel.observeProgress(this) {
            binding.progress.visibility = it
        }


        viewModel.observeStateSubcategoryList(this) {

            when (it) {
                is SubcategoryResultUI.Success -> {
                    subcategoryListAdapter.change( it.list )
                }
                is SubcategoryResultUI.Failure -> {
                    binding.tvCategory.text = it.message
                }
            }
        }

        viewModel.observeStateCocktailList(this) {
            when (it) {
                is CocktailResultUI.Success -> {
                    cocktailListAdapter.change( it.list )
                }
                is CocktailResultUI.Failure -> {
                    binding.tvCategory.text = it.message
                }
            }
        }

        viewModel.fetchListSubcategory()
        viewModel.fetchListCocktails()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CocktailsListFragment()
    }
}