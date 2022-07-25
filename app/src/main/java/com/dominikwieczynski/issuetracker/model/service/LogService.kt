package com.dominikwieczynski.issuetracker.model.service

interface LogService {
    fun logNonFatalException(throwable: Throwable )
}