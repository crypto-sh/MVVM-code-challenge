package com.mobiquity.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.base.ActivityParent
import com.mobiquity.IntentManager
import com.mobiquity.R
import com.mobiquity.adapter.MainPagerAdapter
import com.mobiquity.databinding.ActivityMainBinding
import com.mobiquity.di.DaggerAppComponent
import com.mobiquity.viewModel.MainViewModel
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainActivity : ActivityParent<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun getResourceLayoutId(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun getFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return mainViewModel as T
            }
        }
    }

    override fun inject() {
        DaggerAppComponent.builder()
            .coreComponent(getCoreComponent())
            .build()
            .inject(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDataResponse().observe(this, Observer {
            val adapter = MainPagerAdapter(supportFragmentManager, it)
            dataBinding.adapter = adapter
        })
        viewModel.getProductSelected().observe(this, Observer {
            IntentManager.startDetailActivity(WeakReference(this), it)
        })
        viewModel.requestList()
    }

    override fun showProgress() {
        super.showProgress()
        dataBinding.showingProgress = true
    }

    override fun hideProgress() {
        super.hideProgress()
        dataBinding.showingProgress = false
    }

    override fun showError(error: String) {
        super.showError(error)
        dataBinding.showingError    = error
    }
}
