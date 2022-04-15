package tribore.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tribore.rickandmorty.R
import tribore.rickandmorty.databinding.ItemForRecyclerBinding
import tribore.rickandmorty.domain.models.PersonDomainModel

class MainAdapter(private val onClickForInfo: (PersonDomainModel) -> Unit) :
    ListAdapter<PersonDomainModel, MainAdapter.MainViewHolder>(DiffCallback) {


    inner class MainViewHolder(private val binding: ItemForRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PersonDomainModel) {
            binding.tvName.text = item.name
            binding.tvGender.text = item.gender
            binding.tvRace.text = item.race

            Glide
                .with(itemView)
                .load(item.urlAvatar)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .fallback(R.drawable.ic_placeholder)
                .centerCrop()
                .into(binding.imageAvatar)
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<PersonDomainModel>() {
        override fun areItemsTheSame(
            oldItem: PersonDomainModel,
            newItem: PersonDomainModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PersonDomainModel,
            newItem: PersonDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(
            ItemForRecyclerBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}