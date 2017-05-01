package Primary;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Project {
	public printAllInformation printAllInformition;
	public Project(String fileName,String filePath){
		ArrayList<StoreInformition> classDiagram = new Parser().getFile(fileName,filePath);
		convet(classDiagram);
		new printAllInformation(classDiagram);
	}
	
	public void  convet(ArrayList<StoreInformition> arrayList){
		
		
		for(int i =0;i<arrayList.size();i++){
			for(int j=0;j<arrayList.get(i).listOfAttributes.size();j++){
				String attribute = "";
				if(arrayList.get(i).listOfAttributes.get(j).contains("public")){
					  attribute = arrayList.get(i).listOfAttributes.get(j).replace("public", "+ ");
				}
				else if(arrayList.get(i).listOfAttributes.get(j).contains("private")){
					 attribute = arrayList.get(i).listOfAttributes.get(j).replace("private", "- ");
				}
				else if(arrayList.get(i).listOfAttributes.get(j).contains("protected")){
					 attribute = arrayList.get(i).listOfAttributes.get(j).replace("protected", "# ");
				}
				else {
					 attribute ="~ "+ arrayList.get(i).listOfAttributes.get(j);
				}
				arrayList.get(i).listOfAttributes.set(j, arrayList.get(i).listOfAttributes.get(j).replace(arrayList.get(i).listOfAttributes.get(j), attribute));
			}
			for(int j=0;j<arrayList.get(i).listOfMethods.size();j++){
				String attribute = "";
				if(arrayList.get(i).listOfMethods.get(j).contains("public")){
					  attribute = arrayList.get(i).listOfMethods.get(j).replace("public", "+ ");
				}
				else if(arrayList.get(i).listOfMethods.get(j).contains("private")){
					 attribute = arrayList.get(i).listOfMethods.get(j).replace("private", "- ");
				}
				else if(arrayList.get(i).listOfMethods.get(j).contains("protected")){
					 attribute = arrayList.get(i).listOfMethods.get(j).replace("protected", "# ");
				}
				else {
					 attribute =" ~ "+ arrayList.get(i).listOfMethods.get(j);
				}
				arrayList.get(i).listOfMethods.set(j, arrayList.get(i).listOfMethods.get(j).replace(arrayList.get(i).listOfMethods.get(j), attribute));
			}
			for(int j=0;j<arrayList.get(i).listOfMethods.size();j++){
				
				String method = "";
				String method1 = arrayList.get(i).listOfMethods.get(j);
				StringTokenizer tokenizer = new StringTokenizer(method1);
				boolean flag = false;
				
				String temp2 = method1.substring(method1.indexOf("(")+1, method1.indexOf(")"));
				for(int k=0;k<temp2.length();k++) if(temp2.charAt(k)!=' ') flag = true;
				
			 
			    if(flag){
			    	tokenizer = new StringTokenizer(method1);
					String temp= "",temp1 = "";
					while(tokenizer.hasMoreTokens()){
						temp = tokenizer.nextToken();
						if(!temp.equals(",")&&!temp.equals(")")){
							method = method + " "+temp1;
						}
						else if(temp.equals("(")) method = method + " "+temp1 + "(";
						temp1 = temp;
					}
					method = method.replaceAll("  "," ");
					arrayList.get(i).listOfMethods.set(j,method+" )");
			    }
			    
			}
		}
	}
}
