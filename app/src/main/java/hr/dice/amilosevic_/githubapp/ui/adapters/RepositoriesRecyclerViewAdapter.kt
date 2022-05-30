package hr.dice.amilosevic_.githubapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import hr.dice.amilosevic_.githubapp.R
import hr.dice.amilosevic_.githubapp.databinding.ItemRepositoryBinding
import hr.dice.amilosevic_.githubapp.helpers.formatThousands
import hr.dice.amilosevic_.githubapp.models.Repository

class RepositoriesRecyclerViewAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Repository, RepositoriesRecyclerViewAdapter.RepositoryViewHolder>(COMPARATOR) {

    companion object{
        val COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onItemClickListener.onItemClick(getItem(position)) }
    }

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository) {
            binding.mtvFullName.text = repository.fullName
            binding.mtvDescription.text = repository.description
            binding.mtvStarsCount.text = formatThousands(repository.stargazersCount)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(repository: Repository)
    }
}