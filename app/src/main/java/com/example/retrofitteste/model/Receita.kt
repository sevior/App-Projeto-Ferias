package com.example.retrofitteste.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Receita (
    var _id: String,
    var nomeReceita: String,
    var tempoPreparo: String,
    var rendimento: String,
    var ingredientes: String,
    var modoPreparo: String
): Parcelable


