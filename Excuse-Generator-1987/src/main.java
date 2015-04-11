import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class main {
	static HashMap<Integer, ArrayList<String>> excuses = new HashMap<Integer, ArrayList<String>>();
	
	@SuppressWarnings("unchecked")
	public static <T> void removeDuplicate(List <T> list) {
		Set <T> set = new HashSet <T>();
	    List <T> newList = new ArrayList <T>();
	    for (Iterator <T>iter = list.iterator(); iter.hasNext(); ) {
	    	Object element = iter.next();
	        if (set.add((T) element)) {
	        	newList.add((T) element);
	        }
	    }
	    list.clear();
	    list.addAll(newList);
	}
	
	public static void removeBlankLines() throws IOException {
		FileReader fr = new FileReader("excusesList.txt"); 
		BufferedReader br = new BufferedReader(fr);
		String line;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outfile.txt", true)));

		while((line = br.readLine()) != null)
		{
		    if (!line.equals("")) // don't write out blank lines
		    {
		        line.replace("\\n\\s+$", "");
		        out.println(line);
		        
		    }
		}
		out.close();
		br.close();
		try{
			 
    		File file = new File("excusesList.txt");
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    			
    			// Rename outfile to excusesList.txt
    			File oldFile = new File("outfile.txt");
    			File newFile = new File("excusesList.txt");
    		    if(newFile.exists()) throw new java.io.IOException("file exists");

    		    // Rename file (or directory)
    		    boolean success = oldFile.renameTo(newFile);
    		    if (!success) {
    		        // File was not successfully renamed
    		    }
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
	}
	
	public static void removeExcuse(int theIntensity, String theExcuse) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();	
		stringBuilder.append(Integer.toString(theIntensity));
		stringBuilder.append(",");
		stringBuilder.append(theExcuse);
        String finalExcuse = stringBuilder.toString();
        
		ArrayList<String> test = excuses.get(theIntensity);
		
		File file = new File("excusesList.txt");
		File temp = File.createTempFile("file", ".txt", file.getParentFile());
		String charset = "UTF-8";
		String delete = finalExcuse;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
		for (String line; (line = reader.readLine()) != null;) {
			line = line.replace(delete, "");
			line.trim();
			writer.println(line);
		}
		reader.close();
		writer.close();
		file.delete();
		temp.renameTo(file);
	    test.remove(theExcuse);
	}
	public static void addExcuse(int theIntensity, String theExcuse) {
		StringBuilder stringBuilder = new StringBuilder();		
		ArrayList<String> test = excuses.get(theIntensity);
	    
	    if(!test.contains(theExcuse)) {
		    try {
		        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("excusesList.txt", true)));
		        stringBuilder.append(Integer.toString(theIntensity));
				stringBuilder.append(",");
				stringBuilder.append(theExcuse);
		        String finalExcuse = stringBuilder.toString();
		        
		        out.println(finalExcuse);
		        out.close();
		        test.add(theExcuse);
		    } catch (IOException e) {
		        //exception handling left as an exercise for the reader
		    	System.out.print(e);
		    }
	    } else {
	    	System.out.println("That excuse already exists with this intensity");
	    }
	}

	public static void main(String[] args)  {
		
		File f = null;
	    String path = "";
	    boolean bool = false;
	    
	    try{
	       // create new files
	       f = new File("excusesList.txt");
	       
	       // returns true if the file exists
	       bool = f.exists();
	       
	       // if file exists
	       if(bool)
	       {
	          // get absolute path
	          path = f.getAbsolutePath();
	          
	          // prints
	          Scanner inputStream = new Scanner(new FileInputStream(path));
	          String line;
	          while(inputStream.hasNextLine()){
	              line = inputStream.nextLine();
	              String[] parts = line.split(",");
	        	  Integer intensity = Integer.parseInt(parts[0]);
	        	  String excuseItem = parts[1];
	        	  ArrayList<String> list = excuses.get(intensity);
	        	  if (list == null) {
	        		   list = new ArrayList<String>();
	        		   excuses.put(intensity, list);
	        	  }
	        	  removeDuplicate(list);
	        	  list.add(excuseItem);
	              //...
	          }
	          inputStream.close();
	       }
	       
	    Set<Entry<Integer, ArrayList<String>>> setMap = excuses.entrySet();
	    Iterator<Entry<Integer,  ArrayList<String>>> iteratorMap = setMap.iterator();
	    
	    
	    System.out.println("All data:\n--------------------");
        while(iteratorMap.hasNext()) {
        	Entry<Integer, ArrayList<String>> entry = 
        			(Map.Entry<Integer, ArrayList<String>>) iteratorMap.next();
            Integer key = entry.getKey();
            List<String> values = entry.getValue();
            System.out.println("Intensity: " + key);
            System.out.println("Excuses:");
            for (String element : values) {
            	System.out.println("   " + element);
            }
            System.out.print("\n");
        }
	    }catch(Exception e){
	       // if any error occurs
	       e.printStackTrace();
	    }
	    
	    try {
			removeBlankLines();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			removeExcuse(3,"lkjs");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
