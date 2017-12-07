package mobileappscompany.w2d2hwasynctask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fallaye on 12/6/17.
 */

public class MyMonitorThread implements Runnable{

    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    MyTask someTask = new MyTask();

    public MyMonitorThread(ThreadPoolExecutor executor, int delay){
        this.executor = executor;
        this.seconds = delay;
    }

    public void shutdown(){
        this.run = false;
    }

    @Override
    public void run() {
        while (run){
            System.out.println(
                    String.format("[monitor] [%d/%d] active: %d, Complete: %d, " +
                    "Task: %d, ishutdown: %s, isTerminated: %s ",
                    this.executor.getPoolSize(),
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getTaskCount(),
                    this.executor.isShutdown(),
                    this.executor.isTerminated())
            );
            try{
                Thread.sleep(2000);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}