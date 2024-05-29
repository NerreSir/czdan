package com.mba.czdan.data.di

import com.mba.czdan.data.repository.TransactionRepository
import com.mba.czdan.data.repository.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideTransactionRepository(transactionRepositoryImpl: TransactionRepositoryImpl): TransactionRepository =
        transactionRepositoryImpl
}