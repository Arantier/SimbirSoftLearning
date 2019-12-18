package ru.shcherbakovdv.ss.trainee.data_classes

data class Profile(var name: String,
                   var pictureUrl: String,
        //TODO: Возможно стоит хранить в календарном виде
                   var birthDate: String,
                   var business: String,
                   var friendsArray: Array<Profile>?)