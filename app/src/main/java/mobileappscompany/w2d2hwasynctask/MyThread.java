package mobileappscompany.w2d2hwasynctask;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by fallaye on 12/6/17.
 */

public class MyThread extends Thread{
    private String initialStr = "";
    private TextView tvDisplayData;
    int i = 0;

    public static final String TAG = "Mythread";

    public MyThread(String initialStr, TextView tvDisplayData){
        this.initialStr = initialStr;
        this.tvDisplayData = tvDisplayData;
    }

    @Override
    public void run(){
        super.run();
        Log.d(TAG,  "Initial String: " + initialStr);
        Log.d(TAG, "run: " + Thread.currentThread());

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(2000);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            Log.d(TAG, "run: progress" + i);
        }
        Log.d(TAG, "Thread Complete!");
    }
}
