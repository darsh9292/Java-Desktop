/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darshan.desktop.alpha;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Darshan Patel
 */
public class PanelImageAlpha extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	public PanelImageAlpha() {
		setOpaque(false);
		image = new ImageIcon(getClass().getResource("/alpha/icon.png"))
				.getImage();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gd = (Graphics2D) g.create();
		gd.setComposite(AlphaComposite.SrcOver.derive(0.5F));
		gd.drawImage(image, 0, 0, null);
		gd.dispose();
	}
}