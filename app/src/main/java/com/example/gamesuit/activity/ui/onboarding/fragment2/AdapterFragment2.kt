package com.example.gamesuit.activity.ui.onboarding.fragment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesuit.databinding.ActivityItemTopscoreBinding

class AdapterFragment2(
    private val dataPlayer: List<User>
) : RecyclerView.Adapter<AdapterFragment2.DataLeaderboardFregment2Holder>() {
    var rangking: Int = 0


    inner class DataLeaderboardFregment2Holder(private val itemBinding: ActivityItemTopscoreBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(dataPlayer: User) {
            itemBinding.tvRank.text = rangking.toString()
            itemBinding.namaPlayer.text = dataPlayer.username
            itemBinding.AvatarPlayer.setImageResource(dataPlayer.avatarId)
            itemBinding.angkamenang.text = dataPlayer.wins.toString()
            itemBinding.angkakalah.text = dataPlayer.loses.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataLeaderboardFregment2Holder {
        val itemBinding =
            ActivityItemTopscoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false  )
        return DataLeaderboardFregment2Holder(itemBinding)
    }


    override fun getItemCount(): Int = dataPlayer.size



    override fun onBindViewHolder(holder: DataLeaderboardFregment2Holder, position: Int) {
        val user: User = dataPlayer[position]
        rangking = position + 1
        holder.bindView(user)
    }

}






