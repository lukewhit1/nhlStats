package com.example.nhlstatsapp.view.adapter

import android.R.id.button1
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nhlstatsapp.databinding.PlayerListStatsItemBinding
import com.example.nhlstatsapp.model.PlayerDisplayStats
import com.example.nhlstatsapp.view.fragment.PopupFragment
import com.google.android.material.internal.ContextUtils.getActivity


class SinglePlayerStatsAdapter(
    private val statsByPlayer: MutableList<PlayerDisplayStats> = mutableListOf(),
    private val openDetails: (PlayerDisplayStats) -> Unit
): RecyclerView.Adapter<SinglePlayerStatsAdapter.SinglePlayerStatsViewHolder>() {

    fun setPlayerList(newList: List<PlayerDisplayStats>) {
        statsByPlayer.clear()
        statsByPlayer.addAll(newList)
        notifyItemRangeChanged(0, itemCount)
    }

    inner class SinglePlayerStatsViewHolder(
        private val binding: PlayerListStatsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlayerDisplayStats) {
            binding.tvPlayerName.text = data.fullName
            binding.tvTeamName.text = data.teamName
            binding.tvStatistics.text = "${data.goals}G   ${data.assists}A   ${data.points}P"

            Glide.with(binding.ivListImage)
                .load(data.photograph)
                .into(binding.ivListImage)

            binding.root.setOnClickListener {
                //println("     card clicked")
                openDetails(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePlayerStatsViewHolder {
        return SinglePlayerStatsViewHolder(
            PlayerListStatsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SinglePlayerStatsViewHolder, position: Int) {
        holder.onBind(statsByPlayer[position])
    }

    override fun getItemCount() = statsByPlayer.size
}