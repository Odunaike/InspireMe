package com.example.inspireme.collections

import com.example.inspireme.R
import com.example.inspireme.models.HomeCategoryModel

val homeCategoryList: List<HomeCategoryModel> = listOf(
    HomeCategoryModel(
        imageID = R.drawable.kanye_west,
        categoryName = R.string.kanye_west_quotes
    ),
    HomeCategoryModel(
        imageID = R.drawable.bible_img,
        categoryName = R.string.bible_verses
    ),
    HomeCategoryModel(
        imageID = R.drawable.anime_image,
        categoryName = R.string.anime_quotes
    ),
    HomeCategoryModel(
        imageID = R.drawable.inspo_one,
        categoryName = R.string.grinding_quotes
    ),
    HomeCategoryModel(
        imageID = R.drawable.james_clear,
        categoryName = R.string.james_clear_quotes
    )
)