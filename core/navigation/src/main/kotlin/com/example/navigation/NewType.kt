package com.example.navigation

import android.net.Uri
import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.example.model.New
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object NewType : NavType<New>(isNullableAllowed = false) {

    override fun put(bundle: Bundle, key: String, value: New) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): New? =
        BundleCompat.getParcelable(bundle, key, New::class.java)

    override fun parseValue(value: String): New {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: New): String = Uri.encode(Json.encodeToString(value))
}
