package Primary;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
import java.util.Vector;
import java.util.BitSet;
import java.util.Arrays;

public class Parser {

	private  static int lineCount = 0;
	private  static String className;
	private  HashSet<String> keyword  = new HashSet<String>();
	private  ArrayList<String> listOfAttributes = new ArrayList<String>() ;
	private  ArrayList<String> listOfMethods = new ArrayList<String>() ;
	private  ArrayList<String> javaFile = new ArrayList<String>() ;
	private  HashMap<String, String> fileLink = new HashMap<String,String>() ;
	private  ArrayList<String> objectArray = new ArrayList<>();
	ArrayList<StoreInformition> classDiagram = new ArrayList<>();
	private ArrayList<String> relation= new ArrayList<>();

	public Parser(){
		
	}
	
	
	public  void initializeKeyword() {
		keyword = new HashSet<String>();
		BufferedReader br = null;
		FileReader file = null;

		try {
			file = new FileReader("keywordlist.txt");
			br = new BufferedReader(file);
			String line = br.readLine();
			while (line != null) {
				keyword.add(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public void findAbstactMethods() {
		Set<String> identifiare = new TreeSet<>();
		identifiare.add("int");
		identifiare.add("float");
		identifiare.add("double");
		identifiare.add("long");
		identifiare.add("Integer");
		identifiare.add("Double");
		identifiare.add("Float");
		identifiare.add("boolean");
		identifiare.add("Boolean");
		identifiare.add("void");
		identifiare.add("String");
		identifiare.add("Void");
		identifiare.add("BitSet");
		identifiare.add("BigInteger");
		identifiare.add("BigDecimal");
		identifiare.add("Character");
		identifiare.add("char");
		identifiare.add("CharSequence");
		for(String string : objectArray){
			identifiare.add(string);
		}

		Set<String> collection = new TreeSet<>();
		collection.add("ArrayList");
		collection.add("ArrayDeque");
		collection.add("Vector");
		collection.add("Set");
		collection.add("TreeSet");
		collection.add("LinkedList");
		collection.add("LinkedHashSet");
		collection.add("HashSet");
		collection.add("List");
		collection.add("Queue");
		collection.add("PriorityQueue");
		collection.add("HashMap");
		collection.add("LinkedHashMap");
		collection.add("Hashtable");
		collection.add("TreeMap");
		collection.add("Map");

		try {
			FileReader file = new FileReader("output.txt");
			BufferedReader reader = new BufferedReader(file);
			Set<String> pb = new TreeSet<>();

			pb.add("public");
			pb.add("private");
			pb.add("protected");
			pb.add("static");
			pb.add("abstract");
			String str = "";
			String line = reader.readLine();
			while (line != null) {
				str += line + " ";
				line = reader.readLine();
			}			
			StringTokenizer tokenizer = new StringTokenizer(str);
			while (tokenizer.hasMoreElements()) {
				String method = "";

				line = (String) tokenizer.nextElement();
				if (pb.contains(line)) {
					method += line + " ";
					line = (String) tokenizer.nextElement();
					if (line.contains(className)) {						
						method += line + " ";
						line = (String) tokenizer.nextElement();
						if (line.equals("(") && tokenizer.hasMoreElements()) {
							Stack<Integer> stack = new Stack<>();

							if (line.equals("(")) {
								method += line + " ";
								while (tokenizer.hasMoreTokens()) {
									line = tokenizer.nextToken();
									method += line + " ";
									if (line.equals(")"))
										break;
								}
							}
							line = (String) tokenizer.nextElement();
							if(line.equals(";")) listOfMethods.add(method);

						}						
					}					
					if (pb.contains(line) && tokenizer.hasMoreElements()) {
						method += line + " ";
						line = (String) tokenizer.nextElement();						
						boolean flag = false;
						boolean flag1 = false;
						if (collection.contains(line)) {
							flag = true;
							method += " " + line;
							String temp = "";
							line = tokenizer.nextToken();
							if(line.equals("<"))
							{
								temp = " "+line;
								flag1 = true;
								while (tokenizer.hasMoreElements()) {
									line = (String) tokenizer.nextElement();
									if (line.equals(">"))
										break;
									else
										temp += line;
								}
							}
							method += " " + temp;
						}
						if ((identifiare.contains(line) || flag) && tokenizer.hasMoreElements()) {
							method += line;													
							line = (String) tokenizer.nextElement();							
							String temp = line;
							if (tokenizer.hasMoreTokens())
								line = (String) tokenizer.nextElement();
							else
								break;
	
							if (line.equals("(") && tokenizer.hasMoreElements()) {
								Stack<Integer> stack = new Stack<>();
								method += " " + temp;
								if (line.equals("(")) {
									method += " " + line;
									while (tokenizer.hasMoreTokens()) {
										line = tokenizer.nextToken();
										method += " " + line;
										if (line.equals(")"))
											break;
									}
								}
								line = (String) tokenizer.nextElement();
								if(line.equals(";")) listOfMethods.add(method);

							}

						}
					} else {
						boolean flag = false;
						boolean flag1 = false;
						if (collection.contains(line)) {
							flag = true;
							method += " " + line;
							String temp = "";
							line = tokenizer.nextToken();
							if(line.equals("<"))
							{
								temp = " "+line;
								flag1= true;
								while (tokenizer.hasMoreElements()) {
									line = (String) tokenizer.nextElement();
									if (line.equals(">"))
										break;
									else
										temp += line;
								}
							}
							method += " " + temp;
						}
						if ((identifiare.contains(line) || flag) && tokenizer.hasMoreElements()) {
							method += line;							
							line = (String) tokenizer.nextElement();
							
							String temp = line;
							if (tokenizer.hasMoreElements())
								line = (String) tokenizer.nextElement();
							else
								break;							
							if (line.equals("(") && tokenizer.hasMoreElements()) {
								Stack<Integer> stack = new Stack<>();
								method += " " + temp;
								if (line.equals("(")) {
									method += line + " ";
									while (tokenizer.hasMoreTokens()) {
										line = tokenizer.nextToken();
										method += line + " ";
										if (line.equals(")"))
											break;
									}

								}
								line = (String) tokenizer.nextElement();
								if(line.equals(";")) listOfMethods.add(method);

							}

						}
					}

				} else {
					boolean flag = false;
					boolean flag1 = false;
					if (collection.contains(line)) {
						flag = true;
						method += line;
						String temp = "";
						line = tokenizer.nextToken();
						if(line.equals("<"))
						{
							temp = " "+line;
							flag1= true;
							while (tokenizer.hasMoreElements()) {
								line = (String) tokenizer.nextElement();
								if (line.equals(">"))
									break;
								else
									temp += line;
							}
						}
						method += " " + temp;
					}
					if ((identifiare.contains(line) || flag) && tokenizer.hasMoreElements()) {
						method += line;
						
						line = (String) tokenizer.nextElement();
						
						String temp = line;
						if (tokenizer.hasMoreElements())
							line = (String) tokenizer.nextElement();
						else
							break;
						if (line.equals("(") && tokenizer.hasMoreElements()) {
							Stack<Integer> stack = new Stack<>();
							method += " " + temp;
							if (line.equals("(")) {
								method += " " + line;
								while (tokenizer.hasMoreTokens()) {
									line = tokenizer.nextToken();
									method += " " + line;
									if (line.equals(")"))
										break;
								}
							}
							line = (String) tokenizer.nextElement();
							if(line.equals(";")) listOfMethods.add(method);
						}
					}
				}
			}
			
			reader.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public  void keyWordOfProgram() {
		try {
			FileReader fReader = new FileReader("output.txt");
			BufferedReader reader = new BufferedReader(fReader);
			File file = new File("out.txt");
			PrintWriter writer = new PrintWriter(file);
			String line;
			line = reader.readLine();
			// int cnt = 0;
			TreeSet<String> key = new TreeSet<>();
			while (line != null) {				
				String newString = "";
				boolean flag = false;
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) >= 21 && line.charAt(i) <= 122)
						flag = true;
					if (flag)
						newString += line.charAt(i);
				}
				line = newString;				
				StringTokenizer tokenizer = new StringTokenizer(line);
				while (tokenizer.hasMoreElements()) {
					String newLine = (String) tokenizer.nextElement();
					if (keyword.contains(newLine)) {
						key.add(newLine);
					}					
				}
				line = reader.readLine();
			}
			writer.println("The Total Key Word Of This Program Is : " + key.size());
			Iterator<String> iterator = key.iterator();
			while (iterator.hasNext()) {
				writer.println(iterator.next());
			}
			writer.close();
			fReader.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void findMethods() {
		Set<String> identifiare = new TreeSet<>();
		identifiare.add("int");
		identifiare.add("float");
		identifiare.add("double");
		identifiare.add("long");
		identifiare.add("Integer");
		identifiare.add("Double");
		identifiare.add("Float");
		identifiare.add("boolean");
		identifiare.add("Boolean");
		identifiare.add("void");
		identifiare.add("String");
		identifiare.add("Void");
		identifiare.add("BitSet");
		identifiare.add("BigInteger");
		identifiare.add("BigDecimal");
		identifiare.add("Character");
		identifiare.add("char");
		identifiare.add("CharSequence");
		for(String string : objectArray){
			identifiare.add(string);
		}

		Set<String> collection = new TreeSet<>();
		collection.add("ArrayList");
		collection.add("ArrayDeque");
		collection.add("Vector");
		collection.add("Set");
		collection.add("TreeSet");
		collection.add("LinkedList");
		collection.add("LinkedHashSet");
		collection.add("HashSet");
		collection.add("List");
		collection.add("Queue");
		collection.add("PriorityQueue");
		collection.add("HashMap");
		collection.add("LinkedHashMap");
		collection.add("Hashtable");
		collection.add("TreeMap");
		collection.add("Map");

		try {
			FileReader file = new FileReader("output.txt");
			BufferedReader reader = new BufferedReader(file);
			Set<String> pb = new TreeSet<>();

			pb.add("public");
			pb.add("private");
			pb.add("protected");
			pb.add("static");

			String str = "";
			String line = reader.readLine();
			while (line != null) {
				str += line + " ";
				line = reader.readLine();
			}			
			StringTokenizer tokenizer = new StringTokenizer(str);
			while (tokenizer.hasMoreElements()) {
				String method = "";

				line = (String) tokenizer.nextElement();
				if (pb.contains(line)) {
					method += line + " ";
					line = (String) tokenizer.nextElement();
					if (line.contains(className)) {						
						method += line + " ";
						line = (String) tokenizer.nextElement();
						if (line.equals("(") && tokenizer.hasMoreElements()) {
							Stack<Integer> stack = new Stack<>();

							if (line.equals("(")) {
								method += line + " ";
								while (tokenizer.hasMoreTokens()) {
									line = tokenizer.nextToken();
									method += line + " ";
									if (line.equals(")"))
										break;
								}
							}
							listOfMethods.add(method);

							line = (String) tokenizer.nextElement();

							if (line.equals("{") && tokenizer.hasMoreTokens()) {
								stack.push(1);
								while (!stack.isEmpty() && tokenizer.hasMoreElements()) {
									line = (String) tokenizer.nextElement();
									if (line.equals("{"))
										stack.push(1);
									else if (line.equals("}"))
										stack.pop();									
								}

							}

						}						
					}					
					if (pb.contains(line) && tokenizer.hasMoreElements()) {
						method += line + " ";
						line = (String) tokenizer.nextElement();						
						boolean flag = false;
						boolean flag1 = false;
						if (collection.contains(line)) {
							flag = true;
							method += " " + line;
							String temp = "";
							line = tokenizer.nextToken();
							if(line.equals("<"))
							{
								temp = " "+line;
								flag1 = true;
								while (tokenizer.hasMoreElements()) {
									line = (String) tokenizer.nextElement();
									if (line.equals(">"))
										break;
									else
										temp += line;
								}
							}
							method += " " + temp;
						}
						if ((identifiare.contains(line) || flag) && tokenizer.hasMoreElements()) {
							method += line;													
							line = (String) tokenizer.nextElement();							
							String temp = line;
							if (tokenizer.hasMoreTokens())
								line = (String) tokenizer.nextElement();
							else
								break;
							// if(line==null) break;
							if (line.equals("(") && tokenizer.hasMoreElements()) {
								Stack<Integer> stack = new Stack<>();
								method += " " + temp;
								if (line.equals("(")) {
									method += " " + line;
									while (tokenizer.hasMoreTokens()) {
										line = tokenizer.nextToken();
										method += " " + line;
										if (line.equals(")"))
											break;
									}
								}
								listOfMethods.add(method);
								line = (String) tokenizer.nextElement();
								if (line.equals("{") && tokenizer.hasMoreTokens()) {
									stack.push(1);
									while (!stack.isEmpty() && tokenizer.hasMoreElements()) {
										line = (String) tokenizer.nextElement();
										if (line.equals("{"))
											stack.push(1);
										else if (line.equals("}"))
											stack.pop();										
									}

								}

							}

						}
					} else {
						boolean flag = false;
						boolean flag1 = false;
						if (collection.contains(line)) {
							flag = true;
							method += " " + line;
							String temp = "";
							line = tokenizer.nextToken();
							if(line.equals("<"))
							{
								temp = " "+line;
								flag1= true;
								while (tokenizer.hasMoreElements()) {
									line = (String) tokenizer.nextElement();
									if (line.equals(">"))
										break;
									else
										temp += line;
								}
							}
							method += " " + temp;
						}
						if ((identifiare.contains(line) || flag) && tokenizer.hasMoreElements()) {
							method += line;							
							line = (String) tokenizer.nextElement();
							
							String temp = line;
							if (tokenizer.hasMoreElements())
								line = (String) tokenizer.nextElement();
							else
								break;							
							if (line.equals("(") && tokenizer.hasMoreElements()) {
								Stack<Integer> stack = new Stack<>();
								method += " " + temp;
								if (line.equals("(")) {
									method += line + " ";
									while (tokenizer.hasMoreTokens()) {
										line = tokenizer.nextToken();
										method += line + " ";
										if (line.equals(")"))
											break;
									}

								}
								listOfMethods.add(method);
								line = (String) tokenizer.nextElement();
								if (line.equals("{") && tokenizer.hasMoreElements()) {
									stack.push(1);
									while (!stack.isEmpty() && tokenizer.hasMoreTokens()) {
										line = (String) tokenizer.nextElement();
										if (line.equals("{"))
											stack.push(1);
										else if (line.equals("}"))
											stack.pop();										
									}

								}

							}

						}
					}

				} else {
					boolean flag = false;
					boolean flag1 = false;
					if (collection.contains(line)) {
						flag = true;
						method += line;
						String temp = "";
						line = tokenizer.nextToken();
						if(line.equals("<"))
						{
							temp = " "+line;
							flag1= true;
							while (tokenizer.hasMoreElements()) {
								line = (String) tokenizer.nextElement();
								if (line.equals(">"))
									break;
								else
									temp += line;
							}
						}
						method += " " + temp;
					}
					if ((identifiare.contains(line) || flag) && tokenizer.hasMoreElements()) {
						method += line;
						
						line = (String) tokenizer.nextElement();
						
						String temp = line;
						if (tokenizer.hasMoreElements())
							line = (String) tokenizer.nextElement();
						else
							break;
						// if(line==null) break;
						if (line.equals("(") && tokenizer.hasMoreElements()) {
							Stack<Integer> stack = new Stack<>();
							method += " " + temp;
							if (line.equals("(")) {
								method += " " + line;
								while (tokenizer.hasMoreTokens()) {
									line = tokenizer.nextToken();
									method += " " + line;
									if (line.equals(")"))
										break;
								}
							}
							listOfMethods.add(method);
							line = (String) tokenizer.nextElement();

							if (line.equals("{") && tokenizer.hasMoreElements()) {
								stack.push(1);
								while (!stack.isEmpty() && tokenizer.hasMoreTokens()) {
									line = (String) tokenizer.nextElement();
									if (line.equals("{"))
										stack.push(1);
									else if (line.equals("}"))
										stack.pop();									
								}
							}
						}
					}
				}
			}
	
			reader.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findAttributes() {
		Set<String> identifiare = new TreeSet<>();
		identifiare.add("int");
		identifiare.add("float");
		identifiare.add("double");
		identifiare.add("long");
		identifiare.add("Integer");
		identifiare.add("Double");
		identifiare.add("Float");
		identifiare.add("boolean");
		identifiare.add("Boolean");
		identifiare.add("String");
		identifiare.add("BitSet");
		identifiare.add("BigInteger");
		identifiare.add("BigDecimal");
		identifiare.add("Character");
		identifiare.add("char");
		identifiare.add("CharSequence");
		for(String string : objectArray){
			identifiare.add(string);
		}
		Set<String> collection = new TreeSet<>();
		collection.add("ArrayList");
		collection.add("ArrayDeque");
		collection.add("Vector");
		collection.add("Set");
		collection.add("TreeSet");
		collection.add("LinkedList");
		collection.add("LinkedHashSet");
		collection.add("HashSet");
		collection.add("List");
		collection.add("Queue");
		collection.add("PriorityQueue");
		collection.add("HashMap");
		collection.add("LinkedHashMap");
		collection.add("Hashtable");
		collection.add("TreeMap");
		collection.add("Map");

		try {
			FileReader file = new FileReader("output.txt");
			BufferedReader reader = new BufferedReader(file);
			Set<String> pb = new TreeSet<>();

			pb.add("public");
			pb.add("private");
			pb.add("protected");
			pb.add("static");
			pb.add("final");

			String str = "";
			String line = reader.readLine();
			while (line != null) {
				str += "  " + line;
				line = reader.readLine();
			}
			str = str.replace(str.subSequence(str.indexOf("class")+1, str.indexOf("{")),"");
			StringTokenizer tokenizer = new StringTokenizer(str);
			String t = "";
			while (tokenizer.hasMoreTokens()) {
				line = tokenizer.nextToken();
				if (line.equals("(")) {
					t += line + " ";
					while (!line.equals(")") && tokenizer.hasMoreTokens()) {
						line = tokenizer.nextToken();
					}
				}
				if (line.equals(")")) {
					t += line + " ";
					Stack<Integer> stack = new Stack<>();
					if (tokenizer.nextToken().equals("{"))
						stack.push(1);
					line = tokenizer.nextToken();
					while (!stack.isEmpty() && tokenizer.hasMoreTokens()) {
						String te = tokenizer.nextToken();
						if (te.equals("{"))
							stack.push(1);
						else if (te.equals("}"))
							stack.pop();
						if (stack.isEmpty())
							break;
					}
				} else
					t += line + " ";
			}
			str = t;
			StringTokenizer tokenizer1 = new StringTokenizer(str);
			t = "";
			while (tokenizer1.hasMoreTokens()) {
				line = tokenizer1.nextToken();
				if (line.equals("=")) {
					while (tokenizer1.hasMoreTokens()) {
						line = tokenizer1.nextToken();
						if (line.equals(";")) {
							t += " " + line;
							break;
						}
					}
				} else
					t += line + " ";
			}

			str = t;
			// System.out.println(t);
			StringTokenizer stringTokenizer = new StringTokenizer(str);
			String at = "";
			while (stringTokenizer.hasMoreElements()) {
				at = "";
				String l1 = (String) stringTokenizer.nextElement();
				if (pb.contains(l1)) {
					if (stringTokenizer.hasMoreElements()) {
						at += l1 + " ";
						l1 = (String) stringTokenizer.nextElement();
						if (l1.equals(className))
							break;
						if (pb.contains(l1)) {
							if (stringTokenizer.hasMoreElements()) {
								at += l1 + " ";
								l1 = (String) stringTokenizer.nextElement();
								if (pb.contains(l1) && stringTokenizer.hasMoreElements()) {
									at += l1 + " ";
									l1 = (String) stringTokenizer.nextElement();
									boolean flag = false;
									if (collection.contains(l1)) {
										flag = true;
										at += " " + l1;
										String temp = "";
										while (stringTokenizer.hasMoreElements()) {
											l1 = (String) stringTokenizer.nextElement();
											if (l1.equals(">"))
												break;
											else
												temp += l1;
										}
										at += " " + temp;
									}
									if ((identifiare.contains(l1) || flag) && stringTokenizer.hasMoreElements()) {
										at += " " + l1;
										l1 = (String) stringTokenizer.nextElement();
										listOfAttributes.add(at + " " + l1);
										String temp = at + " " + l1;
										while (stringTokenizer.hasMoreElements()) {
											l1 = (String) stringTokenizer.nextElement();
											// System.out.println(l1);
											if (l1.equals("(") && stringTokenizer.hasMoreElements()) {
												listOfAttributes.remove(temp);
												Stack<Integer> stack = new Stack<>();

												l1 = (String) stringTokenizer.nextElement();
												
												while (!l1.equals(")") && stringTokenizer.hasMoreElements()) {
													l1 = (String) stringTokenizer.nextElement();
													
												}
												l1 = (String) stringTokenizer.nextElement();
												if (l1.equals("{") && stringTokenizer.hasMoreTokens()) {
													stack.push(1);
													while (!stack.isEmpty() && stringTokenizer.hasMoreElements()) {
														l1 = (String) stringTokenizer.nextElement();
														if (l1.equals("{"))
															stack.push(1);
														else if (l1.equals("}"))
															stack.pop();
													}
												}
											} else if (l1.equals(","))
												continue;
											else if (l1.equals("=")) {
												while (stringTokenizer.hasMoreElements()) {
													if (stringTokenizer.nextElement().equals(",")
															|| stringTokenizer.nextElement().equals(";"))
														break;
													else
														stringTokenizer.nextElement();													
												}
											}else if (l1.equals(";") || l1.equals("("))
												break;
											 if (!l1.equals("(") && !l1.equals(")") && !l1.equals("}")
													&& !l1.equals("{"))
												listOfAttributes.add(at + " " + l1);
											 at = "";
										}
									}
								} else {
									boolean flag = false;
									if (collection.contains(l1)) {
										flag = true;
										at += " " + l1;
										String temp = "";
										while (stringTokenizer.hasMoreElements()) {
											l1 = (String) stringTokenizer.nextElement();
											if (l1.equals(">"))
												break;
											else
												temp += l1;
										}
										at += " " + temp;
									}
									if ((identifiare.contains(l1) || flag) && stringTokenizer.hasMoreElements()) {
										at += l1 + " ";
										l1 = (String) stringTokenizer.nextElement();
										listOfAttributes.add(at + " " + l1);
										String temp = at + " " + l1;
										while (stringTokenizer.hasMoreElements()) {
											l1 = (String) stringTokenizer.nextElement();
										
											if (l1.equals("(") && stringTokenizer.hasMoreElements()) {
												listOfAttributes.remove(temp);
											
												Stack<Integer> stack = new Stack<>();
												if (stringTokenizer.hasMoreElements()) {
													l1 = (String) stringTokenizer.nextElement();
													while (!l1.equals(")") && stringTokenizer.hasMoreElements()) {
														l1 = (String) stringTokenizer.nextElement();
													}
												}
												if (stringTokenizer.nextElement().equals("{")) {
													stack.push(1);													
												}
												while (!stack.isEmpty() && stringTokenizer.hasMoreElements()) {

													String temp1 = (String) stringTokenizer.nextElement();
													if (temp1.equals("{"))
														stack.push(1);
													else if (temp1.equals("}"))
														stack.pop();													
												}

											} else if (l1.equals(","))
												continue;
											else if (l1.equals("=")) {
												while (stringTokenizer.hasMoreElements()) {
													if (stringTokenizer.nextElement().equals(",")
															|| stringTokenizer.nextElement().equals(";"))
														break;
													else
														stringTokenizer.nextElement();													
												}
											}

											else if (l1.equals(";"))
												break;
											if (!l1.equals("(") && !l1.equals(")") && !l1.equals("}")
													&& !l1.equals("{"))
												listOfAttributes.add(at + " " + l1);
											at = "";
										}
									}
								}
							}
						}else {
							boolean flag = false;
							if (collection.contains(l1)) {
								flag = true;
								at += " " + l1;
								String temp = "";
								while (stringTokenizer.hasMoreElements()) {
									l1 = (String) stringTokenizer.nextElement();
									if (l1.equals(">"))
										break;
									else
										temp += l1;
								}
								at += " " + temp;
							}
							if ((identifiare.contains(l1) || flag) && stringTokenizer.hasMoreElements()) {
								at += l1 + " ";
								l1 = (String) stringTokenizer.nextElement();
								listOfAttributes.add(at + " " + l1);
								String temp = at + " " + l1;
								while (stringTokenizer.hasMoreElements()) {
									l1 = (String) stringTokenizer.nextElement();
									
									if (l1.equals("(") && stringTokenizer.hasMoreElements()) {
										listOfAttributes.remove(temp);
										
										Stack<Integer> stack = new Stack<>();
										if (stringTokenizer.hasMoreElements()) {
											String temp1 = (String) stringTokenizer.nextElement();
											while (!temp1.equals(")") && stringTokenizer.hasMoreElements()) {
												temp1 = (String) stringTokenizer.nextElement();
												
											}
										}
										if (stringTokenizer.nextElement().equals("{")) {
											stack.push(1);
										}
										while (!stack.isEmpty()) {

											String temp1 = (String) stringTokenizer.nextElement();
											if (temp1.equals("{"))
												stack.push(1);
											else if (temp1.equals("}"))
												stack.pop();											
										}
									}
									if (l1.equals(","))
										continue;
									if (l1.equals("=")) {
										while (stringTokenizer.hasMoreElements()) {
											if (stringTokenizer.nextElement().equals(",")
													|| stringTokenizer.nextElement().equals(";"))
												break;
											else
												stringTokenizer.nextElement();											
										}
									}
									if (l1.equals(";"))
										break;
									if (!l1.equals("(") && !l1.equals(")") && !l1.equals("}") && !l1.equals("{"))
										listOfAttributes.add(at + " " + l1);
									at = "";
								}
							}
						}
					}

				} else {
					boolean flag = false;
					if (collection.contains(l1)) {
						flag = true;
						at += l1;
						String temp = "";
						while (stringTokenizer.hasMoreElements()) {
							l1 = (String) stringTokenizer.nextElement();
							if (l1.equals(">"))
								break;
							else
								temp += l1;
						}
						at += " " + temp;
					}
					if ((identifiare.contains(l1) || flag) && stringTokenizer.hasMoreElements()) {
						at += l1 + " ";
						l1 = (String) stringTokenizer.nextElement();
						listOfAttributes.add(at + " " + l1);
						String temp = at + " " + l1;
						while (stringTokenizer.hasMoreElements()) {
							l1 = (String) stringTokenizer.nextElement();
							if (l1.equals("(") && stringTokenizer.hasMoreElements()) {
								listOfAttributes.remove(temp);								
								Stack<Integer> stack = new Stack<>();
								if (stringTokenizer.hasMoreElements()) {
									l1 = (String) stringTokenizer.nextElement();
									while (!l1.equals(")") && stringTokenizer.hasMoreTokens()) {
										l1 = (String) stringTokenizer.nextElement();
									}
								}
								if (stringTokenizer.nextElement().equals("{")) {
									stack.push(1);									
								}
								while (!stack.isEmpty() && stringTokenizer.hasMoreElements()) {

									String temp1 = (String) stringTokenizer.nextElement();
									if (temp1.equals("{"))
										stack.push(1);
									else if (temp1.equals("}"))
										stack.pop();								
								}

							}else if (l1.equals(","))
								continue;
							 else if (l1.equals("=")) {
								while (stringTokenizer.hasMoreElements()) {
									if (stringTokenizer.nextElement().equals(",")
											|| stringTokenizer.nextElement().equals(";"))
										break;
									else
										stringTokenizer.nextElement();									
								}
							}

							else if (l1.equals(";"))
								break;
							if (!l1.equals("(") && !l1.equals(")") && !l1.equals("}") && !l1.equals("{"))
								listOfAttributes.add(at + " " + l1);
							at = "";
						}
					}
				}
			}
			reader.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void relationFind(){
		for(int i=0;i<objectArray.size();i++){
			for(int j=0;j<listOfAttributes.size();j++){
				if(listOfAttributes.get(j).contains(objectArray.get(i))){
					relation.add(objectArray.get(i));
					break;
				}
			}
		}
	}
	
	
	public  void removeComent() {
		BufferedReader br = null;
		FileReader file = null;
		PrintWriter pr = null;
		File fl = null;
		try {
			file = new FileReader("output.txt");
			br = new BufferedReader(file);
			fl = new File("output2.txt");
			pr = new PrintWriter(fl);
			int cha = br.read();
			// Stack<Integer> stack = new Stack<Integer>();
			while (cha != -1) {
				if ((char) cha == '/') {
					// char ch = (char) cha;
					cha = br.read();
					// System.out.print((char)cha+ " ");
					if ((char) cha == '*') {
						// System.out.print(ch+ ""+(char)cha+ " ");
						// stack.push(1);
						while (cha != -1) {
							cha = br.read();
							if ((char) cha == '*') {
								cha = br.read();
								if ((char) cha == '/') {
									// stack.pop();
									// if(stack.empty()) break;
									break;
								}
							}
						}
					} else
						pr.print("/" + (char) cha);
				} else {
					pr.print((char) cha);

				}
				cha = br.read();
			}
			br.close();
			pr.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			file = new FileReader("output2.txt");
			br = new BufferedReader(file);
			fl = new File("output.txt");
			pr = new PrintWriter(fl);
			int cha = br.read();
			while (cha != -1) {
				if ((char) cha == '/') {
					cha = br.read();
					if ((char) cha == '/') {
						while (cha != -1) {
							if ((char) cha == '\n') {
								pr.println();
								break;
							}
							cha = br.read();
						}
					} else
						pr.print('/');

				} else
					pr.print((char) cha);
				cha = br.read();
			}
			file.close();
			br.close();
			pr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void makeMyFile(String input) {
		BufferedReader br = null;
		FileReader file = null;
		PrintWriter pr = null;
		File fl = null;
		

		try {
			file = new FileReader(input);
			br = new BufferedReader(file);
			fl = new File("output.txt");
			if (!fl.exists())
				fl.createNewFile();
			pr = new PrintWriter(fl);
			String line = br.readLine();
			while (line != null) {

				String newline = "";
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == 'Â')
						newline += "";
					else
						newline += line.charAt(i);
				}

				line = newline;
				line = line.trim();
				newline = "";
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '{' || line.charAt(i) == '}' || line.charAt(i) == '(' || line.charAt(i) == ')'
							|| line.charAt(i) == ',' || line.charAt(i) == ';') {
						newline += " ";
						newline += line.charAt(i);
						newline += " ";
					} else if ( line.charAt(i) == '<') {
						newline += " ";
						newline += line.charAt(i) + " ";
					} else if ( line.charAt(i) == '>') {
						newline += " " + line.charAt(i);
						newline += " ";
					} else if(line.charAt(i)==']'&&line.charAt(i+1)==' '){
						newline += line.charAt(i);
						i++;
					}
					else if(line.charAt(i)=='[') newline += " "+line.charAt(i);
					else
						newline += line.charAt(i);
				}

				pr.println(newline);
				line = br.readLine();
			}
			br.close();
			pr.close();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	public  int line(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) >= 21 && input.charAt(i) <= 126) {
				return 1;
			}
		}
		return 0;
	}
	
	public  int lineOfCode(String input) {
		BufferedReader br = null;
		FileReader file = null;

		try {
			file = new FileReader(input);
			br = new BufferedReader(file);
			String line = br.readLine();
			while (line != null) {
				lineCount += line(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return lineCount;

	}


	public  void FindClassName() {
		try {
			FileReader file = new FileReader("output.txt");
			BufferedReader reader = new BufferedReader(file);
			Set<String> pb = new TreeSet<>();
			pb.add("public");
			pb.add("private");
			pb.add("protected");
			String line = reader.readLine();
			while (line != null) {
				StringTokenizer tokenizer = new StringTokenizer(line);
				while (tokenizer.hasMoreElements()) {
					String newString = (String) tokenizer.nextElement();
					String clas = "";
					if (newString.equals("class")||newString.equals("interface")) {
						if (tokenizer.hasMoreElements()) {
							clas = (String) tokenizer.nextElement();
							
							//if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("{")) {
								className = clas;
							//}

						}

					} else if (newString.equals("public")) {
						
						String nString = "";
						if(tokenizer.hasMoreElements())
						nString = (String) tokenizer.nextElement();
						if (tokenizer.hasMoreElements() &&( nString.equals("class")||nString.equals("interface"))) {
							if (tokenizer.hasMoreElements())
								clas = (String) tokenizer.nextElement();

							//if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("{"))
								className = clas;

						}

					} else if (newString.equals("static")) {
						String nString = "";
						if(tokenizer.hasMoreElements())
						nString = (String) tokenizer.nextElement();
						if (tokenizer.hasMoreElements() &&( nString.equals("class")||nString.equals("interface"))) {
						
							if (tokenizer.hasMoreElements())
								clas = (String) tokenizer.nextElement();

							//if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("{"))
								className = clas;

						}

					} else if (newString.equals("public")) {
						if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("static")) {
							String nString = "";
							if(tokenizer.hasMoreElements())
							nString = (String) tokenizer.nextElement();
							if (tokenizer.hasMoreElements() &&( nString.equals("class")||nString.equals("interface"))) {
							
								if (tokenizer.hasMoreElements())
									clas = (String) tokenizer.nextElement();
								//if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("{"))
									className = clas;
							}

						}

					} else if (newString.equals("static")) {
						if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("public")) {
							String nString = "";
							if(tokenizer.hasMoreElements())
							nString = (String) tokenizer.nextElement();
							if (tokenizer.hasMoreElements() &&( nString.equals("class")||nString.equals("interface"))) {
							
								if (tokenizer.hasMoreElements())
									clas = (String) tokenizer.nextElement();

								//if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("{"))
									className = clas;

							}

						}

					} else if (newString.equals("public")) {
						if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("abstract")) {
							String nString = "";
							if(tokenizer.hasMoreElements())
							nString = (String) tokenizer.nextElement();
							if (tokenizer.hasMoreElements() &&( nString.equals("class")||nString.equals("interface"))) {
							
								if (tokenizer.hasMoreElements())
									clas = (String) tokenizer.nextElement();

								///if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("{"))
									className = clas;

							}

						}

					} else if (newString.equals("abstract")) {
						if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("public")) {
							String nString = "";
							if(tokenizer.hasMoreElements())
							nString = (String) tokenizer.nextElement();
							if (tokenizer.hasMoreElements() &&( nString.equals("class")||nString.equals("interface"))) {
							
								if (tokenizer.hasMoreElements())
									clas = (String) tokenizer.nextElement();

								//if (tokenizer.hasMoreElements() && tokenizer.nextElement().equals("{"))
									className = clas;

							}

						}

					}
				}
				line = reader.readLine();
			}
			reader.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader() {
			try {
				reader = new BufferedReader(new FileReader("output.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

	}
	
	public void readFile() {
		BufferedReader br = null;
		FileReader file = null;

		try {
			file = new FileReader("input.txt");
			br = new BufferedReader(file);
			String line = br.readLine();
			while (line != null) {
				lineCount += line(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(lineCount);

	}
	
	public void Init(String fileName) {
		initializeKeyword();
		makeMyFile(fileName);
		removeComent();
		keyWordOfProgram();
		FindClassName();
		findAttributes();
		findMethods();
		findAbstactMethods();
		relationFind();

	}
	
	public String inharite(){
		String inhariteString = null;
		
		try {
			FileReader file = new FileReader("output.txt");
			BufferedReader reader = new BufferedReader(file);
			String line = reader.readLine();
			String str = "";
			while(line!=null){
				str += line;
				line = reader.readLine();
			}
			
			StringTokenizer tokenizer = new StringTokenizer(str);
			while(tokenizer.hasMoreTokens()){
				if(tokenizer.nextToken().equals("class")&&tokenizer.hasMoreTokens()){
					line = tokenizer.nextToken();
					if(tokenizer.nextToken().equals("extends")&&tokenizer.hasMoreTokens()){
						inhariteString = tokenizer.nextToken();
						break;
					}
				}
			}
			reader.close();
			file.close();
		} catch (Exception e) {
			
		}
		return inhariteString;
		
	}
	
	public  ArrayList<StoreInformition> getFile(String file1,String pat){
		ArrayList<String> listOfOtherClass = new ArrayList<>();
		
		/*
		JFileChooser chooser = new JFileChooser("C:\\Users\\Unity BD\\Desktop\\");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    "txt", "java");
		chooser.setFileFilter(filter);
		String path = null;
		int returnVal = chooser.showOpenDialog(null);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   String fileName = chooser.getSelectedFile().getName();
		   String filePath = chooser.getSelectedFile().getAbsolutePath();
		   String fileDectory = filePath.replaceFirst(fileName,"");
		   path = fileDectory;
		   File file = new File(fileDectory);
		   String [] allFile = file.list();
		   
		  
		   for(String string : allFile){
			   if(!string.equals(fileName))
				   if(string.endsWith(".java")) javaFile.add(string);
		   }
		   for(String string: javaFile){
			   int len = string.lastIndexOf('.');
			   String temp = string.substring(0,len);
			   objectArray.add(temp);
			   fileLink.put(temp,string);
		   }
		    Init(filePath);
		    String inharite = inharite();
		    String cl = className;
			ArrayList<String> list = (ArrayList<String>) listOfAttributes.clone();
			ArrayList<String> list2 = (ArrayList<String>) listOfMethods.clone();
			ArrayList<String> list3 = (ArrayList<String>) relation.clone();
			String inharit = inharite();
			classDiagram.add(new StoreInformition(cl, inharit,list, list2,list3));
		   if(inharite!=null){
			   listOfOtherClass.add(inharite+".java");
			   //System.out.println(inharite);
		   }
		   
		   objectArray.add(fileName.substring(0,fileName.lastIndexOf('.')));
		}
		
		*/
		/////////////////////////////////////////////////////
		
		   String fileName = file1;
		   String filePath = pat;
		   String fileDectory = filePath.replaceFirst(fileName,"");
		   String path = fileDectory;
		   File file = new File(fileDectory);
		   String [] allFile = file.list();
		   
		  
		   for(String string : allFile){
			   if(!string.equals(fileName))
				   if(string.endsWith(".java")) javaFile.add(string);
		   }
		   for(String string: javaFile){
			   int len = string.lastIndexOf('.');
			   String temp = string.substring(0,len);
			   objectArray.add(temp);
			   fileLink.put(temp,string);
		   }
		    Init(filePath);
		    String inharite = inharite();
		    String cl = className;
			ArrayList<String> list = (ArrayList<String>) listOfAttributes.clone();
			ArrayList<String> list2 = (ArrayList<String>) listOfMethods.clone();
			ArrayList<String> list3 = (ArrayList<String>) relation.clone();
			String inharit = inharite();
			classDiagram.add(new StoreInformition(cl, inharit,list, list2,list3));
		   if(inharite!=null){
			   listOfOtherClass.add(inharite+".java");
			   //System.out.println(inharite);
		   }
		   
		   objectArray.add(fileName.substring(0,fileName.lastIndexOf('.')));
		
		
		
		
		/////////////////////////////////////////////
		
		for(String string : objectArray){
			for(int i=0;i<listOfAttributes.size();i++){
				if(listOfAttributes.get(i).contains(string)) {
					listOfOtherClass.add(fileLink.get(string));
					break;
				}
			}
		}
		for(int i=0;i<listOfOtherClass.size();i++){
			listOfAttributes.clear();
			listOfMethods.clear();
			relation.clear();
			objectArray.remove(listOfOtherClass.get(i).substring(0,listOfOtherClass.get(i).lastIndexOf('.')));
			Init(path+listOfOtherClass.get(i));
			String inharite1 = inharite();
			
			if(inharite1!=null) listOfOtherClass.add(inharite1+".java");
			
			for(String string : objectArray){
				for(int j=0;j<listOfAttributes.size();j++){
					if(listOfAttributes.get(j).contains(string)) {
						listOfOtherClass.add(fileLink.get(string));
						break;
					}
				}
			}
			ArrayList<String> list1 = (ArrayList<String>) listOfAttributes.clone();
			ArrayList<String> list21 = (ArrayList<String>) listOfMethods.clone();
			ArrayList<String> list31 = (ArrayList<String>) relation.clone();
			String cl1 = className;
			classDiagram.add(new StoreInformition(cl1,inharite1, list1, list21,list31));	
			objectArray.add(listOfOtherClass.get(i).substring(0,listOfOtherClass.get(i).lastIndexOf('.')));
		}
		return classDiagram;
	}

}
