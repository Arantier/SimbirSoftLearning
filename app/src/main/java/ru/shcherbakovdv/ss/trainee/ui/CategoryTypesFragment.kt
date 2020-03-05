package ru.shcherbakovdv.ss.trainee.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_category_screen.*
import kotlinx.android.synthetic.main.fragment_category_screen.view.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.CategoryActivity
import ru.shcherbakovdv.ss.trainee.data.Category
import ru.shcherbakovdv.ss.trainee.ui.categories.CategoriesGridAdapter
import ru.shcherbakovdv.ss.trainee.ui.categories.CategoryTypesMvpView
import ru.shcherbakovdv.ss.trainee.ui.categories.CategoriesTypesPresenter
import ru.shcherbakovdv.ss.trainee.data.OnCategoryClickListener
import ru.shcherbakovdv.ss.trainee.utilites.extensions.getClassIntent
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible


class CategoryTypesFragment : MvpAppCompatFragment(), CategoryTypesMvpView, OnCategoryClickListener {

    @InjectPresenter
    lateinit var presenter: CategoriesTypesPresenter

    override fun onCategoryClick(id: Int, name: String) = startCategoryActivity(id, name)

    override fun setLoadingState() {
        progressBar.makeVisible()
        recyclerCategories.makeGone()
        imageError.makeGone()
        textError.makeGone()
    }

    override fun setErrorState() {
        recyclerCategories.makeGone()
        progressBar.makeGone()
        imageError.makeVisible()
        textError.makeVisible()
    }

    override fun updateList(categories: Array<Category>) {
        if (recyclerCategories.visibility == View.GONE) {
            recyclerCategories.makeVisible()
            progressBar.makeGone()
            imageError.makeGone()
            textError.makeGone()
        }
        recyclerCategories.adapter = CategoriesGridAdapter(categories, this@CategoryTypesFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_category_screen, container, false)

    override fun onStart() {
        super.onStart()
        val storageAvailable = ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if (storageAvailable) {
            presenter.requestCategories()
        } else {
            setErrorState()
        }
    }

    private fun startCategoryActivity(id: Int, name: String) {
        val intent = context!!.getClassIntent<CategoryActivity>().apply {
            putExtra(CategoryActivity.CATEGORY_ID, id)
            putExtra(CategoryActivity.CATEGORY_NAME, name)
        }
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        val TAG = CategoryTypesFragment::class.simpleName

        @JvmStatic
        fun newInstance() = CategoryTypesFragment()
    }
}
