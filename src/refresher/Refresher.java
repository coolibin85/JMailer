package refresher;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Refresher {
	private ScheduledExecutorService schedular = null;
	private RefreshingContext context = null;
	private long lastT = System.currentTimeMillis();
	
	public Refresher(RefreshingContext context) {
		this.context = context;
//		ScheduledExecutorService schedular = Executors.newSingleThreadScheduledExecutor();
	}
	
	public void start() {
		schedular = Executors.newSingleThreadScheduledExecutor();
		schedular.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// CALL CONTEXT REQUEST
				System.out.println("Delay:" + (System.currentTimeMillis() - lastT));
				context.requestAction();
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	public void stop() {
		long t = System.currentTimeMillis(); 
		schedular.shutdown();
		try {
			if(!schedular.awaitTermination(1, TimeUnit.SECONDS)) {
				System.out.println("shutdownNow1");
				schedular.shutdownNow();
				if(!schedular.awaitTermination(2, TimeUnit.SECONDS)) {
					System.out.println("shutdownNow2");
				}
			}
		} catch(InterruptedException ie) {
			System.out.println("InterruptedException!");
			schedular.shutdownNow();
			Thread.currentThread().interrupt();
		}
		System.out.println("Shutdown Time:" + (System.currentTimeMillis() - t));
	}
}
