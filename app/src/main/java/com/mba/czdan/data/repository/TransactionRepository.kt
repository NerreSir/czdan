package com.mba.czdan.data.repository

import com.mba.czdan.data.model.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun insertTransaction(transaction: TransactionEntity)
    fun getAllTransactions(): Flow<List<TransactionEntity>>
}