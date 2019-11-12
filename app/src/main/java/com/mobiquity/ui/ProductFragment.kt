package com.mobiquity.ui


import android.os.Bundle
import android.view.View
import com.core.base.FragmentParentShared
import com.core.dto.CategoryModel
import com.mobiquity.R
import com.mobiquity.adapter.MainListAdapter
import com.mobiquity.databinding.FragmentProductBinding
import com.mobiquity.viewModel.MainViewModel


/**
 * A simple [FragmentParentShared] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : FragmentParentShared<MainViewModel, FragmentProductBinding>() {

    val adapter: MainListAdapter by lazy {
        MainListAdapter {
            viewModel.onProductSelected(it)
        }
    }

    private var category: CategoryModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getParcelable(ARG_CATEGORY)
        }
    }

    override fun getResourceLayoutId(): Int = R.layout.fragment_product

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.adapter = adapter
        category?.let {
            adapter.submitList(it.products)
        }
    }

    companion object {

        const val ARG_CATEGORY = "category"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param category is the base data for this activity.
         *
         * @return A new instance of fragment ProductFragment.
         */
        @JvmStatic
        fun newInstance(category: CategoryModel) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CATEGORY, category)
                }
            }
    }
}
