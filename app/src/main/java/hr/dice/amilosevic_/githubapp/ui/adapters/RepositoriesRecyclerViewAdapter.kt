package hr.dice.amilosevic_.githubapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import hr.dice.amilosevic_.githubapp.R
import hr.dice.amilosevic_.githubapp.helpers.formatThousands
import hr.dice.amilosevic_.githubapp.models.Repository

class RepositoriesRecyclerViewAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RepositoriesRecyclerViewAdapter.RepositoryViewHolder>() {

    private var repositories: ArrayList<Repository> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repository = repositories[position])
        holder.itemView.setOnClickListener { onItemClickListener.onItemClick(repositories[position]) }
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    fun addRepositories(repos: ArrayList<Repository>) {
        this.repositories.clear()
        this.repositories.addAll(repos)
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mtvFullName = itemView.findViewById<MaterialTextView>(R.id.mtv_full_name)
        private val mtvDescription = itemView.findViewById<MaterialTextView>(R.id.mtv_description)
        private val mtvStarsCount = itemView.findViewById<MaterialTextView>(R.id.mtv_stars_count)

        fun bind(repository: Repository) {
            mtvFullName.text = repository.full_name
            mtvDescription.text = repository.description
            mtvStarsCount.text = formatThousands(repository.stargazers_count)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(repository: Repository)
    }
}