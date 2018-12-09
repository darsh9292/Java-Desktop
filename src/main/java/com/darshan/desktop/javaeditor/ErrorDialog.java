package com.darshan.desktop.javaeditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @author Darshan Patel
 */
public class ErrorDialog {
	ErrorDialog() throws IOException {

		final JFrame jfrm = new JFrame("Compilation Result");

		jfrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JPanel jpa = new JPanel();

		JLabel jlab;

		JPanel jpa1 = new JPanel();

		jpa1.setLayout(new FlowLayout(FlowLayout.LEFT));

		jpa.setLayout(new FlowLayout());
		jfrm.setLayout(new BorderLayout());

		jfrm.setSize(500, 300);
		jfrm.setLocation(250, 200);

		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception w) {
		}

		FileReader fr = new FileReader("d:\\Newfolder\\com.txt");
		BufferedReader br = new BufferedReader(fr);
		String s;

		String s1 = "<html>";
		String s2 = "";
		String s3 = "</html>";

		String fs;
		while ((s = br.readLine()) != null) {
			s2 = s2 + s + "<br>";
		}
		fr.close();

		fs = s1 + s2 + s3;

		jlab = new JLabel(fs);

		JButton jb = new JButton("OK");

		jfrm.getRootPane().setDefaultButton(jb);

		jpa1.add(jlab);

		JScrollPane jsp = new JScrollPane(jpa1);

		jpa.add(jb);

		jfrm.add(jsp, "Center");

		jfrm.add(jpa, "South");

		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.exit(0);
				jfrm.dispose();
			}
		});

		jfrm.setResizable(false);

		jfrm.setVisible(true);
	}

	public static void main(String args[]) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new ErrorDialog();
				} catch (IOException ex) {
					Logger.getLogger(ErrorDialog.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});
	}
}
