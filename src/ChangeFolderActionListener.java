import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;


public class ChangeFolderActionListener implements ActionListener {
	private static final String PREV_SSN_DATA_FILENAME = "prev.session.dat"; 
	private MailerFrame mainFrm = null;
	private FolderContentModel tm = null;
	private FolderContentRefresher refresher = null;
	private JFileChooser fc = new JFileChooser();
//	private List<String> lastState = new LinkedList<String>();
//	private boolean running = false;
	
//	public void run() {
		
		
		
//	}
	public ChangeFolderActionListener(MailerFrame mainFrm, FolderContentModel tm) {
		this.mainFrm = mainFrm;
		this.tm = tm;
	}
	
	public void actionPerformed(ActionEvent event) {
		
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(mainFrm);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().getAbsolutePath();
			System.out.println("Current Folder:" + path);
			
//			Map<String, Boolean> recents = new HashMap<String, Boolean>();
			List<String> unchecked = new LinkedList<String>();
			try {
				System.out.println("Previous Session File:" + path + "//" + PREV_SSN_DATA_FILENAME);
				// Each line filename * (false | true)
				List<String> lines = loadTxtFile(path + "//" + PREV_SSN_DATA_FILENAME);
				String nospace, part1, part2;
				int asterix;
				for(String s : lines) {
					nospace = s.replaceAll("\\s+", "");	// ?????? SPACEs
					asterix = nospace.indexOf('*');
					part1 = nospace.substring(0, asterix);
					part2 = nospace.substring(asterix + 1, nospace.length());
					if(part2.equalsIgnoreCase("false")) {
						unchecked.add(part1);
					}
				}
				
				
			} catch(IOException ex) {
				System.out.println("PREVIOUS_SESSION_FILE_LOADING_ERROR");
			}
			
			
			List<FolderContentModel.TableEntry> data = new LinkedList<FolderContentModel.TableEntry>();
			File[] fList = new File(path).listFiles();	// REGEX PATTERN: .*\d{5}.*
			boolean checked;
			for(File f : fList) {
				//if(f.isDirectory()) {
				// String fullname, String serial, String nickname, String email
				
				// NEED RECOGNIZE BY SERIAL NUMBER!!!
				checked = true;
				if(unchecked.contains(f.getName()))
					checked = false;
				FolderContentModel.TableEntry entry = new FolderContentModel.TableEntry(
						f.getName(), "@sn", "@nickname", "@email", true);//checked);	
				data.add(entry);
				//}
			}
			tm.updateContent(data);
			
			
//			if(refresher != null) {
//				refresher.terminate();
//				refresher = new FolderContentRefresher(fc.getSelectedFile().getAbsolutePath());
//			}
			
			
		}
	}
	
	private List<String> loadTxtFile(String filename) throws IOException {		
		List<String> content = new LinkedList<String>();
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		while((line = br.readLine()) != null) {
			content.add(line);
		}		
		return content;
	}
	
	private List<File> chooseMatchingFilesOnly(File pathfile, String pattern) {
		List<File> chosen = new LinkedList<File>();
		File[] fList = pathfile.listFiles();
		for(File f : fList) {
			if(f.getName().matches(pattern))
				chosen.add(f);
		}
		return chosen;
	}
	
	// ******************* Initializer *********************
	private class TableContentInitializer implements Runnable {
		private Thread t = null;
		private String path;
		
		public TableContentInitializer(String path) {
			this.path = path;
			t = new Thread(this, "THR_INIT_TCONT");
			t.start();
		}
		
		@Override
		public void run() {
			Map<String, Boolean> sels = new HashMap<String, Boolean>();
		
			
			
			final File[] fList = new File(path).listFiles();
			// ******* FILTERING FILEs CODE HERE
			// ******* GETTING USERs INFO CODE HERE
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {

				}
			});
		}
	}
	// ********************* Refresher *********************
	private class FolderContentRefresher implements Runnable {
		private Thread t = null;
//		private 
		private String path;
		private Set<String> last = new HashSet<String>();
		private volatile boolean running = true;
		
		public FolderContentRefresher(String path) {
			this.path = path;
			t = new Thread(this, "ReFRESHER");
			t.start();
		}
		
		@Override
		public void run() {
			Map<String, Boolean> sels = new HashMap<String, Boolean>();
			boolean atFirst = true;
			
			// if (running)
			// 		load recent session sels
			// while(running)..
			//		get all files in folder
			//		filter only with iprog #
			//		get info for each
			//		<EDT>
			//		
			// ..
			
			// ..while running
			// if 
			
			while(running) {
				if(atFirst) {
					// *********** LOAD RECENT SESSION INFO(IF EXISTS) ***********				
					try {
						FileReader fr = new FileReader("recent.ssn");
						BufferedReader br = new BufferedReader(fr);
						
						String line;
						
						while((line = br.readLine()) != null) {
							
						}
						
					} catch(IOException ex) {
						System.out.println("NO RECENT SESSION INFO FILE!");
					}
					atFirst = false;
				}
				
				final File[] fList = new File(path).listFiles();
//				List<CurrentFolderTableModel.TableEntry> 
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
//						tm.updateContent(sel);
						int i = fList.length;
					}
				});
				
				try {
					Thread.sleep(1000);		// 1 SEC
				} catch(InterruptedException e) {}
				// BROWSE FOLDER HERE
				System.out.println("ReFRESH");
			}
			
		}
		
		public void terminate() {
			running = false;
			try {
				t.join();
			} catch(InterruptedException e) {}
		}
	}
}
