import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;

public class imageCreator extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	String[] stringArray;
	Composite composite;
	String[] stringArray1;
	Composite composite1 = this;
	Image initialImage;
	Label imageLabel;
	final Image scaled;
	private GC gc;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public imageCreator(Composite parent, int style) {
		super(parent, style);

		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 20, 678, 425);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		// loading image
		initialImage = new Image(getDisplay(), "C:/Users/xu/Desktop/girls.jpg");

		// loading image
		initialImage = new Image(getDisplay(), "resources/girls.jpg");

		// setting String[] to all 0
		stringArray = new String[3];
		for (int i = 0; i < 3; i++) {
			stringArray[i] = "abc";
		}

		// code to check if image is small enough to fit in
		int width = initialImage.getBounds().width;
		int height = initialImage.getBounds().height;
		// if not scale it down
		scaled = new Image(getDisplay(), initialImage.getImageData().scaledTo(
				(int) (width * 0.75), (int) (height * 0.75)));

		// set draws text
		gc = new GC(scaled);
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		gc.drawText(stringArray[0], 10, 10, true);
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		gc.drawText(stringArray[1], 10, 150, true);
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		gc.drawText(stringArray[2], 10, 200, true);

		gc.dispose();

		Button btnImageCreatorImport = new Button(composite, SWT.NONE);
		// btnImageCreatorImport.addSelectionListener(new
		// imageCreatorOpenPage());
		btnImageCreatorImport.setBounds(574, 25, 94, 28);

		toolkit.adapt(btnImageCreatorImport, true, true);

		btnImageCreatorImport.setText("Import..");

		Button btnImageCreatorEdit = new Button(composite, SWT.NONE);
		btnImageCreatorEdit.addSelectionListener(new imageCreatorEditPage());
		btnImageCreatorEdit.setBounds(574, 58, 94, 28);

		toolkit.adapt(btnImageCreatorEdit, true, true);

		btnImageCreatorEdit.setText("Edit");

		Button btnImageCreatorSave = new Button(composite, SWT.NONE);
		// btnImageCreatorSave.addSelectionListener(new imageCreatorSavePage());
		btnImageCreatorSave.setBounds(574, 92, 94, 28);

		toolkit.adapt(btnImageCreatorSave, true, true);

		btnImageCreatorSave.setText("Save");

		Button btnImageCreatorClear = new Button(composite, SWT.NONE);
		// btnImageCreatorClear.addSelectionListener(new
		// imageCreatorClearPage());
		btnImageCreatorClear.setBounds(574, 126, 94, 28);
		toolkit.adapt(btnImageCreatorClear, true, true);
		btnImageCreatorClear.setText("Clear");

		// draws the image
		imageLabel = new Label(composite, SWT.None);
		imageLabel.setBounds(50, 25, 382, 331);
		imageLabel.setImage(scaled);
		Canvas canvas = new Canvas(composite, SWT.NO_REDRAW_RESIZE);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawImage(scaled, 0, 0);
				imageLabel.redraw();
			}
		});
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		FillLayout fill = new FillLayout();
		imageCreator imgCreator = new imageCreator(shell, SWT.NONE);

		// Canvas canvas = new Canvas(shell, SWT.NONE);
		// final Image image_2 = new Image(display,
		// "/Users/niwde/Desktop/new_imgres.jpeg");

		/*
		 * canvas.addPaintListener(new PaintListener() { public void
		 * paintControl(PaintEvent e) { e.gc.drawImage(image_2, 0, 0); } });
		 */
		/*
		 * Canvas canvas = new Canvas(shell, SWT.NO_REDRAW_RESIZE);
		 * canvas.addPaintListener(new PaintListener() {
		 * 
		 * @Override public void paintControl(PaintEvent e) {
		 * e.gc.drawImage(initialImage, 0,0); e.gc.drawImage(editedImage, 0,0);
		 * //e.gc.drawImage(quaterImage, 280, 0); } }
		 */

		/*
		 * //code to use the color RGB cyanRGB = new RGB(0,255,255); Color
		 * cyanColor = new Color(display, cyanRGB); //code to use cyanColor
		 * cyanColor.dispose();
		 * 
		 * //Image idealImage = new
		 * ImageData("/Users/niwde/Desktop/imgres.jpeg");
		 * 
		 * ImageData imageData = initialImage.getImageData(); //Button
		 * dataButton = new Button(shell, SWT.PUSH);
		 * //dataButton.setData(imageData); //dataButton.setLayoutData(fill);
		 * 
		 * //saving images ImageLoader imageLoader = new ImageLoader();
		 * imageLoader.data = new ImageData[] {imageData};
		 * imageLoader.save("/Users/niwde/Desktop/new_imgres.jpeg"
		 * ,SWT.IMAGE_JPEG);
		 */
		imgCreator.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

	/*
	 * public class imageCreatorOpenPage extends SelectionAdapter { public void
	 * widgetSelected(SelectionEvent e) { Shell facilitatorAddItemPage = new
	 * Shell(getDisplay()); FacilitatorAddItem facilitatorAddItem = new
	 * FacilitatorAddItem( facilitatorAddItemPage, SWT.None,
	 * table_eventPlanning_actualEvents_facilitators);
	 * facilitatorAddItem.pack(); facilitatorAddItemPage.pack();
	 * facilitatorAddItemPage.open(); } }
	 */

	public class imageCreatorEditPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell imageCreatorEditPage = new Shell(getDisplay());
			ImageCreatorEditPage imageCreatorEdit = new ImageCreatorEditPage(
					imageCreatorEditPage, SWT.None, gc, stringArray, imageLabel);
			imageCreatorEdit.pack();
			imageCreatorEditPage.pack();
			imageCreatorEditPage.open();
		}
	}

	/*
	 * public class imageCreatorSavePage extends SelectionAdapter { public void
	 * widgetSelected(SelectionEvent e) { Shell facilitatorAddItemPage = new
	 * Shell(getDisplay()); FacilitatorAddItem facilitatorAddItem = new
	 * FacilitatorAddItem( facilitatorAddItemPage, SWT.None,
	 * table_eventPlanning_actualEvents_facilitators);
	 * facilitatorAddItem.pack(); facilitatorAddItemPage.pack();
	 * facilitatorAddItemPage.open(); } }
	 */

	/*
	 * public class imageCreatorClearPage extends SelectionAdapter { public void
	 * widgetSelected(SelectionEvent e) { Shell facilitatorAddItemPage = new
	 * Shell(getDisplay()); FacilitatorAddItem facilitatorAddItem = new
	 * FacilitatorAddItem( facilitatorAddItemPage, SWT.None,
	 * table_eventPlanning_actualEvents_facilitators);
	 * facilitatorAddItem.pack(); facilitatorAddItemPage.pack();
	 * facilitatorAddItemPage.open(); } }
	 */

}
