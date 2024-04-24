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
		// if(key == ' ')				return;
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		
		case ' ':
			Main.trigger = "";
			Main.pressed = false;
			break;
		
		case 'w':
			Main.trigger = "w is triggered";
			Main.pressed = true;
			Main.facingForward = false;
			Main.facingAway = true;
			Main.mainSprite.setTag("FacingAway");
			Main.facingLeft = false;
			Main.facingRight = false;
			
			
			break;
			
		case 'a':
			Main.trigger = "a is triggered";
			Main.pressed = true;
			Main.facingForward = false;
			Main.facingAway = false;
			Main.facingLeft = true;
			Main.mainSprite.setTag("FacingLeft");
			Main.facingRight = false;
			break;
			
		case 's':
			Main.trigger = "s is triggered";
			Main.pressed = true;
			Main.facingForward = true;
			Main.mainSprite.setTag("FacingForward");
			Main.facingAway = false;
			Main.facingLeft = false;
			Main.facingRight = false;
			break;
			
		case 'd':
			Main.trigger = "d is triggered";
			Main.pressed = true;
			Main.facingForward = false;
			Main.facingAway = false;
			Main.facingLeft = false;
			Main.facingRight = true;
			Main.mainSprite.setTag("FacingRight");
			break;
			
		case '$':
			Main.trigger = "spacebar is triggered";
			Main.pressed = true;
			break;
			
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}