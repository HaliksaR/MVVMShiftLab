package ru.shiftlab.mvvmshiftlab.vacancy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy
import ru.shiftlab.mvvmshiftlab.databinding.VacancyItemBinding

class VacancyAdapter(val clickListener: VacancyListener) : ListAdapter<Vacancy, VacancyAdapter.ViewHolder>(
    VacancyDiffCallback()
) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }


    class ViewHolder private constructor(private val binding: VacancyItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Vacancy, clickListener: VacancyListener){
            binding.vacancy = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VacancyItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }

    }




}

class VacancyDiffCallback : DiffUtil.ItemCallback<Vacancy>() {

    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }

}

class VacancyListener(val clickListener: (vacancyId: Int) -> Unit) {
    fun onClick(vacancy: Vacancy) = clickListener(vacancy.id!!)
}