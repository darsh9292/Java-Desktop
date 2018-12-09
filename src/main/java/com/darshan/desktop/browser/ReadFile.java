package com.darshan.desktop.browser;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Darshan Patel
 */
public class ReadFile extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7480664158722056533L;
	private JTextField addbar;
	private JEditorPane display;

	public ReadFile() {
		super("Browser");

		addbar = new JTextField("Enter url");
		addbar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loadCrap(ae.getActionCommand());
			}
		});

		add(addbar, BorderLayout.NORTH);

		display = new JEditorPane();

		display.setEditable(false);

		display.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent he) {
				if (he.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					loadCrap(he.getURL().toString());
				}
			}

		}

		);

		add(new JScrollPane(display), BorderLayout.CENTER);

		setSize(1000, 700);
		setVisible(true);

	}

	private void loadCrap(String user) {
		try {
			display.setPage(user);
			addbar.setText(user);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}
	}

	public static void main(String args[]) {
		ReadFile rf = new ReadFile();
		rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
