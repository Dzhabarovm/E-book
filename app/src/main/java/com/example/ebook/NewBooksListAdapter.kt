package com.example.ebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ebook.databinding.NewestBookHolderBinding

class NewBooksListAdapter : ListAdapter<Book, NewBooksListAdapter.NewBooksViewHolder>
    (BookDiffUtil()) {

    var onClickItem: ((Long) -> Unit)? = null
    var onMarkedItem: ((Long) -> Unit)? = null

    inner class NewBooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = NewestBookHolderBinding.bind(view)

        fun bind(item: Book) {
            binding.newBookCover.setImageResource(item.cover ?: R.drawable.ic_book_template)
            binding.newBookTitle.text = item.title
            binding.newBookAuthor.text = item.author
            binding.ratingBar.rating = item.rating.toFloat()
            binding.bookMark.setImageResource(
                if (item.marked)
                    R.drawable.ic_bookmark_true
                else
                    R.drawable.ic_bookmark
            )
        }

        init {
            binding.bookMark.setOnClickListener {
                onMarkedItem?.invoke(getItem(adapterPosition).id)
            }
            binding.root.setOnClickListener {
                onClickItem?.invoke(getItem(adapterPosition).id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewBooksViewHolder {
        return NewBooksViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.newest_book_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewBooksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}