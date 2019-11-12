package com.mobiquity.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.core.dto.CategoryModel
import com.mobiquity.ui.ProductFragment


class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val categories : List<CategoryModel>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    /**
     * Return the Fragment associated with a specified position.
     */
    override fun getItem(position: Int): Fragment = ProductFragment.newInstance(categories[position])

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int = categories.size

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    override fun getPageTitle(position: Int): CharSequence? = categories[position].name
}