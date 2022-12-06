package com.melyseev.cocktails2023.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.databinding.FragmentCocktailsListBinding
import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.presentation.communications.Communications
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications

class CocktailsListFragment : Fragment() {

    private var _binding: FragmentCocktailsListBinding? = null
    private val binding: FragmentCocktailsListBinding
        get() = _binding ?: throw RuntimeException("Object FragmentCocktailsListBinding is null")



    private val progressCommunication = Communications.ProgressCommunication.Base()
    private val stateCommunication = Communications.SubcategoryStateCommunication.Base()
    private val listCommunication = Communications.SubcategoryListCommunication.Base()

    val communications = SubcategoryCommunications.Base(progressCommunication, stateCommunication, listCommunication)
    val interactor = CocktailsInteractor.Base()


    private val viewModel  = CocktailsListViewModel(communications= communications, interactor = interactor)




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailsListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val subcategoryListAdapter = SubcategoryListAdapter()
        binding.recyclerViewSubcategory.layoutManager  = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL ,false)
        binding.recyclerViewSubcategory.adapter = subcategoryListAdapter

        binding.tvCategory.text = "Non alcoholic"


        viewModel.observeProgress(this){
            binding.progress.visibility = it
        }


        viewModel.observeState(this){

        }

        viewModel.observeList(this){
            subcategoryListAdapter.map(it)
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