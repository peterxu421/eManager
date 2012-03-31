import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries.SeriesType;

public class TaskChart extends Composite {

	DatabaseReader db;
	ArrayList<Organizer> listOfPeople;
	ArrayList<Task> listOfTasks;
	ArrayList<Integer> listOfTotalIndividualTask;
	ArrayList<Integer> listOfTasksDone;
	Event event;
	double[] ySeries;

	public TaskChart(Composite parent, int style, Event event) {
		// set attributes
		super(parent, style);

		db = new DatabaseReader();
		listOfPeople = db.getOrganizers(event);
		listOfTasks = db.getTasks(event);
		listOfTotalIndividualTask = new ArrayList<Integer>();
		listOfTasksDone = new ArrayList<Integer>();

		// initialize
		for (int i = 0; i < listOfPeople.size(); i++) {
			listOfTotalIndividualTask.add(0);
			listOfTasksDone.add(0);
		}

		String[] stringOfPeople = new String[listOfPeople.size()];

		for (int i = 0; i < listOfPeople.size(); i++) {
			// populating the stringOfPeople
			stringOfPeople[i] = listOfPeople.get(i).getName();
			for (int j = 0; j < listOfTasks.size(); j++) {
				if (listOfPeople.get(i).getName().equals(listOfTasks.get(j).getAssignedTo())){
					countTotal++;
					if(listOfTasks.get(j).isDone()) countDone++;
				}
			}
			listOfTotalIndividualTask.add(countTotal);
			listOfTasksDone.add(countDone);
		}
		//for checking
		/*for(int i=0;i<listOfPeople.size(); i++){
			System.out.println(listOfPeople.get(i).getName());
		}*/

		// create a chart
		Chart chart = new Chart(parent, SWT.LEFT);

		// set titles
		chart.getTitle().setText("Task Chart");
		chart.getAxisSet().getXAxis(0).getTitle().setText("abc");
		chart.getAxisSet().getYAxis(0).getTitle().setText("% of work done");
		chart.setLocation(0, 0);
		chart.setBounds(0, 0, 400, 400);

		// set xAxis
		IAxisSet axisSet = chart.getAxisSet();
		IAxis xAxis = axisSet.getXAxis(0);
		xAxis.setCategorySeries(stringOfPeople);
		xAxis.enableCategory(true);

		// set yAxis

		ySeries = new double[listOfPeople.size()];
		for (int i = 0; i < listOfPeople.size(); i++) {
			// populating the y-series
			if (listOfTotalIndividualTask.get(i) == 0) {
				ySeries[i] = 50;
				break;
			}
			ySeries[i] = ((double)listOfTasksDone.get(i))/listOfTotalIndividualTask.get(i)*100;
		}

		// set Horizontal
		chart.setOrientation(SWT.VERTICAL);

		// create bar series
		IBarSeries barSeries = (IBarSeries) chart.getSeriesSet().createSeries(
				SeriesType.BAR, "bar series");
		barSeries.setYSeries(ySeries);

		// setting the bar's color
		Color color = new Color(Display.getDefault(), 238, 221, 130);
		barSeries.setBarColor(color);

		// adjust the axis range
		//chart.getAxisSet().adjustRange();

	}

}