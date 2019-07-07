package com.linkdev.demokotlin.ui.base

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

abstract class PermissionHandlerFragment : BaseFragment() {

    protected abstract fun onPermissionGranted(codePermission: Int)

    protected abstract fun onPermissionDenied(codePermission: Int)


    protected fun checkPermissions(context: Context?, permissionCode: Int, vararg permissions: String) {
        if (context == null) return
        if (!hasPermissions(context, *permissions)) {
            requestPermissions(permissions, permissionCode)
        } else {
            onPermissionGranted(permissionCode)
        }
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (context == null) {
            return false
        }
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        var isAllPermissionsGranted = true
        for (permission in grantResults) {
            if (permission != PackageManager.PERMISSION_GRANTED) {
                isAllPermissionsGranted = false
                break
            }
        }
        if (isAllPermissionsGranted) {
            onPermissionGranted(requestCode)
        } else {
            onPermissionDenied(requestCode)
        }
    }

}

