package teilchenbautkasten.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import teilchenbautkasten.gui.component.TButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7272139904039060471L;
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
					MenuGUI frame = new MenuGUI();
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
	public MenuGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				running = false;
			}
		});
		setTitle("Teilchenbaukasten");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 406, 292);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeilchenbaukasten = new JLabel("Teilchenbaukasten");
		lblTeilchenbaukasten.setForeground(Color.WHITE);
		lblTeilchenbaukasten.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTeilchenbaukasten.setBounds(22, 26, 191, 27);
		contentPane.add(lblTeilchenbaukasten);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(212, 26, 1, 309);
		contentPane.add(separator);
		
		JLabel lblcopyrightLukasWolfrum = new JLabel("\u00A9 Lukas Wolfrum 2019/20");
		lblcopyrightLukasWolfrum.setForeground(Color.LIGHT_GRAY);
		lblcopyrightLukasWolfrum.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblcopyrightLukasWolfrum.setHorizontalAlignment(SwingConstants.TRAILING);
		lblcopyrightLukasWolfrum.setBounds(22, 64, 180, 20);
		contentPane.add(lblcopyrightLukasWolfrum);
		
		JLabel lblImRahmenDes = new JLabel("im Rahmen des MINT Seminars");
		lblImRahmenDes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblImRahmenDes.setForeground(Color.LIGHT_GRAY);
		lblImRahmenDes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblImRahmenDes.setBounds(22, 84, 180, 20);
		contentPane.add(lblImRahmenDes);
		
		JLabel lblHttpwwwlukasdeteilchenbaukasten = new JLabel("lukas-10100.de/teilchenbaukasten");
		lblHttpwwwlukasdeteilchenbaukasten.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHttpwwwlukasdeteilchenbaukasten.setForeground(Color.LIGHT_GRAY);
		lblHttpwwwlukasdeteilchenbaukasten.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		lblHttpwwwlukasdeteilchenbaukasten.setBounds(10, 115, 192, 20);
		contentPane.add(lblHttpwwwlukasdeteilchenbaukasten);
		
		TButton button = new TButton("Teilchenzoo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop d = Desktop.getDesktop();
				try {
				    d.browse(new URI("http://teilchenzoo.lukas-10100.de"));
				} catch (IOException | URISyntaxException e2) {
				    e2.printStackTrace();
				} 
			}
		});
		button.setAccent(Color.YELLOW);
		button.setBounds(223, 39, 157, 37);
		contentPane.add(button);
		
		TButton button_1 = new TButton("Theorie");
		button_1.setAccent(Color.YELLOW);
		button_1.setBounds(223, 83, 157, 37);
		contentPane.add(button_1);
		
		TButton button_2 = new TButton("Quellen");
		button_2.setAccent(Color.YELLOW);
		button_2.setBounds(223, 126, 157, 37);
		contentPane.add(button_2);
		
		TButton button_3 = new TButton("Einstellungen");
		button_3.setAccent(Color.CYAN);
		button_3.setBounds(223, 189, 157, 37);
		contentPane.add(button_3);
		
	}
}
