package com.melyseev.cocktails2023.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melyseev.cocktails2023.databinding.FragmentCocktailsListBinding
import com.melyseev.cocktails2023.presentation.App
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryResultUI
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryUI
import com.melyseev.cocktails2023.presentation.activity.NavigationStrategy
import com.melyseev.cocktails2023.presentation.details_cocktail.DetailsCocktailFragment
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailResultUI
import com.melyseev.cocktails2023.presentation.main.list_cocktails.recyclerview.CocktailsListAdapter
import com.melyseev.cocktails2023.presentation.main.list_subcategories.recyclerview.SubcategoryListAdapter
import com.melyseev.cocktails2023.presentation.main.list_subcategories.recyclerview.SubcategoryUIClick
import com.melyseev.cocktails2023.presentation.select_category.SelectCategoryFragment
import javax.inject.Inject

class CocktailsListFragment : Fragment() {

    private var _binding: FragmentCocktailsListBinding? = null
    private val binding: FragmentCocktailsListBinding
        get() = _binding ?: throw RuntimeException("Object FragmentCocktailsListBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModuleFactory
    private var movedScrollPositionSubcategory = false
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

        daggerApplicationComponent.inject(this)

        binding.imageButton.setOnClickListener {
            val fragment = SelectCategoryFragment.newInstance()
            val navigationStrategy = NavigationStrategy.Replace(fragment)
            viewModel.change(navigationStrategy)
        }

        val subcategoryUIClick = object : SubcategoryUIClick {
            override fun click(subcategoryUI: SubcategoryUI) {
                viewModel.selectSubcategoryCocktails(subcategoryUI.title)
            }
        }
        val subcategoryListAdapter = SubcategoryListAdapter(subcategoryUIClick)
        binding.recyclerViewSubcategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSubcategory.adapter = subcategoryListAdapter


        val cocktailListAdapter = CocktailsListAdapter {
            val fragment = DetailsCocktailFragment.newInstance(it)
            val navigationStrategy = NavigationStrategy.Replace(fragment)
            viewModel.change(navigationStrategy)
        }
        binding.recyclerViewCocktails.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewCocktails.adapter = cocktailListAdapter

        viewModel.observeProgress(this) {
            binding.progress.visibility = it
        }


        viewModel.observeStateSubcategoryList(this) {
            when (it) {
                is SubcategoryResultUI.Success -> {
                    subcategoryListAdapter.change(it.list)
                    if (!movedScrollPositionSubcategory) {
                        val index = it.list.indexOfFirst { it.isSelected }
                        if (index != -1)
                            binding.recyclerViewSubcategory.smoothScrollToPosition(index)
                        movedScrollPositionSubcategory = true
                    }
                }
                is SubcategoryResultUI.Failure -> {
                    binding.tvCategory.text = it.message
                }
            }
        }

        viewModel.observeStateCocktailList(this) {
            when (it) {
                is CocktailResultUI.Success -> {
                    cocktailListAdapter.change(it.list)
                }
                is CocktailResultUI.Failure -> {
                    binding.tvCategory.text = it.message
                }
            }
        }

        viewModel.observeStateCategoryName(this) {
            binding.tvCategory.text = it
        }

        viewModel.fetchCategoryName()
        viewModel.fetchData()
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