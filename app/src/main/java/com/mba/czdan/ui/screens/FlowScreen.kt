package com.mba.czdan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mba.czdan.ui.theme.expenseTabTransparent
import com.mba.czdan.ui.theme.incomeTabTransparent
import com.mba.czdan.ui.theme.topBarGradient
import com.mba.czdan.ui.viewmodel.FlowViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowScreen(
    navHostController: NavHostController,
    flowViewModel: FlowViewModel = hiltViewModel()
) {
    val transactions by flowViewModel.allTransactions.collectAsState()

    LaunchedEffect(Unit) {
        flowViewModel.fetchAllTransactions()
    }


    var selectedTab by remember { mutableStateOf(0) }

    Column {
        Column(
            modifier = Modifier
                .background(topBarGradient)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                )
                {
                    Text(text = "asdf")
                    Text(text = "1234")
                }

            }

        }


        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.White)
                .fillMaxWidth()
        ) {
            if (transactions.isEmpty()) {
                Text(
                    text = "Lütfen işlem ekle/kaldır menüsünden işlem ekleyiniz.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )


                //TODO buğra buraya bir buton ekleyip transactionscreen'e yönlendirme yapılabilir ben denedim olmadı sıkıldım ve bıraktım asdgasdf
                /*
                Button(
                    onClick = { navHostController.navigate("TransactionScreen") },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("İşlem Ekle")
                }
                */

            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    // TODO filtreleme ve sıralama özelliğinin eklenmesi
                    // transactions.filter {  }
                    items(transactions, key = { it.id }) { transaction ->
                        val dismissState = rememberDismissState()
                        if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                            LaunchedEffect(transaction) {
                                flowViewModel.deleteTransaction(transaction)
                            }
                        }

                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(DismissDirection.EndToStart),
                            background = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 4.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(Color.Red)
                                        .padding(horizontal = 8.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.White
                                    )
                                }
                            },
                            dismissContent = {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clickable {
                                            navHostController.navigate("transactionUpdate/${transaction.id}")
                                        },
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (transaction.inOutComeControl) incomeTabTransparent else expenseTabTransparent
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxSize(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(1f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Text(
                                                "Name: ${transaction.name}",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                            Text(
                                                "Amount: ${transaction.amount}",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                            Text(
                                                "Date: ${transaction.date}",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                        Column(
                                            modifier = Modifier.weight(1f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Text(
                                                "Category: ${transaction.category}",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                            Text(
                                                "Frequency: ${transaction.frequency}",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                            transaction.period?.let {
                                                Text(
                                                    "Period: $it",
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}