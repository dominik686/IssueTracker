package com.dominikwieczynski.issuetracker.model.service

interface AccountService
{
   fun authenticate(email: String, password: String, onResult: (Throwable?)-> Unit)
   fun createUserWithEmailAndPassword(email: String, password: String, onResult: (Throwable?) -> Unit)

}