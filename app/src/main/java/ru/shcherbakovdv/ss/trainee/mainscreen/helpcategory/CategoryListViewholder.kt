package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_category_list.view.*
import ru.shcherbakovdv.ss.trainee.dataclasses.Category
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider

class CategoryListViewholder (val view : View, private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(category: Category){
        view.text.text = category.name
        ImageProvider.loadImage(category.pictureUrl, view.image)
        view.setOnClickListener {
            onCategoryClickListener.onCategoryClick(category.id, category.name)
        }
    }
}