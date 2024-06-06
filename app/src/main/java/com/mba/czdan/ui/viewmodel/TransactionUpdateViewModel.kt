package com.mba.czdan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mba.czdan.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionUpdateViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _transactionName = MutableStateFlow<String?>(null)
    val transactionName: MutableStateFlow<String?> = _transactionName

    private val _transactionAmount = MutableStateFlow<Double?>(null)
    val transactionAmount: MutableStateFlow<Double?> = _transactionAmount

    private val _transactionDate = MutableStateFlow<String?>(null)
    val transactionDate: MutableStateFlow<String?> = _transactionDate

    private val _transactionCategory = MutableStateFlow<String?>(null)
    val transactionCategory: MutableStateFlow<String?> = _transactionCategory

    private val _transactionFrequency = MutableStateFlow<String?>(null)
    val transactionFrequency: MutableStateFlow<String?> = _transactionFrequency

    fun getTransaction(transactionId: String) {
        viewModelScope.launch {
            repository.getTransactionById(transactionId.toInt())?.let {
                _transactionName.value = it.name
                _transactionAmount.value = it.amount
                _transactionDate.value = it.date
                _transactionCategory.value = it.category
                _transactionFrequency.value = it.frequency
            }
        }
    }

    fun updateTransaction(
        name: String,
        amount: Double,
        date: String,
        category: String,
        frequency: String,
        id: Int
    ) {
        viewModelScope.launch {
            repository.getUpdateTransactionById(
                userName = name,
                amount = amount,
                date = date,
                category = category,
                frequency = frequency,
                id = id
            )
        }
    }
}
