package com.darshan.desktop.screensaver;

import java.awt.*;
import java.awt.event.*;

/**
 * <applet code="ss" width=950 height=650>
 * </applet>
 * @author Darshan Patel
 */
public class ScreenSaver extends Frame implements ActionListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int x = 0, y = 300;
	static int bb = 0;

	Panel p;
	TextField t;
	Button ok;
	static String msg = "Darshan";

	public ScreenSaver() {
		init();
		setTitle("Darshan");
		setVisible(true);
		setSize(950, 650);
		setLocation(0, 0);

		Thread t = new Thread(this);
		t.start();

	}

	static int d = 0;

	public void run() {
		try {
			while (true) {
				while (d == 0) {
					if (x < 860) {
						repaint();
						bb++;
						x = x + 10;
						Thread.sleep(100);
					} else {
						d = 1;
					}
				}
				while (d == 1) {
					if (x > 0) {
						repaint();
						bb--;
						x = x - 10;
						Thread.sleep(100);
					} else {
						d = 0;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("" + e);
		}
	}

	public void init() {
		setLayout(new BorderLayout());
		p = new Panel();
		ok = new Button("ok");
		t = new TextField(20);
		p.add(t);
		p.add(ok);
		add(p, "North");
		ok.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ok) {
			String sp = t.getText();
			if (!sp.equals("")) {
				msg = t.getText();
				x = 0;
				y = 300;
				repaint();
				t.setText("");
			}
		}
	}

	public void paint(Graphics g) {
		setForeground(Color.blue);
		setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(msg, x, y);

	}

	public static void main(String args[]) {
		new ScreenSaver();
	}
}
