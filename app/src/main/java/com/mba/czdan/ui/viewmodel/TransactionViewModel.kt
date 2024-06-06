package com.mba.czdan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mba.czdan.data.model.TransactionEntity
import com.mba.czdan.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _transactions = MutableStateFlow<List<TransactionEntity>>(emptyList())
    val transactions: StateFlow<List<TransactionEntity>> = _transactions.asStateFlow()

    fun addTransaction(
        name: String,
        amount: Double,
        date: String,
        category: String,
        frequency: String,
        inOutComeControl: Boolean
    ) {
        val newTransaction = TransactionEntity(
            name = name,
            amount = amount,
            date = date,
            category = category,
            frequency = frequency,
            inOutComeControl = inOutComeControl
        )
        viewModelScope.launch {
            transactionRepository.insertTransaction(newTransaction)
            loadTransactions()
        }
    }

    fun loadTransactions() {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().let { transactionsList ->
                _transactions.value = transactionsList
            }
        }
    }
}