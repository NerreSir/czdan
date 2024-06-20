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
class FlowViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _allTransactions = MutableStateFlow<List<TransactionEntity>>(emptyList())
    val allTransactions: StateFlow<List<TransactionEntity>> = _allTransactions.asStateFlow()


    fun fetchAllTransactions() {
        viewModelScope.launch {
            repository.getAllTransactions()?.let { transactions ->
                _allTransactions.value = transactions
            }
        }
    }

    fun deleteTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            repository.deleteTransaction(transaction)
            fetchAllTransactions()
        }
    }
}