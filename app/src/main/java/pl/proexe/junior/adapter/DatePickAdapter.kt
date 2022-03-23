package pl.proexe.junior.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.proexe.junior.databinding.ItemDateBinding
import pl.proexe.junior.model.data.DayTile
import java.text.FieldPosition

class DatePickAdapter: ListAdapter<DayTile, DatePickHolder>(DiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatePickHolder {
        return DatePickHolder(
           ItemDateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: DatePickHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUtilItemCallback: DiffUtil.ItemCallback<DayTile>() {
    override fun areItemsTheSame(oldItem: DayTile, newItem: DayTile): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DayTile, newItem: DayTile): Boolean {
        return oldItem.dayLabel == newItem.dayLabel && oldItem.timestamp == newItem.timestamp
    }

}


class DatePickHolder(
    private val binding: ItemDateBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(item: DayTile){
        binding.tvDate.text = item.dayLabel.toString()
    }

}