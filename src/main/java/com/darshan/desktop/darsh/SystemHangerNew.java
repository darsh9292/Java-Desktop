package com.darshan.desktop.darsh;

import java.awt.*;

/**
 * @author Darshan Patel
 */
public class SystemHangerNew extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SystemHangerNew() {
		int a = 0, b = 0;
		for (int i = 0; i <= 30000; i++) {

			new darsh2(a, b).setVisible(false);

			a = a + 75;

			if (a == 900) {
				b = b + 60;
				a = 0;
				if (b == 600) {
					b = 0;
				}
			}

		}
	}

	public static void main(String ar[]) {
		new SystemHangerNew();
	}
}

class darsh2 extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	darsh2(int k, int j) {
		setVisible(true);
		setTitle("Darshan");
		setLocation(k, j);
		setSize(300, 300);
	}

}
