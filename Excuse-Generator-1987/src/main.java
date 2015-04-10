import java.io.File;
import java.io.FileInputStream;
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

	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> excuses = new HashMap<String, ArrayList<String>>();
		
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
	        	  String intensity = parts[0];
	        	  String excuseItem = parts[1];
	        	  ArrayList<String> list = excuses.get(intensity);
	        	  if (list == null) {
	        		   list = new ArrayList<String>();
	        		   excuses.put(intensity, list);
	        	  }
	        	  list.add(excuseItem);
	              //...
	          }
	          inputStream.close();
	       }
	       
	    Set<Entry<String, ArrayList<String>>> setMap = excuses.entrySet();
	    Iterator<Entry<String,  ArrayList<String>>> iteratorMap = setMap.iterator();
	    
	    
	    System.out.println("All data:\n--------------------");
        while(iteratorMap.hasNext()) {
        	Map.Entry<String, ArrayList<String>> entry = 
        			(Map.Entry<String, ArrayList<String>>) iteratorMap.next();
            String key = entry.getKey();
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
	}
}
