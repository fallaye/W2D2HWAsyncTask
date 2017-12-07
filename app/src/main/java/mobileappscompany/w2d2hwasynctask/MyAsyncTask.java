package mobileappscompany.w2d2hwasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by fallaye on 12/6/17.
 */

public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    public static final String TAG = "MyAsyncTask";
    TextView textView;

    public MyAsyncTask(TextView tvTextView){
        this.textView = tvTextView;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        Log.d(TAG, "onPrexecute: " + Thread.currentThread() );
        textView.setText("Starting the task");
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doingInBackground: " + strings[0]);
        Log.d(TAG, "doingInBackground: " + Thread.currentThread());

        delayTask();
        publishProgress(20);
        delayTask();

        return "Tasl Completed. ";
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressexecute: " + Thread.currentThread() );
        textView.setText("Doing task no: " + values[0]);
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: " + Thread.currentThread() );
        textView.setText(s);
    }

    private void delayTask(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
