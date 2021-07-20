package io.indrian.whatgames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.indrian.whatgames.databinding.GameItemLayoutBinding

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GameItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 10

    inner class ViewHolder(itemBinding: GameItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)
}