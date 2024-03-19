package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Data.Vector2D;
import Data.spriteInfo;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(113,38,235);

	public static stopWatchX timer = new stopWatchX(50);
	
	public static spriteInfo s1 = new spriteInfo(new Vector2D(500,550), "Walking0");
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	public static int currentSpriteIndex = 0;
	public static spriteInfo tmp = new spriteInfo(new Vector2D(-100,100), "Walking0");
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		int walkingIndex = 0;
		for (int x = -128; x<= 1280; x+=8) {
			spriteInfo stmp = new spriteInfo(new Vector2D(x, 150), "Walking" + walkingIndex);
			sprites.add(stmp);
			walkingIndex++;
			if (walkingIndex == 4) {
				walkingIndex = 0;
			}
		}
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.drawString(1070, 650, "Daniel Carello", c); // Test drawing text on screen where you want (Remove later! Test only!)
		
		
		if (timer.isTimeUp()) {
			currentSpriteIndex++;
			tmp = sprites.get(currentSpriteIndex);
			if (currentSpriteIndex == sprites.size() - 1) {
				currentSpriteIndex = 0;
			}
			timer.resetWatch();
		}
		ctrl.addSpriteToFrontBuffer(tmp.getCoords().getX(), tmp.getCoords().getY(), tmp.getTag());
	}
	
	// Additional Static methods below...(if needed)

}
