import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


public class UsersTableModel extends AbstractTableModel {
	class UserEntry {
		public String filePath;
		public String serial;
		public String nickName;
		public String email;
		
		public UserEntry(String filePath, String serial, String nickName, String email) {
			this.filePath = filePath;
			this.serial = serial;
			this.nickName = nickName;
			this.email = email;
		}
		
	}
//	class iProgUser {
//		
//	}
//	private Map<Integer, List<String>> progUsers = new LinkedHashMap<Integer, List<String>>();
	private Map<Integer, Integer> bySerial = new HashMap<Integer, Integer>();
	private List<UserEntry> userList = new ArrayList<UserEntry>();
	
	private String[] columnNames = { "File", "Serial #", "Nickname", "E-Mail" };
//	private Object[][] data = {	{	new Integer(327),	"coolibin", "coolibin@mail.ru"	},
//								{	new Integer(756),	"ivanc", 	"ivanc72@gmail.com"	}	};
	
	public UsersTableModel() {
		BufferedReader br = null;
		
		String fileName = System.getProperty("user.dir") + "\\users.lst";
//		System.out.println("Working Directory : " + fileName);
		File f = new File(fileName);
		if(f.exists())
		{
//			JOptionPane.showMessageDialog(null, "File Exist!");
			try {
//				UserEntry userEntry = null;
//				List<String> userData = null;
				String textLine;
				Integer srlNum = null;
				String	serial = null,
						nickName = null,
						email = null;
				
				int lineId;
				
				br = new BufferedReader(new FileReader(f));
				lineId = 0;
				while((textLine = br.readLine()) != null) {
					if(lineId == 0) {						// serial #
						srlNum = Integer.parseInt(textLine);
						serial = new String(textLine);
//						userData = new ArrayList<String>();
//						userEntry = new UserEntry();
						lineId = 1;
					}
					else if(lineId == 1) {					// nickname
//						userData.add(new String(textLine));
						nickName = new String(textLine);
						lineId = 2;
					}
					else {									// email
						email = new String(textLine);
//						userEntry = new UserEntry(serial, nickName, email);
						userList.add(new UserEntry(serial, serial, nickName, email));
						bySerial.put(srlNum, userList.size() - 1);
//						userData.add(new String(textLine));
//						progUsers.put(serial, userData);
						
						lineId = 0;
//						email = new String(textLine);
//						progUsers.put
//						userData = new ArrayList<String>();
//						userData.add(nickName);
//						userData.add(email);
						
						
					}
				}
				
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(br != null)	br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}
//		System.out.println("Working Directory : " + System.getProperty("user.dir"));
//		JOptionPane.showMessageDialog(null, System.getProperty("user.dir"));
		
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;	// serial number/nickname/email
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return userList.size();
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		UserEntry userE = userList.get(row);
		switch(col) {
		case 0 :
			return userE.serial; 
		case 1 :
			return userE.nickName;
		case 2 :
			return userE.email;
		default :
			return null;
		}
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}

//	public getUserDataBySerial(srlNum) {
//		
//	}
}
