package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_category_screen.view.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.categoryscreen.CategoryActivity
import ru.shcherbakovdv.ss.trainee.dataclasses.Category
import ru.shcherbakovdv.ss.trainee.mainscreen.getClassIntent
import ru.shcherbakovdv.ss.trainee.utilites.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.makeVisible


class CategoryFragment : MvpAppCompatFragment(), CategoryMvpView, OnCategoryClickListener {

    @InjectPresenter
    lateinit var presenter: CategoryPresenter

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
            recyclerCategories.adapter = CategoryListAdapter(categories, this@CategoryFragment)
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
        val TAG = CategoryFragment::class.simpleName

        @JvmStatic
        fun newInstance() = CategoryFragment()
    }
}
