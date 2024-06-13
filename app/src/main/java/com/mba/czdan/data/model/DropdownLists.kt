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

val timePeriodList = listOf(
    TimePeriod(displayText = "1", iconRes = null),
    TimePeriod(displayText = "2", iconRes = null),
    TimePeriod(displayText = "3", iconRes = null),
    TimePeriod(displayText = "4", iconRes = null),
    TimePeriod(displayText = "5", iconRes = null),
    TimePeriod(displayText = "6", iconRes = null),
    TimePeriod(displayText = "7", iconRes = null),
    TimePeriod(displayText = "8", iconRes = null),
    TimePeriod(displayText = "9", iconRes = null),
    TimePeriod(displayText = "10", iconRes = null),
    TimePeriod(displayText = "11", iconRes = null),
    TimePeriod(displayText = "12", iconRes = null),
    TimePeriod(displayText = "13", iconRes = null),
    TimePeriod(displayText = "14", iconRes = null),
    TimePeriod(displayText = "15", iconRes = null),
    TimePeriod(displayText = "16", iconRes = null),
    TimePeriod(displayText = "17", iconRes = null),
    TimePeriod(displayText = "18", iconRes = null),
    TimePeriod(displayText = "19", iconRes = null),
    TimePeriod(displayText = "20", iconRes = null),
    TimePeriod(displayText = "21", iconRes = null),
    TimePeriod(displayText = "22", iconRes = null),
    TimePeriod(displayText = "23", iconRes = null),
    TimePeriod(displayText = "24", iconRes = null),
)
