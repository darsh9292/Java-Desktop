package com.darshan.desktop.filesearch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 * @author Darshan Patel
 */
public class FileSearchGui implements Runnable {

	JTextArea jta;
	JTextField jtf;
	String dirname = "c:/";
	int mg = 0;
	int count = 0;
	String s = "C:";
	int flagflag = 0;

	Thread t1 = new Thread(this);
	Thread t2 = new Thread(this);
	Thread t3 = new Thread(this);
	Thread t4 = new Thread(this);

	Object[][] rowData = {};

	Object[] columnNames = { "No", "FileName", "InFolder" };

	int row = 0;
	int col = 0;

	int tcount = 0;

	JTable table;
	DefaultTableModel model;
	File frun = null;

	@SuppressWarnings("rawtypes")
	FileSearchGui() throws IOException {

		final JFrame jfrm = new JFrame("Compilation Result");

		JPanel tjp = new JPanel();

		String drives[] = { "C:", "D:", "E:", "F:", "All" };

		JLabel jlab;

		JPanel jpa1 = new JPanel();

		tjp.setLayout(new FlowLayout(FlowLayout.LEFT));

		jpa1.setLayout(new FlowLayout(FlowLayout.LEFT));

		jfrm.setLayout(new BorderLayout());

		// jfrm.setSize(500,300);
		// jfrm.setLocation(250, 200);

		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception w) {
		}

		jlab = new JLabel("Chose your option:");

		@SuppressWarnings("unchecked")
		final JComboBox jcb = new JComboBox(drives);

		jtf = new JTextField(22);

		JButton jb = new JButton("Search");

		model = new DefaultTableModel(rowData, columnNames);
		table = new JTable();
		table.setModel(model);

		table.setEnabled(false);

		/*
		 * jta=new JTextArea(); jta.setEditable(false);
		 */
		tjp.add(jlab);
		tjp.add(jcb);
		tjp.add(jtf);
		tjp.add(jb);

		JScrollPane jsp = new JScrollPane(table);

		// JScrollPane jsp = new JScrollPane(jta);

		jfrm.add(tjp, "North");

		jfrm.add(jsp, "Center");

		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				tcount = 0;
				count = 0;
				// jta.setText("");
				if (mg == 0) {
					File f2f2 = new File(dirname);
					serchfun(f2f2);
				} else {
					try {
						t1.start();
						// File f2f2f1 = new File("d:/");
						// serchfun(f2f2f1);
						flagflag = 1;
						t2.start();
						// File f2f2f2 = new File("e:/");
						// serchfun(f2f2f2);
						flagflag = 2;
						t3.start();
						// File f2f2f3 = new File("F:/");
						// serchfun(f2f2f3);
						flagflag = 3;
						t4.start();
						// File f2f2f4 = new File("c:/");
						// serchfun(f2f2f4);
					} catch (Exception ee) {
						System.out.println(ee);
					}
				}
			}
		});

		jcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				s = (String) jcb.getSelectedItem();

				if (s.startsWith("C:")) {
					mg = 0;
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					tcount = 0;

					// jta.setText("");
					dirname = "c:/";

				} else if (s.startsWith("D:")) {
					mg = 0;
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					tcount = 0;
					// jta.setText("");
					dirname = "d:/";

				} else if (s.startsWith("E:")) {
					mg = 0;
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					tcount = 0;
					// jta.setText("");
					dirname = "e:/";

				} else if (s.startsWith("F:")) {
					mg = 0;
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					tcount = 0;
					// jta.setText("");
					dirname = "f:/";

				} else if (s.startsWith("All")) {
					mg = 1;

					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					tcount = 0;
					// jta.setText("");

				}
			}
		});

		jfrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jfrm.setVisible(true);
	}

	public void serchfun(File f1) {
		try {
			if (f1.isDirectory()) {
				String s[] = f1.list();

				for (int i = 0; i < s.length; i++) {
					if (f1.isDirectory()) {
						if (s[i].toLowerCase().contains(
								jtf.getText().toLowerCase())) {
							// rowData[count]={{++count,s[i],f1.getPath()}};
							// model.addRow(rowData[0]);

							tcount++;

							model.addRow(new Object[] { tcount, s[i],
									f1.getPath() });
							count++;
							// jta.append(count+")"+s[i]+"\t"+f1.getPath()+"\n");
							// jta.validate();
						}

						serchfun(new File(f1, s[i]));

					} else {
						if (s[i].toLowerCase().contains(
								jtf.getText().toLowerCase())) {

							model.addRow(new Object[] { tcount, s[i],
									f1.getPath() });
							// model.addRow(rowData[0]);

							tcount++;
							model.insertRow(1,
									new Object[] { tcount, s[i], f1.getPath() });

							count++;
							// jta.append(count+")"+s[i]+"\t"+f1.getPath()+"\n");
							// jta.validate();
						}

					}
				}
			}

		} catch (Exception ee) {
			System.out.println(count);
			System.out.println("" + ee);
		}
	}

	public void run() {
		if (flagflag == 0) {
			frun = new File("d:/");
			serchfun(frun);
		} else if (flagflag == 1) {
			frun = new File("e:/");
			serchfun(frun);
		} else if (flagflag == 2) {
			frun = new File("f:/");
			serchfun(frun);
		} else {
			frun = new File("c:/");
			serchfun(frun);
		}

	}

	public static void main(String args[]) {

		// SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		try {
			new FileSearchGui();
		} catch (Exception ex) {
			Logger.getLogger(FileSearchGui.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		// }
		// });
	}

}