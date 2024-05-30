package com.mba.czdan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mba.czdan.ui.viewmodel.TransactionViewModel

@Composable
fun HomeScreen(
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    val transactions by transactionViewModel.transactions.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { transactionViewModel.loadTransactions() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load Transactions")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(transactions) { transaction ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Name: ${transaction.name}", style = MaterialTheme.typography.bodyLarge)
                        Text(
                            "Amount: ${transaction.amount}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text("Date: ${transaction.date}", style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}