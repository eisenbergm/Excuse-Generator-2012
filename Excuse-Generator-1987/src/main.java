import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;


public class main extends JFrame implements ActionListener {
	// Initiate hashmap and set intensity to 0
	static HashMap<Integer, ArrayList<String>> excuses = new HashMap<Integer, ArrayList<String>>();
	public int intensity = 1;
	private JPanel contentPane;
	private JTextField textField;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel lblSeverityLevel;
	public static JLabel status;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public static int randInt(int max) {
		int min = 0;
		max=max -1;

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	// main constructor for the GUI
	public main() {
		setTitle("Excuse Generator 1927");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		status = new JLabel("");
		textField.setColumns(10);
		JRadioButton rdbtnLevel = new JRadioButton("Level 1");
		rdbtnLevel.setSelected(true);
		rdbtnLevel.setMnemonic('A');
	
		rdbtnLevel.addActionListener(this);
		buttonGroup.add(rdbtnLevel);
		
		

		rdbtnNewRadioButton = new JRadioButton("Level 2");
		rdbtnNewRadioButton.setMnemonic('B');
		rdbtnNewRadioButton.addActionListener(this);
		buttonGroup.add(rdbtnNewRadioButton);
		
		
		rdbtnNewRadioButton_1 = new JRadioButton("Level 3");
		rdbtnNewRadioButton_1.setMnemonic('C');
		rdbtnNewRadioButton_1.addActionListener(this);
		buttonGroup.add(rdbtnNewRadioButton_1);
		
		
		lblSeverityLevel = new JLabel("Intensity Level");
		
		JButton btnNewButton = new JButton("Generate Excuse");
		btnNewButton.addActionListener(this);
		
		JButton btnNewButton_1 = new JButton("Add Excuse");
		btnNewButton_1.addActionListener(this);
		
		JButton btnNewButton_2 = new JButton("Delete Excuse");
		btnNewButton_2.addActionListener(this);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(4)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(15)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(180)
					.addComponent(lblSeverityLevel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(169))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(188)
					.addComponent(rdbtnNewRadioButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(177))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(117)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(107))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(117)
					.addComponent(status, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(107))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(188)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnLevel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rdbtnNewRadioButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(177))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblSeverityLevel)
					.addGap(7)
					.addComponent(rdbtnLevel)
					.addGap(3)
					.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(rdbtnNewRadioButton_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	// remove duplicates from hashmap, not text file
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
	
	// getter for getting random excuse given an intensity
	public static String getExcuse(int intensity) {
		ArrayList<String> list = excuses.get(intensity);
		int size = list.size();
		int stringKey = randInt(size);
		return list.get(stringKey);
		
	}
	
	// remove blank lines from text file to avoid errors when reading
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
	
	// delete excuse from hashmap and text file; remove blank lines
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
	    try {
	    	removeBlankLines();
	    } catch (IOException e1) {
	    	// TODO Auto-generated catch block
	    	e1.printStackTrace();
	    }
	}
	
	// Add excuse to hashmap and text file
	public static Integer addExcuse(int theIntensity, String theExcuse) {
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
		        return 1;
		    } catch (IOException e) {
		        //exception handling left as an exercise for the reader
		    	System.out.print(e);
		    	return 0;
		    }
	    } else {
	    	return -1;
	    }
	}
	
	// Handle button bresses in GUI
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Level 1")) {
			intensity = 1;
			status.setText("");
		} else if (e.getActionCommand().equals("Level 2")) {
			intensity = 2;
			status.setText("");
		} else if (e.getActionCommand().equals("Level 3")) {
			intensity = 3;
			status.setText("");
		} else if (e.getActionCommand().equals("Generate Excuse")) {
			status.setText("");
			textField.setText(getExcuse(intensity));
		} else if (e.getActionCommand().equals("Add Excuse")) {
			String entry = textField.getText();
			if (entry.equals("")) {
				status.setText("You must enter SOME text, dummy!");
			} else {
				int add = addExcuse(intensity, entry);
				if (add == -1) {
					status.setText("This excuse already exists with this intensity level.");
				} else if (add == 1) {
					status.setText("Your excuse has been added!");
				} else {
					status.setText("Something went wrong...");
				}
			}
		} else if (e.getActionCommand().equals("Delete Excuse")) {
			String entry = textField.getText();
			if (entry.equals("")) {
				status.setText("You must enter SOME text, ugh!");
			} else {
				try {
				removeExcuse(intensity, entry);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				status.setText("We tried deleting your excuse. We hope it worked! :+)");
			}
		}
	}

	// generate GUI and test print all data
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
	    
	    main gui = new main();
		gui.setVisible(true);
	}
}
