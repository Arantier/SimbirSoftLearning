package ru.shcherbakovdv.ss.trainee.data

data class User(var name: String,
                var photoUrl: String,
                var birthDate: String,
                var business: String,
                var friends: Array<String>?)