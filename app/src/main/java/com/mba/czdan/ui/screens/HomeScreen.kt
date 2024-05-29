package com.mba.czdan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mba.czdan.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val getAllTransaction = viewModel.getAllTransaction.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Home Page", style = MaterialTheme.typography.headlineSmall)
        getAllTransaction.value?.let {
            for (
            element in it
            ) {
                println(element)
            }
        }
    }
}