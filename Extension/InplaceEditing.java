package Extension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class InplaceEditing {
	
	public static void main(String[] args){
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final AdvancedTable table = new AdvancedTable(shell, SWT.BORDER | SWT.FULL_SELECTION);
		final TableEditor editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		
		
		for(int i=0; i<3; i++){
			TableColumn col = new TableColumn(table, SWT.None);
		}
		table.getColumn(0).setText("Column1");
		table.getColumn(1).setText("Column2");
		table.getColumn(2).setText("Column3");

		for(int i=0; i<3; i++){
			TableItem item = new TableItem(table, SWT.None);
			item.setText(new String[]{"Hello","world","!!!"});
		}

		for(int i=0; i<3; i++){
			table.getColumn(i).pack();
		}

		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				Control control = editor.getEditor();
				
				if(control!=null) control.dispose();
				
				TableItem item = (TableItem)e.item;

				Text newEditor = new Text(table,SWT.None);
				newEditor.selectAll();
				//newEditor.setFocus();
			}
		});

		shell.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
	/*
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());

		// create a a table with 3 columns and fill with data
		final Table table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableColumn column1 = new TableColumn(table, SWT.NONE);
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		TableColumn column3 = new TableColumn(table, SWT.NONE);
		for (int i = 0; i < 100; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "cell "+i+" 0", "cell "+i+" 1", "cell "+i+" 2"});
		}
		column1.pack();
		column2.pack();
		column3.pack();

		// create a TableCursor to navigate around the table
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		// create an editor to edit the cell when the user hits "ENTER" 
		// while over a cell in the table
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;

		cursor.addSelectionListener(new SelectionAdapter() {
			// when the TableEditor is over a cell, select the corresponding row in 
			// the table 
			//used when the selection is a single click
			//when there is a single click, it does nothing but make a selection
			public void widgetSelected(SelectionEvent e) {
				table.setSelection(new TableItem[] {cursor.getRow()});
			}
			// when the user hits "ENTER" in the TableCursor, pop up a text editor so that 
			// they can change the text of the cell
			public void widgetDefaultSelected(SelectionEvent e){
				final Text text = new Text(cursor, SWT.NONE); //not tableEditor???
				TableItem row = cursor.getRow();
				int column = cursor.getColumn();
				text.setText(row.getText(column));
				text.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						// close the text editor and copy the data over 
						// when the user hits "ENTER"
						if (e.character == SWT.CR) {
							TableItem row = cursor.getRow();
							int column = cursor.getColumn();
							row.setText(column, text.getText());
							text.dispose();
						}
						// close the text editor when the user hits "ESC"
						if (e.character == SWT.ESC) {
							text.dispose();
						}
					}
				});
				editor.setEditor(text);
				text.setFocus();
			}
		});
		// Hide the TableCursor when the user hits the "MOD1" or "MOD2" key.
		// This allows the user to select multiple items in the table.
		cursor.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.MOD1 || 
						e.keyCode == SWT.MOD2 || 
						(e.stateMask & SWT.MOD1) != 0 || 
						(e.stateMask & SWT.MOD2) != 0) {
					cursor.setVisible(false);
				}
			}
		});
		// Show the TableCursor when the user releases the "MOD2" or "MOD1" key.
		// This signals the end of the multiple selection task.
		table.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.MOD1 && (e.stateMask & SWT.MOD2) != 0) return;
				if (e.keyCode == SWT.MOD2 && (e.stateMask & SWT.MOD1) != 0) return;
				if (e.keyCode != SWT.MOD1 && (e.stateMask & SWT.MOD1) != 0) return;
				if (e.keyCode != SWT.MOD2 && (e.stateMask & SWT.MOD2) != 0) return;

				TableItem[] selection = table.getSelection();
				TableItem row = (selection.length == 0) ? table.getItem(table.getTopIndex()) : selection[0];
				table.showItem(row);
				cursor.setSelection(row, 0);
				cursor.setVisible(true);
				cursor.setFocus();
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	*/

}
