package Primary;

import java.util.ArrayList;

public class StoreInformition {
	public String classNmae;
	public String parent = null;
	public ArrayList<String> listOfAttributes;
	public ArrayList<String> listOfMethods;
	public ArrayList<String> listOfRelation;
	public StoreInformition(String className,String parent,ArrayList<String> arrayList,ArrayList<String> arrayList2,ArrayList<String> arrayList3){
		this.classNmae = className;
		this.parent = parent;
		this.listOfAttributes = arrayList;
		this.listOfMethods = arrayList2;
		this.listOfRelation = arrayList3;
	}
}
