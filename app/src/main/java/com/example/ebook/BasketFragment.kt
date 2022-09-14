package com.example.ebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.ebook.databinding.FragmentBasketBinding
import com.example.ebook.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketFragment : Fragment() {
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val args : BasketFragmentArgs by navArgs()
        val bookId = args.bookId

        lifecycleScope.launch(Dispatchers.IO) {
            val book = viewModel.getBook(bookId)
            binding.bookCover.setImageResource(book.cover ?: R.drawable.ic_book_template)
            binding.bookTitle.text = book.title
            binding.bookAuthor.text = book.author

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}