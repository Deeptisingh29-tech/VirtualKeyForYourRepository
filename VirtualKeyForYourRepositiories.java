package phase1_end;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VirtualKeyForYourRepositiories {

	
	public static void main(String[] args) {
		
		System.out.println("\n");
		System.out.println("        *********************************        ");
		System.out.println("    *****************************************    ");
		System.out.println("*************************************************");
		System.out.println("|*                                             *|");
		System.out.println("|*          |    ~* LOCKEDME *~    |           *|");
		System.out.println("|*                                             *|");
		System.out.println("|*      *********************************      *|");
		System.out.println("|*         Developed by : DEEPTI SINGH         *|");
		System.out.println("*************************************************");
		System.out.println("    *****************************************    ");
		System.out.println("        *********************************        ");
		System.out.println("\n\n");
		
        optionsSelection();
        
        
        

    }
    private static void optionsSelection() {
    	
    	/*Enter your desired Directory path */
    	String path = "E:\\Eclipse\\New";
        File directory = new File(path);
        String listOfFiles[]= directory.list();
        String[] arr = {"1. List of files",
        				"2. Perform some operation",
        				"3. Close the application"
        };
        
        int[] arr1 = {1,2,3};
        int  slen = arr1.length;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("|          CHOOSE OPTION            |");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=0; i<slen;i++){
            System.out.println(arr[i]);
            // display the all the Strings mentioned in the String array
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        System.out.println("Enter your choice:\t");
        Scanner sc = new Scanner(System.in);
        int  options =  sc.nextInt();
        for(int j=1;j<=slen;j++){
            if(options==j){
                switch (options){
                    case 1:
                        sortFilesAsc(listOfFiles, directory, path);                     
                        break;
                    case 2:
                    	performOperation(listOfFiles, path);
                        break;                        
                    case 3:
                        closeApp();
                        break;
                    default:
                        System.out.println("You have made an invalid choice!");
                        
                }
            }
        }
	}
       
    
	//CASE 1: list the files of directory in ascending order
    private static void sortFilesAsc(String[] listOfFiles, File directory, String path) {
    	
    	if (path == null || path.isEmpty() || path.isBlank())
			throw new NullPointerException("Path cannot be Empty or null");
    	
    	if(directory.isFile())
			throw new IllegalArgumentException("The given path is a file. A directory is expected.");
		
    	if(listOfFiles != null && listOfFiles.length > 0) {
    		System.out.println("\n------------------------------------------------");
    		System.out.println("| All files in the directory are listed below: |");
    		System.out.println("------------------------------------------------\n");
    		Arrays.sort(listOfFiles);        //sorting of files
    		for(int i=0; i< listOfFiles.length; i++){
    			System.out.println(listOfFiles[i]);
    		}
    		System.out.println("\n------------------------------------------------");
    		System.out.println("|   Total "+listOfFiles.length+" items present in "+directory.getPath()+"   |");
    		System.out.println("------------------------------------------------\n");
		
    	}else {
    		System.out.println("Directory is empty");
    	}
    	System.out.println("\n\n");
		optionsSelection();
    }
    
    
    //CASE 2: display the options of operation to perform on file
    private static void performOperation(String[] listOfFiles, String path) {
    	List<String> fileList = new ArrayList<String>(Arrays.asList(listOfFiles));
    	Scanner sc = new Scanner(System.in);
    	String[] arr2 = {
                "1. ADD a file",
                "2. DELETE a file",
                "3. SEARCH a file",
                "4. NAVIGATE BACK ",
          };
          int[] arr3 = {1,2,3,4};
          int  nlen = arr2.length;
          System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println("|         SELECT OPERATION          |");
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          for(int i=0; i<nlen;i++){
          	System.out.println(arr2[i]);
              // display the all the Strings mentioned in the String array
          }
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println("Enter the operation you want to perform:\t");
          int choice = sc.nextInt();
          
          for(int c=0; c<=nlen; c++) {
        	  if( choice == c) {
        		  switch(choice) {
        		  case 1: 
        		  		addFile(path);
        		  		performOperation(listOfFiles, path);
        		  		break;
        		  		
        		  case 2: 
  		  				deleteFile(path);
  		  				performOperation(listOfFiles, path);
  		  				break;
  		  				
        		  case 3:
	  					search(listOfFiles, path);
	  					performOperation(listOfFiles, path);
	  					break;
	  					
        		  case 4: 
        		  		navigateBack();        		  		
        		  		break;
        		  		
        		  default: System.out.println("Invalid choice\nPlease choose from 1-4");
        		  		performOperation(listOfFiles, path);
        		  }
        	  }
          }
    }
    
    
	
		//CASE 2.1 method to add a file 
		private static void addFile(String path) {
		
			if (path == null || path.isEmpty() || path.isBlank())
				throw new NullPointerException("Path cannot be Empty or null");
			
			System.out.println("\nEnter the name of the file to be added:\t");
			Scanner sc = new Scanner(System.in);
			String fname = sc.nextLine();
			
			if (fname == null || fname.isEmpty() || fname.isBlank())
				throw new NullPointerException("File Name cannot be Empty or null");
			
			File newFile = new File(path + File.separator + fname);
			
			boolean createFile = false;
			try {
				createFile = newFile.createNewFile();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			if (createFile) {
				System.out.println("\n=>Adding the file......");
				System.out.println("------------------------------------------------");
				System.out.println(fname+" Successfully added: " + newFile.getAbsolutePath());
				System.out.println("------------------------------------------------\n\n");
			}else if(!createFile) {
				System.out.println("\n------------------------------------------------");
				System.out.println("File Already Exist.. Please try again." );
				System.out.println("------------------------------------------------\n\n");
			}		
		}
		
		
		
	
		//CASE 2.2 method to delete a file 
		private static void deleteFile(String path) {
			if (path == null || path.isEmpty() || path.isBlank())
				throw new NullPointerException("Path cannot be Empty or null");
			
			System.out.println("\nEnter the name of the file to be deleted:\t");
			Scanner sc = new Scanner(System.in);
			String fname = sc.nextLine();
			
			if (fname == null || fname.isEmpty() || fname.isBlank())
				throw new NullPointerException("File Name cannot be Empty or null");
			
			File newFile = new File(path + File.separator + fname);
			
			boolean deleteFile = newFile.delete();
			if (deleteFile) {
				System.out.println("\n=> Deleting the file......");
				System.out.println("------------------------------------------------");
				System.out.println(fname+" Successfully deleted: " + newFile.getAbsolutePath()+"");
				System.out.println("------------------------------------------------\n\n");
			}else {
				System.out.println("\n------------------------------------------------");
				System.out.println("File Not Found.. Please try again." );	
				System.out.println("------------------------------------------------\n\n");
				
			}			
		}
		
		//CASE 2.3 search a file
		private static void search(String[] listOfFiles,String path) {
			if (path == null || path.isEmpty() || path.isBlank())
				throw new NullPointerException("Path cannot be Empty or null");
			
			System.out.println("\nEnter the name of the file to be searched:\t");
			Scanner sc = new Scanner(System.in);
			String fname = sc.nextLine();
			
			if (fname == null || fname.isEmpty() || fname.isBlank())
				throw new NullPointerException("File Name cannot be Empty or null");
			
			File newFile = new File(path + File.separator + fname);
			
			boolean flag = false;
			
			Pattern pat = Pattern.compile(fname);
			
			if(listOfFiles != null && listOfFiles.length > 0) {
				for(String file:listOfFiles) {
					Matcher mat = pat.matcher(file);
					if(mat.matches()) {
						System.out.println("\n=> Searching the file.......");
						System.out.println("------------------------------------------------");
						System.out.println("File Found with "+fname+" name");
						System.out.println("------------------------------------------------\n\n");
						flag = true;
						break;
					}
				}
			}
			
			if(flag == false) {
				System.out.println("\n=> Searching the file.......");
				System.out.println("\n------------------------------------------------");
				System.out.println("File Not Found.. Please try again.");
				System.out.println("------------------------------------------------\n\n");
			}
			
	    }
		
		
		//CASE 2.4 Go Back
		private static void navigateBack() {
	        System.out.println("\n\n\n=> Navigating back.....");
	        optionsSelection();
	    }
	
		
	//CASE 3. Close the application	
	private static void closeApp() {
        System.out.println("\n\n=> Closing your application... \n\n");
        System.out.println("    *****************************************    ");
		System.out.println("*************************************************");
		System.out.println("*                                               *");
		System.out.println("*    ~THANK YOU FOR VISITING LOCKEDME.COM~      *");
		System.out.println("*                                               *");
		System.out.println("*        Hope you liked the experience          *");
		System.out.println("*                                               *");
		System.out.println("*************************************************");
		System.out.println("    *****************************************    ");
		System.out.println("\n\n");
    }

}
