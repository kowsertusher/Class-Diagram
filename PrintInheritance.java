package Primary;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.Entity;

public class PrintInheritance {
	
	class Tree extends JPanel{
		
		ArrayList<Node> map;
		
		public Tree(ArrayList<Node> map) {
			this.map = map ;
		}
		public void paint(Graphics g){
			
			g.drawRect(10, 10, 500, (map.size()+3)*10+ 10);
			g.drawString("Parent_Class", 100,22);
			g.drawString("Child_Class", 350,22);
			g.drawLine(10, 25, 500,25);
			g.drawLine(250+5,10,250+5,(map.size()+3)*10+10);
			
			
			int cnt =0;
			for(Node node:map){
				g.drawString(node.a,20, 40+cnt*10);
				g.drawString(node.b,255+20, 40+cnt*10);
				cnt++;
				
			}
		}
	}
	public PrintInheritance(ArrayList<Node> map){
		
		JFrame window = new JFrame("Inheritance");
		
		window.getContentPane().add(new Tree(map));
		window.setSize(500, 300);
		window.setVisible(true);
	}

}
