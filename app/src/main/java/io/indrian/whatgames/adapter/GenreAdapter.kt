package io.indrian.whatgames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.indrian.whatgames.databinding.GenreItemLayoutBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

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

    }

    override fun getItemCount(): Int = 10

    inner class ViewHolder(private val itemBinding: GenreItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {
            with(itemBinding) {

            }
        }
    }
}