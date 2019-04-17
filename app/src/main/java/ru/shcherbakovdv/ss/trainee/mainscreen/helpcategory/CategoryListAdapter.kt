package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.Category

class CategoryListAdapter(private val categories: Array<Category>,
                          private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoryListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, state: Int): CategoryListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_list, parent, false)
        return CategoryListViewHolder(view, onCategoryClickListener)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(viewHolder: CategoryListViewHolder, position: Int) =
            viewHolder.bind(categories[position])

}