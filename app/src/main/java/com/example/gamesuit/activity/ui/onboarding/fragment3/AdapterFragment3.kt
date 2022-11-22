package com.example.gamesuit.activity.ui.onboarding.fragment3

import android.icu.util.Calendar
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.databinding.ActivityHistoryBinding
import com.example.gamesuit.databinding.ActivityItemHistoryBinding
import com.example.gamesuit.databinding.ActivityItemTopscoreBinding
import java.text.SimpleDateFormat

class AdapterFragment3(
    private val dataPlayer: List<User>
) : RecyclerView.Adapter<AdapterFragment3.DataLeaderboardFregment3Holder>() {



    inner class DataLeaderboardFregment3Holder(private val itemBinding: ActivityItemHistoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @RequiresApi(Build.VERSION_CODES.N)
        fun bindView(dataPlayer: User) {
            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
            val formatedDate = formatter.format(date)

            itemBinding.namaPlayer.text = dataPlayer.username
            itemBinding.AvatarPlayer.setImageResource(dataPlayer.avatarId)
            itemBinding.date.text = formatedDate

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataLeaderboardFregment3Holder {
        val itemBinding =
            ActivityItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false  )
        return DataLeaderboardFregment3Holder(itemBinding)
    }


    override fun getItemCount(): Int = dataPlayer.size



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: DataLeaderboardFregment3Holder, position: Int) {
        val user: User = dataPlayer[position]
        holder.bindView(user)
    }

}






