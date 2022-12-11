package com.melyseev.cocktails2023.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melyseev.cocktails2023.data.CocktailsRepositoryImpl
import com.melyseev.cocktails2023.data.HandleErrorToDomainException
import com.melyseev.cocktails2023.databinding.FragmentCocktailsListBinding
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.HandleDomainExceptionToString
import com.melyseev.cocktails2023.presentation.communications.Communications
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications
import com.melyseev.cocktails2023.presentation.recyclerview.SubcategoryListAdapter
import com.melyseev.cocktails2023.presentation.recyclerview.SubcategoryUIClick
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

    /*
    private val progressCommunication = Communications.ProgressCommunication.Base()
    private val stateCommunication = Communications.SubcategoryStateCommunication.Base()

    val communications = SubcategoryCommunications.Base(progressCommunication, stateCommunication)

    val handleErrorToDomainException = HandleErrorToDomainException.Base()
    val repository = CocktailsRepositoryImpl(handleErrorToDomainException)

    val handleDomainExceptionToString = HandleDomainExceptionToString.Base()
    val interactor = CocktailsInteractor.Base(repository, handleDomainExceptionToString)

    val dispatchersList = DispatchersList.Base()

    private val viewModel = CocktailsListViewModel(
        dispatchersList = dispatchersList,
        communications = communications,
        interactor = interactor
    )

     */


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
        val subcategoryUIClick = object : SubcategoryUIClick {
            override fun click(subcategoryUI: SubcategoryUI) {
                CocktailsListViewModel.SUBCATEGORY = subcategoryUI.title
                viewModel.fetchListSubcategory()
            }
        }
        val subcategoryListAdapter = SubcategoryListAdapter(subcategoryUIClick)

        binding.recyclerViewSubcategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSubcategory.adapter = subcategoryListAdapter
        binding.tvCategory.text = "Non alcoholic"

        viewModel.observeProgress(this) {
            binding.progress.visibility = it
        }

        viewModel.observeState(this) {
            when (it) {
                is ResultUI.Success -> {
                    subcategoryListAdapter.change( it.list )
                    val selectedCategory = it.list.find { it.isSelected }
                    selectedCategory?.let {
                        CocktailsListViewModel.SUBCATEGORY = it.title
                        viewModel.fetchListCocktails()
                    }

                }
                is ResultUI.Failure -> {
                    binding.tvCategory.text = it.message
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
        fun newInstance() = CocktailsListFragment()
    }
}