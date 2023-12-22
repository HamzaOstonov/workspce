package com.is.nibbd_srv;

import org.apache.log4j.Logger;


public class TimerContainer {
	private static NibbdTimer timer;
	private static Logger logger = Logger.getLogger(TimerContainer.class.getName());	
	
	public static void run() {
		timer = new NibbdTimer();
        timer.run();
	}
	
	public static void restart() {
        //timer.restart();
	}
}
