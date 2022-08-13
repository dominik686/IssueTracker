package com.dominikwieczynski.issuetracker.model.service

interface AccountService
{
   fun isUserLoggedIn() : Boolean
   fun setAuthenticationListener(onUserLoggedOut: () -> Unit, onUserLoggedIn: () -> Unit)
   fun getUserId() : String
   fun authenticate(email: String, password: String, onResult: (Throwable?)-> Unit)
   fun createUserWithEmailAndPassword(email: String, password: String, onResult: (Throwable?) -> Unit)

}