package ru.shcherbakovDmitry.ss.androidTraineeEducation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

class EditPhotoDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.dialog_photo_edit)
        return builder.create()
    }

    companion object {
        val TAG = "edit_photo"
    }
}