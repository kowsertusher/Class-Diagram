package Primary;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.namespace.QName;

public class PrintDependencyTree {
	
	class Tree1 extends JPanel{
		
		StoreInformition arrayList;
		int x , y;
		public Tree1( StoreInformition arrayList,int x,int y){
			this.arrayList = arrayList;	
			this.x = x;
			this.y = y;
		}
		public void paint(Graphics g){
			g.drawRect(x, y,300,arrayList.listOfRelation.size()*10+25 );
			g.drawString(arrayList.classNmae, x+100, y+15);
			g.drawLine(x, y+20, 300+x, y+20);
			
			int cnt=1;
			for(String relation: arrayList.listOfRelation ){
				g.drawString(relation, x+10,y+(cnt*10)+22 );
				cnt++;
			}
		}
	}
	
	
	public PrintDependencyTree(ArrayList<StoreInformition > arrayList){
		
		
		
		JFrame window = new JFrame("DependancyRelation");
		
		int x =0, y=0 , cnt = 1;
		for(int i=0;i<arrayList.size();i++){
			x = y = 0;
			if(cnt%2==1){
				x = 10;
				for(int j=0;j<cnt-1;j+=2){
					y += arrayList.get(j).listOfRelation.size()*10+30;
				}
				if(i!=0)
				y += 20;
			}
			if(cnt%2==0){
				x = 10 + 350;
				for(int j=1;j<cnt-1;j+=2){
					y += arrayList.get(j).listOfRelation.size()*10+30;
				}
				if(i!=1)
				y += 20;
			}
			 Tree1 dCanvas = new Tree1(arrayList.get(i), x, y);
	    	 // dCanvas.setBorder(BorderFactory.createMatteBorder(x,y,500+20, (list.get(i).listOfAttributes.size()+list.get(i).listOfMethods.size() + 6 )*10+10,Color.blue));
	    	 // dCanvas.setBounds(x,y,500+20, (list.get(i).listOfAttributes.size()+list.get(i).listOfMethods.size() + 6 )*10+10);
	    	 dCanvas.setSize(300+20, (arrayList.get(i).listOfRelation.size() +3)*10+20);
	    	 window.add(dCanvas);
			 cnt++;
			
		}
		window.setSize(600, 400);
		window.setVisible(true);
		
		
		/*for(StoreInformition informition : arrayList){
			
			JFrame card = new JFrame("Class Card");
			JPanel cardPanel = new JPanel();

			int cnt = 1;
			JLabel lJLabel = new JLabel(informition.classNmae + " Has Dependency Relation With: \n");
			//lJLabel.setBounds(10,cnt++*20,50,1000);
			cardPanel.add(lJLabel);
			for(String rString: informition.listOfRelation){
				JLabel label = new JLabel(rString); 
				//label.setBounds(10,cnt++*20,20,300);
				cardPanel.add(label);
			}
			card.add(cardPanel);
			card.setSize(1000, 400);
			card.setVisible(true);
		}
		*/
		
		
	}
	
}
