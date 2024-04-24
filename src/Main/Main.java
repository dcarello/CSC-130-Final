package Main;

import java.awt.Color;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.StringTokenizer;

import Data.Vector2D;
import Data.spriteInfo;
//import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(113,38,235);

	public static stopWatchX timer = new stopWatchX(500);
	
	public static spriteInfo mainSprite = new spriteInfo(new Vector2D(800, 400), "FacingForward");
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	public static int currentSpriteIndex = 0;
//	public static spriteInfo tmp = new spriteInfo(new Vector2D(-100,100), "WalkingRight0");
	
	public static ArrayList<String> ForwardAnimations = new ArrayList<>();
	public static ArrayList<String> AwayAnimations = new ArrayList<>();
	public static ArrayList<String> LeftAnimations = new ArrayList<>();
	public static ArrayList<String> RightAnimations = new ArrayList<>();
	public static int walkingIndex = 0;
	

	
//	public static EZFileRead ezr = new EZFileRead("Dialogue.txt");
//	public static HashMap<String, String> dialogue = new HashMap<>();
//	public static int dialogueIndex = 0;
//	public static String key = "string";
//	public static String currentLine = "";
	
	public static String trigger = "";
	public static boolean pressed = false;
	public static boolean facingForward = true;
	public static boolean facingAway = false;
	public static boolean facingLeft = false;
	public static boolean facingRight = false;
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		loadAnimations();

		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.drawString(1070, 650, "Daniel Carello", c); // Test drawing text on screen where you want (Remove later! Test only!)
		
		ctrl.addSpriteToFrontBuffer(0, 0, "Background");
		
		if (timer.isTimeUp()) {
//			dialogueIndex++;
//			if (dialogueIndex == ezr.getNumLines() + 1) {
//				dialogueIndex = 1;
//			}
//			key = "string";
//			key = key + dialogueIndex;
//			currentLine = dialogue.get(key);
			
			
			if (!pressed) {
				trigger = "";
			}
			timer.resetWatch();
		}
		
		if (facingForward) {
			if (pressed) {
				// sets the new sprites coords 1 pixel forward
				mainSprite.setCoords(mainSprite.getCoords().getX(), mainSprite.getCoords().getY() + 8);
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  ForwardAnimations.get(walkingIndex));
				if (walkingIndex == 3) {
					walkingIndex = 0;
				}else {
					walkingIndex += 1;
				}	
			}else {
				walkingIndex = 0;
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
			}
			
		}	
		
		if (facingAway) {
			if (pressed) {
				mainSprite.setCoords(mainSprite.getCoords().getX(), mainSprite.getCoords().getY() - 8);
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  AwayAnimations.get(walkingIndex));
				if (walkingIndex == 3) {
					walkingIndex = 0;
				}else {
					walkingIndex += 1;
				}
				
			}else {
				walkingIndex = 0;
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
			}
		}
		
		if (facingLeft) {
			if (pressed) {
				mainSprite.setCoords(mainSprite.getCoords().getX() - 8, mainSprite.getCoords().getY());
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  LeftAnimations.get(walkingIndex));
				if (walkingIndex == 3) {
					walkingIndex = 0;
				}else {
					walkingIndex += 1;
				}	
			}else {
				walkingIndex = 0;
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
			}
		}
		
		if (facingRight) {
			if (pressed) {
				mainSprite.setCoords(mainSprite.getCoords().getX() + 8, mainSprite.getCoords().getY());
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  RightAnimations.get(walkingIndex));
				if (walkingIndex == 3) {
					walkingIndex = 0;
				}else {
					walkingIndex += 1;
				}	
			}else {
				walkingIndex = 0;
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
			}
		}
//		
		ctrl.drawString(100, 250, trigger, c);
//		ctrl.drawString(100, 250, currentLine, c);
	}
	
	// Additional Static methods below...(if needed)
	public static void loadAnimations() {
		
		for (int i = 0; i < 4; i++) {
			ForwardAnimations.add("WalkingForward" + i);
			AwayAnimations.add("WalkingAway" + i);
			LeftAnimations.add("WalkingLeft" + i);
			RightAnimations.add("WalkingRight" + i);
		}
	}

}
