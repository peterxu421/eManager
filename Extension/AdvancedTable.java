package Extension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class AdvancedTable extends Table{
	private TableCursor cursor;
	
	protected void checkSubclass(){
		return;
	}
	public AdvancedTable(Composite parent, int style) {
		super(parent, style);
		cursor = new TableCursor(this, SWT.None);
	}
	
}
	

