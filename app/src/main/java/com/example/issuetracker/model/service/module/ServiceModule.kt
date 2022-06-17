package com.example.issuetracker.model.service.module

import com.example.issuetracker.model.service.impl.AccountServiceImpl
import com.example.issuetracker.model.service.AccountService
import com.example.issuetracker.model.service.LogService
import com.example.issuetracker.model.service.impl.LogServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl) : AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl) : LogService

}