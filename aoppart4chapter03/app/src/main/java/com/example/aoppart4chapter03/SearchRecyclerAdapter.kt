package com.example.aoppart4chapter03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aoppart4chapter03.databinding.ViewholderSearchResultItemBinding

class SearchRecyclerAdapter :
    RecyclerView.Adapter<SearchRecyclerAdapter.SearchResultItemViewHolder>() {

    private var searchResultList: List<Any> = listOf()
    private lateinit var searchResultClickListener: (Any) -> Unit

    class SearchResultItemViewHolder(
        val binding: ViewholderSearchResultItemBinding,
        val searchResultClickListener: (Any) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Any) = with(binding) {
            textTextView.text = "제목"
            subtextTextView.text = "부제목"
        }

        fun bindViews(data: Any) {
            binding.root.setOnClickListener {
                searchResultClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder {
        val view = ViewholderSearchResultItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchResultItemViewHolder(view, searchResultClickListener)
    }

    override fun onBindViewHolder(holder: SearchResultItemViewHolder, position: Int) {
        holder.bindData(Any())
        holder.bindViews(Any())
    }

    override fun getItemCount(): Int = 10

    fun setSearchResultListener(searchResultList: List<Any>, searchResultClickListener: (Any) -> Unit) {
        this.searchResultList = searchResultList
        this.searchResultClickListener = searchResultClickListener
    }
}