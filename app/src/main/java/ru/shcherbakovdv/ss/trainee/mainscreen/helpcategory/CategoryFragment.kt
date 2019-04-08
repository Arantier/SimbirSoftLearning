package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_category_screen.*
import kotlinx.android.synthetic.main.fragment_category_screen.view.*
import ru.shcherbakovdv.ss.trainee.HelpCategoryActivity
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.Category
import ru.shcherbakovdv.ss.trainee.utilites.Logger
import java.io.File
import java.io.FileReader
import io.reactivex.Flowable


class CategoryFragment : MvpAppCompatFragment(), CategoryMvpView, OnCategoryClickListener {

    @InjectPresenter
    lateinit var presenter: CategoryPresenter

    lateinit var fragmentView: View

    override fun onCategoryClick(id: Int) {
        startCategoryActivity(id)
    }

    override fun setLoadingState() {
        if (fragmentView.progressBar.visibility == View.GONE) {
            fragmentView.recyclerCategories.visibility = View.GONE
            fragmentView.progressBar.visibility = View.VISIBLE
            fragmentView.imageError.visibility = View.GONE
            fragmentView.textError.visibility = View.GONE
        }
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
        presenter.requestCategories()
        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        recyclerCategories.layoutManager = GridLayoutManager(context, 2)
    }

    fun startCategoryActivity(id: Int) {
        val intent = Intent(context, HelpCategoryActivity::class.java).apply {
            putExtra(HelpCategoryActivity.CATEGORY_ID, id)
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
