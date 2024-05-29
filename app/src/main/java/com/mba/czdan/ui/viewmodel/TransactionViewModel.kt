package com.mba.czdan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mba.czdan.data.model.TransactionEntity
import com.mba.czdan.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _transactions = MutableStateFlow<List<TransactionEntity>>(emptyList())
    val transactions: StateFlow<List<TransactionEntity>> = _transactions

    fun saveTransaction(description: String) {
        viewModelScope.launch {
            transactionRepository.insertTransactions(TransactionEntity(description = description))
            loadTransactions()
        }
    }

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            _transactions.value = transactionRepository.getTransactions()
        }
    }
}