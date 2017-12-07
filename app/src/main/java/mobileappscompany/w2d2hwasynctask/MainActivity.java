package mobileappscompany.w2d2hwasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplayData;
    MyAsyncTask myAsynckTask;
    public static final String TAG = "MainActivityTAG";

    private String initialStr = "Task Started.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplayData = findViewById(R.id.tvDisplayData);
    }

    public void handlingMultithreading(View view) throws InterruptedException {

        switch (view.getId()){
            case R.id.btnAsyncTask:
                myAsynckTask = new MyAsyncTask(tvDisplayData);
                myAsynckTask.execute("Data from the main activity");
                break;
            case R.id.btnThread:
                MyThread myThread = new MyThread("Start the thread", tvDisplayData);
                myThread.start();
                break;
            case R.id.btnRunnable:

                break;

            case R.id.btnPoolThread:
                workerPool();

            case R.id.btnRunOnUIThread:
                tvDisplayData.setText("Start task.");
                runOnUIThread();
                tvDisplayData.setText("Task Completed.");
                break;

            case R.id.btnAsyncTaskLoader:
                MyAsyncTaskLoader myAsyncTaskLoader = new MyAsyncTaskLoader(getApplicationContext());
                String str = myAsyncTaskLoader.loadInBackground();
                tvDisplayData.setText(str);

            default:
                break;
        }
    }

    private void runOnUIThread(){
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
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
        }));
    }

    private void workerPool() throws InterruptedException {
        //RejectedExecutorHandler implementation
        RejectedExecutionHandlerImpl rejectedExecutionHandler =
                new RejectedExecutionHandlerImpl();

        //Get thread factory impl to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //create the thread factory
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,4,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectedExecutionHandler);

        //start the monitor thread
        MyMonitorThread monitor = new MyMonitorThread(poolExecutor, 3);
        Thread monitoredThread =    new Thread(monitor);
        monitoredThread.start();

        //submit worker to the pool
        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(new WorkerThread("cmd" + i));
        }
        Thread.sleep(100);
        //shutdown the pool
        poolExecutor.shutdown();
        //shutdown the monitor thread
        Thread.sleep(5000);
        monitor.shutdown();

    }

    @Override
    protected void onStop(){
        super.onStop();
        if(myAsynckTask == null){
            myAsynckTask.cancel(true);
        }
    }
}
