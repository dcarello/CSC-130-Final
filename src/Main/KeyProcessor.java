/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')				return;
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
			
			
		case ' ':
			Main.trigger = "";
			break;
		
		case 'w':
			Main.trigger = "w is triggered";
			Main.pressed = true;
			break;
			
		case 'a':
			Main.trigger = "a is triggered";
			Main.pressed = true;
			break;
	
		case 's':
			Main.trigger = "s is triggered";
			Main.pressed = true;
			break;
	
	
		case 'd':
			Main.trigger = "d is triggered";
			Main.pressed = true;
			break;
			
		case '$':
			Main.trigger = "spacebar is triggered";
			Main.pressed = true;
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}