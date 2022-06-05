package org.example.ui;

import java.awt.Graphics;

import javax.swing.JFrame;

import org.example.framework.Observer;
import org.example.framework.UIController;

/**
 * 
 * @author diegogarcia
 *
 */
public class UI extends JFrame {
	
	public static int PERSPECTIVE = 0;
	public static int FRONTAL = 1;
	public static int SUPERIOR = 2;
	public static int LATERAL = 3;
	
	private Viewport[] viewports;
	private MessageBox box;
	
	public UI(String title, int width, int height) {
		
		super(title);
		setSize(width, height);
	}
	
	public void matchViewport(int i, Observer o) {
		
		o.setViewport(viewports[i]);
	}
	
	public void setController(UIController c) {
		
		addKeyListener(c);
	}
	
	public void showMessage(String msg) {
		
		box.addString(msg);
		box.updateGraphics();
	}
	
	public void moveOffsetBox(int i) {
		
		box.moveOffset(i);
		box.updateGraphics();
	}
	
	public void setUp() {

		viewports = new Viewport[4];
		viewports[PERSPECTIVE] = new Viewport(50, 80, 500, 380);
		viewports[FRONTAL] = new Viewport(550, 80, 800, 220);
		viewports[SUPERIOR] = new Viewport(550, 245, 800, 385);
		viewports[LATERAL] = new Viewport(550, 410, 800, 550);
		
		box = new MessageBox(60, 415, 430, 125);
		box.setRange(8);
		box.updateGraphics();
	}
	
	@Override
	public void paint(Graphics g) {

		for (Viewport v: viewports) {
			g.drawImage(v, v.getox(), v.getoy(), null);
		}
		g.drawImage(box, box.getox(), box.getoy(), null);
	}

}
