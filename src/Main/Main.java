package Main;

import java.awt.Color;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

//import Data.Vector2D;
//import Data.spriteInfo;
import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(113,38,235);

	public static stopWatchX timer = new stopWatchX(4000);

	
	public static EZFileRead ezr = new EZFileRead("Dialogue.txt");
	public static HashMap<String, String> dialogue = new HashMap<>();
	public static int dialogueIndex = 0;
	public static String key = "string";
	public static String currentLine = "";
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		for (int i = 0; i < ezr.getNumLines(); i++) {
			String raw = ezr.getLine(i);
			StringTokenizer st = new StringTokenizer(raw, "*");
			String hashKey = st.nextToken(); // Acquires the Key from the raw String
			String hashValue = st.nextToken(); // Acquires the Value from the raw String
			dialogue.put(hashKey, hashValue);
		}
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.drawString(1070, 650, "Daniel Carello", c); // Test drawing text on screen where you want (Remove later! Test only!)
		
		if (timer.isTimeUp()) {
			dialogueIndex++;
			if (dialogueIndex == ezr.getNumLines() + 1) {
				dialogueIndex = 1;
			}
			key = "string";
			key = key + dialogueIndex;
			currentLine = dialogue.get(key);
			timer.resetWatch();
		}
		ctrl.drawString(100, 250, currentLine, c);
	}
	
	// Additional Static methods below...(if needed)

}
