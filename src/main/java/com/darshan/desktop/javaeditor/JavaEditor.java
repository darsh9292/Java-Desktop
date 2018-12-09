package com.darshan.desktop.javaeditor;

/**
 * @author Darshan Patel
 */
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class JavaEditor {
	public static void main(String[] ar) {
		Frame2 f = new Frame2();

		f.fgh = 0;
		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception w) {
		}

		f.setVisible(true);
	}
}

class Frame2 extends JFrame implements Printable {
	private static final long serialVersionUID = 1L;

	JPanel jp1, jp2;

	JMenuBar mbar = new JMenuBar();
	JMenu m1 = new JMenu("File");
	JMenu m2 = new JMenu("Compile/Run");
	JMenu m3 = new JMenu("Help");
	JMenu m4 = new JMenu("Edit");

	int uy = 0;

	String getpath, getpath2;
	String s22, s26;

	String s24 = "javac ";
	String s25 = " 2> \"d:\\Newfolder\\com.txt\"";
	String s12, sss;
	String s23 = "cd /d ";
	String path;

	JMenuItem m11 = new JMenuItem("New");
	JMenuItem m12 = new JMenuItem("Open");
	JMenuItem m13 = new JMenuItem("Save");
	JMenuItem m14 = new JMenuItem("Save AS");
	JMenuItem m15 = new JMenuItem("Page Setup");
	JMenuItem m16 = new JMenuItem("Print");
	JMenuItem m17 = new JMenuItem("EXIT");

	JMenuItem m21 = new JMenuItem("Compile");
	JMenuItem m22 = new JMenuItem("Run");

	JMenuItem m31 = new JMenuItem("About");

	JMenuItem m41 = new JMenuItem("Undo");
	JMenuItem m42 = new JMenuItem("Redo");
	JMenuItem m43 = new JMenuItem("Cut");
	JMenuItem m44 = new JMenuItem("Copy");
	JMenuItem m45 = new JMenuItem("Paste");
	JMenuItem m46 = new JMenuItem("Select All");

	UndoManager undo = new UndoManager();
	Document doc;

	int scs = 0;

	FileDialog f;

	boolean isOpen = false;
	String filename = "";
	PrinterJob pj;
	PageFormat pf = new PageFormat();

	int darsh = 0;

	int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
	int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

	int fgh = 0;

	JTextArea t = new JTextArea();

	JTextArea t1 = new JTextArea();

	JScrollPane jpjp;

	JScrollPane jpjp1;

	FileWriter wr1, wrr;

	Thread thth;

	String tfinalstring;
	String tfstring = "";

	Frame2() {
		this.setTitle("My JCompiler_Editor");
		// this.setLayout(null);

		this.setSize(new Dimension(900, 700));
		this.setLocation(50, 20);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);

		jp1 = new JPanel();
		jp2 = new JPanel();

		this.setJMenuBar(mbar);
		// this.add(t);
		jpjp = new JScrollPane(t);

		doc = t.getDocument();

		doc.addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent evt) {
				undo.addEdit(evt.getEdit());
			}
		});

		t1.setText("Compilation Result:");

		jpjp1 = new JScrollPane(t1);

		t1.setEditable(false);

		t.setText("class class_name\n{\n\tpublic static void main(String args[])\n\t{\n\t\tSystem.out.println(\"Hello\");\n\t}\n}\n\n\n\n\n\n\n\n\n\n\n\n\n");

		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jpjp, jpjp1);

		this.add(jsp);

		m1.setMnemonic('F');
		m2.setMnemonic('C');
		m3.setMnemonic('H');
		m4.setMnemonic('E');

		mbar.add(m1);
		mbar.add(m4);
		mbar.add(m2);
		mbar.add(m3);

		m11.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
				java.awt.Event.CTRL_MASK));

		m13.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
				java.awt.Event.CTRL_MASK));

		m12.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
				java.awt.Event.CTRL_MASK));

		m16.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P,
				java.awt.Event.CTRL_MASK));

		m17.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_F4, java.awt.Event.ALT_MASK));

		m21.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1,
				java.awt.Event.CTRL_MASK));

		m22.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2,
				java.awt.Event.CTRL_MASK));

		m41.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,
				java.awt.Event.CTRL_MASK));

		m42.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y,
				java.awt.Event.CTRL_MASK));

		m43.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
				java.awt.Event.CTRL_MASK));

		m44.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
				java.awt.Event.CTRL_MASK));

		m45.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V,
				java.awt.Event.CTRL_MASK));

		m46.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
				java.awt.Event.CTRL_MASK));

		/**********************************************************/
		m1.add(m11);
		m1.add(m12);
		m1.add(m13);
		m1.add(m14);

		m1.addSeparator();
		m1.add(m15);
		m1.add(m16);

		m1.addSeparator();
		m1.add(m17);

		m4.add(m41);
		m4.add(m42);
		m4.addSeparator();
		m4.add(m43);
		m4.add(m44);
		m4.add(m45);
		m4.add(m46);

		/**********************************************************/

		m2.add(m21);
		m2.add(m22);

		m3.add(m31);

		FileWriter twr1;
		try {
			String strDirectoy = "d:\\Newfolder";

			(new File(strDirectoy)).mkdir();

			twr1 = new FileWriter("d:\\Newfolder\\com.txt");

			twr1.write("");
			twr1.close();
		} catch (IOException ex) {
			Logger.getLogger(Frame2.class.getName())
					.log(Level.SEVERE, null, ex);
		}

		t.setFont(new Font("Arial", Font.PLAIN, 20));

		m41.addActionListener(new ActionListener() {
			@SuppressWarnings("serial")
			public void actionPerformed(ActionEvent e) {
				t.getActionMap().put("Undo", new AbstractAction("Undo") {
					public void actionPerformed(ActionEvent evt) {
						try {
							if (undo.canUndo()) {
								undo.undo();
							}
						} catch (CannotUndoException e) {
						}
					}
				});
				// t.getInputMap().put(KeyStroke.getKeyStroke("control Z"),
				// "Undo");
				t.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit
								.getDefaultToolkit().getMenuShortcutKeyMask()),
						"Undo");
			}
		});
		m42.addActionListener(new ActionListener() {
			@SuppressWarnings("serial")
			public void actionPerformed(ActionEvent e) {
				t.getActionMap().put("Redo", new AbstractAction("Redo") {
					public void actionPerformed(ActionEvent evt) {
						try {
							if (undo.canRedo()) {
								undo.redo();
							}
						} catch (CannotRedoException e) {
						}
					}
				});
				// t.getInputMap().put(KeyStroke.getKeyStroke("control Y"),
				// "Redo");
				t.getInputMap().put(
						KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit
								.getDefaultToolkit().getMenuShortcutKeyMask()),
						"Redo");
			}
		});

		m17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					m17_click();
				} catch (Exception eee) {

				}
			}
		});
		m11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_click();
			}
		});
		m12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m12_click();
			}
		});
		m13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_click();
			}
		});
		m14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveas_click();
			}
		});

		m15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pagesetup_click();
			}
		});
		m16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print_click();
			}
		});

		m31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show_dialog();
			}
		});

		m21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					show_dialog_compile();
				} catch (IOException ex) {

				}

			}
		});
		m22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,
						"Open directory where you save and run run.bat file.",
						"About", JOptionPane.PLAIN_MESSAGE);

			}
		});

		pj = PrinterJob.getPrinterJob();

		thth = new Thread() {
			@SuppressWarnings({ "static-access", "deprecation" })
			public void run() {

				try {
					while (darsh == 0) {
						try {
							thth.sleep(1500);
						} catch (Exception e) {
						}
						try {
							tfstring = "";
							tfinalstring = "";

							String strDirectoy = "d:\\Newfolder";

							(new File(strDirectoy)).mkdir();
							if (s22 == null) {
								FileWriter twr = new FileWriter(
										"d:\\Newfolder\\sam_java.java");
								twr.write(t.getText());
								twr.close();
								s26 = "" + s24 + "sam_java.java" + s25;
							} else {
								FileWriter twr = new FileWriter(
										"d:\\Newfolder\\" + s22);
								twr.write(t.getText());
								twr.close();
								s26 = "" + s24 + s22 + s25;
							}

							String tsp = "d:\\Newfolder";

							String tss = "" + s23 + tsp;

							wr1 = new FileWriter("d:\\Newfolder\\2.bat");

							wr1.write(tss + "\r\n" + s26);

							wr1.close();

							Runtime r = Runtime.getRuntime();

							r.exec("d:\\Newfolder\\2.bat");

							FileReader tfr = new FileReader(
									"d:\\Newfolder\\com.txt");
							@SuppressWarnings("resource")
							BufferedReader tbr = new BufferedReader(tfr);

							while ((tfinalstring = tbr.readLine()) != null) {
								tfstring = tfstring + tfinalstring + "\n";

							}
							tfstring = tfstring.replace("sam_java.java",
									"result.java");

							t1.setText("Compilation Result:\n" + tfstring);

						} catch (Exception e) {
							System.out.println("Exception:" + e);
							thth.suspend();
							thth.resume();
						}
					}
				} catch (Exception e) {
					System.out.println("Exception:" + e);
					thth.suspend();
					thth.resume();
				}

			}
		};
		thth.start();

	}

	/**********************************************************/

	public int print(Graphics g, PageFormat p, int numOfPages) {
		return 0;
	}

	void show_dialog() {
		JOptionPane
				.showMessageDialog(
						null,
						"This editor can give a notepad facility as well as Java compiler.\n\nNote:If you find no reply from editor then check your directory or subdirectory name which have no space to run successfully.\n\nNote:Save before exit.",
						"About", JOptionPane.PLAIN_MESSAGE);

	}

	void show_dialog_compile() throws IOException {

		try {
			String strDir = "d:\\Newfolder\\com.txt";

			boolean success = (new File(strDir)).exists();

			if (success == false) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Save it first",
						"Compilation Result:", JOptionPane.PLAIN_MESSAGE);
			}

			FileReader fr = new FileReader("d:\\Newfolder\\com.txt");
			BufferedReader br = new BufferedReader(fr);
			String finalstring;
			String fstring = "";

			@SuppressWarnings("unused")
			int ght = 0;

			while ((finalstring = br.readLine()) != null) {
				fstring = fstring + finalstring + "\n";

				ght++;
			}

			if (fstring.equals("") && (t.getText() != null) && (s22 != null)) {
				JOptionPane.showMessageDialog(null, "No error",
						"Compilation Result:" + s22, JOptionPane.PLAIN_MESSAGE);
			} else if ((t.getText() == null)) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Enter the code first",
						"Compilation Result:" + s22, JOptionPane.PLAIN_MESSAGE);
			} else if (((t.getText() != null) && (s22 == null)) || (fgh == 0)) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Save it first",
						"Compilation Result:" + s22, JOptionPane.PLAIN_MESSAGE);
			}

			else {
				if (fgh == 1) {

					new ErrorDialog();
					// JOptionPane.showMessageDialog(null,fstring,"Compilation Result:"+s22,JOptionPane.PLAIN_MESSAGE);
				}
			}

			fr.close();

		} catch (Exception e) {
			System.out.println("error===" + e.getMessage());
			e.printStackTrace();

		}
	}

	void pagesetup_click() {
		pf = pj.pageDialog(pf);
	}

	void print_click() {
		pj.printDialog();
	}

	void new_click() {

		int nn = JOptionPane.showConfirmDialog(null, "Do you want to save?",
				"JCompiler", JOptionPane.YES_NO_CANCEL_OPTION);

		if (nn == JOptionPane.YES_OPTION) {
			// save_click();

			if (s12 != null) {

				try {

					FileWriter fr = new FileWriter(filename);
					fr.write(t.getText());
					fr.close();

					repeat_code();

					uy = 1;

				} catch (Exception ee) {
				}
			} else {
				save_click();
			}

			fgh = 0;
			isOpen = false;
			filename = "";
			t.setText("class class_name\n{\n\tpublic static void main(String args[])\n\t{\n\t\tSystem.out.println(\"Hello\");\n\t}\n}");
		} else if (nn == JOptionPane.NO_OPTION) {
			fgh = 0;
			isOpen = false;
			filename = "";
			t.setText("class class_name\n{\n\tpublic static void main(String args[])\n\t{\n\t\tSystem.out.println(\"Hello\");\n\t}\n}");
		}

	}

	void repeat_code() {
		try {
			getpath = "" + path;
			s12 = f.getDirectory();
			s22 = f.getFile();

			String strDirectoy = "d:\\Newfolder";

			(new File(strDirectoy)).mkdir();

			sss = "" + s23 + s12;

			s26 = "" + s24 + s22 + s25;

			wr1 = new FileWriter("d:\\Newfolder\\1.bat");

			wr1.write(sss + "\r\n" + s26);

			wr1.close();

			Runtime r = Runtime.getRuntime();

			r.exec("d:\\Newfolder\\1.bat");

			wrr = new FileWriter(s12 + "run.bat");

			wrr.write("start");

			wrr.close();

			fgh = 1;
		} catch (Exception e) {

		}
	}

	void save_click() {
		if (isOpen) {

			try {

				FileWriter fr = new FileWriter(filename);
				fr.write(t.getText());
				fr.close();

				repeat_code();

				uy = 1;

			} catch (Exception ee) {
			}
		} else {

			f = new FileDialog(this, "Save file as...", FileDialog.SAVE);

			f.setVisible(true);

			if (f.getFile() != null) {
				path = f.getDirectory() + f.getFile();
				try {

					FileWriter fr = new FileWriter(path);
					fr.write(t.getText());
					fr.close();

					repeat_code();

					fgh = 1;
					uy = 1;

					isOpen = true;
					filename = path;
				} catch (Exception ee) {
				}
			}
		}
	}

	void saveas_click() {
		f = new FileDialog(this, "Save file as...", FileDialog.SAVE);
		f.setVisible(true);
		if (f.getFile() != null) {
			String path = f.getDirectory() + f.getFile();
			try {
				FileWriter fr = new FileWriter(path);
				fr.write(t.getText());
				fr.close();

				repeat_code();

				uy = 1;

				isOpen = true;
				filename = path;

			} catch (Exception ee) {
			}
		}
	}

	void m12_click() {
		f = new FileDialog(this);
		f.setVisible(true);
		if (f.getFile() != null) {
			String path = f.getDirectory() + f.getFile();
			try {
				FileReader fr = new FileReader(path);
				BufferedReader bfr = new BufferedReader(fr);
				String s = bfr.readLine();
				String data = "";
				while (s != null) {
					data = data + s + "\n";
					s = bfr.readLine();
				}
				t.setText(data);
				bfr.close();
				fr.close();

				repeat_code();

				uy = 1;

				isOpen = true;
				filename = path;

			} catch (Exception ee) {
			}

		}

	}

	public boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	void m17_click() throws Exception {

		int nn = JOptionPane.showConfirmDialog(null, "Do you want to save?",
				"JCompiler", JOptionPane.YES_NO_CANCEL_OPTION);

		if (nn == JOptionPane.YES_OPTION) {
			darsh = 1;

			if (s12 != null) {

				try {

					FileWriter fr = new FileWriter(filename);
					fr.write(t.getText());
					fr.close();

					repeat_code();

					uy = 1;

				} catch (Exception ee) {
				}
			} else {
				save_click();
			}

			deleteDir(new File("d:/Newfolder"));

			System.exit(0);

		} else if (nn == JOptionPane.NO_OPTION) {
			darsh = 1;

			// thth.stop();
			deleteDir(new File("d:/Newfolder"));

			System.exit(0);
		}

	}
	/**********************************************************/
}
