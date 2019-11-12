package com.core.base


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.core.di.CoreComponent
import com.core.di.CoreModule
import com.core.di.DaggerCoreComponent
import com.core.dto.NetworkState


abstract class FragmentParentShared<T : BaseViewModel, E : ViewDataBinding>  : Fragment(){

    lateinit var viewModel  : T

    lateinit var dataBinding: E

    abstract fun getResourceLayoutId() : Int

    abstract fun getViewModelClass(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity!!.run {
            viewModel= ViewModelProviders.of(this).get(getViewModelClass())
            viewModel.getNetworkStatus().observe(this, Observer {
                when(it){
                    NetworkState.LOADED -> hideProgress()
                    NetworkState.LOADING -> showProgress()
                    else -> showError(it.msg)
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),getResourceLayoutId(), container, false)
        lifecycle.addObserver(viewModel)
        viewModel.onCreated()
        return dataBinding.root
    }

    fun hideKeyboard(view: View) {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    open fun showProgress(){}
    open fun hideProgress(){}
    open fun showError(error : String?){}

    protected fun getCoreComponent() : CoreComponent {
        return DaggerCoreComponent.create()
    }

}