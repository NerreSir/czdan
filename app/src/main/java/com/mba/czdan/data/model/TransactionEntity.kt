package com.mba.czdan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val amount: Double,
    val date: String,
    val category: String,
    val frequency: String,
    val period: Int?,
    val inOutComeControl: Boolean
)