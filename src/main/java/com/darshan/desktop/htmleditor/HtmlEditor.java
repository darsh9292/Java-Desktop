package com.darshan.desktop.htmleditor;

/**
 * @author Darshan Patel
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class HtmlEditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
	int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
	JTextArea a1;
	JEditorPane ed;
	JToolBar tb;
	JPanel p1;
	JButton open, save, save_as, view;
	Thread t1;
	String current_file = "";
	Container cp;
	String html_struct = "<html> \n\t<body>\n\t <bgcolor=red>\n\t<center>\n\t<font color=red size=38>G</font>\n\t<font color=blue size=38>o</font>\n\t<font color=green size=38>o</font>\n\t<font color=yellow size=38>g</font>\n\t<font color=blue size=38>l</font>\n\t<font color=red size=38>e</font>\n\t<br><input type=text value=Search id=io>\n\t<input type=submit value=Search >\n\t</center>\n\t</body>\n </html>";

	HtmlEditor() {
		setTitle("JEditor HTML");
		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception w) {
		}

		cp = getContentPane();
		cp.setLayout(new GridLayout(0, 1));

		// Show runtime output
		ed = new JEditorPane();
		ed.setContentType("text/html");
		cp.add(ed);
		ed.setEditable(false);

		ed.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Output"),
				BorderFactory.createEmptyBorder(0, 0, 5, 5)));

		BorderLayout br = new BorderLayout();
		p1 = new JPanel();
		p1.setLayout(br);
		cp.add(p1);

		tb = new JToolBar();
		p1.add(tb, BorderLayout.NORTH);

		tb.addSeparator();

		open = new JButton("Open");

		tb.add(open);

		tb.addSeparator();
		tb.addSeparator();

		save = new JButton("Save");

		tb.add(save);
		tb.addSeparator();

		save_as = new JButton("Save As");

		tb.add(save_as);

		tb.addSeparator();
		tb.addSeparator();

		view = new JButton("Browser View");

		tb.add(view);
		tb.addSeparator();
		tb.addSeparator();

		a1 = new JTextArea();
		a1.setBackground(Color.black);
		a1.setForeground(Color.white);
		JScrollPane js = new JScrollPane(a1, v, h);
		p1.add(js, BorderLayout.CENTER);

		a1.setText(html_struct);

		a1.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Code"),
				BorderFactory.createEmptyBorder(0, 0, 5, 5)));

		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					FileDialog fd = new FileDialog(new JFrame(),
							"Select HMTL/HTM File");
					fd.setVisible(true);

					String dir = fd.getDirectory();
					String file = fd.getFile();

					FileReader ff = new FileReader(dir + file);
					a1.read(ff, null);
					ff.close();

				} catch (Exception ew) {
				}

			}
		});

		save_as.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					FileDialog fd = new FileDialog(new JFrame(),
							"Save HMTL/HTM File");
					fd.setMode(FileDialog.SAVE);
					fd.setVisible(true);

					String dir = fd.getDirectory();
					String file = fd.getFile();

					current_file = dir + file;

					FileWriter wr = new FileWriter(current_file);
					wr.write(a1.getText());
					wr.close();

				} catch (Exception ew) {
				}

			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					FileWriter wr = new FileWriter(current_file);
					wr.write(a1.getText());
					wr.close();

				} catch (Exception ew) {
				}

			}
		});

		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					FileWriter wr = new FileWriter("sample.html");
					wr.write(a1.getText());
					wr.close();

					FileWriter wr1 = new FileWriter("1.bat");
					wr1.write("start sample.html");
					wr1.close();

					Runtime r = Runtime.getRuntime();
					r.exec("1.bat");

				} catch (Exception ew) {
				}

			}
		});

		// Thread

		t1 = new Thread() {
			@SuppressWarnings({ "static-access", "deprecation" })
			public void run() {

				try {
					while (true) {
						try {
							t1.sleep(2500);
						} catch (Exception e) {
						}
						try {
							ed.setText(a1.getText());
						} catch (Exception e) {
							System.out
									.println("Memory Out of Bounds Exception!\n"
											+ e);
							t1.suspend();
							t1.resume();
						}
					}

				} catch (Exception e) {
					System.out.println("Memory Out of Bounds Exception!\n" + e);
					t1.suspend();
					t1.resume();
				}

			}
		};
		t1.start();

	}

	public static void main(String args[]) {
		HtmlEditor h = new HtmlEditor();
		h.setVisible(true);
		h.setSize(700, 600);

	}

}
