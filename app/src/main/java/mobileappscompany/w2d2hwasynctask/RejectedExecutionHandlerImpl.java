package mobileappscompany.w2d2hwasynctask;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by fallaye on 12/6/17.
 */

public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        System.out.println(runnable.toString() + " is rejected");
    }
}
