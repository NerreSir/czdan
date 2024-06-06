package com.mba.czdan.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mba.czdan.data.model.TransactionEntity

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<TransactionEntity>


    @Query("SELECT * FROM transactions WHERE id = :transactionId")
    suspend fun getTransactionById(transactionId: Int): TransactionEntity

    @Query("UPDATE transactions SET name = :userName,amount = :amount,date = :date, category = :category, frequency = :frequency WHERE id = :id")
    suspend fun getUpdateTransactionById(
        userName: String,
        amount: Double,
        date: String,
        category: String,
        frequency: String,
        id: Int
    )


}