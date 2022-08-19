package com.example.ebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ebook.databinding.PopularBookHolderBinding

class PopularBooksListAdapter : ListAdapter<Book, PopularBooksListAdapter.PopularBookHolder>
    (BookDiffUtil()) {

    var onClickItem: ((Long) -> (Unit))? = null
    var onMarkedItem: ((Long) -> (Unit))? = null

    inner class PopularBookHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PopularBookHolderBinding.bind(view)

        fun bind(item: Book) {
            binding.popularBookCover.setImageResource(item.cover ?: R.drawable.ic_book_template)
            binding.popularBookTitle.text = item.title
            binding.author.text = item.author
        }

        init {
            binding.root.setOnClickListener {
                onClickItem?.invoke(getItem(adapterPosition).id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularBookHolder {
        return PopularBookHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_book_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PopularBookHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BookDiffUtil : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}