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
	
	//Interaction Boxes and Variable
	public static boolean interact = false;
	public static BoundingBox deskInteract = new BoundingBox(1024, 1208, 242, 271);
	public static BoundingBox leftBedInteract = new BoundingBox(345, 389, 435, 688);
	public static BoundingBox topBedInteract = new BoundingBox(389, 555, 405, 435);
	public static BoundingBox rightBedInteract = new BoundingBox(555, 599, 435, 688);
	
	//Array for Interaction Boxes
	public static ArrayList<BoundingBox> InteractionBoxes = new ArrayList<>();
	
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
		
		InteractionBoxes.add(deskInteract);
		InteractionBoxes.add(leftBedInteract);
		InteractionBoxes.add(topBedInteract);
		InteractionBoxes.add(rightBedInteract);

		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.drawString(1070, 650, "Daniel Carello", c); // Test drawing text on screen where you want (Remove later! Test only!)
		
		// Creates the background
		ctrl.addSpriteToFrontBuffer(0, 0, "Background");

		
		if (timer.isTimeUp()) {
			
			if (!pressed) {
				trigger = "";
			}
			timer.resetWatch();
		}
		
		if (facingForward) {
			if (interact && BoundingBox.isCollision(mainSpriteBox, topBedInteract)) {
				BedInteract(ctrl);
				
			}
			else if (pressed) {
				moveForward(ctrl);
			}
			// if the button is not pressed it prints the character standing facing the appropriate direction
			else {
				standingStill(ctrl);
			}
			
		}	
		
		if (facingAway) {
			if (interact && BoundingBox.isCollision(mainSpriteBox, deskInteract)) {
				DeskInteract(ctrl);
			} 
			else if (pressed) {
				moveAway(ctrl);
			}
			// if the button is not pressed it prints the character standing facing the appropriate direction
			else {
				standingStill(ctrl);
			}
		}
		
		if (facingLeft) {
			if (interact && BoundingBox.isCollision(mainSpriteBox, rightBedInteract)) {
				BedInteract(ctrl);
			}
			else if (pressed) {
				moveLeft(ctrl);
			}
			// if the button is not pressed it prints the character standing facing the appropriate direction
			else {
				standingStill(ctrl);
			}
		}
		
		if (facingRight) {
			if (interact && BoundingBox.isCollision(mainSpriteBox, leftBedInteract)) {
				BedInteract(ctrl);
			}
			else if (pressed) {
				moveRight(ctrl);
			}
			// if the button is not pressed it prints the character standing facing the appropriate direction
			else {
				standingStill(ctrl);
			}
		}	
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
	
	// Opens popup when interacting with bed
	public static void BedInteract(Control ctrl) {
		ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
		ctrl.addSpriteToFrontBuffer(0, 0, "Popup");
		ctrl.drawString(445, 280, "This bed seems really large compared to me.", c);
		ctrl.drawString(515, 300, "Maybe I should get a new bed.", c);
		ctrl.drawString(470, 385, "Press spacebar or start moving to close.", c);
		
	}
	
	
	// Opens popup when interacting with desk
	public static void DeskInteract(Control ctrl) {
		ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
		ctrl.addSpriteToFrontBuffer(0, 0, "Popup");
		ctrl.drawString(440, 260, "I love playing Valorant on my fancy computer.", c);
		ctrl.drawString(515, 280, "I wish I could get out of gold.", c);
		ctrl.drawString(530, 300, "I just need more practice.", c);
		ctrl.drawString(470, 385, "Press spacebar or start moving to close.", c);
	}
	
	// Shows character standing still and resets animation
	public static void standingStill(Control ctrl) {
		walkingIndex = 0;
		ctrl.addSpriteToFrontBuffer(mainSprite.getCoords().getX(), mainSprite.getCoords().getY(),  mainSprite.getTag());
		interact = false;
	}
	
	// Code to move the character to the foward when button pressed
	public static void moveForward(Control ctrl) {
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
		interact = false;
	}
	
	// Code to move the character to the away when button pressed
	public static void moveAway(Control ctrl) {
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
		interact = false;
	}
	
	// Code to move the character to the left when button pressed
	public static void moveLeft(Control ctrl) {
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
		interact = false;
	}
	
	
	// Code to move the character to the right when button pressed
	public static void moveRight(Control ctrl) {
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
		interact = false;	
	}
}
