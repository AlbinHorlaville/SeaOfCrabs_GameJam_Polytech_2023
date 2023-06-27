package info3.game.vue.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;

public class AutomataView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private boolean modified;
	private int changesCount;
	private JButton btnSave;
	private JLabel lblModifications;

	public AutomataView() {

		initWindow(); // init window's configuration

		modified = false;
		changesCount = 0;

		// *************************************************************//
		// Content Pane
		// *************************************************************//

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// *************************************************************//
		// Table
		// *************************************************************//

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);

		Object[][] data = new Object[GameEntity.values().length][2];

		for (int i = 0; i < GameEntity.values().length; i++) {
			Object[] o = AutomateLoader.getHashMapValueByName(GameEntity.values()[i]);
			if (o != null) {
				data[i] = o;
			}
		}

		DefaultTableModel model = new DefaultTableModel(data, new String[] { "Entity familly", "Automata" }) {

			// Override the isCellEditable method to make the second column non-editable
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0; // Make the second column (index 1) non-editable
			}
		};

		table.setModel(model);

		// *************************************************************//
		// Scroll Pane
		// *************************************************************//

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(100, 150));
		scrollPane.setViewportView(table);

		// *************************************************************//
		// Button save
		// *************************************************************//

		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modified) {

					int input = JOptionPane.showConfirmDialog(null, "Are you sure to make these changes?");
					// 0=yes, 1=no, 2=cancel

					if (input == 0) {
						Object[][] tableData = new Object[GameEntity.values().length][];
						int rowCount = table.getRowCount();
						int columnCount = table.getColumnCount();

						for (int row = 0; row < rowCount; row++) {
							tableData[row] = new Object[columnCount];

							for (int column = 0; column < columnCount; column++) {
								Object value = table.getValueAt(row, column);
								tableData[row][column] = value;
							}
						}

						AutomateLoader.updateConfig(tableData);
						btnSave.setEnabled(false);
						lblModifications.setText("");
						changesCount = 0;
						modified = false;
						AutomateLoader.getAutomateLoader();
					}

				}
			}
		});

		// *************************************************************//
		// Modification Label
		// *************************************************************//

		lblModifications = new JLabel("");
		lblModifications.setForeground(Color.red);

		// *************************************************************//
		// Combo Box
		// *************************************************************//

		JComboBox<String> cb = new JComboBox<>();
		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changesCount = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					Object inTable = table.getModel().getValueAt(i, 1);
					Object inFile = data[i][1];
					if (!inTable.equals(inFile)) {
						btnSave.setEnabled(true);
						changesCount += 1;
						modified = true;
					}
				}
				if (modified && changesCount != 0) {
					lblModifications.setText("Warning: you have modified " + changesCount + " automata");
				} else {
					lblModifications.setText("");
					btnSave.setEnabled(false);
				}
			}

		});

		for (String s : AutomateLoader.getList_Automate_name()) {
			cb.addItem(s);
		}

		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cb));

		contentPane.add(scrollPane, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		panel_1.add(lblModifications);
		panel_1.add(btnSave);

	}

	private void initWindow() {
		setResizable(false);
		setTitle("SeaOfCrabs - Configure game's automata");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(680, 400);
	}

}
