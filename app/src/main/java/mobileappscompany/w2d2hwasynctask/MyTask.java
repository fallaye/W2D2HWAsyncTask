package mobileappscompany.w2d2hwasynctask;

import android.util.Log;

/**
 * Created by fallaye on 12/6/17.
 */

public class MyTask extends Thread{

    public MyTask(){}

    public static final String TAG = "MyTask";
    public static String startTask(String initialStr){
        Log.d(TAG, "run init data: " + initialStr);
        Log.d(TAG, "run: " + Thread.currentThread());
        for (int i = 0; i < 5; i++) {
            try{
                Thread.sleep(2000);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            Log.d(TAG, "run: progress " + i);

            Log.d(TAG, "run: status: Task Completed.");
        }
        return "Task Completed";
    }


}
