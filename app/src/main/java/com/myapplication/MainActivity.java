package com.myapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Looper.prepare();
        final Handler handler = new Handler() {
            @Override
            public void handlerMessage(Message message) {
                super.handlerMessage(message);
                Log.e("TAG1",message.toString()+"Handle"+Thread.currentThread().getName());
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    while(true){
                        try {
                            Message message = new Message();
                            message.what = 1;
                            message.obj = "Handler";
                            Log.e("TAG2",message.toString()+"Handle"+Thread.currentThread().getName());
                            handler.sendMessage(message);
                            new Thread().sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        Looper.loop();
    }


}
