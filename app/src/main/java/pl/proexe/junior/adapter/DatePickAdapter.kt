package pl.proexe.junior.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.proexe.junior.R
import pl.proexe.junior.databinding.ItemDateBinding
import pl.proexe.junior.model.data.DayTile
import pl.proexe.junior.view.epg.EpgUtils.formatDate
import pl.proexe.junior.view.epg.EpgUtils.pressedButton

class DatePickAdapter(
    private val onItemClickListener: OnItemClickListener
) : ListAdapter<DayTile, DatePickHolder>(DiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatePickHolder {
        return DatePickHolder(
            ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context,
            onItemClickListener
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DatePickHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUtilItemCallback : DiffUtil.ItemCallback<DayTile>() {
    override fun areItemsTheSame(oldItem: DayTile, newItem: DayTile): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DayTile, newItem: DayTile): Boolean {
        return oldItem.dayLabel == newItem.dayLabel && oldItem.timestamp == newItem.timestamp
    }

}

interface OnItemClickListener{
    fun onClick(dayTile: DayTile)
}


class DatePickHolder(
    private val binding: ItemDateBinding,
    private val context: Context,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(item: DayTile) {
        when (item.dayLabel) {
            R.string.today -> {
                binding.tvDate.text = context.getString(R.string.today)
            }
            R.string.yesterday -> {
                binding.tvDate.text = context.getString(R.string.yesterday)
            }
            R.string.day_before_yesterday -> {
                binding.tvDate.text = context.getString(R.string.day_before_yesterday)
            }
            R.string.tomorrow -> {
                binding.tvDate.text = context.getString(R.string.tomorrow)
            }
            R.string.day_after_tomorrow -> {
                binding.tvDate.text = context.getString(R.string.day_after_tomorrow)
            }
            else -> {
                binding.tvDate.text = formatDate(item.timestamp).toString()
            }

        }

        binding.tvDate.setOnClickListener {
            pressedButton(binding.tvDate)
            onItemClickListener.onClick(item)
        }

    }






}