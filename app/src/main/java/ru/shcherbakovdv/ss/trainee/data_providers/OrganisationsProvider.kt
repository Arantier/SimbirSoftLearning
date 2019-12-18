package ru.shcherbakovdv.ss.trainee.data_providers

import ru.shcherbakovdv.ss.trainee.data_classes.Organisation
import java.util.*

object OrganisationsProvider {

    fun requestOrganisations(): Array<Organisation> {
        val organisationArray = ArrayList<Organisation>()
        val random = Random()
        val numberOfElements = random.nextInt(32)
        for (i in 1..numberOfElements) {
            var string = ""
            val stringLength = random.nextInt(32)
            for (j in 1..stringLength) {
                string += random.nextInt(10).toString()
            }
            organisationArray.add(Organisation(string,
                    "Point Nemo",
                    arrayOf("8-800-555-35-35", "1-234-567-89-10"),
                    "google@google.com",
                    "127.0.0.1"))
        }
        return organisationArray.toTypedArray()
    }

}