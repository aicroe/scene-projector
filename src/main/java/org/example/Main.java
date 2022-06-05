package org.example;

import javax.swing.JFrame;

import org.example.framework.UIController;
import org.example.ui.UI;

/**
 * 
 * @author diegogarcia
 *
 */
public class Main {
	public static void main(String[] args) {
		UI ui = new UI("Shelf Projector", 850, 600);
		ui.setUp();
		UIController controller = new UIController(ui);
		ui.setController(controller);
		controller.init();
		
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setVisible(true);
	}
}
