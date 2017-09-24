import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;

import listeners.ScanFolderActionListener;
import refresher.Refresher;
import refresher.RefreshingContext;


public class MailerFrame extends JFrame{
	private static Font fontCourierNew = new Font("Courier New", Font.BOLD, 18);
	JPanel cards;
//	final static String BUTTONPANEL = "Card with Buttons";
//	final static String TEXTPANEL = "Card with JTextField";
	static Connection con;
//	private JToolBar toolbar = new JToolBar("Toolbar");
	private FolderContentModel folderModel = new FolderContentModel();
	private JFileChooser fc = new JFileChooser();
//	private JLabel lbCurDirectory = new JLabel("CURRENT DIRECTORY");
	private JButton bnSend = null;
	
	public MailerFrame() {
		initUI();
	}
	
	private void initUI() {
		JToolBar toolbar = new JToolBar("Toolbar");
		toolbar.setFloatable(false);
		toolbar.addSeparator();
		
		JButton btnSelectFolder = createSelectFolderButton();
		toolbar.add(btnSelectFolder);
		toolbar.add(Box.createHorizontalGlue());
		JButton sendOut = createSendOutButton();
		toolbar.add(sendOut);
		
		JTable tblEmails = new JTable(folderModel);
//		tblEmails.setEnabled(false);
//		tblEmails.getModel().addTableModelListener(this);
		tblEmails.setFillsViewportHeight(true);
		tblEmails.getColumnModel().getColumn(0).setMaxWidth(20);		// SEL
//		tblEmails.getColumnModel().getColumn(1).setPreferredWidth(500);		// Filename
//		tblEmails.getColumnModel().getColumn(2).setPreferredWidth(75);		// s / n
//		tblEmails.getColumnModel().getColumn(3).setPreferredWidth(250);		// nickname
//		tblEmails.getColumnModel().getColumn(4).setPreferredWidth(250);		// E-Mail
//		tblEmails.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane paneEmails = new JScrollPane(tblEmails);
//		paneEmails.setEnabled(false);
		
		JPanel pane = new JPanel(new GridBagLayout());
		pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
//		c.insets = new Insets(0, 0, 10, 10);
//		pane.add(toolbar, c);
		
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		pane.add(paneEmails, c);
		
		add(toolbar, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);
		
		
		setTitle("JMailer+");
		setSize(720, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initUI1() {
//		Font 
//		JButton btnChooseFolder = new JButton("<html><b>CHOOSE FOLDER..</b>");	// <br>
		JToolBar toolbar = new JToolBar("Toolbar");
		toolbar.setFloatable(false);
		toolbar.addSeparator();
		
//		toolbar.
		
		JButton btnChooseFolder = createSelectFolderButton();
//		btnChooserFolder.setFont(new Font("Ariel", Font.ITALIC, 24));
		
		
//		lbCurDirectory.setFont(new Font("Courier New", Font.PLAIN, 14));
//		lbCurDirectory.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		
		JTable tblEmails = new JTable(folderModel); 
//		stblEmails.getModel().addTableModelListener(this);
		tblEmails.setFillsViewportHeight(true);
		tblEmails.getColumnModel().getColumn(0).setPreferredWidth(10);		// SEL
		tblEmails.getColumnModel().getColumn(1).setPreferredWidth(500);		// Filename
		tblEmails.getColumnModel().getColumn(2).setPreferredWidth(75);		// s / n
		tblEmails.getColumnModel().getColumn(3).setPreferredWidth(250);		// nickname
		tblEmails.getColumnModel().getColumn(4).setPreferredWidth(250);		// E-Mail
//		tblEmails.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane paneEmails = new JScrollPane(tblEmails);
		
		createEMailButton();
		
		JPanel pane = new JPanel(new GridBagLayout());
		pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 50;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 10, 10);
		pane.add(btnChooseFolder, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 0, 0);
//		pane.add(lbCurDirectory, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 1.0;
		pane.add(paneEmails, c);
		
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.ipadx = 100;
		c.ipady = 10;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.insets = new Insets(10, 10, 10, 0);
		pane.add(bnSend, c);
		
		// ********* Temp *********
//		JButton btnTemp = new JButton("Temp");
//		btnTemp.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				((CurrentFolderTableModel)tblEmails.getModel()).declareChanged();
//			}
//		});
//		
//		c.fill = GridBagConstraints.NONE;
//		c.anchor = GridBagConstraints.EAST;
//		c.ipadx = 100;
//		c.ipady = 10;
//		c.gridx = 0;
//		c.gridy = 2;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.weightx = 1.0;
//		c.weighty = 0.0;
//		c.insets = new Insets(10, 10, 10, 0);
//		pane.add(btnTemp, c);
		
		
		
		add(pane, BorderLayout.CENTER);
		
		
		
		setTitle("JMailer+");
		setSize(720, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	// **************** JDialog "Scanning" *****************
//	private JDialog createScanningDialog() {
//		
//	}
	// ********** Create Button "Choose Folder" Pressed ***********
	private JButton createSelectFolderButton() {
		JButton chooseButton = new JButton("Select Folder");	//"<html><b>CHOOSE FOLDER</b>"
		chooseButton.setFont(new Font("Courier New", Font.PLAIN, 16));
		chooseButton.setFocusPainted(false);
		chooseButton.addActionListener(new ActionListener() {
			//new ChangeFolderActionListener(this, folderModel));
			@Override
			public void actionPerformed(ActionEvent event) {
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(MailerFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					Refresher refr = new Refresher(new RefreshingContext(fc.getSelectedFile()));
					refr.start();
				}
			}
		});
			
		return chooseButton;
	}
	
	private JButton createSelectFolderButton1() {
		// 050 234 16 55 [ÂÅÒ ÑÅÐÂÈÑ]
		
		JButton btnChooseFolder = new JButton("Select Folder");	//"<html><b>CHOOSE FOLDER</b>"
		btnChooseFolder.setFont(new Font("Courier New", Font.PLAIN, 16));
		btnChooseFolder.setFocusPainted(false);
		btnChooseFolder.addActionListener( new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent event) {
				JFileChooser fc = new JFileChooser();
				
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(MailerFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
				
					JDialog dlgScanning = new JDialog(MailerFrame.this, "No Title", true);
	//				dlgScanning.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					dlgScanning.setUndecorated(true);
					
					JLabel lbScanning = new JLabel("Scanning..", SwingConstants.CENTER);	// Scanning 	"<html><b><font size=+1>SCANNING..</font></b>"
					lbScanning.setFont(fontCourierNew); //new Font("Courier New", Font.BOLD, 18));
					lbScanning.setAlignmentX(Component.CENTER_ALIGNMENT);
	//				lbScanning.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					
					JLabel lbProgess = new JLabel("<html><b>...</b>", SwingConstants.CENTER);
					lbProgess.setAlignmentX(Component.CENTER_ALIGNMENT);
	//				lbProgess.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					
					JPanel wrapper = new JPanel();
					wrapper.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
									
					JPanel pane = new JPanel();
					pane.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
					pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	//				pane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					
					pane.add(Box.createVerticalStrut(5));
					pane.add(lbScanning);
					pane.add(Box.createVerticalStrut(5));
					pane.add(lbProgess);
					pane.add(Box.createVerticalStrut(5));
	//				lbProgess
					
					wrapper.add(pane, BorderLayout.CENTER);
					
	//				dlgScanning.add(wrapper);
					dlgScanning.add(BorderLayout.CENTER, wrapper);
	//				dlgScanning.add(BorderLayout.CENTER, lbStatus);
					dlgScanning.setSize(450, 80);
					dlgScanning.setResizable(false);
	//				dlgScanning.pack();
					dlgScanning.setLocationRelativeTo(MailerFrame.this);
	//				dlgScanning.setVisible(true);
					
					SwingWorker<Object, String> worker = new SwingWorker<Object, String>() {
						@Override
						protected Void doInBackground() throws Exception {
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									dlgScanning.setVisible(true);
								}
							});
							
							File[] files = fc.getSelectedFile().listFiles();
							for(File f : files) {
								Thread.sleep(350);
								publish(f.getName());
							}
							
//							for(int i = 0; i <= 10; i++) {
//								Thread.sleep(500);
//								publish(Integer.toString(i));
//							}
							return null;
						}
						
						protected void done() {
							dlgScanning.setVisible(false);
							lbProgess.setText("DONE!");
//							lbCurDirectory.setText(fc.getSelectedFile().getAbsolutePath());
						}
						
						@Override 
						protected void process(List<String> chunks) {
							String last = chunks.get(chunks.size() - 1);
							lbProgess.setText(last);
						}
					};
				
					worker.execute();
					
					
				}
			}
		});
		
		return btnChooseFolder;
	}
	
	// ********** On Button "Send EMails" Pressed ***********
	private JButton createSendOutButton() {
		JButton sendOutFiles = new JButton("SEND OUT");
		sendOutFiles.setFont(new Font("Courier New", Font.BOLD, 16));
		sendOutFiles.setFocusPainted(false);
		sendOutFiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				
				JDialog mailing = new JDialog(MailerFrame.this);
				mailing.setModal(true);
//				mailing.setUndecorated(true);
				mailing.setSize(400, 300);
				mailing.setLocationRelativeTo(MailerFrame.this);
				mailing.setResizable(false);
				mailing.setVisible(true);
			}
		});
		
		return sendOutFiles;
	}
	
	
	// ********** On Button "Send EMails" Pressed ***********
	private void createEMailButton() {
		bnSend = new JButton("Send");
		bnSend.setFont(new Font("Courier New", Font.ITALIC, 18));
//		bnSend.setBorderPainted(false);
		bnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
//				JDialog dlgSendSettings = new JDialog(JMailerFrame.this, "Mailing", true);
//				
//				JPanel pane
//				emailsTModel.
			}
		});
	}
	
	
	private void initUI2() {
//		ChooseFolderAction chAct = new ChooseFolderAction(this);
		//getContentPane().add(createUITree(), BorderLayout.CENTER);
		
		JPanel top = new JPanel();
		top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
		
		String[] recentDirs = { "File1", "File2", "File3", "File4" };
		JComboBox recentList = new JComboBox(recentDirs);
		recentList.setMaximumSize(new Dimension(700, recentList.getPreferredSize().height));
		recentList.setSelectedIndex(-1);
		
//		String[] openOpt = { "Scripts (.IPR)", "Archieves (.RAR and .ZIP)", "Folders" };
//		JComboBox optionsList = new JComboBox(openOpt);
//		optionsList.setMaximumSize(new Dimension(200, recentList.getPreferredSize().height));
//		optionsList.setSelectedIndex(0);
		
		JButton browseFolder = new JButton("Browse..");
		browseFolder.setMaximumSize(new Dimension(150, browseFolder.getPreferredSize().height));
		browseFolder.addActionListener(new ScanFolderActionListener(this, recentList));
		
		JButton btnScanOptions = new JButton("Options");
		btnScanOptions.setMaximumSize(new Dimension(btnScanOptions.getPreferredSize().height,
														btnScanOptions.getPreferredSize().height));
		
//		btnScanOptions.setMaximumSize(new Dimension(50,
//				50));
		
		btnScanOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ScanOptionsDialog scanOptDlg = new ScanOptionsDialog(MailerFrame.this);
				//scanOptDlg.setResizeble(false);
				scanOptDlg.setVisible(true);
			
			}
		});
		
		
		FolderContentModel emailsData = new FolderContentModel();
		JTable emailsTable = new JTable(emailsData);
		JScrollPane emailsScroll = new JScrollPane(emailsTable);
		emailsTable.setFillsViewportHeight(true);
//		JButton browseArchives = new JButton("Archives (.RAR and .ZIP)");
//		browseArchives.setMaximumSize(new Dimension(150, browseArchives.getPreferredSize().height));
//		
//		JButton browseFolder = new JButton("Browse..");
//		browseFolders.setMaximumSize(new Dimension(150, browseFolders.getPreferredSize().height));
		
		
		top.add(recentList);
//		top.add(optionsList);
		top.add(Box.createHorizontalStrut(10));
		top.add(browseFolder);
		top.add(Box.createHorizontalStrut(50));
		top.add(btnScanOptions);
//		top.add(browseArchives);
//		top.add(browseFolders);
		add(top, BorderLayout.NORTH);
		add(emailsScroll, BorderLayout.CENTER);
		
		
//		JPanel card1 = new JPanel();
//		JPanel card2 = new JPanel();
//		
//		cards = new JPanel(new CardLayout());
//		cards.add(card1, BUTTONPANEL);
//		cards.add(card2, TEXTPANEL);
//		
//		JPanel comboBoxPane = new JPanel();
//		String comboBoxItems[] = {	BUTTONPANEL, TEXTPANEL	};
//		JComboBox cb = new JComboBox(comboBoxItems);
//		cb.setEditable(false);
//		cb.addItemListener(this);
//		comboBoxPane.add(cb);
		
		
//		pane.add(comboBoxPane, BorderLayout.PAGE_START);
//		pane.add(cards, BorderLayout.CENTER);
		
		setTitle("JMailer+");
		setSize(720, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JPanel createUITree()
	{
		JPanel	mp = new JPanel(new BorderLayout());
		
		UsersTableModel uTModel = new UsersTableModel();
		JTable usersTable = new JTable(uTModel);
		JScrollPane usersSP = new JScrollPane(usersTable);
		usersTable.setFillsViewportHeight(true);
		mp.add(usersSP, BorderLayout.WEST);
		
		FolderContentModel rptTableModel = new FolderContentModel();
		JTable rptTable = new JTable(rptTableModel);
		JScrollPane rptSP = new JScrollPane(rptTable);
		rptTable.setFillsViewportHeight(true);
		mp.add(rptSP, BorderLayout.EAST);
		
//		JButton cb = new JButton(new ChooseFolderAction(this));
//		mp.add(cb, BorderLayout.SOUTH);
		return mp;
	}
	
	// ************************* OBSERVER *************************
	public void update(MyObservable obs, Object obj) {
		
	}
	
//	public void itemStateChanged(ItemEvent evt)	{
//		CardLayout cl = (CardLayout)(cards.getLayout());
//		cl.show(cards, (String)evt.getItem());
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Working Dir: " + System.getProperty("user.dir"));
		UserDetailsLoadingWindow.splash();	;// splash = new UserDetailsLoadingWindow(null);
		
//		EventQueue.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				MailerFrame mFrm = new MailerFrame();
//				mFrm.setVisible(true);
//			}
//		});
	}

}
