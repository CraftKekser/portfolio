package teilchenbautkasten.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import teilchenbautkasten.gui.component.TButton;
import teilchenbautkasten.particles.ElementaryParticle;
import teilchenbautkasten.particles.ParticleType;
import teilchenbautkasten.particles._all;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewParticleGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3738006220165876908L;
	private JPanel contentPane;
	public static boolean running = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewParticleGUI frame = new NewParticleGUI();
					frame.setVisible(true);
					running = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewParticleGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				running = false;
			}
		});
		setBackground(Color.DARK_GRAY);
		setTitle("Neues Teilchen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNeuesTeilchen = new JLabel("Neues Teilchen");
		lblNeuesTeilchen.setHorizontalAlignment(SwingConstants.CENTER);
		lblNeuesTeilchen.setForeground(Color.WHITE);
		lblNeuesTeilchen.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNeuesTeilchen.setBounds(121, 11, 191, 27);
		contentPane.add(lblNeuesTeilchen);

		JLabel lblArt = new JLabel("Art:");
		lblArt.setForeground(Color.WHITE);
		lblArt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblArt.setHorizontalAlignment(SwingConstants.TRAILING);
		lblArt.setBounds(70, 69, 46, 20);
		contentPane.add(lblArt);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Baryon", "Meson"}));
		comboBox.setBounds(138, 69, 191, 22);
		contentPane.add(comboBox);

		JLabel lblPreset = new JLabel("Preset:");
		lblPreset.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPreset.setForeground(Color.WHITE);
		lblPreset.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPreset.setBounds(70, 100, 46, 20);
		contentPane.add(lblPreset);

		JComboBox<String> comboBox_1 = new JComboBox<>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Leer", "Proton", "Neutron", "..."}));
		comboBox_1.setBounds(138, 100, 191, 22);
		contentPane.add(comboBox_1);

		TButton button = new TButton("Erstellen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ParticleType type = comboBox.getSelectedIndex()==0?ParticleType.BARYON:ParticleType.MESON;
				switch(comboBox_1.getSelectedIndex()) {
				case 1:
					MainGUI.baukastenPanel.put(type, (ElementaryParticle[]) _all.proton.getContent());
					MainGUI.selectedSpin.setSelectedIndex(0);
					break;
				default:
					MainGUI.baukastenPanel.reset(type);
					MainGUI.selectedSpin.setSelectedIndex(type==ParticleType.BARYON?0:2);
					break;
				}
				MainGUI.baukastenPanel.rerecognize();
				MainGUI.baukastenPanel.repaint();
				running = false;
				dispose();
			}
		});
		button.setBounds(156, 147, 119, 36);
		contentPane.add(button);
	}
}
