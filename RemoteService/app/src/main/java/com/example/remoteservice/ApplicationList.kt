package com.example.remoteservice

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log



class ApplicationList {
    val AppList = mapOf(
        "chrome" to arrayOf("com.android.chrome", "com.google.android.apps.chrome.Main"),
        "設定" to arrayOf("com.android.settings", "com.android.settings.Settings"),
        "youtube" to arrayOf("com.google.android.youtube", "com.google.android.youtube.app.honeycomb.Shell\$HomeActivity"),
        "マップ" to arrayOf("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"),
        "パズドラ" to arrayOf("jp.gungho.pad", "jp.gungho.pad.AppDelegate"),
        "モンスト" to arrayOf("jp.co.mixi.monsterstrike", "jp.co.mixi.monsterstrike.SplashActivity"),
        "ミュージック" to arrayOf("com.google.android.apps.youtube.music", "com.google.android.apps.youtube.music.activities.MusicActivity"),
        "カレンダー" to arrayOf("com.google.android.calendar", "com.android.calendar.AllInOneActivity"),
        "メール" to arrayOf("com.google.android.gm", "com.google.android.gm.ConversationListActivityGmail"),
        "twitter" to arrayOf("com.twitter.android", "com.twitter.android.StartActivity")
    )

    @SuppressLint("LongLogTag")
    fun getInstalledApplication(packageManager : PackageManager) : ArrayList<String> {
        val packageList = ArrayList<String>()

        val packageInfoList = packageManager.getInstalledPackages(
            PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES
        )
        for (packageInfo in packageInfoList) {
            if (packageManager.getLaunchIntentForPackage(packageInfo.packageName) != null) {
                val packageName = packageInfo.packageName
                val className = packageManager.getLaunchIntentForPackage(packageInfo.packageName)?.component?.className + ""
                Log.i("check:起動可能なパッケージ名", packageName)
                Log.i("check:起動可能なクラス名", className)
                packageList.add(packageName)
            } else {
                Log.i("check:----------起動不可能なパッケージ名", packageInfo.packageName)
            }
        }
        Log.i("checkResult", packageList.toString())

        return packageList
    }
}