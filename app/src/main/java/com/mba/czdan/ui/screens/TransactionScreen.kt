package com.mba.czdan.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mba.czdan.data.model.frequencyList
import com.mba.czdan.data.model.iconCategoryList
import com.mba.czdan.ui.theme.topBarGradient
import com.mba.czdan.ui.viewmodel.TransactionViewModel
import java.util.Calendar

@Composable
fun TransactionScreen(
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        Column(
            modifier = Modifier
                .background(topBarGradient)
                .fillMaxWidth()
        ) {
            CustomTabs(
                selectedIndex = selectedTab,
                tabTitles = listOf("Gelir Ekle", "Gider Ekle"),
                onTabSelected = { selectedTab = it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.White)
                .fillMaxWidth()
        ) {
            if (selectedTab == 0) {
                IncomeEntryScreen(transactionViewModel, true)
            } else {
                ExpenseEntryScreen(transactionViewModel, false)
            }
        }
    }
}

@Composable
fun IncomeEntryScreen(transactionViewModel: TransactionViewModel, inOutComeControl: Boolean) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf("Please Click Icon") }
    var category by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("") }

    val context = LocalContext.current

    var showError by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                isError = showError && name.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Name")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = amount,
                onValueChange = {
                    if (it.text.all { char -> char.isDigit() || char == '.' }) {
                        amount = it
                    }
                },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = showError && amount.text.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(Icons.Default.Money, contentDescription = "Amount")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = date,
                onValueChange = {},
                label = { Text("Date") },
                readOnly = true,
                isError = showError && date == "Please Click Icon",
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Select Date",
                        modifier = Modifier.clickable {
                            showDatePicker = true
                        }
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropdownMenuComponent(
                label = "Category",
                items = iconCategoryList.map { it.category },
                selectedItem = category,
                onItemSelected = { category = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropdownMenuComponent(
                label = "Frequency",
                items = frequencyList.map { it.category },
                selectedItem = frequency,
                onItemSelected = { frequency = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    showError =
                        name.isEmpty() || amount.text.isEmpty() || date == "Please Click Icon" || category.isEmpty() || frequency.isEmpty()
                    val amountDouble = amount.text.toDoubleOrNull()
                    if (!showError && amountDouble != null) {
                        transactionViewModel.addTransaction(
                            name,
                            amountDouble,
                            date,
                            category,
                            frequency,
                            inOutComeControl
                        )
                        name = ""
                        amount = TextFieldValue("")
                        date = "Please Click Icon"
                        Toast.makeText(
                            context,
                            "İşleminiz başarıyla gerçekleşmiştir",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Transaction")
            }
        }
    }

    if (showDatePicker) {
        ShowDatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            onDateChange = { selectedDate ->
                date = selectedDate
                showDatePicker = false
            }
        )
    }
}

@Composable
fun ExpenseEntryScreen(transactionViewModel: TransactionViewModel, inOutComeControl: Boolean) {
    Column {
        Column {
            Text(text = "Gider Ekle")
            Text(text = "Gider Ekle")
            Text(text = "Gider Ekle")
            Text(text = "Gider Ekle")
            Text(text = "Gider Ekle")
        }
    }
}

@Composable
fun ShowDatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateChange: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    android.app.DatePickerDialog(
        context,
        { _, year, monthOfYear, dayOfMonth ->
            onDateChange("$dayOfMonth/${monthOfYear + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).apply {
        setOnDismissListener { onDismissRequest() }
    }.show()
}

@Composable
fun DropdownMenuComponent(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label)
        Box {
            Text(
                text = selectedItem.ifEmpty { "Select $label" },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .background(Color.Gray)
                    .padding(8.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        onItemSelected(item)
                    }) {
                        Text(text = item)
                    }
                }
            }
        }
    }
}


@Composable
fun CustomTabs(selectedIndex: Int, tabTitles: List<String>, onTabSelected: (Int) -> Unit) {
    val selectedTabColor = Color(0xFFB39DDB)
    val unselectedTabColor = Color.White

    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(50)),
        indicator = {
            Box {}
        }
    ) {
        tabTitles.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected) Modifier.background(selectedTabColor) else Modifier.background(
                    unselectedTabColor
                ),
                selected = selected,
                onClick = { onTabSelected(index) },
                text = { Text(text = text, color = if (selected) Color.White else Color.Black) }
            )
        }
    }
}
