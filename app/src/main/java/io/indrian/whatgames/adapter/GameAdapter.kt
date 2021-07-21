package io.indrian.whatgames.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.indrian.whatgames.databinding.GameItemLayoutBinding
import io.indrian.whatgames.ui.detail.DetailActivity

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
        holder.bind()
    }

    override fun getItemCount(): Int = 10

    inner class ViewHolder(private val itemBinding: GameItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {
            with(itemBinding) {
                cardGame.setOnClickListener {
                    val intent = Intent(itemBinding.root.context, DetailActivity::class.java)
                    itemBinding.root.context.startActivity(intent)
                }
            }
        }
    }
}