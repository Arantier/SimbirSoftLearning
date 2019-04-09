package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.Category

class CategoryListAdapter(private val categories: Array<Category>,
                          private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoryListViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, state: Int): CategoryListViewholder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_list, parent, false)
        return CategoryListViewholder(view, onCategoryClickListener)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(viewholder: CategoryListViewholder, position: Int) {
        viewholder.bind(categories[position])
    }

}