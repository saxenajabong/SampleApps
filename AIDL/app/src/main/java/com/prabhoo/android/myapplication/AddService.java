package com.prabhoo.android.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AddService extends Service {

    public AddService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public int Add(int value1, int value2) throws RemoteException {
                return value1 + value2;
            }
        };
    }
}
