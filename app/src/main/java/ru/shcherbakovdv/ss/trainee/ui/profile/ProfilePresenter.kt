package ru.shcherbakovdv.ss.trainee.ui.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import moxy.MvpPresenter
import ru.shcherbakovdv.ss.trainee.data.Profile

class ProfilePresenter : MvpPresenter<ProfileMvpView>() {

    val auth = FirebaseAuth.getInstance()

    fun requestUser() {
        auth.currentUser?.let { firebaseUser ->
            Firebase.database
                .getReference("user/${firebaseUser.uid}")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        viewState.fillProfileScreen(
                            Profile(
                                firebaseUser.displayName ?: "",
                                firebaseUser.photoUrl.toString(),
                                dataSnapshot.child("birthDate").getValue(String::class.java) ?: "",
                                dataSnapshot.child("business").getValue(String::class.java) ?: "",
                                dataSnapshot.child("friends").getValue(
                                    Array<String>::class.java
                                )
                            )
                        )
                    }

                    override fun onCancelled(p0: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
        } ?: viewState.proceedToLoginScreen()
    }
}