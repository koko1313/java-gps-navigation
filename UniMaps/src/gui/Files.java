package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import uni.fmi.informatics.Link;
import uni.fmi.informatics.LinkType;
import uni.fmi.informatics.Map;
import uni.fmi.informatics.Node;

public class Files {

	public static void readFile(String fileName) {
		String curline = "";
		BufferedReader br;
		Map.g.getMap().clear();
		
		try {
			Console.println("Importing "+ fileName + " ...");
			br = new BufferedReader(new FileReader(fileName));
			
			while ((curline = br.readLine()) != null) {
				curline = curline.split(";")[0];
				String[] row = curline.split(",");
				
				// възел
				if(row.length == 4) {
					String name = row[0];
					int xCoord = Integer.parseInt(row[1]);
					int yCoord = Integer.parseInt(row[2]);
					double weight = Double.parseDouble(row[3]);
					
					Map.addNode(name, weight, xCoord, yCoord);
				} else
				// връзка
				if(row.length == 5) {
					String fromName = row[0];
					String toName = row[1];
					LinkType type = LinkType.valueOf(row[3].toUpperCase());
					double length = Double.parseDouble(row[2]);
					boolean isTwoWay = true;
					
					if(row[4].equals("T")) {
						isTwoWay = true;
					} else 
					if(row[4].equals("F")){
						isTwoWay = false;
					}
					
					Map.addLink(fromName, toName, type, length, isTwoWay);
				}
			}
			
			br.close();
			Console.println("File "+ fileName +" was successfuly imported.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveFile(String fileName) {
		PrintWriter writer;
		String row;
		String links = "";
		
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			
			for(Node n : Map.g.getMap().values()) {
				row = n.getName() + "," + n.getX() + "," + n.getY() +","+ n.getWeight() + ";";
				writer.println(row);
				
				for(Link l : n.getLinks()) {
					String from = n.getName();
					String to = l.getToNode().getName();
					double length = l.getLenght();
					LinkType type = l.getType();
					boolean isTwoWay = false;
					
					// проверяваме за друпосочен път
					for(Link toNodeLink : Map.g.getMap().get(to).getLinks()) {
						if(toNodeLink.getToNode() == n) {
							if(toNodeLink.getLenght() == l.getLenght()) {
								isTwoWay = true;
							}
						}
					}
					
					if(isTwoWay) {
						if(!links.contains(from + "," + to) && !links.contains(to + "," + from)) {
							row = from + "," + to + "," + length + "," + type + "," + "T;";
						} else {
							continue;
						}
					} else {
						row = from + "," + to + "," + length + "," + type + "," + "F;";
					}
					
					links += "\n" + row;
				}
			}
			
			writer.print(links);
			
			Console.println("Exported to "+ fileName);
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
}
