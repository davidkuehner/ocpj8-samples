package ocp._01_java_class_design.encapsulation;

/**
 * Here the attributes are not exposed to the outside world. The Point class do
 * the word. Idea is : Don't ask the class the attributes needed for a task but
 * ask the class doing the task.
 */
public class PointEncapsupated2 {
	private int x;
	private int y;

	// private long x;
	// private long y;

	/**
	 * @param x
	 * @param y
	 */
	public PointEncapsupated2(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Place a point on a chart. The logic is moved inside the point class. Now
	 * you can change the representation without braking the code around the
	 * class.
	 * 
	 * @param chart
	 */
	public void placeOnChart(Chart chart) {
		chart.place(x, y);
		// chart.place((int)x, (int)y);
	}
}
