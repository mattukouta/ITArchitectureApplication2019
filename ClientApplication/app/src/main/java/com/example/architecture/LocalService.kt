package com.example.architecture

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.widget.Toast

class LocalService : Service() {

    private val mBinder = object : ILocalService.Stub() {
        @Throws(RemoteException::class)
        override fun start(text: String) {

            val appList = ApplicationList().AppList
            if (appList.containsKey(text)) {

                val packageList = ApplicationList().getInstalledApplication(packageManager)
                val infoList = appList[text]

                if (packageList.contains(infoList!![0])) {
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.action = "android.intent.category.LAUNCHER"
                    intent.setClassName(infoList[0], infoList[1])
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "インストールされていません", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "対象のアプリに存在しません", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }


}
