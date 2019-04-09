package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_category_screen.*
import kotlinx.android.synthetic.main.fragment_category_screen.view.*
import ru.shcherbakovdv.ss.trainee.categoryscreen.CategoryActivity
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.Category


class CategoryFragment : MvpAppCompatFragment(), CategoryMvpView, OnCategoryClickListener {

    @InjectPresenter
    lateinit var presenter: CategoryPresenter

    private lateinit var fragmentView: View

    override fun onCategoryClick(id: Int, name: String) {
        startCategoryActivity(id, name)
    }

    override fun setLoadingState() {
        fragmentView.recyclerCategories.visibility = View.GONE
        fragmentView.progressBar.visibility = View.VISIBLE
        fragmentView.imageError.visibility = View.GONE
        fragmentView.textError.visibility = View.GONE
    }

    override fun setErrorState() {
        fragmentView.recyclerCategories.visibility = View.GONE
        fragmentView.progressBar.visibility = View.GONE
        fragmentView.imageError.visibility = View.VISIBLE
        fragmentView.textError.visibility = View.VISIBLE
    }

    override fun updateList(categories: Array<Category>) {
        if (fragmentView.recyclerCategories.visibility == View.GONE) {
            fragmentView.recyclerCategories.visibility = View.VISIBLE
            fragmentView.progressBar.visibility = View.GONE
            fragmentView.imageError.visibility = View.GONE
            fragmentView.textError.visibility = View.GONE
        }
        fragmentView.recyclerCategories.adapter = CategoryListAdapter(categories, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_category_screen, container, false)
        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        recyclerCategories.layoutManager = GridLayoutManager(context, 2)
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            presenter.requestCategories()
        } else {
            setErrorState()
        }
    }

    private fun startCategoryActivity(id: Int, name: String) {
        val intent = Intent(context, CategoryActivity::class.java).apply {
            putExtra(CategoryActivity.CATEGORY_ID, id)
            putExtra(CategoryActivity.CATEGORY_NAME, name)
        }
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        val TAG = CategoryFragment::class.simpleName

        @JvmStatic
        fun newInstance(): CategoryFragment {
            return CategoryFragment()
        }
    }
}
