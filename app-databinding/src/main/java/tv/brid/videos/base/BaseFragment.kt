package tv.brid.videos.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import tv.brid.videos.BR

abstract class BaseFragment<BindingT : ViewDataBinding, ViewModelT : ViewModel> : Fragment() {

    lateinit var binding: BindingT
        private set

    lateinit var viewModel: ViewModelT
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = provideViewModel()
        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.vm, viewModel)
        }
        setupUi()
        subscribeObservers()
        return binding.root
    }

    open fun setupUi() { }

    open fun subscribeObservers() { }

    private fun provideViewModel() = ViewModelProvider(this)[provideViewModelClass()]

    /**
     * Provides [ViewModelT] class so that the viewmodel can be generated.
     */

    abstract fun provideViewModelClass(): Class<ViewModelT>

    /**
     * Provides layout id to be inflated.
     */
    @LayoutRes
    abstract fun provideLayoutId(): Int


}