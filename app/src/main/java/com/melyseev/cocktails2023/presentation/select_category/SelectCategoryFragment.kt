package com.melyseev.cocktails2023.presentation.select_category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.melyseev.cocktails2023.databinding.FragmentSelectCategoryBinding
import com.melyseev.cocktails2023.presentation.App
import com.melyseev.cocktails2023.presentation.ShowFragment
import com.melyseev.cocktails2023.presentation.main.ViewModuleFactory
import com.melyseev.cocktails2023.presentation.select_subcategory.SelectSubcategoryFragment
import javax.inject.Inject


class SelectCategoryFragment : Fragment() {

    private var _binding: FragmentSelectCategoryBinding? = null
    private val binding: FragmentSelectCategoryBinding
        get() = _binding ?: throw RuntimeException("Object FragmentSelectCategoryBinding is null")

    private var showFragment: ShowFragment? = null

    @Inject
    lateinit var viewModelFactory: ViewModuleFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SelectCategoryViewModel::class.java]
    }

    private val daggerApplicationComponent by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = context as ShowFragment
    }

    override fun onDetach() {
        showFragment = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daggerApplicationComponent.inject(this)

        binding.btnSelectSubcategories.setOnClickListener {
            val fragment = SelectSubcategoryFragment.newInstance()
            showFragment?.show(fragment, add = false)
        }
        val listCheckedBox = listOf(
            binding.checkboxCategories, binding.checkboxAlcoholic,
            binding.checkboxGlasses, binding.checkboxIngredients, binding.checkboxFavorites
        )

        listCheckedBox.forEach { checkBox ->
            val selectedCategory = checkBox.text.toString()
            checkBox.setOnClickListener {
                viewModel.fetchSelectedCategory2(selectedCategory)
            }
        }

        viewModel.observeSelectedCategory(this) { selectedCheck ->
            listCheckedBox.forEach {
                it.isChecked = false
            }
            listCheckedBox.find { it.text == selectedCheck }?.isChecked = true
            viewModel.changeCategory(selectedCheck)
        }
        viewModel.fetchSelectedCategory()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SelectCategoryFragment()
    }
}