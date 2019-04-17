package ru.shcherbakovdv.ss.trainee.main_fragments.categories_list

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_category_list.view.*
import ru.shcherbakovdv.ss.trainee.data_classes.Category
import ru.shcherbakovdv.ss.trainee.data_providers.ImageProvider

class CategoriesListViewHolder (val view : View, private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(category: Category){
        view.text.text = category.name
        ImageProvider.loadImage(category.pictureUrl, view.image)
        view.setOnClickListener {
            onCategoryClickListener.onCategoryClick(category.id, category.name)
        }
    }
}