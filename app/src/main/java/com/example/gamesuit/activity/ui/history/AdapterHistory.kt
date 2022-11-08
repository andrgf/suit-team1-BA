package com.example.gamesuit.activity.ui.history

import android.icu.util.Calendar
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.databinding.ActivityItemHistoryBinding
import java.text.SimpleDateFormat

class AdapterHistory(
    private val dataPlayer: List<User>
) : RecyclerView.Adapter<AdapterHistory.DataHistoryViewHolder>() {



    inner class DataHistoryViewHolder(private val itemBinding: ActivityItemHistoryBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHistoryViewHolder {
        val itemBinding =
            ActivityItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return DataHistoryViewHolder(itemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: DataHistoryViewHolder, position: Int) {
        val user: User = dataPlayer[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int = dataPlayer.size

}
