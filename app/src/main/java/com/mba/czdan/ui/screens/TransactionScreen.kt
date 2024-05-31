package com.mba.czdan.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.mba.czdan.ui.viewmodel.TransactionViewModel
import java.util.Calendar

@Composable
fun TransactionScreen(
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf("") }
    val context = LocalContext.current

    var showError by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
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
                IncomeEntryScreen(transactionViewModel)
            } else {
                ExpenseEntryScreen()
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
fun IncomeEntryScreen(transactionViewModel: TransactionViewModel) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf("") }
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
                modifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Date") },
                readOnly = true,
                isError = showError && date.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Select Date")
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    showError = name.isEmpty() || amount.text.isEmpty() || date.isEmpty()
                    val amountDouble = amount.text.toDoubleOrNull()
                    if (!showError && amountDouble != null) {
                        transactionViewModel.addTransaction(name, amountDouble, date)
                        name = ""
                        amount = TextFieldValue("")
                        date = ""
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
fun ExpenseEntryScreen() {
    Column() {
        Column() {
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
fun CustomTabs(selectedIndex: Int, tabTitles: List<String>, onTabSelected: (Int) -> Unit) {
    val selectedTabColor = Color(0xFFB39DDB)
    val unselectedTabColor = Color.White

    TabRow(
        selectedTabIndex = selectedIndex,
        //backgroundColor = Color(0xFF1E88E5),
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
                modifier = if (selected) Modifier
                    .background(selectedTabColor)
                else Modifier
                    .background(unselectedTabColor),
                selected = selected,
                onClick = { onTabSelected(index) },
                text = { Text(text = text, color = if (selected) Color.White else Color.Black) }
            )
        }
    }
}