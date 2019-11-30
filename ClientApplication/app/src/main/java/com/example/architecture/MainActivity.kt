package com.example.architecture

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    var myService: ILocalService? = null
    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            myService = ILocalService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            myService = null
        }
    }

    var mService: IRemoteService? = null
    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mService = IRemoteService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            mService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceIntent = Intent(this, LocalService::class.java)
        serviceIntent.action = ILocalService::class.java.name
        bindService(
            serviceIntent,
            myConnection,
            Context.BIND_AUTO_CREATE
        )

        val mIntent = Intent(this, LocalService::class.java)
        serviceIntent.action = ILocalService::class.java.name
        bindService(
            serviceIntent,
            myConnection,
            Context.BIND_AUTO_CREATE
        )

        findViewById<View>(R.id.startAppButton).setOnClickListener {
            val appName = findViewById<EditText>(R.id.requestAppName)
            try {
                myService?.start(appName.text.toString())
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }
}

