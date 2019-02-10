package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses

class UserProfile
constructor(var userId: Int,
            var name: String,
            var pictureUrl: String,
        //TODO: Возможно стоит хранить в календарном виде
            var birthDate: String,
            var business: String,
            var friendsArray: Array<UserProfile>?)