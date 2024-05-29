package com.mba.czdan.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mba.czdan.data.model.Transaction

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<Transaction>
}