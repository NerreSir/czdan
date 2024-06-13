package com.mba.czdan.data.repository

import com.mba.czdan.data.model.TransactionEntity

interface TransactionRepository {
    suspend fun insertTransaction(transaction: TransactionEntity)
    suspend fun getAllTransactions(): List<TransactionEntity>

    suspend fun getTransactionById(id: Int): TransactionEntity?

    suspend fun getUpdateTransactionById(
        userName: String,
        amount: Double,
        date: String,
        category: String,
        frequency: String,
        period: Int,
        id: Int
    )
}