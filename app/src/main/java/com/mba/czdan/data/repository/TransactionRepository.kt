package com.mba.czdan.data.repository

import com.mba.czdan.data.model.TransactionEntity

interface TransactionRepository {
    suspend fun getTransactions(): List<TransactionEntity>
    suspend fun insertTransactions(transactions: TransactionEntity)
}