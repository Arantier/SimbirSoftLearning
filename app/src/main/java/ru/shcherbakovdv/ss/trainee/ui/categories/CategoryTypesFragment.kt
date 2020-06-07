package ru.shcherbakovdv.ss.trainee.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_category_screen.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.shcherbakovdv.ss.trainee.CategoryPageActivity
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Category
import ru.shcherbakovdv.ss.trainee.data.OnCategoryClickListener
import ru.shcherbakovdv.ss.trainee.utilites.Logger
import ru.shcherbakovdv.ss.trainee.utilites.extensions.getClassIntent
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible


class CategoryTypesFragment : MvpAppCompatFragment(R.layout.fragment_category_screen),
    CategoryTypesMvpView,
    OnCategoryClickListener {

    private val presenter by moxyPresenter { CategoriesTypesPresenter() }

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_category_screen, container, false)

    override fun onStart() {
        super.onStart()
        presenter.requestCategories()
    }

    private fun startCategoryActivity(id: Int, name: String) {
        context?.let { context ->
            val intent = context.getClassIntent<CategoryPageActivity>().apply {
                putExtra(CategoryPageActivity.CATEGORY_ID, id)
                putExtra(CategoryPageActivity.CATEGORY_NAME, name)
            }
            startActivity(intent)
        }
        if (context == null) {
            Logger.flatError("Context is null in CategoryTypesFragment")
        }
    }

    companion object {
        @JvmStatic
        val TAG = CategoryTypesFragment::class.simpleName

        @JvmStatic
        fun newInstance() = CategoryTypesFragment()
    }
}
