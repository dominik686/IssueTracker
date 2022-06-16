package com.example.issuetracker.model.service.impl

import com.example.issuetracker.model.service.interfaces.AccountService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(): AccountService {
    override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            onResult(it.exception)
        }
    }
}