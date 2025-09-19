package shapes;

import java.awt.Point;

public interface Locatable {
	public abstract Point locate();
	public void moveTo(int x, int y);
}
