package ru.shcherbakovdv.ss.trainee.mainscreen.search.organisationtab

import ru.shcherbakovdv.ss.trainee.mainscreen.dataclasses.Organisation
import java.util.*

object OrganisationTabInteractor {

    fun requestOrganisations(key: String?): Array<Organisation> {
        val organisationArray = ArrayList<Organisation>()
        val random = Random()
//        val numberOfElements = random.nextInt(32)
        val numberOfElements = random.nextInt(32)
        for (i in 1..numberOfElements) {
            var string = ""
            val stringLength = random.nextInt(32)
            for (j in 1..stringLength) {
                string += random.nextInt(10).toString()
            }
            organisationArray.add(Organisation(string))
        }
        return organisationArray.toTypedArray()
    }

}