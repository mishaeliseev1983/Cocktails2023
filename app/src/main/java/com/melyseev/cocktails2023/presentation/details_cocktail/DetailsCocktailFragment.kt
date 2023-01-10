package com.melyseev.cocktails2023.presentation.details_cocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.databinding.FragmentDetailsCocktailBinding
import com.melyseev.cocktails2023.presentation.App
import com.melyseev.cocktails2023.presentation.activity.NavigationStrategy
import com.melyseev.cocktails2023.presentation.main.ViewModuleFactory
import javax.inject.Inject


private const val ARG_ID_COCKTAIL = "idCocktail"


class DetailsCocktailFragment : Fragment() {
    private var idCocktail: Int? = null

    private var cocktailImageUrl: String = ""
    private var cocktailTitle: String = ""

    private var _binding: FragmentDetailsCocktailBinding? = null
    private val binding: FragmentDetailsCocktailBinding
        get() = _binding ?: throw RuntimeException("Object FragmentDetailsCocktailBinding is null")

    private val daggerApplicationComponent by lazy {
        (requireActivity().application as App).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModuleFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailsCocktailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idCocktail = it.getInt(ARG_ID_COCKTAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsCocktailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daggerApplicationComponent.inject(this)

        viewModel.observeProgress(this) {
            binding.progress.visibility = it
        }

        viewModel.observeStateDetailsCocktail(this) {
            when (it) {

                is DetailsCocktailResultUI.Success -> {
                    binding.tvCocktailTitle.text = it.title
                    binding.tvCocktailIntsructions.text = it.instructions

                    cocktailImageUrl = it.image
                    cocktailTitle = it.title
                    Glide.with(requireContext())
                        .load(it.image)
                        .centerCrop()
                        .into(binding.imageView)
                }
                is DetailsCocktailResultUI.Error -> {
                    binding.tvCocktailTitle.text = it.message
                }
            }
        }

        viewModel.observeStateLikeCocktail(this) {
            when (it) {
                is CocktailFavoriteStateResultUI.Success -> {
                    if (it.isFavorite)
                        binding.imageViewFavorite.setImageResource(R.drawable.ic_favorite_red)
                    else
                        binding.imageViewFavorite.setImageResource(R.drawable.ic_favorite_grey)
                }

                is CocktailFavoriteStateResultUI.Error -> {
                    binding.tvCocktailTitle.text = it.message
                }
            }
        }

        idCocktail?.let {
            viewModel.fetchDetailsCocktail(it)
        }

        binding.imageViewFavorite.setOnClickListener {
            idCocktail?.let {
                viewModel.updateCocktailFavoriteState(it, cocktailTitle, cocktailImageUrl)
            }
        }

        binding.btnBackToCocktails.setOnClickListener {
            viewModel.navigate(NavigationStrategy.Back)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(idCocktail: Int) =
            DetailsCocktailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_COCKTAIL, idCocktail)
                }
            }
    }
}