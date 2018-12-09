/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darshan.desktop.alpha;

/**
 * @author Darshan Patel
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8050514728760695827L;
	private Image image;

	public PanelImage() {
		image = new ImageIcon(getClass().getResource("/alpha/main.jpg"))
				.getImage();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gd = (Graphics2D) g.create();
		gd.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		gd.dispose();
	}
}