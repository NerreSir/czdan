package com.mba.czdan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mba.czdan.data.model.TransactionEntity
import com.mba.czdan.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val transactionRepository: TransactionRepository) :
    ViewModel() {
    private val _getAllTransaction: MutableStateFlow<List<TransactionEntity>?> =
        MutableStateFlow(null)
    val getAllTransaction: MutableStateFlow<List<TransactionEntity>?> = _getAllTransaction

    init {
        viewModelScope.launch{
            transactionRepository.getTransactions()?.let {
                _getAllTransaction.value = it
            }
        }
    }
}