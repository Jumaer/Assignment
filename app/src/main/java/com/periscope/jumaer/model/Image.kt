package com.periscope.jumaer.model

import android.graphics.drawable.Drawable

data class ImageItem(val id : Int , val image : Drawable , val isSelected : Boolean = false)
data class DotColor(val id : Int , val colorPosition : Int)

