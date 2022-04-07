package com.example.performance

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat

internal fun Context.hasPermission(permission: String): Boolean = ContextCompat.checkSelfPermission(
    this,
    permission
) == PackageManager.PERMISSION_GRANTED

internal fun Activity.requestPermission(permission: String, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(arrayOf(permission), requestCode)
    }else{
        Log.e("Context","current SDK is lower than ${Build.VERSION_CODES.M}")
    }
}