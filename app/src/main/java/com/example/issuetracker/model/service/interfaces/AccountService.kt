package com.example.issuetracker.model.service.interfaces

interface AccountService
{
   fun authenticate(email: String, password: String, onResult: (Throwable?)-> Unit)
   fun createUserWithEmailAndPassword(email: String, password: String, onResult: (Throwable?) -> Unit)

}