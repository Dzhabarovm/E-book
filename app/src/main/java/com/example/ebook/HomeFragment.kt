package com.example.ebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ebook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ViewModel

    private val popularBooksListAdapter = PopularBooksListAdapter()
    private val newBooksListAdapter = NewBooksListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        binding.popularBookRv.adapter = popularBooksListAdapter
        binding.newBookRV.adapter = newBooksListAdapter

        viewModel.getAll.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                viewModel.addBooks(Data.books)
            }
        }

        viewModel.getPopular.observe(viewLifecycleOwner) {
            popularBooksListAdapter.submitList(it)
        }

        viewModel.getNewBooks.observe(viewLifecycleOwner) {
            newBooksListAdapter.submitList(it)
        }

        newBooksListAdapter.onMarkedItem = {
            viewModel.updateMark(it)
        }

        val onItemClicked: (Long) -> (Unit) = {
            val action = HomeFragmentDirections.actionHomeToDetailFragment(it)
            findNavController().navigate(action)
        }
        newBooksListAdapter.onClickItem = onItemClicked
        popularBooksListAdapter.onClickItem = onItemClicked

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}