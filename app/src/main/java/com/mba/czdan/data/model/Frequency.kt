package com.mba.czdan.data.model
import com.mba.czdan.R

data class Frequency(val category: String, val icon: Int)

val frequencyList = listOf(
    Frequency(category = "Tek Seferlik", icon = R.drawable.ic_launcher_foreground),
    Frequency(category = "Haftalık", icon = R.drawable.ic_launcher_foreground),
    Frequency(category = "Aylık", icon = R.drawable.ic_launcher_foreground),
    Frequency(category = "Yıllık", icon = R.drawable.ic_launcher_foreground),
)
