package listeners;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;


public class ScanFolderActionListener implements ActionListener {
	JFrame mainFrm = null;
	JComboBox recentList = null;
	JDialog dlgScanning = null;
	JLabel lbStatus = null;
	JFileChooser fc = null;
	
	// 095 7988841
	
	
	
	public ScanFolderActionListener(JFrame mfrm, JComboBox rlst) {
		mainFrm = mfrm;
		recentList = rlst;
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		
		dlgScanning = new JDialog(mainFrm, "Scanning...", true);
//		JPanel dlgPane = (JPanel)dlgScanning.getContentPane();
//		dlgPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));//BorderFactory.createLineBorder(Color.GRAY));
		dlgScanning.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dlgScanning.setUndecorated(true);
//		dlgScanning.setSize(500, 70);
		
		
		JLabel lbProcessing = new JLabel("Scanning..");
//		lbProcessing.setFont(new Font());
		lbProcessing.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		lbStatus = new JLabel("...");
		lbStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbStatus.setMinimumSize(new Dimension(500, 30));
		lbStatus.setPreferredSize(new Dimension(500, 30));
		lbStatus.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		JPanel mainPnl = new JPanel();
		mainPnl.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));//.createEmptyBorder(10, 10, 10, 10));
		mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.PAGE_AXIS));
		mainPnl.setPreferredSize(new Dimension(500, 70));
//		
		mainPnl.add(Box.createVerticalStrut(5));
		mainPnl.add(lbProcessing);
		mainPnl.add(Box.createVerticalStrut(5));
		mainPnl.add(lbStatus);
		mainPnl.add(Box.createVerticalStrut(5));
		
		
		
		dlgScanning.add(BorderLayout.NORTH, mainPnl);
//		dlgScanning.add(BorderLayout.CENTER, lbStatus);
		dlgScanning.pack();
		dlgScanning.setLocationRelativeTo(mainFrm);
	}
	
	
	public class ScanFolderTask extends SwingWorker<String, String> {
	
		
		@Override
		protected String doInBackground() throws Exception {
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					dlgScanning.setVisible(true);
				}
			});
			
			File dir = fc.getSelectedFile();
			File[] files = dir.listFiles();
			
			for(int i = 0; i < files.length; i++) {
				Thread.sleep(150);
				publish(files[i].getName());
			}
			
			return dir.getAbsolutePath();
		}
		
		@Override
		protected void process(List<String> chunks) {
			String last = chunks.get(chunks.size() - 1);
			lbStatus.setText(last);
		}
		
		@Override
		protected void done() {
			dlgScanning.setVisible(false);
//			recentList.addItem("new dir");
//			String scennedDir = null;
			try {
//				dlgScanning.setVisible(false);
				String scannedDir = get();
//				recentList.addItem(scannedDir);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			catch(ExecutionException e) {
				e.printStackTrace();
			}
//			
//			
//			SwingUtilities.invokeLater(new Runnable() {
//				@Override
//				public void run() {
//
//				}
//			});
			
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
//		JFileChooser fc = new JFileChooser();
		
		
		int returnVal = fc.showOpenDialog(mainFrm);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			new ScanFolderTask().execute();
			
		}
		
		
//		dlgScanning.setVisible(true);
	}

}
