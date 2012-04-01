import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class PhotoEditorPage extends Composite{
	private class PaintData{
		public String content;
		public int x;
		public int y;
		public PaintData(String content, int x, int y) {
			this.content = content;
			this.x = x;
			this.y = y;
		}
	}
	private int height = 500;
	private int width = 500;
	private Image newImage = null;
	private LinkedList<ArrayList<PaintData>> stack = null;
	private Canvas canvas = null;
	
	public PhotoEditorPage(Composite parent, int style, final Image image) {
		super(parent, style);
		stack = new LinkedList<ArrayList<PaintData>>();
		
		//bg->
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);
		
		final Group left = new Group(this, SWT.None);
		left.setLayoutData(new GridData(width,height));
		
		Group right = new Group(this, SWT.None);
		right.setLayoutData(new GridData(200,height));
		
		//left->
		FillLayout leftLayout = new FillLayout();
		left.setLayout(leftLayout);
		canvas = new Canvas(left, SWT.None);
		canvas.addPaintListener(new PaintListener(){
			public void paintControl(PaintEvent e) {
				newImage = new Image(getDisplay(), image.getImageData().scaledTo(width, height));
				GC gc = new GC(newImage);
				if(!stack.isEmpty()) paint(gc);
				e.gc.drawImage(newImage, 0, 0);
			}
		});
		canvas.addMouseListener(new AddTextListener());
		
		//right->
		GridLayout rightLayout = new GridLayout();
		rightLayout.numColumns = 1;
		right.setLayout(rightLayout);
		
		Button auto = new Button(right, SWT.PUSH);
		GridData autoData = new GridData(180, 70);
		auto.setLayoutData(autoData);
		auto.setText("Auto-Generate");
		auto.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Shell shell = new Shell(getShell());
				shell.setText("Auto Generate");
				Composite auto = new AutoGenerate(shell, SWT.None);
				auto.pack();
				shell.pack();
				shell.open();
			}
		});
		
		Button undo = new Button(right, SWT.PUSH);
		GridData undoData = new GridData(180, 70);
		undo.setLayoutData(undoData);
		undo.setText("Undo");
		undo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				if(!stack.isEmpty()) {
					stack.pop();
					canvas.redraw();
				}
			}
		});
		
		Button save = new Button(right, SWT.PUSH);
		GridData saveData = new GridData(180, 70);
		save.setLayoutData(saveData);
		save.setText("Save As");
		save.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				ImageLoader imageLoader = new ImageLoader();
				imageLoader.data = new ImageData[] {newImage.getImageData()};
				FileDialog fileDialog = new FileDialog(new Shell(getDisplay()), SWT.SAVE);
				fileDialog.setFilterExtensions(new String[]{"*.jpg"});
				String filePath = fileDialog.open();
				if(filePath!=null) imageLoader.save(filePath, SWT.IMAGE_JPEG);
			}
		});
		
		Group resize = new Group(right, SWT.None);
		GridData resizeData = new GridData(180, 80);
		resize.setLayoutData(resizeData);
		resize.setText("Resize");
		
		Label note = new Label(right, SWT.None);
		GridData noteData = new GridData(180, 50);
		note.setLayoutData(noteData);
		note.setText("Double click on the picture to add\ntext manually");
		
		Button quit = new Button(right, SWT.PUSH);
		GridData quitData = new GridData(180, 50);
		quit.setLayoutData(quitData);
		quit.setText("Cancel");
		quit.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				getParent().dispose();
			}
		});
		//right->resize->
		GridLayout resizeLayout = new GridLayout();
		resizeLayout.numColumns = 2;
		resize.setLayout(resizeLayout);
		
		Label widthLabel = new Label(resize, SWT.None);
		widthLabel.setLayoutData(new GridData(50, 20));
		widthLabel.setText("Width:");
		
		final Text widthText = new Text(resize, SWT.None);
		widthText.setLayoutData(new GridData(100, 20));
		widthText.setText("" + width);
		
		Label heightLabel = new Label(resize, SWT.None); 
		heightLabel.setText("Height:");
		heightLabel.setLayoutData(new GridData(50,20));
		
		final Text heightText = new Text(resize, SWT.None);
		heightText.setLayoutData(new GridData(100, 20));
		heightText.setText("" + height);
		
		Button resizeButton = new Button(resize, SWT.None);
		resizeButton.setText("Resize");
		GridData resizeButtonData = new GridData(50,20);
		resizeButtonData.horizontalSpan = 2;
		resizeButtonData.horizontalAlignment = SWT.RIGHT;
		resizeButton.setLayoutData(resizeButtonData);
		resizeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				width = Integer.parseInt(widthText.getText());
				height = Integer.parseInt(heightText.getText()); 
				getParent().setSize(width+200, height);
				left.setLayoutData(new GridData(width, height));
				getComposite().pack();
				getParent().pack();
				canvas.redraw();
			}
		});
	}
	
	public void paint(GC gc){
		//getLast doesn't work properly therefore I used this
		ArrayList<PaintData> current = stack.pop();
		stack.push(current);
		for(int i=0; i<current.size(); i++){
			gc.drawText(current.get(i).content, current.get(i).x, current.get(i).y, true);
		}
	}
	public Composite getComposite(){
		return this;
	}
	
	public class AutoGenerate extends Composite{
		public AutoGenerate(Composite parent, int style) {
			super(parent, style);
			this.setSize(300, 200);
			FillLayout layout = new FillLayout(SWT.VERTICAL);
			layout.spacing = 10;
			layout.marginHeight = 10;
			layout.marginWidth = 10;
			this.setLayout(layout);
			
			final Button eventName = new Button(this, SWT.CHECK);
			eventName.setText("Event Name");
			
			final Button eventDescription = new Button(this, SWT.CHECK);
			eventDescription.setText("Event Description");
			
			Button generate = new Button(this, SWT.PUSH);
			generate.setText("Generate");
			generate.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e){
					ArrayList<PaintData> current = new ArrayList<PaintData>();
					if(eventName.getSelection()) current.add(new PaintData(SessionManager.getCurrentEvent().getEventName(), 100, 300));
					if(eventDescription.getSelection()) current.add(new PaintData(SessionManager.getCurrentEvent().getEventDescription(), 100, 350));
					stack.push(current);
					canvas.redraw();
					getShell().dispose();
				}
			});
		}
	}
	private class AddTextListener implements MouseListener{
		public void mouseDoubleClick(MouseEvent e) {
			final int x;
			final int y;
			x = e.x;
			y = e.y;
			final Text text = new Text(canvas, SWT.None);
			text.setFocus();
			text.setBounds(x, y, 100, 20);
			text.addKeyListener(new KeyListener(){
				public void keyReleased(KeyEvent e) {}
				public void keyPressed(KeyEvent e) {
					if(e.keyCode == SWT.CR){
						String content = text.getText();
						ArrayList<PaintData> current;
						if(!stack.isEmpty()){
							ArrayList<PaintData> temp = stack.pop();
							stack.push(temp);
							current = (ArrayList<PaintData>) temp.clone();
						}
						else{
							current = new ArrayList<PaintData>();
						}
						current.add(new PaintData(content, x, y));
						stack.push(current);
						canvas.redraw();
						text.dispose();
					}
					if(e.keyCode == SWT.ESC){
						text.dispose();
					}
				}
			});
		}
		public void mouseDown(MouseEvent e) {}
		public void mouseUp(MouseEvent e) {}
		
	}
	
}