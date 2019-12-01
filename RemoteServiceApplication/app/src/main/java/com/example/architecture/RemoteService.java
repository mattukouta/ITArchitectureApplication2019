package com.example.itarchitectureapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import com.example.itarchitectureapplication.IRemoteService;

public class RemoteService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
        public void start(String script) {
            Toast.makeText(getApplicationContext(), "接続完了", Toast.LENGTH_SHORT).show();
        }
    };
}
