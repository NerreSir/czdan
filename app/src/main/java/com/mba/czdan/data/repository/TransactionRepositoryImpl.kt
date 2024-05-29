package com.mba.czdan.data.repository

import com.mba.czdan.data.dao.TransactionDao
import com.mba.czdan.data.model.TransactionEntity
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao) :
    TransactionRepository {
    override suspend fun getTransactions(): List<TransactionEntity> {
        return transactionDao.getAllTransactions()
    }

    override suspend fun insertTransactions(transactions: TransactionEntity) {
        return transactionDao.insert(transactions)
    }
}
