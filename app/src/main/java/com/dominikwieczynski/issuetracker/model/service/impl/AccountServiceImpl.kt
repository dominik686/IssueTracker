package com.dominikwieczynski.issuetracker.model.service.impl

import com.dominikwieczynski.issuetracker.model.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(): AccountService {

    private var authenticationListener : FirebaseAuth.AuthStateListener? = null
    override fun signOut() {
         Firebase.auth.signOut()
    }


    private fun onSignOut(onUserLoggedOut: () -> Unit)
    {
        authenticationListener = FirebaseAuth.AuthStateListener(){
            if(it.currentUser == null)
            {
                onUserLoggedOut()
            }
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return  Firebase.auth.currentUser != null
    }

    override fun setAuthenticationListener(onUserLoggedOut: () -> Unit,
                                           onUserLoggedIn: () -> Unit)
{
        authenticationListener = FirebaseAuth.AuthStateListener(){
            if(it.currentUser == null)
            {
                onUserLoggedOut()
            }
            onUserLoggedIn()
        }
    }


    override fun getUserId(): String {
       return Firebase.auth.currentUser?.uid.orEmpty()
    }

    override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            onResult(it.exception)
        }
    }

    override fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            onResult(it.exception)
        }
    }

    override fun deleteAccount(onResult: (Throwable?) -> Unit) {
        Firebase.auth.currentUser?.delete()?.addOnCompleteListener{
            onResult(it.exception)
        }
    }
}