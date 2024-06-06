package com.mba.czdan.data.model

import com.mba.czdan.R

data class IconCategory(val category: String, val icon: Int)

val iconCategory = listOf(
    IconCategory(category = "Maaş", icon = R.drawable.ic_launcher_background),
    IconCategory(category = "Fatura", icon = R.drawable.ic_launcher_background),
    IconCategory(category = "Diğer", icon = R.drawable.ic_launcher_background),
)
