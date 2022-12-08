package com.melyseev.cocktails2023.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.melyseev.cocktails2023.data.CocktailsRepositoryImpl
import com.melyseev.cocktails2023.data.HandleErrorToDomainException
import com.melyseev.cocktails2023.databinding.FragmentCocktailsListBinding
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.HandleDomainExceptionToString
import com.melyseev.cocktails2023.presentation.communications.Communications
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications
import com.melyseev.cocktails2023.presentation.recyclerview.SubcategoryListAdapter

class CocktailsListFragment : Fragment() {

    private var _binding: FragmentCocktailsListBinding? = null
    private val binding: FragmentCocktailsListBinding
        get() = _binding ?: throw RuntimeException("Object FragmentCocktailsListBinding is null")


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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val subcategoryListAdapter = SubcategoryListAdapter()
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
                    subcategoryListAdapter.map( it.list )
                }
                is ResultUI.Failure -> {
                    binding.tvCategory.text = it.message
                }
            }
        }

        viewModel.init()
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