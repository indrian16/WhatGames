package io.indrian.core.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.indrian.core.R
import io.indrian.core.databinding.GenreItemLayoutBinding
import io.indrian.core.domain.model.Genre

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private var genres: List<Genre> = arrayListOf()

    fun add(genres: List<Genre>?) {
        if (genres?.isNotEmpty() == true) {
            this.genres = genres
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GenreItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int = genres.size

    inner class ViewHolder(private val itemBinding: GenreItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(genre: Genre) {
            with(itemBinding) {
                tvGenre.text = root.context.getString(R.string.display_genre, genre.name)
            }
        }
    }
}