package com.example.architecture

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.example.remoteservice.IRemoteService
import kotlinx.android.synthetic.main.activity_main.*

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
        bindService(
            serviceIntent,
            myConnection,
            Context.BIND_AUTO_CREATE
        )

        val mIntent = Intent("com.example.remoteservice.IRemoteService")
        mIntent.setPackage("com.example.remoteservice")
        bindService(
            mIntent,
            mConnection,
            Context.BIND_AUTO_CREATE
        )

        startAppButton.setOnClickListener {
            try {
                Log.d("checkremote", mService!!.string("hoge"))
                mService?.start("hoge")
            } catch (e: RemoteException) {
                e.printStackTrace()
            }

            val appName = findViewById<EditText>(R.id.requestAppName)
            try {
                myService?.start(appName.text.toString())
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }
}

