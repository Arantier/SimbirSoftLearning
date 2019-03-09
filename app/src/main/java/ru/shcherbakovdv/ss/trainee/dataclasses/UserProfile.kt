package ru.shcherbakovdv.ss.trainee.dataclasses

class UserProfile(var name: String,
                  var pictureUrl: String,
        //TODO: Возможно стоит хранить в календарном виде
                  var birthDate: String,
                  var business: String,
                  var friendsArray: Array<UserProfile>?)