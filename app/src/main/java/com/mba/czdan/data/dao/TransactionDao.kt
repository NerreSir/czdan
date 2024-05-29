package com.mba.czdan.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mba.czdan.data.model.TransactionEntity

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<TransactionEntity>
}