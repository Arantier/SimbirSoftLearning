package ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen.OrganisationsFragment

import ru.shcherbakov_dmitry.ss.android_trainee_education.ModelFiles.Organisation
import java.util.*

object OrganisationTabInteractor {

    fun requestListOfOrganisations(key: String?): Array<Organisation> {
        //TODO: Пока что тут только болванка
        val list = ArrayList<Organisation>()
        val random = Random()
        val numberOfElements = random.nextInt(32)
        for (i in 1..numberOfElements) {
            var string = ""
            val stringLength = random.nextInt(32)
            for (j in 1..stringLength) {
                string += random.nextInt(10).toString()
            }
            list.add(Organisation(string,i))
        }
        return list.toTypedArray()
    }

}