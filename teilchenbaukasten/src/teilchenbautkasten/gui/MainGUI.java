package teilchenbautkasten.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import teilchenbautkasten.gui.component.BaukastenPanel;
import teilchenbautkasten.gui.component.BaukastenPanel.ParticleRecognizedEvent;
import teilchenbautkasten.gui.component.ElementaryParticleCellRenderer;
import teilchenbautkasten.gui.component.TButton;
import teilchenbautkasten.particles.ElementaryParticle;
import teilchenbautkasten.particles.Hadron;
import teilchenbautkasten.particles.ParticleType;
import teilchenbautkasten.particles._all;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443991209440478957L;
	private JPanel contentPane;
	private JList<ElementaryParticle> list;
	public static JTextArea txtrProperties;
	public static BaukastenPanel baukastenPanel;
	public static JLabel lblCharge;
	public static JComboBox<String> selectedSpin;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		_all.init();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setTitle("Teilchenbaukasten");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList<ElementaryParticle>();
		list.setFixedCellWidth(70);
		baukastenPanel = new BaukastenPanel(ParticleType.BARYON);
		baukastenPanel.setBounds(294, 11, 399, 263);
		baukastenPanel.setBackground(Color.BLACK);
		baukastenPanel.setPartlist(list);
		contentPane.add(baukastenPanel);
		baukastenPanel.setLayout(null);

		lblCharge = new JLabel("?e");

		baukastenPanel.tee.add(new ParticleRecognizedEvent() {

			@Override
			public void recognized(Hadron[] teilchen) {
				if(teilchen != null) {
					txtrProperties.setText("Eigenschaften:\r\n"+
							"--------------------\r\n");
					int a = 0;
					for(Hadron particle : teilchen) {
						lblCharge.setText((particle.getCharge() + "e").replace(".0", ""));
						String text = 
								"Name: " + particle.getName() + "\r\n" +
										"Symbol: " + particle.getSymbol() + "\r\n" +
										"Aufbau: " + particle.getContent()[0].getSymbol() + particle.getContent()[1].getSymbol() + (particle.getContent().length>2?particle.getContent()[2].getSymbol():"") + "\r\n" +
										"Ladung: " + particle.getCharge() + "e\r\n" +
										"Masse: " + particle.getMass() + "MeV/c²\r\n" +
										"Lebensdauer: " + (particle.getLifetime()==-1?"Unendlich":particle.getLifetime() + "s") + "\r\n" +
										"Zerfälle: " + "?" + "\r\n" +
										"Spin: " + (particle.getSpin()==-1?"Unbekannt":particle.getSpin()) + "\r\n";
						txtrProperties.setText(txtrProperties.getText()+(teilchen.length>1&&a!=0?"____________________\r\n":"")+text.replace(".0", "").replace('.', ','));
						a++;
					}
				}else {
					String text = "Eigenschaften:\r\n"+
							"--------------------\r\n\r\n";
					txtrProperties.setText(text);
				}
			}
		});

		lblCharge.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblCharge.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCharge.setForeground(Color.LIGHT_GRAY);
		lblCharge.setBounds(267, 11, 110, 25);
		baukastenPanel.add(lblCharge);

		selectedSpin = new JComboBox<>();
		selectedSpin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				switch(selectedSpin.getSelectedIndex()) {
				case 0:
					baukastenPanel.setFilterSpin(0.5F);
					break;
				case 1:
					baukastenPanel.setFilterSpin(1.5F);
					break;
				case 2:
					baukastenPanel.setFilterSpin(1.0F);
					break;
				case 3:
					baukastenPanel.setFilterSpin(0.0F);
					break;
				default:
					baukastenPanel.setFilterSpin(0.5F);
					break;
				}
				baukastenPanel.rerecognize();
				baukastenPanel.repaint();
			}
		});
		selectedSpin.setModel(new DefaultComboBoxModel<String>(new String[] {"1/2", "3/2", "1", "0"}));
		selectedSpin.setBounds(10, 11, 41, 22);
		baukastenPanel.add(selectedSpin);
		baukastenPanel.setFilterSpin(0.5F);

		TButton button = new TButton("Men\u00FC");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!MenuGUI.running) {
					MenuGUI.main(null);
				}
			}
		});
		button.setBounds(727, 11, 119, 42);
		button.setAccent(Color.ORANGE);
		contentPane.add(button);

		TButton button_1 = new TButton("Neu");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!NewParticleGUI.running) {
					NewParticleGUI.main(null);
				}
			}
		});
		button_1.setBounds(727, 59, 119, 42);
		button_1.setAccent(Color.MAGENTA);
		contentPane.add(button_1);

		TButton button_2 = new TButton("Speichern");
		button_2.setBounds(727, 107, 119, 42);
		button_2.setAccent(Color.MAGENTA);
		contentPane.add(button_2);

		TButton button_4 = new TButton("\u00D6ffnen");
		button_4.setBounds(727, 155, 119, 42);
		button_4.setAccent(Color.MAGENTA);
		contentPane.add(button_4);

		TButton button_3 = new TButton("Zerfall");
		button_3.setBounds(727, 203, 119, 42);
		contentPane.add(button_3);

		JLabel lblEigenschaften = new JLabel("Eigenschaften");
		lblEigenschaften.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblEigenschaften.setForeground(Color.WHITE);
		lblEigenschaften.setBounds(10, 11, 119, 22);
		contentPane.add(lblEigenschaften);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(20, 44, 264, 230);
		contentPane.add(scrollPane_1);

		txtrProperties = new JTextArea();
		scrollPane_1.setViewportView(txtrProperties);
		txtrProperties.setBackground(Color.DARK_GRAY);
		txtrProperties.setForeground(Color.LIGHT_GRAY);
		txtrProperties.setText("?");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 296, 844, 84);
		contentPane.add(scrollPane);

		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);	
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(1);
		list.setBackground(Color.DARK_GRAY);
		list.setFixedCellHeight(70);
		DefaultListModel<ElementaryParticle> listModel = new DefaultListModel<ElementaryParticle>();
		listModel.addElement(_all.quark_up);
		listModel.addElement(_all.quark_down);
		listModel.addElement(_all.quark_charm);
		listModel.addElement(_all.quark_strange);
		listModel.addElement(_all.quark_top);
		listModel.addElement(_all.quark_bottom);
		listModel.addElement(_all.ANTIquark_up);
		listModel.addElement(_all.ANTIquark_down);
		listModel.addElement(_all.ANTIquark_charm);
		listModel.addElement(_all.ANTIquark_strange);
		listModel.addElement(_all.ANTIquark_top);
		listModel.addElement(_all.ANTIquark_bottom);
		list.setModel(listModel);
		list.setCellRenderer(new ElementaryParticleCellRenderer());
		scrollPane.setViewportView(list);
	}

}
