package Primary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;

public class printAllInformation {
	
	
	ClassCard card;
	PrintInheritance inheritance;
	PrintDependencyTree dependencyTree;
	ArrayList<Node> inhariteTree ;
	ArrayList<StoreInformition> dependency;
	public printAllInformation(ArrayList<StoreInformition> arrayList) {
		// TODO Auto-generated constructor stub
		bulidInhariteTree(arrayList);
		buildDependencyTree(arrayList);
		print(arrayList);
		new Node("a", "B");
	}
	private void print(ArrayList<StoreInformition> arrayList){
		
		ArrayList<JFrame> card = new ArrayList<>();
		for(int i=0;i<arrayList.size();i++){
			String className = arrayList.get(i).classNmae;
			ArrayList<String> attributs = arrayList.get(i).listOfAttributes;
			ArrayList<String> methods = arrayList.get(i).listOfMethods;
			
			System.out.println("Class Name : "+className);
			System.out.println("List Of Attributes : ");
			for(int j=0;j<attributs.size();j++) System.out.println(attributs.get(j));
			System.out.println("List Of Methods : ");
			for(String string : methods) System.out.println(string);
			new ClassCard(arrayList.get(i), 10, 0);
			//System.out.println(arrayList.get(i).parent);
			//card.add(new ClassDiagram().Demo(arrayList.get(i)));
		}
		 new ClassDiagram(arrayList);
		 new PrintInheritance(inhariteTree);
		 new PrintDependencyTree(dependency);
	}
	private void bulidInhariteTree(ArrayList<StoreInformition> arrayList){
		inhariteTree = new ArrayList<>();
		for(StoreInformition informition : arrayList){
			if(informition.parent!=null){
				inhariteTree.add(new Node(informition.parent,informition.classNmae));
			}
		}
	}
	private void buildDependencyTree(ArrayList<StoreInformition> arrayList){
		dependency = new ArrayList<>();
		for(StoreInformition informition: arrayList){
			if(informition.listOfRelation.size()>0){
				dependency.add(informition);
			}
		}
	}
}
