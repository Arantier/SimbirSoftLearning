package ru.shcherbakovdv.ss.trainee.data

data class Profile(var name: String,
                   var pictureUrl: String,
        //TODO: Возможно стоит хранить в календарном виде
                   var birthDate: String,
                   var business: String,
                   var friends: Array<String>?)