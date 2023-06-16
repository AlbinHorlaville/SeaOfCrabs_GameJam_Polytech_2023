package info3.game.vue.view;

import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import automate.Automate;
import automate.AutomateLoader;
import info3.game.modele.GameEntity;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Panel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JButton;

public class AutomataView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public AutomataView() {
		setResizable(false);
		setTitle("SeaOfCrabs - Configure game's automata");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(680, 393);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewAutomata = new JMenuItem("New automata");
		mnFile.add(mntmNewAutomata);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(100, 150));

		GameEntity.values();

		table = new JTable();
		scrollPane.setViewportView(table);

		Object[][] data = new Object[GameEntity.values().length][];

		int i = 0;

		for (String s : AutomateLoader.getAutomateLoader().keySet()) {
			data[i] = new Object[] { GameEntity.values()[i], AutomateLoader.getAutomateLoader().get(s).name };
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(data, new String[] { "Entity familly", "Automata" }) {
			// Override the isCellEditable method to make the second column non-editable
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0; // Make the second column (index 1) non-editable
			}
		};

		table.setModel(model);

		JComboBox<String> cb = new JComboBox<>();
		for (String s : AutomateLoader.getList_Automate_name()) {
			cb.addItem(s);
		}
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cb));

		contentPane.add(scrollPane, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblModifications = new JLabel("Modifications");
		panel_1.add(lblModifications);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] tableData = new Object[GameEntity.values().length][];
				int rowCount = table.getRowCount();
				int columnCount = table.getColumnCount();

				for (int row = 0; row < rowCount; row++) {
					tableData[row] = new Object[columnCount]; // Initialize the array for each row

					for (int column = 0; column < columnCount; column++) {
						Object value = table.getValueAt(row, column);
						tableData[row][column] = value; // Assign the value to a specific index
					}
				}

				AutomateLoader.updateConfig(tableData);
			}
		});
		panel_1.add(btnSave);

	}

}
