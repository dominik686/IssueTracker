package com.dominikwieczynski.issuetracker.model.service.impl

import com.dominikwieczynski.issuetracker.model.service.LogService
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class LogServiceImpl @Inject constructor(): LogService{
    override fun logNonFatalException(throwable: Throwable) {
        Firebase.crashlytics.recordException(throwable)
    }
}