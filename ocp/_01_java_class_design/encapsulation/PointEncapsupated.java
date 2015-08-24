package ocp._01_java_class_design.encapsulation;


/**
 * Class properly encapsulated against get/set idiom.
 * Problem is, the attributes are exposed and the inner representation
 * of the point can't be changed (i.e. int -> long) without breaking code outside the class.
 */
public class PointEncapsupated {
	private int x;
	private int y;
	/**
	 * @param x
	 * @param y
	 */
	public PointEncapsupated(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		// Do some check here if needed.
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		// Do some check here if needed.
		this.y = y;
	}
}