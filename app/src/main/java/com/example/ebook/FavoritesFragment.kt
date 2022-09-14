package com.example.ebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.ebook.databinding.FragmentFavoritesBinding
import com.example.ebook.databinding.FragmentHomeBinding

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ViewModel
    private val favoritesListAdapter = FavoritesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        binding.favoritesRv.adapter = favoritesListAdapter

        viewModel.getFavorites.observe(viewLifecycleOwner) {
            favoritesListAdapter.submitList(it)
        }

        favoritesListAdapter.onMarkedItem = {
            viewModel.updateMark(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}