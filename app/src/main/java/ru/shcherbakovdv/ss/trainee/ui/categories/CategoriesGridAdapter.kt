package ru.shcherbakovdv.ss.trainee.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Category
import ru.shcherbakovdv.ss.trainee.data.CategoryViewHolder
import ru.shcherbakovdv.ss.trainee.data.OnCategoryClickListener

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