package com.example.issuetracker.model.service.module

import com.example.issuetracker.model.service.impl.AccountServiceImpl
import com.example.issuetracker.model.service.interfaces.AccountService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl) : AccountService

}