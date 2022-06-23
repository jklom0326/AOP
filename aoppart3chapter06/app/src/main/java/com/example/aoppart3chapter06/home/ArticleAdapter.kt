package com.example.aoppart3chapter06.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aoppart3chapter06.databinding.ItemArticleBinding
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter : ListAdapter<ArticleModel, ArticleAdapter.ViewHolder>(diffutil) {

    inner class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articledModel: ArticleModel) {

            val format = SimpleDateFormat("MM월 dd일")
            val date = Date(articledModel.createdAt)

            binding.titleTextView.text = articledModel.title
            binding.dateTextView.text = format.format(date).toString()
            binding.priceTextView.text = articledModel.price

            if (articledModel.imageUrl.isNotEmpty()) {
                Glide.with(binding.thumbnailImageView)
                    .load(articledModel.imageUrl)
                    .into(binding.thumbnailImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffutil = object : DiffUtil.ItemCallback<ArticleModel>() {
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.createdAt == newItem.createdAt
            }

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}