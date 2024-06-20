package com.mba.czdan.data.repository

import com.mba.czdan.data.dao.TransactionDao
import com.mba.czdan.data.model.TransactionEntity
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun insertTransaction(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
    }

    override suspend fun getAllTransactions(): List<TransactionEntity> {
        return transactionDao.getAllTransactions()
    }

    override suspend fun getTransactionById(id: Int): TransactionEntity {
        return transactionDao.getTransactionById(id)
    }

    override suspend fun getUpdateTransactionById(
        userName: String,
        amount: Double,
        date: String,
        category: String,
        frequency: String,
        period: Int,
        id: Int,
        inOutComeControl: Boolean,
        ) {
        transactionDao.getUpdateTransactionById(
            userName,
            amount,
            date,
            category,
            frequency,
            period,
            id,
            inOutComeControl,
        )
    }

    override suspend fun deleteTransaction(transaction: TransactionEntity) {
        transactionDao.deleteTransaction(transaction)
    }
}