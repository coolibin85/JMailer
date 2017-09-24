
public class TableContentRefresher implements Runnable {
	private boolean atFirst = true;
	private String path = null;
	
	public TableContentRefresher(String path) {
		this.path = path;
	}
	
	public void stop() {
		
	}
	
	public void start() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(atFirst) {
			
		}
		
		atFirst = false;

	}
	
	

}
