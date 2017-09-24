package refresher;
import java.io.File;
import java.util.HashSet;
import java.util.Set;


public class RefreshingContext {
	private File fDir;
	private String curDir;
	private State curState = new InitialState();
	private Set<String> prevList = new HashSet<String>();
	
	public RefreshingContext(File fDir) {
		this.fDir = fDir;
	}
	
//	public RefreshingContext(String curDir) {
//		this.curDir = curDir;
//	}
	
	public void requestAction() {
		curState.act(this);
	}
	
	public void loadFilelist() {
		System.out.println("loadFilelist()");
//		try { Thread.sleep(1000); }
//		catch(InterruptedException ex) {
//			ex.printStackTrace();
//		}
		Set<String> curList = new HashSet<String>();
//		File[] filelist = new File(curDir).listFiles();
		File[] filelist = fDir.listFiles();
		for(File f : filelist) {
			String absPath = f.getAbsolutePath();
			System.out.println(absPath);
			curList.add(absPath);
		}
		
		if(curList.size() != prevList.size() || 
				curList.retainAll(prevList)) {
			
		}
//		if(curList.retainAll(prevList)) {
//			
//		}
	}
	
	public void refreshFilelist() {
		System.out.println("refreshFilelist()");
//		try { Thread.sleep(1000); }
//		catch(InterruptedException ex) {
//			ex.printStackTrace();
//		}
	}
	
	public void setState(State s) {
		curState = s;
	}
	
	// ******************* STATES *******************
	private static abstract class State {
		public abstract void act(RefreshingContext rc);
	}
	
	private static class InitialState extends State {
		@Override
		public void act(RefreshingContext rc) {
			System.out.println("InitialState");
			rc.loadFilelist();
			rc.setState(new UpdatingState());
		}
	}
	
	private static class UpdatingState extends State {
		@Override
		public void act(RefreshingContext rc) {
			System.out.println("UpdatingState");
			rc.refreshFilelist();
		}
	}
}
