package com.example.issuetracker.model.service

interface LogService {
    fun logNonFatalException(throwable: Throwable )
}