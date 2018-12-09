package com.darshan.desktop.darsh;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Darshan Patel
 */
public class SystemHanger extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel p1, p2;
	Label l1, l2;
	Button OK;

	SystemHanger() {
		setGui();
		setVisible(true);
		setTitle("Darshan");
		setLocation(100, 100);
		setSize(300, 300);
	}

	void setGui() {
		setLayout(new BorderLayout());
		p1 = new Panel();
		p2 = new Panel();
		l1 = new Label("If you want to see magic than click OK");
		l1.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
		OK = new Button("OK");
		p1.add(l1);
		p2.add(OK);
		add(p1, "North");
		add(p2, "Center");
		OK.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == OK) {
			int a = 0, b = 0;
			while (true) {

				new darsh1(a, b).setVisible(false);

				a = a + 30;

				if (a == 900) {
					b = b + 40;
					a = 0;
					if (b == 600) {
						b = 0;
					}
				}

			}
		}
	}

	public static void main(String ar[]) {
		new SystemHanger();
	}
}

class darsh1 extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	darsh1(int k, int j) {
		setVisible(true);
		setTitle("Darshan");
		setLocation(k, j);
		setSize(300, 300);
	}

}
