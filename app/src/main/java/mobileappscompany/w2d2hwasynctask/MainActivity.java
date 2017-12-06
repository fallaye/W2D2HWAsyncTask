package mobileappscompany.w2d2hwasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplayData;
    MyAsyncTask myAsynckTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplayData = findViewById(R.id.tvDisplayData);



    }

    public void handlingMultithreading(View view) {


        switch (view.getId()){
            case R.id.btnAsyncTask:
                myAsynckTask = new MyAsyncTask(tvDisplayData);
                myAsynckTask.execute("Data from the main activity");
            default:
                break;

        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(myAsynckTask == null){
            myAsynckTask.cancel(true);
        }


    }
}
