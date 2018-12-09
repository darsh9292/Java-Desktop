package com.darshan.desktop.brush;

/*
 <applet code = "Brush1" height=500 width=500>
 </applet>
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Darshan Patel
 */
public class Brush extends Applet implements MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width, height;
	Image backbuffer;
	Graphics backg;

	public void init() {
		width = getSize().width;
		height = getSize().height;

		backbuffer = createImage(width, height);
		backg = backbuffer.getGraphics();
		backg.setColor(Color.black);
		backg.fillRect(0, 0, width, height);
		backg.setColor(Color.white);

		addMouseMotionListener(this);
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		backg.fillOval(x - 5, y - 5, 10, 10);
		repaint();
		e.consume();
	}

	public void update(Graphics g) {
		g.drawImage(backbuffer, 0, 0, this);
	}

	public void paint(Graphics g) {
		update(g);
	}
}
