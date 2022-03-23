package pl.proexe.junior.adapter


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import pl.proexe.junior.databinding.ItemProgramBinding
import pl.proexe.junior.model.data.TvProgramme

class ProgramAdapter : ListAdapter<TvProgramme, ProgramHolder>(ProgramDiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramHolder {
        return ProgramHolder(
            ItemProgramBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProgramHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProgramDiffUtilItemCallback : DiffUtil.ItemCallback<TvProgramme>() {
    override fun areItemsTheSame(oldItem: TvProgramme, newItem: TvProgramme): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TvProgramme, newItem: TvProgramme): Boolean {
        return oldItem.type == newItem.type && oldItem.category == newItem.category
                && oldItem.title == newItem.title && oldItem.imageUrl == newItem.imageUrl
                && oldItem.startTime == newItem.startTime && oldItem.endTime == newItem.endTime
                && oldItem.isFavourite == newItem.isFavourite
                && oldItem.progressPercent == newItem.progressPercent
    }
}

class ProgramHolder(
    private val binding: ItemProgramBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: TvProgramme) {
        binding.ivProgramIcon.load(item.imageUrl)
        binding.tvName.text = item.title
        binding.tvInfo.text =
            item.startTime.toString().substring(11, 16) + "-" + item.endTime.toString()
                .substring(11, 16) + " | " + item.type

        binding.progressBar.progress = item.progressPercent
    }

    private fun dateFormatter(date: String) {


    }

}