package com.prabhoo.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText v1, v2;
    private TextView t1;

    private IMyAidlInterface service;
    private AdditionServiceConnection mConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1 = findViewById(R.id.editText);
        v2 = findViewById(R.id.editText2);
        t1 = findViewById(R.id.textView);
        onBindService();
    }

    private void onBindService() {
        mConnection = new AdditionServiceConnection();
        Intent intent = new Intent();
        intent.setClassName("com.prabhoo.android.myapplication",
                com.prabhoo.android.myapplication.AddService.class.getName());
        boolean ret = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    public void startService(View view) {
        try {
            int value = service.Add(getInt(v1.getText().toString()), getInt(v2.getText().toString()));
            Toast.makeText(this, "Sum: " + value, Toast.LENGTH_LONG).show();
            t1.setText("Sum: " + value);

        } catch (RemoteException e) {

        }
    }

    private int getInt(String v) {
        int result = 0;
        try {
            result = Integer.parseInt(v);
        } catch (Exception e) {

        }
        return result;
    }

    private void releaseService() {
        unbindService(mConnection);
        mConnection = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }

    class AdditionServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;
        }
    }
}
