package com.core.base

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.core.di.CoreComponent
import com.core.di.DaggerCoreComponent
import com.core.dto.NetworkState

abstract class ActivityParent<T : BaseViewModel, E : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModel: T

    lateinit var dataBinding: E

    abstract fun getResourceLayoutId(): Int

    abstract fun getViewModelClass(): Class<T>

    abstract fun getFactory(): ViewModelProvider.Factory

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        dataBinding = DataBindingUtil.setContentView(this, getResourceLayoutId())
        viewModel = ViewModelProviders.of(this, getFactory()).get(getViewModelClass())
        viewModel.getNetworkStatus().observe(this, Observer {
            when (it) {
                NetworkState.LOADED -> hideProgress()
                NetworkState.LOADING -> showProgress()
                else -> showError(it.msg ?: getString(it.event))
            }
        })
        lifecycle.addObserver(viewModel)
        viewModel.onCreated()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    open fun initToolbar(toolbar: Toolbar, title: String? = null) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title ?: ""
    }

    open fun showProgress() {}
    open fun hideProgress() {}
    open fun showError(error: String) {}

    protected fun getCoreComponent(): CoreComponent {
        return DaggerCoreComponent.create()
    }

}