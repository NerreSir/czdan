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


    fun getTransaction(transactionId: String) {
        viewModelScope.launch {
            repository.getTransactionById(transactionId.toInt())?.let {
                _transactionName.value = it.name
                _transactionAmount.value = it.amount
                _transactionDate.value = it.date
            }
        }
    }

    fun updateTransaction(name: String, amount: Double, date: String, transactionId: String) {
        viewModelScope.launch {
            repository.getUpdateTransactionById(name, amount, date, transactionId.toInt())
        }
    }

}
