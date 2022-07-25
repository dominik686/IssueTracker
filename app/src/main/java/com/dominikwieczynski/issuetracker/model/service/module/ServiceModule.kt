package com.dominikwieczynski.issuetracker.model.service.module

import com.dominikwieczynski.issuetracker.model.service.impl.AccountServiceImpl
import com.dominikwieczynski.issuetracker.model.service.AccountService
import com.dominikwieczynski.issuetracker.model.service.LogService
import com.dominikwieczynski.issuetracker.model.service.StorageService
import com.dominikwieczynski.issuetracker.model.service.impl.LogServiceImpl
import com.dominikwieczynski.issuetracker.model.service.impl.StorageServiceImpl
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

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

}