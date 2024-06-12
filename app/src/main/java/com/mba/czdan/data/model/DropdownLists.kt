package com.mba.czdan.data.model
import com.mba.czdan.R


interface DropdownMenuItemInterface {
    val displayText: String
    val iconRes: Int?
}

data class IconCategory(
    override val displayText: String,
    override val iconRes: Int?
) : DropdownMenuItemInterface

val iconCategoryList = listOf(
    IconCategory(displayText = "Maaş", iconRes = R.drawable.ic_launcher_background),
    IconCategory(displayText = "Fatura", iconRes = R.drawable.ic_launcher_background),
    IconCategory(displayText = "Diğer", iconRes = R.drawable.ic_launcher_background),
)

data class Frequency(
    override val displayText: String,
    override val iconRes: Int?
) : DropdownMenuItemInterface


val frequencyList = listOf(
    Frequency(displayText = "Tek Seferlik", iconRes = R.drawable.ic_launcher_foreground),
    Frequency(displayText = "Haftalık", iconRes = R.drawable.ic_launcher_foreground),
    Frequency(displayText = "Aylık", iconRes = R.drawable.ic_launcher_foreground),
    Frequency(displayText = "Yıllık", iconRes = R.drawable.ic_launcher_foreground),
)


data class TimePeriod(
    override val displayText: String,
    override val iconRes: Int?
) : DropdownMenuItemInterface
