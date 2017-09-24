import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;


public class UserDetailsLoadingWindow extends JWindow {
//	JFrame mainFrm = null;
	private static final String USERS_DETAILS_FILENAME = "Users.dtl";
	private JLabel uiInfoLbl = new JLabel("Loading..", JLabel.CENTER);
	
//	private static List<> userDetails = new LinkedList<>();
	private UserDetailsLoadingWindow() {
		//super(parent);
//		JLabel l = new JLabel("LOAD DATA..");
//		JPanel p1 = new JPanel(new GridBagLayout());
//		p1.add(uiInfoLbl);//, BorderLayout.CENTER);
		getContentPane().add(uiInfoLbl, BorderLayout.CENTER);
//		pack();
		setSize(400, 100);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		Runnable closerRunner = new Runnable() {
			@Override
			public void run() {
				instance.setVisible(false);
				instance.dispose();
			}
		};
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
//				SwingUtilities.invokeLater(new Runnable() {
//					@Override
//					public void run() {
//						uiInfoLbl.setText("Loading..");
//					}
//				});
				
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException ex) {
//					ex.printStackTrace();
//				}
				// LOAD FILE WITH USER DETAILS
				// UPDATE PROGRESS
				FileReader fr = null;
				BufferedReader br = null;
				
				try {
					fr = new FileReader(System.getProperty("user.dir")
											+ "//" + USERS_DETAILS_FILENAME);
					br = new BufferedReader(fr);
					String sLine;
					while((sLine = br.readLine()) != null) {
						final String sTemp = sLine; 
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								uiInfoLbl.setText(sTemp);
							}
						});
						
						Thread.sleep(3);
					}
					
				} catch(IOException ex) {
					ex.printStackTrace();
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				} finally {
					try {
						if(br != null)
							br.close();
						if(fr != null)
							fr.close();
					} catch(IOException ex) {
						ex.printStackTrace();
					}
				}
				
				// CLOSE WINDOW IN THE END
				SwingUtilities.invokeLater(closerRunner);
			}
		});
		t.start();
		
	}
	
	public static boolean createUsersDetailFile(String filename) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		StringBuffer strBuf = new StringBuffer(100);
		boolean result = false;
		
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			
			for(int i = 0; i < 1000; i++) {
				strBuf.setLength(0);
				strBuf.append("Nickname");
				strBuf.append(String.format("%05d", i));
				strBuf.append("*");
				strBuf.append(String.format("%05d", i));
				strBuf.append("*");
				strBuf.append("Nickname");
				strBuf.append(Integer.toString(i));
				strBuf.append("@gmail.com");
				strBuf.append(System.getProperty("line.separator"));
				bw.write(strBuf.toString());
			}
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(bw != null)
					bw.close();
				if(fw != null)
					fw.close();
				result = true;	
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static void splash() {
		createUsersDetailFile(System.getProperty("user.dir") + "//Users.dtl");
		instance = new UserDetailsLoadingWindow();
//		instance.setVisible(true);
//		instance.runProcess();
//		
//
//		
//		
//		
//		instance.setVisible(false);
//		instance.dispose();
	}
	
	private static UserDetailsLoadingWindow instance;
}
