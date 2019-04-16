package ru.shcherbakovdv.ss.trainee.dataclasses

data class Organisation(val name: String,
                   val address: String,
                   val phones : Array<String>,
                   val email : String,
                   val site : String)