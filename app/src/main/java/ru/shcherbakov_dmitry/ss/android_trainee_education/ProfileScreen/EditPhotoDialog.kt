package ru.shcherbakov_dmitry.ss.android_trainee_education.ProfileScreen

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import ru.shcherbakov_dmitry.ss.android_trainee_education.R

class EditPhotoDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.dialog_photo_edit)
        return builder.create()
    }
}