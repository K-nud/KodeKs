public class GameTimer extends Thread implements Runnable {
	static boolean running = true;

	int milliSeconds;
	static int seconds;
	static int minutes;
	static String currentTime;
	
	
	public synchronized void run() {
		while(true) {
			if(running == true) {
				milliSeconds++;
				if(milliSeconds > 99) {
					milliSeconds = 0;
					seconds++;
				}
				if(seconds > 59) {
					seconds = 0;
					minutes++;
				}
				KodeKsToolBar.timeLabel.setText("  " + getTime() + "  ");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}
			/*if(!isInterrupted()) {
				milliSeconds = 0;
				seconds = 0;				
			}*/
		}
	}
	
	public String getTime() {
		currentTime = formatTime(1) + ":" + formatTime(2);
		return currentTime;
	}
	
	public static String formatTime(int i) {
		if (i == 1) {
			if (minutes < 10) {
				String formatedMinutes = "0" + minutes;
				return formatedMinutes;
			} else return Integer.toString(minutes);
		}
		else {
			if (seconds < 10) {
				String formatedSeconds = "0" + seconds;
				return formatedSeconds;
			} else return Integer.toString(seconds);
		}
	}
	
	static void setRunning() {
		if(running == true) {
			running = false;
			KodeKsToolBar.toolBarItem_PauseResume.setText("   Resume   ");
		} else {
			running = true;
			KodeKsToolBar.toolBarItem_PauseResume.setText("   Pause   ");
		}
	}
	
	static void resetRunning() {
			//running = true;
			//KodeKsToolBar.toolBarItem_PauseResume.setText("   Pause   ");
			minutes = 0;
			seconds = 0;
	}
}
