package com.atarusov.aston_intensiv_4.task_2.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long = -1,
    val name: String,
    val lastname: String,
    val phoneNumber: String,
    val imageUri: Uri? = null,
) : Parcelable
