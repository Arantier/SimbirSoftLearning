package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses

//TODO: Есть такое чувство, что может в будущем пригодиться фабрика или билдер
class CharityEvent
constructor(
        val title:String,
        val description:String,
        val pictureURL:String,
        val startDate : String,
        val endDate : String,
        val wantedMoney : Int,
        val gatheredMoney : Int,
        val organisationId : Int)