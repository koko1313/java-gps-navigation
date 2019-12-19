package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Console extends JPanel implements ActionListener{
	
	private static JTextArea consoleTextArea;
	private JScrollPane scroll;
	
	JPopupMenu consolePopupMenu = new JPopupMenu();
	JMenuItem consoleClearMenuItem = new JMenuItem("Clear");
	
	public Console() {
		setLayout(new GridLayout(0, 1));
		
		consoleClearMenuItem.addActionListener(this);
		consolePopupMenu.add(consoleClearMenuItem);
		
		consoleTextArea = new JTextArea();
		consoleTextArea.setEditable(false);
		consoleTextArea.setComponentPopupMenu(consolePopupMenu);
		
		scroll = new JScrollPane(consoleTextArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		add(scroll);
	}

	public static void print(String str) {
		consoleTextArea.setText(consoleTextArea.getText() + str);
	}
	
	public static void println(String str) {
		if(consoleTextArea.getText().equals("")) {
			consoleTextArea.setText(str + "\n");
		} else {
			consoleTextArea.setText(consoleTextArea.getText() + str + "\n");
		}
	}
	
	public static void println() {
		consoleTextArea.setText(consoleTextArea.getText() + "\n");
	}
	
	public static void clearConsole() {
		consoleTextArea.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == consoleClearMenuItem) {
			clearConsole();
		}
	}

}
