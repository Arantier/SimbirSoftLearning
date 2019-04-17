package ru.shcherbakovdv.ss.trainee.main_fragments.categories_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data_classes.Category

class CategoriesListAdapter(private val categories: Array<Category>,
                            private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoriesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, state: Int): CategoriesListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_list, parent, false)
        return CategoriesListViewHolder(view, onCategoryClickListener)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(viewHolder: CategoriesListViewHolder, position: Int) =
            viewHolder.bind(categories[position])

}