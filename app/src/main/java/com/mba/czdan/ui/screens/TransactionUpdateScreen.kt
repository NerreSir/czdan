package com.mba.czdan.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mba.czdan.data.model.frequencyList
import com.mba.czdan.data.model.iconCategoryList
import com.mba.czdan.ui.viewmodel.TransactionUpdateViewModel
import java.util.Calendar

@Composable
fun TransactionUpdateScreen(
    transactionEntity: String,
    navController: NavHostController,
    transactionUpdateViewModel: TransactionUpdateViewModel = hiltViewModel()
) {
    transactionUpdateViewModel.getTransaction(transactionEntity)

    val transactionName by transactionUpdateViewModel.transactionName.collectAsState()
    val transactionAmount by transactionUpdateViewModel.transactionAmount.collectAsState()
    val transactionDate by transactionUpdateViewModel.transactionDate.collectAsState()
    val transactionCategory by transactionUpdateViewModel.transactionCategory.collectAsState()
    val transactionFrequency by transactionUpdateViewModel.transactionFrequency.collectAsState()


    val context = LocalContext.current

    var showError by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    transactionName?.let {

        var name by remember { mutableStateOf(transactionName ?: "") }
        var amount by remember { mutableStateOf(transactionAmount?.toString() ?: "") }
        var date by remember { mutableStateOf(transactionDate ?: "Please Click Icon") }
        var category by remember { mutableStateOf(transactionCategory ?: "") }
        var frequency by remember { mutableStateOf(transactionFrequency ?: "") }


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
                    onValueChange = { it2 ->
                        if (it2.all { char -> char.isDigit() || char == '.' }) {
                            amount = it2
                        }
                    },
                    label = { Text("Amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = showError && amount.isEmpty(),
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
                    isError = showError && date == "Select Date",
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
                            name.isEmpty() || amount.isEmpty() || date == "Select Date" || category.isEmpty() || frequency.isEmpty()
                        val amountDouble = amount.toDoubleOrNull()
                        if (!showError && amountDouble != null) {
                            transactionUpdateViewModel.updateTransaction(
                                name,
                                amount.toDouble(),
                                date,
                                category,
                                frequency,
                                transactionEntity.toInt()
                            )
                            name = ""
                            amount = ""
                            date = "Select Date"
                            Toast.makeText(
                                context,
                                "İşleminiz başarıyla gerçekleşmiştir",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        navController.navigate("home")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Update Transaction")
                }
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Cancel")
                }
            }
        }
        if (showDatePicker) {
            UpdateShowDatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                onDateChange = { selectedDate ->
                    date = selectedDate
                    showDatePicker = false
                }
            )
        }
    }
}

@Composable
fun UpdateShowDatePickerDialog(
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
