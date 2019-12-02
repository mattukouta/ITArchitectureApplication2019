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
import android.widget.Toast
import com.example.remoteservice.IRemoteService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    var localService: ILocalService? = null
//    private val localConnection = object : ServiceConnection {
//        override fun onServiceConnected(className: ComponentName, service: IBinder) {
//            localService = ILocalService.Stub.asInterface(service)
//        }
//
//        override fun onServiceDisconnected(className: ComponentName) {
//            localService = null
//        }
//    }

    var remoteService: IRemoteService? = null
    private val remoteConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            remoteService = IRemoteService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            remoteService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val localIntent = Intent(this, LocalService::class.java)
//        bindService(
//            localIntent,
//            localConnection,
//            Context.BIND_AUTO_CREATE
//        )

        val remoteIntent = Intent("com.example.remoteservice.IRemoteService")
        remoteIntent.setPackage("com.example.remoteservice")
        bindService(
            remoteIntent,
            remoteConnection,
            Context.BIND_AUTO_CREATE
        )

        startAppButton.setOnClickListener {
//            if (requestAppName.text.isNotEmpty()) {
//                try {
//                    localService?.start(requestAppName.text.toString())
//                } catch (e: RemoteException) {
//                    e.printStackTrace()
//                }
//            }

            if (requestAppName.text.isNotEmpty()) {
                try {
                    val returnText  = remoteService?.start(requestAppName.text.toString())
                    Toast.makeText(this, returnText.toString(), Toast.LENGTH_SHORT).show()
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
    }
}

