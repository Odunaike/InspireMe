package com.example.inspireme.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HomeCategoryModel (
    @DrawableRes val imageID : Int,
    @StringRes val categoryName: Int
    )