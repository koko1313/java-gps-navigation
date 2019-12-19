package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import uni.fmi.informatics.Map;

public class MenuBar extends JMenuBar implements ActionListener{

	private JMenu fileMenu = new JMenu("File");
	private JMenu aboutMenu = new JMenu("About...");
	
	private JMenuItem newMenuItem = new JMenuItem("New");
	private JMenuItem importMenuItem = new JMenuItem("Import map");
	private JMenuItem exportMenuItem = new JMenuItem("Export map");
	private JMenuItem exitMenuItem = new JMenuItem("Exit");
	
	private JMenuItem aboutMe = new JMenuItem("About me");
	private JMenuItem aboutProject = new JMenuItem("About the project");
	
	public MenuBar() {
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    
	    newMenuItem.addActionListener(this);
	    importMenuItem.addActionListener(this);
	    exportMenuItem.addActionListener(this);
	    exitMenuItem.addActionListener(this);
	    
	    aboutMe.addActionListener(this);
	    aboutProject.addActionListener(this);
	    
	    // hotkeys
	    newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	    importMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	    exportMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	    aboutProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
	    
	    fileMenu.add(newMenuItem);
	    fileMenu.addSeparator();
	    fileMenu.add(importMenuItem);
	    fileMenu.add(exportMenuItem);
	    fileMenu.addSeparator();
	    fileMenu.add(exitMenuItem);
	     
	    aboutMenu.add(aboutMe);
	    aboutMenu.add(aboutProject);
	    
	    add(fileMenu);
	    add(aboutMenu);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		
		if(e.getSource() == newMenuItem) {
			Object[] options = {"Yes", "No"};
			int n = JOptionPane.showOptionDialog(null,
				"The current map will be removed from the program.\nAre you sure?",
				"Are you sure?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				options[0]); //default button title
			
			// ако е Yes
			if(n == 0) {
				Map.resetMap();
			}
		} else
			
		if(e.getSource() == importMenuItem) {
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				Files.readFile(chooser.getSelectedFile().getPath());
			}
		} else 
			
		if(e.getSource() == exportMenuItem) {
			if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				Files.saveFile(chooser.getSelectedFile().getPath());
			}
		} else
			
		if(e.getSource() == exitMenuItem) {
			System.exit(0);
		} else 
			
		if(e.getSource() == aboutMe) {
			JOptionPane.showMessageDialog(null,
				    "Калоян Величков\n"
				    + "Факултетен номер: 1501261021\n"
				    + "Специалност: Информатика\n"
				    + "Курс: 3\n"
				    + "ФМИ - Пловдив",
				    "About me",
				    JOptionPane.PLAIN_MESSAGE);
		} else
			
		if(e.getSource() == aboutProject) {
			JOptionPane.showMessageDialog(null,
				    "Проект по Изкуствен Интелект\n"
				    + "Трети курс, Пролетен семестър",
				    "About project",
				    JOptionPane.PLAIN_MESSAGE);
		}
	}

}
