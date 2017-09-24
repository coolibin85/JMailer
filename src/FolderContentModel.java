import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class FolderContentModel extends AbstractTableModel {
	
	
	public static class TableEntry {
		public boolean checked;
		public String filepath;
		public String serial;
		public String nickname;
		public String email;
		
//		private String filePath;
		
		public TableEntry(String filename, String serial, String nickname, String email, boolean checked) {
			this.checked = checked;
			this.filepath = filename;
			this.serial = serial;
			this.nickname = nickname;
			this.email = email;
		}
	}
	
	
	
	private String[] columnNames = { "", "File", "S / N", "Nickname", "EMail" };
	
//	private Map<Integer, EmailEntry> entryByKey = new HashMap<Integer, EmailEntry>(); 
	private List<TableEntry> entries = new LinkedList<TableEntry>();
	
	public FolderContentModel() {
//		File f = new File(System.getProperty("user.dir"));
//		File files[] = f.listFiles();
	}

//	public void 
//	EmailEntry getEntryByIndex() {
		//return new EmailEntry();
//	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return entries.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		TableEntry e = entries.get(row);
		if(col == 0) return e.checked;
		if(col == 1) return e.filepath;
		if(col == 2) return e.serial;
		if(col == 3) return e.nickname;
		if(col == 4) return e.email;
		return null;
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass(); 
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		if(col == 0) return true;	// checked
		return false;
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		TableEntry e = entries.get(row);
		if(col == 0) e.checked = (boolean)value;
		if(col == 1) e.filepath = (String)value;
		if(col == 2) e.serial = (String)value;
		if(col == 3) e.nickname = (String)value;
		if(col == 4) e.email = (String)value;
		fireTableCellUpdated(row, col);
	}
	
	public void declareChanged() {
//		fireTableDataChanged();
//		fireTableCellUpdated(2, 5);
	}
	
	public void updateContent(List<TableEntry> entries) {
		this.entries = entries;
		this.fireTableDataChanged();
	}
}
