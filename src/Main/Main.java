package Main;

import java.awt.Color;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.StringTokenizer;

import Data.BoundingBox;
import Data.Vector2D;
import Data.spriteInfo;
//import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(113,38,235);

	public static stopWatchX timer = new stopWatchX(500);
	
	// Main Sprite Location and Frame
	public static spriteInfo mainSprite = new spriteInfo(new Vector2D(800, 400), "FacingForward");
	
	// Bounding Boxes in the game
	public static BoundingBox mainSpriteBox = new BoundingBox(mainSprite.getCoords().getX() + 50, mainSprite.getCoords().getX() + 80, mainSprite.getCoords().getY() + 20, mainSprite.getCoords().getY() + 80);
	public static BoundingBox topWall = new BoundingBox(0, 1280, 0, 160);
	public static BoundingBox bottomWall = new BoundingBox(0, 1280, 700, 720);
	public static BoundingBox leftWall = new BoundingBox(0, 25, 0, 720);
	public static BoundingBox rightWall = new BoundingBox(1255, 1280, 0, 720);
	public static BoundingBox Bed = new BoundingBox(387, 555, 435, 690);
	public static BoundingBox leftDeskLeg = new BoundingBox(1020, 1025, 223, 316);
	public static BoundingBox rightDeskLeg = new BoundingBox(1208, 1213, 223, 316);
	public static BoundingBox Desk = new BoundingBox(1020, 1213, 174, 243);
	
	// Array for Bounding Boxes
	public static ArrayList<BoundingBox> Boxes = new ArrayList<>();
	
	// Walking Animation Arrays and variables
	public static ArrayList<String> ForwardAnimations = new ArrayList<>();
	public static ArrayList<String> AwayAnimations = new ArrayList<>();
	public static ArrayList<String> LeftAnimations = new ArrayList<>();
	public static ArrayList<String> RightAnimations = new ArrayList<>();
	public static int walkingIndex = 0;
	public static int pixelStep = 8;
	
	// Direction variables
	public static String trigger = "";
	public static boolean pressed = false;
	public static boolean facingForward = true;
	public static boolean facingAway = false;
	public static boolean facingLeft = false;
	public static boolean facingRight = false;
	
//	public static EZFileRead ezr = new EZFileRead("Dialogue.txt");
//	public static HashMap<String, String> dialogue = new HashMap<>();
//	public static int dialogueIndex = 0;
//	public static String key = "string";
//	public static String currentLine = "";
	

	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		loadAnimations();
		
		// Addes the Bounding Boxes into the Array List to make it easier to check for collisions
		Boxes.add(topWall);
		Boxes.add(bottomWall);
		Boxes.add(leftWall);
		Boxes.add(rightWall);
		Boxes.add(Bed);
		Boxes.add(leftDeskLeg);
		Boxes.add(rightDeskLeg);
		Boxes.add(Desk);

		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.drawString(1070, 650, "Daniel Carello", c); // Test drawing text on screen where you want (Remove later! Test only!)
		
		// Creates the background
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
				// Sets New Bounding Box for mainSprite
				mainSpriteBox.setWalls(mainSpriteBox.getX1(), mainSpriteBox.getX2(), mainSpriteBox.getY1() + pixelStep, mainSpriteBox.getY2() + pixelStep);
				// sets the new sprites coords pixelStep forward
				mainSprite.setCoords(mainSprite.getCoords().getX(), mainSprite.getCoords().getY() + pixelStep);
				
				// If the new movement of the sprite has a collision it puts the coordinates back
				if (SpriteCollision()) {
					mainSpriteBox.setWalls(mainSpriteBox.getX1(), mainSpriteBox.getX2(), mainSpriteBox.getY1() - pixelStep, mainSpriteBox.getY2() - pixelStep);
					mainSprite.setCoords(mainSprite.getCoords().getX(), mainSprite.getCoords().getY() - pixelStep);
				}
				
				// Prints sprite walking with the current animation frame
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  ForwardAnimations.get(walkingIndex));
				
				// Moves the walking frame animation up 1 unless its at 3, the max
				if (walkingIndex == 3) {
					walkingIndex = 0;
				}else {
					walkingIndex += 1;
				}	
			}
			// if the button is not pressed it prints the character standing facing the appropriate direction
			else {
				walkingIndex = 0;
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
			}
			
		}	
		
		if (facingAway) {
			if (pressed) {
				// sets the new sprites coords pixelStep backward
				mainSprite.setCoords(mainSprite.getCoords().getX(), mainSprite.getCoords().getY() - pixelStep);
				// Sets New Bounding Box for mainSprite
				mainSpriteBox.setWalls(mainSpriteBox.getX1(), mainSpriteBox.getX2(), mainSpriteBox.getY1() - pixelStep, mainSpriteBox.getY2() - pixelStep);
				
				// If the new movement of the sprite has a collision it puts the coordinates back
				if (SpriteCollision()) {
					mainSpriteBox.setWalls(mainSpriteBox.getX1(), mainSpriteBox.getX2(), mainSpriteBox.getY1() + pixelStep, mainSpriteBox.getY2() + pixelStep);
					mainSprite.setCoords(mainSprite.getCoords().getX(), mainSprite.getCoords().getY() + pixelStep);
				}
				
				// Prints sprite walking with the current animation frame
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  AwayAnimations.get(walkingIndex));
				
				// Moves the walking frame animation up 1 unless its at 3, the max
				if (walkingIndex == 3) {
					walkingIndex = 0;
				}else {
					walkingIndex += 1;
				}
				
			}
			// if the button is not pressed it prints the character standing facing the appropriate direction
			else {
				walkingIndex = 0;
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
			}
		}
		
		if (facingLeft) {
			if (pressed) {
				// sets the new sprites coords pixelStep left
				mainSprite.setCoords(mainSprite.getCoords().getX() - pixelStep, mainSprite.getCoords().getY());
				// Sets New Bounding Box for mainSprite
				mainSpriteBox.setWalls(mainSpriteBox.getX1() - pixelStep, mainSpriteBox.getX2() - pixelStep, mainSpriteBox.getY1(), mainSpriteBox.getY2());
				
				// If the new movement of the sprite has a collision it puts the coordinates back
				if (SpriteCollision()) {
					mainSpriteBox.setWalls(mainSpriteBox.getX1() + pixelStep, mainSpriteBox.getX2() + pixelStep, mainSpriteBox.getY1(), mainSpriteBox.getY2());
					mainSprite.setCoords(mainSprite.getCoords().getX() + pixelStep, mainSprite.getCoords().getY());
				}
				
				// Prints sprite walking with the current animation frame
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  LeftAnimations.get(walkingIndex));
				
				// Moves the walking frame animation up 1 unless its at 3, the max
				if (walkingIndex == 3) {
					walkingIndex = 0;
				}else {
					walkingIndex += 1;
				}	
			}
			// if the button is not pressed it prints the character standing facing the appropriate direction
			else {
				walkingIndex = 0;
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
			}
		}
		
		if (facingRight) {
			if (pressed && !SpriteCollision()) {
				// sets the new sprites coords pixelStep right
				mainSprite.setCoords(mainSprite.getCoords().getX() + pixelStep, mainSprite.getCoords().getY());
				// Sets New Bounding Box for mainSprite
				mainSpriteBox.setWalls(mainSpriteBox.getX1() + pixelStep, mainSpriteBox.getX2() + pixelStep, mainSpriteBox.getY1(), mainSpriteBox.getY2());
				
				// If the new movement of the sprite has a collision it puts the coordinates back
				if (SpriteCollision()) {
					mainSpriteBox.setWalls(mainSpriteBox.getX1() - pixelStep, mainSpriteBox.getX2() - pixelStep, mainSpriteBox.getY1(), mainSpriteBox.getY2());
					mainSprite.setCoords(mainSprite.getCoords().getX() - pixelStep, mainSprite.getCoords().getY());
				}
				
				// Prints sprite walking with the current animation frame
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  RightAnimations.get(walkingIndex));
				
				// Moves the walking frame animation up 1 unless its at 3, the max
				if (walkingIndex == 3) {
					walkingIndex = 0;
				}else {
					walkingIndex += 1;
				}	
			}
			// if the button is not pressed it prints the character standing facing the appropriate direction
			else {
				walkingIndex = 0;
				ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
			}
		}
//		
		String coords =  "X1: " + Integer.toString(mainSpriteBox.getX1()) + ", X2: " + Integer.toString(mainSpriteBox.getX2()) + ", Y1: " +  Integer.toString(mainSpriteBox.getY1()) + ", Y2: " + Integer.toString(mainSpriteBox.getY2());
		
		// Draws the sprite onto the screen
		ctrl.drawString(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(), coords, c);
		ctrl.drawString(100, 250, trigger, c);
//		ctrl.drawString(100, 250, currentLine, c);
	}
	
	// Additional Static methods below...(if needed)
	
	// Loads the walking animations into their respective Array Lists
	public static void loadAnimations() {
		
		for (int i = 0; i < 4; i++) {
			ForwardAnimations.add("WalkingForward" + i);
			AwayAnimations.add("WalkingAway" + i);
			LeftAnimations.add("WalkingLeft" + i);
			RightAnimations.add("WalkingRight" + i);
		}
	}
	
	// goes through all of the Bounding Boxes and checks if the sprite collides with any of them
	public static boolean SpriteCollision() {
		for (int i = 0; i < Boxes.size(); i++) {
			if (BoundingBox.isCollision(mainSpriteBox, Boxes.get(i))){
				return true;
			}
		}
		return false;
	}

}
