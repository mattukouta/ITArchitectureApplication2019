package com.example.remoteservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class RemoteService : Service() {

    private val binder = object : IRemoteService.Stub() {
        override fun start(text: String): String {

            val returnText: String


            val appList = ApplicationList().AppList
            if (appList.containsKey(text)) {

                val packageList = ApplicationList().getInstalledApplication(application.packageManager)
                val infoList = appList[text]

                if (packageList.contains(infoList!![0])) {
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.action = "android.intent.category.LAUNCHER"
                    intent.setClassName(infoList[0], infoList[1])
                    startActivity(intent)
//
                    returnText = "${text}を起動します"
                } else {
                    returnText = "インストールされていません"
                }
            } else {
                returnText = "対象のアプリに存在しません"
            }

            return returnText
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }
}