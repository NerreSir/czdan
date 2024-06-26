package com.mba.czdan.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mba.czdan.data.dao.TransactionDao
import com.mba.czdan.data.model.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}