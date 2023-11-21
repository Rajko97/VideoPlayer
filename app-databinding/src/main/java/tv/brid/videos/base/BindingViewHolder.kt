package tv.brid.videos.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import tv.brid.videos.BR

class BindingViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindViewModel(viewModel: ViewModel) {
        binding.apply {
            setVariable(BR.vm, viewModel)
            executePendingBindings()
        }
    }
}