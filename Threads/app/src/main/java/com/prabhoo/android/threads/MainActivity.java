package com.prabhoo.android.threads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Handler mHandler, mTaskHandler = new Handler();
    ProgressBar mProgress;
    TextView mTextView;
    ArrayList<Handler> outBound = new ArrayList<>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = findViewById(R.id.progressBar);
        mTextView = findViewById(R.id.status);



        //Looper.prepare();

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 101:
                        mProgress.setProgress(msg.arg1);
                        if (msg.arg1 == 9) {
                            Message message = mTaskHandler.obtainMessage();
                            message.what = 502;
                            message.arg1 = msg.arg1;
                            message.obj = "Status free for Progress Bar";
                            mTaskHandler.sendMessage(message);
                        } else {
                            Message message = mTaskHandler.obtainMessage();
                            message.what = 501;
                            message.arg1 = msg.arg1;
                            message.obj = "Status busy for Progress Bar";
                            mTaskHandler.sendMessage(message);
                        }
                        break;
                    case 201:
                        break;
                    case 301:
                        break;
                }
            }
        };


    }

    public void startTask(View view) {
        new Thread(new Task()).start();
    }

    public void startThread(View view) {

    }

    class Task implements Runnable {

        @Override
        public void run() {
            for(int i=0; i<10; i++) {
                Message message = mHandler.obtainMessage();
                message.what = 101;
                message.arg1 = i;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                mHandler.sendMessage(message);
            }

            Looper.prepare();
            mTaskHandler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 500:
                            Log.d("tag", "Status received from parent : " + msg.obj);
                            break;
                        case 501:
                            Log.d("tag", "Status received from parent : " + msg.obj + " msg.arg1 : " + msg.arg1);
                            break;
                        case 502:
                            Log.d("tag", "Status received from parent : " + msg.obj + " msg.arg1 : " + msg.arg1);
                            break;
                    }
                }
            };
            Looper.loop();
        }
    }


}

