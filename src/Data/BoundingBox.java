package Data;

public class BoundingBox{
	private int x1, x2, y1, y2;
	
	public BoundingBox(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public static boolean isCollision(BoundingBox b1, BoundingBox b2) {
		if(b2.getX1() > b1.getX2()) {
			return false;
		}
		if(b2.getX2() < b1.getX1()) {
			return false;
		}
		if(b2.getY2() < b1.getY1()) {
			return false;
		}
		if(b2.getY1() > b1.getY2()) {
			return false;
		}
		return true;
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getY2() {
		return y2;
	}
	
	public void setWalls(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
}