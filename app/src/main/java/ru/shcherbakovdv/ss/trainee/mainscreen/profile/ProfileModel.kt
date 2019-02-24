package ru.shcherbakovdv.ss.trainee.mainscreen.profile

import ru.shcherbakovdv.ss.trainee.mainscreen.dataclasses.UserProfile

object ProfileModel {

    //TODO: Как только появится более точная модель - переделай
    private val testFriendsArray = arrayOf(
            UserProfile("Дмитрий Валерьевич",
                    "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/E0F81DAD-ED24-4342-957A-8DC6B274A0BA.png",
                    "",
                    "",
                    null),
            UserProfile("Евгений Александров",
                    "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/3DF5C78D-DCEE-4751-BE1D-7ADA5DE00F19.png",
                    "",
                    "",
                    null),
            UserProfile("Виктор Кузнецов",
                    "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/F79B5B72-528E-498C-A6BD-C693FA05D12D.png",
                    "",
                    "",
                    null)
    )
    var userProfile: UserProfile? = UserProfile("Константинов Денис",
            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/1D81E1C0-76EC-48C4-9272-A6411B832DF4.png",
            "01 февраля 1980",
            "Хирургия, травмотология",
            testFriendsArray)
}