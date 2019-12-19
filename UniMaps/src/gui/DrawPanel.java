package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import uni.fmi.informatics.Link;
import uni.fmi.informatics.Map;
import uni.fmi.informatics.Node;

public class DrawPanel extends JPanel implements ActionListener{
	
	private Graphics2D g;
	private final int CITY_SIZE = 20;
	private int totalWidth;
	private int totalHeight;
	
	private JPopupMenu drawPanelPopupMenu = new JPopupMenu();
	private JMenuItem drawPanelRefreshMenuItem = new JMenuItem("Refresh");
	
	public DrawPanel () {
		drawPanelRefreshMenuItem.addActionListener(this);
		drawPanelPopupMenu.add(drawPanelRefreshMenuItem);
		setComponentPopupMenu(drawPanelPopupMenu);
		
		repaint();
	}
	
	public void paint(Graphics gg) {
		g = (Graphics2D) gg;
		
		totalWidth = super.getWidth()-20;
		totalHeight = super.getHeight()-20;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, totalWidth+20, totalHeight+20);
		g.setColor(Color.BLACK);
		
		float maxX = 0;
		float maxY = 0;
		
		
		for(Node n : Map.g.getMap().values()) {
			if(n.getX() > maxX) maxX = n.getX();
			if(n.getY() > maxY) maxY = n.getY();
		}
		
		if(maxX == 0) maxX = 1;
		if(maxY == 0) maxY = 1;
		
		float coefX = 100/maxX;
		float coefY = 100/maxY;
		
		for(Node n : Map.g.getMap().values()) {
			
			float x = n.getX()*coefX *totalWidth/100 - CITY_SIZE;
			float y = totalHeight-( n.getY()*coefY *totalHeight)/100 - CITY_SIZE;
			
			if(x < 0.1f) x = 10;
			if(y < 0.1f) y = 10;

			Ellipse2D.Double city = new Ellipse2D.Double(x, y, CITY_SIZE, CITY_SIZE);
			
			for(Link l : n.getLinks()) {
				float xTo = l.getToNode().getX()*coefX *totalWidth/100 - CITY_SIZE;
				float yTo = totalHeight-( l.getToNode().getY()*coefY *totalHeight)/100 - CITY_SIZE;
				
				if(xTo < CITY_SIZE) xTo = 10;
				if(yTo < CITY_SIZE) yTo = 10;
				
				Line2D.Double path = new Line2D.Double(x + CITY_SIZE/2, y + CITY_SIZE/2, xTo + CITY_SIZE*70/100, yTo + CITY_SIZE*70/100);
				
				Random rand = new Random();
				float r = rand.nextFloat();
				float gr = rand.nextFloat();
				float b = rand.nextFloat();
				
				g.setColor(new Color(r, gr, b));
				g.drawString(l.getLenght()+"", (x+xTo)/2 + xTo*10/100, (y+yTo)/2 + yTo*10/100);
				g.draw(path);
				g.setColor(Color.BLACK);
			}
			
			g.drawString(n.getName(), x, y);
			g.drawString(n.getWeight()+"", x+CITY_SIZE, y+CITY_SIZE);
			g.draw(city);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == drawPanelRefreshMenuItem) {
			repaint();
		}
	}
	
}
