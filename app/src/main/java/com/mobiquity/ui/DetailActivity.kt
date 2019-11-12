package com.mobiquity.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.base.ActivityParent
import com.core.dto.ProductModel
import com.mobiquity.IntentManager
import com.mobiquity.R
import com.mobiquity.databinding.ActivityDetailBinding
import com.mobiquity.viewModel.DetailViewModel
import com.mobiquity.viewModel.DetailViewModelImpl
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : ActivityParent<DetailViewModel, ActivityDetailBinding>() {

    var product : ProductModel? = null

    override fun getResourceLayoutId(): Int = R.layout.activity_detail

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun getFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory(){
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailViewModelImpl() as T
            }
        }
    }


    /**
     * There is no need to inject in this activity
     */
    override fun inject() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.hasExtra(IntentManager.Product_KEY)){
            intent.getParcelableExtra<ProductModel>(IntentManager.Product_KEY)?.let {
                dataBinding.product = it
            }
        }
        initToolbar(toolbar, product?.name ?: "")
    }
}
