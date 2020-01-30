package ru.shcherbakovdv.ss.trainee.ui.profile

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import ru.shcherbakovdv.ss.trainee.R

class EditPhotoDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.dialog_photo_edit)
        return builder.create()
    }

    companion object {
        val TAG = EditPhotoDialog::class.simpleName
    }
}