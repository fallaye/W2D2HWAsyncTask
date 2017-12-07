package mobileappscompany.w2d2hwasynctask;

/**
 * Created by fallaye on 12/6/17.
 */

public class WorkerThread implements Runnable {
    private String command;

    public WorkerThread(String str) {
        this.command = str;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +
                "Start. Command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName() +
                "Start. Command = " + command);
    }

    private void processCommand(){
        try{
            Thread.sleep(2000);

        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
