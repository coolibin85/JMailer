import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ScanOptionsDialog extends JDialog implements ActionListener {
	
//	private JLabel lbFolders = new JLabel("Folders");
//	private JLabel lbArchives = new JLabel("RARs + ZIPs");
//	private JLabel lbIRP = new JLabel("iProg+ Script Files");
	//private JLabel lbFolder = new JLabel("Folder");
	
//	
	
	
	private static class FixedWidthButton extends JButton {
		public FixedWidthButton(String text) {
			super(text);
		}
		
		@Override
		public Dimension getPreferredSize() {
//			System.out.println("getPreferredSize()");
			return (new Dimension(100, super.getPreferredSize().height));
		}
		
		@Override
		public Dimension getMinimumSize() {
//			System.out.println("getMinimumSize()");
			return (new Dimension(100, super.getPreferredSize().height));
		}
		
		@Override
		public Dimension getMaximumSize() {		// not USED by GridBagLayout !! But...)))
//			System.out.println("getMaximumSize()");
			return (new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		}
	}
	
	private class ApplyScanOptionsActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			
		}
	}
	
	private class CloseDialogActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			dispose();
		}
	}
	
	
	private JCheckBox chbFolders = new JCheckBox("Subfolders ( 1 level )");
	private JCheckBox chbArchives = new JCheckBox("RARs + ZIPs");
	private JCheckBox chbIPRs = new JCheckBox("IPRs");
	
	private FixedWidthButton buttonOk = new FixedWidthButton("OK");
	private FixedWidthButton buttonCancel = new FixedWidthButton("Cancel");

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == buttonOk)
		{
			
		}
	}
	
	public ScanOptionsDialog(JFrame parent) {
		super(parent, "Folder Scanning Options", true);
		
		setupForm();
		
		
		pack();
		setResizable(false);
//		setSize(300, 300);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}
	
	
	private void setupForm() {
		
//		System.out.println("PREF. FOLDERS SIZE : " + chbFolders.getPreferredSize().toString());
//		System.out.println("PREF. ARCHIVES SIZE : " + chbArchives.getPreferredSize().toString());
//		System.out.println("PREF. IPRS SIZE : " + chbIPRs.getPreferredSize().toString());
//		
//		System.out.println("MIN. FOLDERS SIZE : " + chbFolders.getMinimumSize().toString());
//		System.out.println("MIN. ARCHIVES SIZE : " + chbArchives.getMinimumSize().toString());
//		System.out.println("MIN. IPRS SIZE : " + chbIPRs.getMinimumSize().toString());
//		
//		System.out.println("MAX. FOLDERS SIZE : " + chbFolders.getMaximumSize().toString());
//		System.out.println("MAX. ARCHIVES SIZE : " + chbArchives.getMaximumSize().toString());
//		System.out.println("MAX. IPRS SIZE : " + chbIPRs.getMaximumSize().toString());
		
//		System.out.println("PREF. OK SIZE : " + buttonOk.getPreferredSize().toString());
//		System.out.println("MIN. OK SIZE : " + buttonOk.getMinimumSize().toString());
//		System.out.println("MAX. OK SIZE : " + buttonOk.getMaximumSize().toString());
//		
//		System.out.println("PREF. CANCEL SIZE : " + buttonCancel.getPreferredSize().toString());
//		System.out.println("MIN. CANCEL SIZE : " + buttonCancel.getMinimumSize().toString());
//		System.out.println("MAX. CANCEL SIZE : " + buttonCancel.getMaximumSize().toString());
		
//		chbFolders.setHorizontalAlignment(SwingConstants.LEFT);
//		chbFolders.setHorizontalAlignment(SwingConstants.LEFT);
//		chbFolders.setHorizontalAlignment(SwingConstants.LEFT);
//		chbFolders.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
//		chbArchives.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
//		chbIPRs.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
		
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
//		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		
		JPanel panelChecks = new JPanel();
		panelChecks.setLayout(new BoxLayout(panelChecks, BoxLayout.PAGE_AXIS));
		
//		panelChecks.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
//		chbFolders.setAlignmentX(LEFT_ALIGNMENT);
//		chbArchives.setAlignmentX(LEFT_ALIGNMENT);
//		chbArchives.setAlignmentX(LEFT_ALIGNMENT);
		
		panelChecks.add(Box.createVerticalGlue());
		panelChecks.add(Box.createVerticalStrut(10));
		panelChecks.add(chbFolders);
		panelChecks.add(Box.createVerticalStrut(10));
		panelChecks.add(chbArchives);
		panelChecks.add(Box.createVerticalStrut(10));
		panelChecks.add(chbIPRs);
		panelChecks.add(Box.createVerticalStrut(10));
		panelChecks.add(Box.createVerticalGlue());
		
//		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.VERTICAL;
		
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 2;
//		pane.add(chbFolders, c);
//
//		c.gridx = 0;
//		c.gridy = 1;
//		c.gridwidth = 2;
//		pane.add(chbArchives, c);
//
//		c.gridx = 0;
//		c.gridy = 2;
//		c.gridwidth = 2;
//		pane.add(chbIPRs, c);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
//		c.weightx = 1.0;
		c.weighty = 0.5;
		
		pane.add(panelChecks, c);
		
//		c.anchor = GridBagConstraints.CENTER;
		
		c.insets = new Insets(5, 5, 5, 5);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
//		c.weightx = 0.5;
//		c.weighty = 0.5;
//		c.ipadx = 200;
		pane.add(buttonOk, c);
		
		//c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
//		c.weightx = 0.5;
//		c.weighty = 0.5;
//		c.ipadx = 200;
		pane.add(buttonCancel, c);
		
		
		
		add(pane);
	}
	
	private void setupForm1() {
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		chbFolders.setAlignmentX(CENTER_ALIGNMENT);
		chbArchives.setAlignmentX(CENTER_ALIGNMENT);
		chbIPRs.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel panelChboxes = new JPanel();
		panelChboxes.setLayout(new BoxLayout(panelChboxes, BoxLayout.PAGE_AXIS));
//		panelChboxes.setBorder(BorderFactory.createTitledBorder("Title"));// createTitledBorder
		panelChboxes.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		
		
		panelChboxes.add(chbFolders);
		panelChboxes.add(Box.createVerticalStrut(10));
		panelChboxes.add(chbArchives);
		panelChboxes.add(Box.createVerticalStrut(10));
		panelChboxes.add(chbIPRs);
//		panelChboxes.add(Box.createVerticalGlue());
		
		pane.add(panelChboxes);
		add(pane);
	}
	
}
