package de.craftkekser.ips.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.craftkekser.ips.gui.component.CButton;
import de.craftkekser.ips.gui.component.CProgressBar;
import de.craftkekser.ips.gui.component.JResponsiveImagePanel;
import de.craftkekser.ips.processing.ImageUtils;
import de.craftkekser.ips.processing.PanoramaProcessor;
import de.craftkekser.ips.processing.SplitAlignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7786975905444594536L;
	private JPanel contentPane;
	private JResponsiveImagePanel inputPrev;
	private JResponsiveImagePanel outputPrev;

	public static BufferedImage image;
	public static File lastLoadFile = new File(System.getProperty("user.home") + File.separator + "Pictures");
	public static File currentFile = null;
	private CProgressBar progressBar;

	public static List<BufferedImage> images = new ArrayList<BufferedImage>();
	private JLabel lblx;
	private CButton btnLoadImage;
	private CButton btnExport;
	private CButton btnStart;
	private JMenuItem mntmLoadImage;
	private JMenuItem mntmExportResult;
	private JLabel lblInput;

	/**
	 * Launch the application.
	 * (WindowBuilder)
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showConfirmDialog(contentPane, "Do you want to exit the program?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					dispose();
					System.exit(0);
				}
			}
		});
		setTitle("Instagram Panorama Splitter");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1233, 628);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.LIGHT_GRAY);
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("file");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		mntmLoadImage = new JMenuItem("load image...");
		mntmLoadImage.addMenuKeyListener(new MenuKeyListener() {
			public void menuKeyPressed(MenuKeyEvent e) {
				loadImage();
			}
			public void menuKeyReleased(MenuKeyEvent e) {
			}
			public void menuKeyTyped(MenuKeyEvent e) {
			}
		});
		mntmLoadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadImage();
			}
		});
		mntmLoadImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnFile.add(mntmLoadImage);

		mntmExportResult = new JMenuItem("export result...");
		mntmExportResult.addMenuKeyListener(new MenuKeyListener() {
			public void menuKeyPressed(MenuKeyEvent e) {
				export();
			}
			public void menuKeyReleased(MenuKeyEvent e) {
			}
			public void menuKeyTyped(MenuKeyEvent e) {
			}
		});
		mntmExportResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});
		mntmExportResult.setEnabled(false);
		mntmExportResult.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnFile.add(mntmExportResult);
		mnFile.addSeparator();
		JMenuItem mntmExit = new JMenuItem("exit");
		mntmExit.addMenuKeyListener(new MenuKeyListener() {
			public void menuKeyPressed(MenuKeyEvent e) {
				int confirm = JOptionPane.showConfirmDialog(contentPane, "Do you want to exit the program?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					dispose();
					System.exit(0);
				}
			}
			public void menuKeyReleased(MenuKeyEvent e) {
			}
			public void menuKeyTyped(MenuKeyEvent e) {
			}
		});
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(contentPane, "Do you want to exit the program?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					dispose();
					System.exit(0);
				}
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnFile.add(mntmExit);

		JMenu mnOptions = new JMenu("options");
		mnOptions.setMnemonic('O');
		menuBar.add(mnOptions);

		JMenuItem mntmSettings = new JMenuItem("settings");
		mntmSettings.setEnabled(false);
		mntmSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		mnOptions.add(mntmSettings);

		JMenu mnHelp = new JMenu("help");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);

		JMenuItem mntmGetHelp = new JMenuItem("get help");
		mntmGetHelp.setEnabled(false);
		mntmGetHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmGetHelp);

		JMenuItem mntmVisitWebsite = new JMenuItem("visit website");
		mntmVisitWebsite.setEnabled(false);
		mntmVisitWebsite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_MASK));
		mnHelp.add(mntmVisitWebsite);

		JMenuItem mntmCredits = new JMenuItem("credits");
		mntmCredits.setEnabled(false);
		mntmCredits.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mnHelp.add(mntmCredits);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][200px:500px:500px]", "[grow][30px:30px:30px][grow][30px:30px:30px]"));

		inputPrev = new JResponsiveImagePanel((BufferedImage) null);
		inputPrev.setBackground(Color.DARK_GRAY);
		inputPrev.setBorder(new BevelBorder(BevelBorder.LOWERED));
		contentPane.add(inputPrev, "cell 0 0 2 1,grow");
		inputPrev.setLayout(new BorderLayout(5, 5));

		lblInput = new JLabel("  input");
		lblInput.setAlignmentY(Component.TOP_ALIGNMENT);
		lblInput.setForeground(Color.LIGHT_GRAY);
		lblInput.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		lblInput.setVerticalAlignment(SwingConstants.TOP);
		inputPrev.add(lblInput, BorderLayout.NORTH);

		lblx = new JLabel("0x0    0 Segments");
		lblx.setHorizontalAlignment(SwingConstants.CENTER);
		lblx.setVerticalAlignment(SwingConstants.TOP);
		lblx.setForeground(Color.LIGHT_GRAY);
		lblx.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblx.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		inputPrev.add(lblx, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, "cell 0 1,grow");
		panel.setLayout(null);

		btnLoadImage = new CButton();
		btnLoadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadImage();
			}
		});
		btnLoadImage.setBounds(0, 0, 91, 30);
		btnLoadImage.setText("load image");
		panel.add(btnLoadImage);

		CButton btnLeftAl = new CButton();
		CButton btnCenterAl = new CButton();
		CButton btnRightAl = new CButton();
		btnLeftAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLeftAl.setSelected(true);
				btnCenterAl.setSelected(false);
				btnRightAl.setSelected(false);
			}
		});
		btnLeftAl.setBounds(118, 0, 69, 30);
		btnLeftAl.setBackground(new Color(0, 15, 15));
		btnLeftAl.setText("left");
		panel.add(btnLeftAl);

		btnCenterAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLeftAl.setSelected(false);
				btnCenterAl.setSelected(true);
				btnRightAl.setSelected(false);
			}
		});
		btnCenterAl.setBounds(185, 0, 69, 30);
		btnCenterAl.setBackground(new Color(0, 15, 15));
		btnCenterAl.setText("center");
		btnCenterAl.setSelected(true);
		panel.add(btnCenterAl);

		btnRightAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLeftAl.setSelected(false);
				btnCenterAl.setSelected(false);
				btnRightAl.setSelected(true);
			}
		});
		btnRightAl.setBounds(252, 0, 69, 30);
		btnRightAl.setBackground(new Color(0, 15, 15));
		btnRightAl.setText("right");
		panel.add(btnRightAl);

		btnStart = new CButton();
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread splitting = new Thread(new Runnable() {

					@Override
					public void run() {
						disableActionButtons();
						SplitAlignment align = SplitAlignment.LEFT;
						if(btnLeftAl.isSelected())
							align = SplitAlignment.LEFT;
						if(btnCenterAl.isSelected())
							align = SplitAlignment.CENTER;
						if(btnRightAl.isSelected())
							align = SplitAlignment.RIGHT;

						progressBar.setValue(10);
						images = PanoramaProcessor.split(image, align);
						progressBar.setValue(60);
						outputPrev.setImage(ImageUtils.combinePreview(images, 0.3, outputPrev));
						progressBar.setValue(100);
						try { Thread.sleep(200); } catch (InterruptedException e) { }
						progressBar.setValue(0);
						enableActionButtons();
					}
				});
				splitting.start();
			}
		});
		btnStart.setBounds(352, 0, 91, 30);
		btnStart.setText("split");
		btnStart.setBackground(new Color(10, 20, 0));
		btnStart.setEnabled(false);
		panel.add(btnStart);

		progressBar = new CProgressBar();
		contentPane.add(progressBar, "cell 1 1,grow");
		progressBar.setValue(0);

		outputPrev = new JResponsiveImagePanel((BufferedImage) null);
		FlowLayout flowLayout_1 = (FlowLayout) outputPrev.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		outputPrev.setBackground(Color.DARK_GRAY);
		outputPrev.setBorder(new BevelBorder(BevelBorder.LOWERED));
		contentPane.add(outputPrev, "cell 0 2 2 1,grow");

		JLabel lblOutput = new JLabel("output");
		lblOutput.setVerticalAlignment(SwingConstants.TOP);
		lblOutput.setForeground(Color.LIGHT_GRAY);
		lblOutput.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		outputPrev.add(lblOutput);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, "cell 0 3,grow");
		panel_1.setLayout(null);

		btnExport = new CButton();
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});
		btnExport.setBounds(0, 0, 105, 30);
		btnExport.setText("export as png");
		btnExport.setEnabled(false);
		panel_1.add(btnExport);
	}

	public void disableActionButtons() {
		btnLoadImage.setEnabled(false);
		btnExport.setEnabled(false);
		btnStart.setEnabled(false);
		mntmLoadImage.setEnabled(false);
		mntmExportResult.setEnabled(false);
	}
	public void enableActionButtons() {
		btnLoadImage.setEnabled(true);
		if(images.size()>0)
			btnExport.setEnabled(true);
		if(images.size()>0)
			mntmExportResult.setEnabled(true);
		if(image != null)
			btnStart.setEnabled(true);
		mntmLoadImage.setEnabled(true);
	}

	public void loadImage() {
		JFileChooser chooser = new JFileChooser(lastLoadFile);
		chooser.setFileFilter(new FileNameExtensionFilter("Pictures", "png", "jpg", "jpeg", "tif", "tiff"));
		chooser.setMultiSelectionEnabled(false);
		int result = chooser.showOpenDialog(contentPane);
		if(result == JFileChooser.APPROVE_OPTION) {
			File f;
			if((f=chooser.getSelectedFile())!=null) {
				System.out.println("Loading file " + f.getAbsolutePath());
				Thread loader = new Thread(new Runnable() {

					@Override
					public void run() {
						disableActionButtons();
						lastLoadFile = f.getParentFile();
						currentFile = f;
						try {
							progressBar.setValue(10);
							boolean wasNull = image==null;
							BufferedImage beforeImg = image;
							image = ImageIO.read(f);
							progressBar.setValue(40);
							image = ImageUtils.toCompatibleImage(image);
							progressBar.setValue(68);
							long segments = PanoramaProcessor.getMaxSegments(image);
							if(segments < 2) {
								JOptionPane.showMessageDialog(contentPane, "At least 2 segments are required, only " + segments + " " + (segments==1?"is":"are") + " possible", "Error", JOptionPane.ERROR_MESSAGE);
								progressBar.setValue(0);
								if(wasNull) {
									image = null;
								}else {
									image = beforeImg;
								}
								enableActionButtons();
								return;
							}
							progressBar.setValue(70);
							inputPrev.setImage(ImageUtils.scale(image, 0.3));
							progressBar.setValue(80);
							lblx.setText(image.getWidth() + "x" + image.getHeight() + "    " + segments + " Segments");
							lblInput.setText("  input [" + f.getName() + "]");
							images.clear();
							outputPrev.setImage(null);
							progressBar.setValue(100);
							Thread.sleep(200);
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(contentPane, "Read Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
						progressBar.setValue(0);
						enableActionButtons();
					}
				});
				loader.start();
			}else {
				JOptionPane.showMessageDialog(contentPane, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void export() {
		JFileChooser chooser = new JFileChooser(currentFile.getParentFile());
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		int result = chooser.showSaveDialog(contentPane);
		if(result == JFileChooser.APPROVE_OPTION) {
			File f;
			if((f=chooser.getSelectedFile())!=null) {
				if(f.isDirectory()) {
					Thread export = new Thread(new Runnable() {

						@Override
						public void run() {
							disableActionButtons();
							progressBar.setValue(10);
							if(!f.exists()) {
								f.mkdirs();
							}
							progressBar.setValue(20);
							int cnt = 1;
							for(BufferedImage bi : images) {
								File imageFile = new File(f.getAbsolutePath() + File.separator + currentFile.getName().split("\\.")[0] + "_seg" + cnt + ".png");
								try {
									ImageIO.write(bi, "png", imageFile);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								progressBar.setValue(progressBar.getValue() + (80/images.size()));
								cnt++;
							}
							try { Thread.sleep(200); } catch (InterruptedException e) { }
							progressBar.setValue(0);
							enableActionButtons();
						}
					});
					export.start();
				}else {
					JOptionPane.showMessageDialog(contentPane, "File must be a directory", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(contentPane, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
