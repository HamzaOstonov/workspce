package com.is.nibbd_srv;

import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;

import com.is.customer_.core.utils.GeneralUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.customer_.sap.EmergencyMode;
import com.is.utils.CheckNull;

public class NibbdTimer {
    static Timer timer;
    private static Logger logger = ISLogger.getLogger();
    private static int startingTime;
    private static int timeout;

    public void run() {


        init_variables();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (!EmergencyMode.isTrue) {
                        SapSynchronizer.handle();
                        PendingResponseHandler pendingResponseHandler = new PendingResponseHandler();
                        pendingResponseHandler.handle();
                    }
                    NibbdReisMaker.make();
                }
                catch (Exception e){
                    logger.error("Exception ... ");
                    logger.error(CheckNull.getPstr(e));
                }
                catch (Throwable throwable){
                    logger.error("Stacktrace ..." + throwable);
                    logger.error(GeneralUtils.stackTraceToString(throwable));
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    logger.error(CheckNull.getPstr(e));
                }
            }
        }, startingTime, timeout);
    }

    public void restart() {
        //logger.info("nibbd timer stopping...");
        timer.cancel();
        //logger.info("nibbd timer stopped");
        timer = null;
        //logger.info("nibbd timer starting...");
        run();
        //logger.info("nibbd timer sttarted");
    }

    private boolean init_variables() {
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            //startingTime = 1000 * Integer.parseInt(com.is.file_reciever_srv.common.Service.get_set_value(c, "ext_timer_starting_time"));
            startingTime = 1000;
            timeout =  1000 * 30;//Integer.parseInt(com.is.file_reciever_srv.common.Service.get_set_value(c, "ext_timer_timeout"));
            //startingTime = 1000;
            //timeout = 1000 * 30;
        } catch (Exception e) {
            logger.error(com.is.utils.CheckNull.getPstr(e));
            return false;
        } finally {
            try {
                if (c != null) c.close();
            } catch (Exception e) {
            }
        }
        return true;
    }

    private void generateOOM() throws Exception {
        int iteratorValue = 20;
        System.out.println("\n=================> OOM test started..\n");
        for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
            System.out.println("Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
            int loop1 = 2;
            int[] memoryFillIntVar = new int[iteratorValue];
            // feel memoryFillIntVar array in loop..
            do {
                memoryFillIntVar[loop1] = 0;
                loop1--;
            } while (loop1 > 0);
            iteratorValue = iteratorValue * 5;
            System.out.println("\nRequired Memory for next loop: " + iteratorValue);
            Thread.sleep(1000);
        }
    }
}
