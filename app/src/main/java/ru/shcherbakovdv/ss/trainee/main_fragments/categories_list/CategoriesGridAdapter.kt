package ru.shcherbakovdv.ss.trainee.main_fragments.categories_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data_classes.Category
import ru.shcherbakovdv.ss.trainee.data_classes.representations.CategoryViewHolder
import ru.shcherbakovdv.ss.trainee.data_classes.representations.OnCategoryClickListener

class CategoriesGridAdapter(private val categories: Array<Category>,
                            private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, state: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_list, parent, false)
        return CategoryViewHolder(view, onCategoryClickListener)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) =
            viewHolder.bind(categories[position])

}