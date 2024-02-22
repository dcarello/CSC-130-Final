package Main;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import Data.Vector2D;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(113,38,235);
	public static boolean isImageDrawn = false;
	public static stopWatchX timer = new stopWatchX(50);
	
	public static Queue<Vector2D> vecs1 = new LinkedList<>();
	public static Queue<Vector2D> vecs2 = new LinkedList<>();
	public static Vector2D currentVec = new Vector2D(-100,100);
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		for (int x = -128; x <= 1280; x+=8) {
			vecs1.add(new Vector2D(x, 150));
		}
		
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.drawString(100, 500, "" + vecs1.size(), Color.red);
		ctrl.drawString(100, 550, "" + vecs2.size(), Color.red);
								 				// Add a tester sprite to render list by tag (Remove later! Test only!)
		ctrl.drawString(1070, 650, "Daniel Carello", c); // Test drawing text on screen where you want (Remove later! Test only!)
		if (timer.isTimeUp()) {
			currentVec = vecs1.remove();
			vecs2.add(new Vector2D(currentVec.getX(), currentVec.getY()));
			if (vecs1.isEmpty()) {
				while(!vecs2.isEmpty()) {
					vecs1.add(vecs2.remove());
				}
			}
			timer.resetWatch();
		}
		ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), "Biker2");
	}
	
	// Additional Static methods below...(if needed)

}
