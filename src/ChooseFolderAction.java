import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class ChooseFolderAction extends AbstractAction {
	JFrame 			mFrm;
	JFileChooser	fc = new JFileChooser();
	
	
	public ChooseFolderAction(JFrame mFrm) {
		super("Choose Folder To Send...");
		
		this.mFrm = mFrm;
//		putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_A));
		
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(mFrm);
		
		if(returnVal == JFileChooser.APPROVE_OPTION)	{

			File files[] = fc.getCurrentDirectory().listFiles();
		}
	}

}
