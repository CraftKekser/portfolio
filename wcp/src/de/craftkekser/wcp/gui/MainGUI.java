package de.craftkekser.wcp.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import de.craftkekser.ips.gui.component.CButton;
import de.craftkekser.ips.gui.component.CProgressBar;
import de.craftkekser.ips.gui.component.CTextField;
import de.craftkekser.wcp.ImageProcessing;
import de.craftkekser.wcp.Scaling;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6182345572505525861L;
	private JPanel contentPane;
	// Variablen der Steuerelemente
	private CTextField textField;
	private CTextField txtfldThumbnail;
	private CTextField txtfldOutput;
	private CTextField txtfldInput;
	private CButton button;
	private CButton button_1;
	private CButton btnKeepRatio;
	private CButton btnProcess;
	private CProgressBar progressBar;
	private JTextArea txtrPoutput;

	/**
	 * Launch the application.
	 * (WindowBuilder)
	 */
	public static void main(String[] args) {
		// Look and feel setzen, wobei sowieso eigene Steuerelemente verwendet werden
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Fenster sichtbar machen
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
	 * (WindowBuilder)
	 */
	public MainGUI() {
		// Hier werden alle Steuerelemente initialisiert und deren Eigenschaften festgelegt
		// Ausserdem werden hier EventListener gesetzt
		
		// Die Grundlagen, wie z.B. das erstellen und initialisieren der Variablen hat WindowBuilder (Eclipse-Plugin) in diesem Fall uebernommen
		
		// Einstellungen des Fensters an sich
		setTitle("WCP");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 376);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Andere Steuerelemente
		JLabel lblInput = new JLabel("Input:");
		lblInput.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInput.setForeground(Color.WHITE);
		lblInput.setBounds(10, 13, 70, 14);
		contentPane.add(lblInput);

		txtfldInput = new CTextField();
		txtfldInput.setBounds(90, 6, 452, 27);
		txtfldInput.setEditable(false);
		txtfldInput.setText(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "wcp_in");
		contentPane.add(txtfldInput);

		button = new CButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Dateiauswahldialog erstellen und anzeigen
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Es sollen nur Verzeichnisse ausgewaehlt werden koennen
				chooser.setMultiSelectionEnabled(false); // Mehrfachauswahl deaktivieren
				File lastFile = new File(txtfldInput.getText().trim());
				if(lastFile.exists())
					chooser.setSelectedFile(lastFile); // Zuletzt besuchten Pfad wieder nutzen, sollte er existieren
				int ret = chooser.showOpenDialog(contentPane); // Dialog oeffnen und gewaehlte Option speichern
				if(ret == JFileChooser.APPROVE_OPTION) { // Nur fortfahren, wenn der Nutzer nicht "Abbrechen" angeklickt hat
					File f;
					if((f=chooser.getSelectedFile())!=null) { // Pruefen, ob eine Datei ausgewaehlt ist
						if(f.exists()) {
							// Wenn die Datei existiert, Pfad uebernehmen
							txtfldInput.setText(f.getAbsolutePath());
						}else {
							JOptionPane.showMessageDialog(contentPane, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(contentPane, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button.setBounds(541, 7, 51, 25);
		button.setText("...");
		contentPane.add(button);

		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOutput.setForeground(Color.WHITE);
		lblOutput.setBounds(10, 42, 70, 14);
		contentPane.add(lblOutput);

		txtfldOutput = new CTextField();
		txtfldOutput.setBounds(90, 35, 452, 27);
		txtfldOutput.setEditable(false);
		txtfldOutput.setText(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "wcp_out");
		contentPane.add(txtfldOutput);

		button_1 = new CButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Dateiauswahldialog erstellen und anzeigen
				// TL;DR Im Grunde das gleiche wie bei der Eingabedatei
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Es sollen nur Verzeichnisse ausgewaehlt werden koennen
				chooser.setMultiSelectionEnabled(false); // Mehrfachauswahl deaktivieren
				File lastFile = new File(txtfldOutput.getText().trim());
				if(lastFile.exists())
					chooser.setSelectedFile(lastFile); // Zuletzt besuchten Pfad wieder nutzen, sollte er existieren
				int ret = chooser.showSaveDialog(contentPane); // Dialog oeffnen und gewaehlte Option speichern
				if(ret == JFileChooser.APPROVE_OPTION) { // Nur fortfahren, wenn der Nutzer nicht "Abbrechen" angeklickt hat
					File f;
					if((f=chooser.getSelectedFile())!=null) { // Pruefen, ob eine Datei ausgewaehlt ist
						// Hier wird nicht geprueft, ob die Datei existiert, da sie spaeter erstellt werden wuerde
						txtfldOutput.setText(f.getAbsolutePath());
					}else {
						JOptionPane.showMessageDialog(contentPane, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button_1.setBounds(541, 36, 51, 25);
		button_1.setText("...");
		contentPane.add(button_1);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.GRAY);
		separator.setBounds(10, 73, 622, 2);
		contentPane.add(separator);

		JLabel lblThumbnail = new JLabel("Thumbnail");
		lblThumbnail.setForeground(Color.WHITE);
		lblThumbnail.setHorizontalAlignment(SwingConstants.CENTER);
		lblThumbnail.setBounds(34, 86, 86, 14);
		contentPane.add(lblThumbnail);

		txtfldThumbnail = new CTextField();
		txtfldThumbnail.setBounds(27, 98, 103, 27);
		txtfldThumbnail.setText("450x300");
		contentPane.add(txtfldThumbnail);

		JLabel label = new JLabel("Thumbnail");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setBounds(147, 86, 86, 14);
		contentPane.add(label);

		textField = new CTextField();
		textField.setBounds(140, 98, 103, 27);
		textField.setText("900x600");
		contentPane.add(textField);

		btnKeepRatio = new CButton();
		btnKeepRatio.setBounds(290, 98, 103, 27);
		btnKeepRatio.setText("Keep Ratio");
		btnKeepRatio.setBackground(new Color(0, 15, 20));
		btnKeepRatio.setSelected(true);
		contentPane.add(btnKeepRatio);

		btnProcess = new CButton();
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Prozess starten

				// Skalierungen verifizieren und speichern
				Scaling thumbnail;
				Scaling slide;
				if((thumbnail=Scaling.valueOf(txtfldThumbnail.getText()))==null) {
					// Umwandlung fehlgeschlagen -> ungueltige Eingabe -> Fehlermeldung und Event beenden (return)
					JOptionPane.showMessageDialog(contentPane, "Invalid thumbnail size", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if((slide=Scaling.valueOf(textField.getText()))==null) {
					// Umwandlung fehlgeschlagen -> ungueltige Eingabe -> Fehlermeldung und Event beenden (return)
					JOptionPane.showMessageDialog(contentPane, "Invalid slide size", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Dateien verifizieren und speichern
				File in = new File(txtfldInput.getText().trim());
				File out = new File(txtfldOutput.getText().trim());
				if((!in.exists()) || (!in.isDirectory())) {
					// Existiert nicht oder kein Verzeichnis -> ungueltige Eingabe -> Fehlermeldung und Event beenden (return)
					JOptionPane.showMessageDialog(contentPane, "Invalid input file: Must be a directory and has to exist", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!out.exists()) {
					// Sollte out noch nicht existieren: Verzeichnisse erstellen
					out.mkdirs();
				}
				if(!in.isDirectory()) {
					// Kein Verzeichnis -> ungueltige Eingabe -> Fehlermeldung und Event beenden (return)
					JOptionPane.showMessageDialog(contentPane, "Invalid output file: Must be a directory", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// Steuerelemente deaktivieren (Verhindern von mehreren Prozessen gleichzeitig)
				txtfldInput.setEnabled(false);
				txtfldOutput.setEnabled(false);
				txtfldThumbnail.setEnabled(false);
				textField.setEnabled(false);
				button.setEnabled(false);
				button_1.setEnabled(false);
				btnKeepRatio.setEnabled(false);
				btnProcess.setEnabled(false);

				// Umwandlung in neuem Thread
				Thread process = new Thread(new Runnable() {

					@Override
					public void run() {
						// Log-Ausgabe leeren und ProgressBar zuruecksetzen
						txtrPoutput.setText("");
						progressBar.setValue(0);
						
						// Anzahl der Bilder speichern und Werteschritte der Progressbar berechnen, Dateien Laden
						File[] files = in.listFiles();
						List<String> failed = new ArrayList<String>();
						int fileAmount = files.length;
						double step = 100.0D/fileAmount;
						double value = 0.0D; // Genauer Stand der Progressbar, der dann in einen Integer konvertiert wird
						
						// Liste der Dateien durchlaufen
						for(int i = 0; i < fileAmount; i++) {
							File theFile = files[i];
							if(theFile.getName().toLowerCase().endsWith(".jpg")) {
								txtrPoutput.append("Processing: " + theFile.getName() + "\r\n");
								try {
									// Bild aus Datei laden
									BufferedImage original = ImageIO.read(theFile);
									txtrPoutput.append("   > Scaling...\r\n");
									
									// Bilder skalieren (-> ImageProcessing)
									BufferedImage[] scaled = ImageProcessing.scale(original, thumbnail, slide, txtrPoutput);
									
									// Skalierte Bilder speichern
									File thumbFile = new File(out + File.separator + "thmb_" + theFile.getName() + ".png");
									File slideFile = new File(out + File.separator + "sld_" + theFile.getName() + ".png");
									ImageIO.write(scaled[0], "png", thumbFile);
									txtrPoutput.append("   > Saved: " + thumbFile.getName() + "\r\n");
									ImageIO.write(scaled[1], "png", slideFile);
									txtrPoutput.append("   > Saved: " + thumbFile.getName() + "\r\n");
									
									// Original umbenennen und kopieren
									File originalFile = new File(out + File.separator + "org_" + theFile.getName());
									Files.copy(theFile.toPath(), originalFile.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
									txtrPoutput.append("   > Saved: " + originalFile.getName() + "\r\n");
								} catch (IOException e) {
									txtrPoutput.append("Failed: " + e.getMessage() + "\r\n");
									failed.add(theFile.getName());
								}
							}else {
								txtrPoutput.append("Failed: Not a .jpg file\r\n");
								failed.add(theFile.getName());
							}
							value+=step;
							progressBar.setValue(((int)value)>=100?100:(int)value);
						}
						
						// Zusammenfassung ausgeben
						txtrPoutput.append("\r\nSummary:\r\n"
								+ "   " + fileAmount + " Files total\r\n"
								+ "   " + failed.size() + " Failed\r\n\r\n");
						if(failed.size()>0) {
							txtrPoutput.append("Files which failed:\r\n");
							// Sollte etwas Fehlgeschlagen sein, betroffene Dateinamen auflisten
							failed.forEach(fileName -> {
								txtrPoutput.append("    " + fileName + "\r\n");
							});
						}
						
						// Steuerelemente wieder aktivieren

						txtfldInput.setEnabled(true);
						txtfldOutput.setEnabled(true);
						txtfldThumbnail.setEnabled(true);
						textField.setEnabled(true);
						button.setEnabled(true);
						button_1.setEnabled(true);
						btnKeepRatio.setEnabled(true);
						btnProcess.setEnabled(true);
						
					}
				});
				process.start();

			}
		});
		btnProcess.setBounds(403, 98, 103, 27);
		btnProcess.setText("Process");
		btnProcess.setBackground(new Color(0, 20, 10));
		contentPane.add(btnProcess);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.GRAY);
		separator_1.setBounds(10, 136, 622, 2);
		contentPane.add(separator_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 140, 622, 174);
		contentPane.add(scrollPane);

		txtrPoutput = new JTextArea();
		txtrPoutput.setEditable(false);
		txtrPoutput.setBorder(new EmptyBorder(4, 4, 4, 4));
		txtrPoutput.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtrPoutput.setForeground(Color.LIGHT_GRAY);
		txtrPoutput.setBackground(Color.BLACK);
		scrollPane.setViewportView(txtrPoutput);
		txtrPoutput.setText("Ready.");

		progressBar = new CProgressBar();
		progressBar.setBounds(10, 316, 622, 14);
		progressBar.setValue(0);
		progressBar.setForeground(new Color(200, 250, 0));
		contentPane.add(progressBar);
	}
}
