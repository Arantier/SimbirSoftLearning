package ru.shcherbakovdv.ss.trainee.main_fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_category_screen.view.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.CategoryActivity
import ru.shcherbakovdv.ss.trainee.data_classes.Category
import ru.shcherbakovdv.ss.trainee.main_fragments.categories_list.CategoriesListAdapter
import ru.shcherbakovdv.ss.trainee.main_fragments.categories_list.CategoriesListMvpView
import ru.shcherbakovdv.ss.trainee.main_fragments.categories_list.CategoriesListPresenter
import ru.shcherbakovdv.ss.trainee.main_fragments.categories_list.OnCategoryClickListener
import ru.shcherbakovdv.ss.trainee.utilites.getClassIntent
import ru.shcherbakovdv.ss.trainee.utilites.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.makeVisible


class CategoriesListFragment : MvpAppCompatFragment(), CategoriesListMvpView, OnCategoryClickListener {

    @InjectPresenter
    lateinit var presenter: CategoriesListPresenter

    private lateinit var fragmentView: View

    override fun onCategoryClick(id: Int, name: String) = startCategoryActivity(id, name)

    override fun setLoadingState() {
        fragmentView.apply{
            progressBar.makeVisible()
            recyclerCategories.makeGone()
            imageError.makeGone()
            textError.makeGone()
        }
    }

    override fun setErrorState() {
        fragmentView.apply{
            recyclerCategories.makeGone()
            progressBar.makeGone()
            imageError.makeVisible()
            textError.makeVisible()
        }
    }

    override fun updateList(categories: Array<Category>) {
        fragmentView.apply {
            if (recyclerCategories.visibility == View.GONE) {
                recyclerCategories.makeVisible()
                progressBar.makeGone()
                imageError.makeGone()
                textError.makeGone()
            }
            recyclerCategories.adapter = CategoriesListAdapter(categories, this@CategoriesListFragment)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_category_screen, container, false)
        return fragmentView
    }

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
        val TAG = CategoriesListFragment::class.simpleName

        @JvmStatic
        fun newInstance() = CategoriesListFragment()
    }
}
