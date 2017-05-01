package Primary;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClassCard {
	
	class MyCanvas extends JComponent {

		  StoreInformition list ;
		  int x, y;
		  public  MyCanvas(StoreInformition list,int x,int y){
			  this.list = list;
			  this.x = x;
			  this.y = y;
		  }

		public void paint(Graphics g) {
			g.drawRect (x, y,500,(list.listOfAttributes.size()+list.listOfMethods.size() + 6 )*10);  
		    g.drawString(list.classNmae, 200+x,20+y);
		   // g.drawLine(x,y,510,y);
		    g.drawLine(x,y+25,x+500,y+25);
		    int cnt =3;
		    for(String attribute: list.listOfAttributes){
		    	cnt++;
		    	g.drawString(attribute,15+x,cnt*10+y);
		    }
		    g.drawLine(x, (cnt+1)*10+y,500+x, (cnt+1)*10+y);
		    cnt++;
		    cnt++;
		    for(String method:list.listOfMethods){
		    	cnt++;
		    	g.drawString(method,15+x,cnt*10+y);
		    }

		  }
		}
	
	ArrayList<JFrame> frames;
	public ClassCard(StoreInformition information,int x,int y){
		
		JFrame window = new JFrame("Class Diagram");
		window.setBounds(20, 30, 500, 500);
		window.getContentPane().add(new MyCanvas(information,x,y));
		window.setVisible(true);
	}
	
	

}
