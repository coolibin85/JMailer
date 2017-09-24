import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

interface MyObserver {
	public void update(MyObservable obs);
}

interface MyObservable {
	public void addObserver(MyObserver o);
	public void deleteObserver(MyObserver o);
	public void notifyObservers();
}



public class CurrentFolder implements MyObservable {
//	private static final String histFileName = "ignore.lst"; 
	private FolderContentModel folderModel;
//;
//	private List<String> hasBeenSent = new ArrayList<String>(512);
//	private String path = System.getProperty("user.dir");
	
	public CurrentFolder(FolderContentModel folderModel) {
		this.folderModel = folderModel;
	}
	

//	
//	public void fileHasBeenSent(String filename) {
//		
//	}
	
//	public void saveHistory() {
//		File fIgnore = new File(path);
//		FileWriter fw = null;
//		BufferedWriter bw = null;
//		
//		try {
//			if(!fIgnore.exists()) {
//				fIgnore.createNewFile();
//			}
//
//			fw = new FileWriter(fIgnore);
//			bw = new BufferedWriter(fw);
//			for(String entry : hasBeenSent) { 
//				bw.write(entry);
//				bw.newLine();
//			}
//		
//			
//		} catch(IOException ex) {
//			System.out.println("Opening writer error" + ex);
//		} finally {
//			try {
//				if(bw != null) {
//					bw.close();
//				}
//				if(fw != null) {
//					fw.close();
//				}
//			} catch (IOException ex) {
//				System.out.print("Closing writer error" + ex);
//			}
//		}
//	}
	
	// ***************** Observable *****************
	private List<MyObserver> observers = new LinkedList<MyObserver>(); 
	
	public void addObserver(MyObserver o) {
		if(!observers.contains(o))
			observers.add(o);
	}
	
	public void deleteObserver(MyObserver o) {
		observers.remove(o);
	}
	
	public void notifyObservers() {
//		ActualFolderInfo inf = new ActualFolderInfo(fullpath, recognized);
		for(MyObserver myObs : observers)
			myObs.update(this);
		
	}
}
