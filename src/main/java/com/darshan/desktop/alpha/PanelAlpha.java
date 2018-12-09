/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darshan.desktop.alpha;

/**
 * @author Darshan Patel
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PanelAlpha extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 500961755799166286L;
	private Color color;

	public PanelAlpha() {
		setOpaque(false);
		color = new Color(getBackground().getRed(), getBackground().getGreen(),
				getBackground().getBlue(), 127);
	}

	public void setBackground(Color bg) {
		super.setBackground(bg);
		color = new Color(getBackground().getRed(), getBackground().getGreen(),
				getBackground().getBlue(), 127);
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gd = (Graphics2D) g.create();
		gd.setColor(color);
		gd.fillRect(0, 0, getWidth(), getHeight());
		gd.dispose();
	}
}
