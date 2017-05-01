package Primary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class MyCanvas extends JLabel {

  StoreInformition list ;
  int x, y;
  public  MyCanvas(StoreInformition list,int x,int y){
	  this.list = list;
	  this.x = x;
	  this.y = y;
	  //setBounds(x+5,y,510, (list.listOfAttributes.size()+list.listOfMethods.size() + 6 )*10);
	  //setBorder(BorderFactory.createMatteBorder(x,y,500+20, (list.listOfAttributes.size()+list.listOfMethods.size() + 6 )*10+10,Color.blue));
  }

  public void paint(Graphics g) {
	g.drawRect (x, y+5,500,(list.listOfAttributes.size()+list.listOfMethods.size() + 6 )*10);  
    g.drawString(list.classNmae, 200+x,20+y);
   // g.drawLine(x,y,510,y);
    g.drawLine(x,y+25+5,x+500,y+25+5);
    int cnt =3;
    for(String attribute: list.listOfAttributes){
    	cnt++;
    	g.drawString(attribute,15+x,cnt*10+y+5);
    }
    g.drawLine(x, (cnt+1)*10+y+5,500+x, (cnt+1)*10+y+5);
    cnt++;
    cnt++;
    for(String method:list.listOfMethods){
    	cnt++;
    	g.drawString(method,15+x,cnt*10+y+5);
    }

  }
}

public class ClassDiagram {
	
  ArrayList<StoreInformition> list = new ArrayList<>();
  
  public ClassDiagram(ArrayList<StoreInformition> list){
	  this.list = list;
	  
	  Demo();
  }
  
  public void Demo(){
	  
    JFrame window = new JFrame("Class Diagram");
   // window.setLayout(new GridBagLayout());
    
   // window.getContentPane().setLayout(new GridBagLayout());
    JPanel card = new JPanel();
    JScrollPane  pane = new JScrollPane(card);
    //card.setSize(1600, 1600);
  //  GridBagConstraints c = new GridBagConstraints();
   // card.setLayout(new GridBagLayout());
   // c.fill = GridBagConstraints.HORIZONTAL;
    
    //window.getContentPane().
    //window.getContentPane().setLayout(new FlowLayout());
   // window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   // window.setBounds(20, 30, 300, 300);
   /// window.getContentPane().add(new MyCanvas());
  //  window.setVisible(true);
	  // frame = new JFrame();
	   //JFrame window = new JFrame();
	   //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  // window.setBounds(20, 30, 300, 300);
	   //window.getContentPane().add(new MyCanvas(informition));
	   //window.add(new MyCanvas(informition));
      MyCanvas dCanvas = null ;
      int x=0,y=0,cnt=1;
      for(int i=0;i<list.size();i++){
    	  
    	 // c.fill = GridBagConstraints.HORIZONTAL;
    	  if(i%2==0){
    		  x = 10;
    		 // c.gridx = 1;
    		 // c.gridwidth = 550;
    		  y = 0;
    		  for(int j=0;j<cnt-1;j+=2){
    			  y += (list.get(j).listOfAttributes.size()+list.get(j).listOfMethods.size() + 5 )*10;
    		  }
    		  if(i!=0)
    		  y += 40;
    		  //c.gridheight =(list.get(i).listOfAttributes.size()+list.get(i).listOfMethods.size()+ 5);
    		  //c.gridy = cnt-1/2;
    	  }
    	  if(i%2==1){
    		  x = 500+20;
    		 // c.gridx = 2;
    		 // c.gridwidth = 550;
    		  y= 0;
    		  for(int j=1;j<cnt-1;j+=2){
    			  y += (list.get(j).listOfAttributes.size()+list.get(j).listOfMethods.size() + 5 )*10 ;
    		  }
    		  if(i!=1)
    		  y += 40;
    		 // c.gridheight =(list.get(i).listOfAttributes.size()+list.get(i).listOfMethods.size()+ 5);
    		 // c.gridy = cnt-1/2;
    	  }
    	  /*if(cnt%3==0){
    		  x = 2*500+20;
    		  y= 0;
    		  for(int j=2;j<cnt-1;j+=3){
    			  y += (list.get(j).listOfAttributes.size()+list.get(j).listOfMethods.size() + 5 )*10 +20;
    		  }
    	  }
    	  */
    	  //x = 10;
    	 // y = 0;
    	
    	if(i==list.size()-1) dCanvas = new MyCanvas(list.get(i),x+10, y);
    	else dCanvas = new MyCanvas(list.get(i),10, 0);
    	// dCanvas.setBorder(BorderFactory.createMatteBorder(x,y,500+20, (list.get(i).listOfAttributes.size()+list.get(i).listOfMethods.size() + 6 )*10+10,Color.blue));
    	dCanvas.setBounds(x,y,500+20, (list.get(i).listOfAttributes.size()+list.get(i).listOfMethods.size() + 6 )*10+10);
    	 //dCanvas.setSize(500+20, (list.get(i).listOfAttributes.size()+list.get(i).listOfMethods.size() + 6 )*10+10);
    	 //dCanvas.setLocation(x, y);
    	 
    	 //if(i!=list.size()-1)
    	 window.add(dCanvas);
    	
    	 cnt++;
    	  //card.add(new MyCanvas(list.get(i), x, y));
    	  
      }
     // window.remove();
      //window.remove(dCanvas);
    //  dCanvas = new MyCanvas(list.get(list.size()-1),10, 0);
     // dCanvas.setBounds(x,y,500+20, (list.get(list.size()-1).listOfAttributes.size()+list.get(list.size()-1).listOfMethods.size() + 6 )*10+10);
     // window.add(dCanvas);
      
      //window.add(card);
      window.pack();
      window.setSize(1200,1200);
      window.setVisible(true);

	   
  }
}