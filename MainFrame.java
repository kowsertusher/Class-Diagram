package Primary;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;




public class MainFrame {

	private JFrame frame;
	private JFileChooser chooser;
	private File currentFile = null;
	private String currentFileName = "New tab";
	private String location = "";
	
	Scanner in;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		initialize();	
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 784, 62);
		frame.getContentPane().add(toolBar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 62, 784, 499);
		frame.getContentPane().add(scrollPane);
		JEditorPane editorPane = new JEditorPane();
		JButton btnOpen = new JButton("Open");
		
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("java/XML/text file", "java", "XML", "txt");
			
				chooser.setFileFilter(filter);
				int status = chooser.showOpenDialog(btnOpen);
				if (status == JFileChooser.APPROVE_OPTION) {
					currentFile = chooser.getSelectedFile();
					location = chooser.getSelectedFile().getAbsolutePath();
					currentFileName = chooser.getSelectedFile().getName();	
				}				
				
				
			}
		});
		//btnOpen.setIcon(new ImageIcon("/Folder-Closed-icon.png"));
		toolBar.add(btnOpen);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Project(currentFileName, location);
			}
		});
		toolBar.add(btnRun);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		ImageIcon exit = null;
		btnExit.setIcon(exit);
		toolBar.add(btnExit);
		
		
	}
	
}
