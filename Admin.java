package collegeadmission;
import java.util.*;

//Admin Class to access all the data
class Admin {
	static Scanner sc= new Scanner(System.in);
	static String ans;
    private static final String adminPassword = "admin123";
  
//Method to check admin Login
    public static boolean login() {
        System.out.println("\t\tEnter admin password:");
        String password = sc.next();
        return password.equals(adminPassword);
    } 

//Method to delete a particular student   
    public static void deleteStudent(int Id) {
    	boolean flag=false;
    	 Student[] registeredStudents = UserClass.getRegisteredStudents();
    	 for(int i=0;i<registeredStudents.length;i++) {
    		 if(registeredStudents[i]!=null && registeredStudents[i].getId()==Id){
    			 flag=true;
    			 registeredStudents[i]=null;
    			 System.out.println("\t\tStudent with Id "+i+" Deleted Successfully");
    			 return;
    		 }
    		 }
    		 if(!flag) {
    			 System.out.println("\t\tId does not exist");
    		 }
    	 }
    
//To answer the question for the students
    public static void answer() {
    	boolean flag=false;
        if (UserClass.n != null) {
        	flag=true;
            System.out.println(UserClass.n);
            System.out.println("\t\tEnter your Answer here:");
            ans = sc.next();
            System.out.println(ans);
        } if(!flag) {
            System.out.println("\t\tNo enquiry to answer.");
        }
    }
    
//To post the answer of the enquries
    public static void post() {
        if (ans != null) {
            System.out.println("\t\tQuestion :"+UserClass.n);
            System.out.println("\t\tAnswer :"+ans);
        } else {
            System.out.println("\t\tNo answer to post.");
        }
    }
   
//Main class to call all the method  
    public static void main(String[] args) {
    	 Scanner scanner = new Scanner(System.in);
    	 System.out.println();
    	 System.out.println();
         UserClass obj=new UserClass();
         System.out.println("\t\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
         System.out.println("\t\t+                                                             +");
         System.out.println("\t\t+                    !! WELCOME !!                            +");
         System.out.println("\t\t+           CUMMINS COLLEGE OF ENGINEERING ,PUNE              +");
         System.out.println("\t\t+    WE BELIEVE IN THE UNLIMITED POWER OF WOMEN IN THE        +");
         System.out.println("\t\t+           CONTEXT OF ENGINEERING AND TECHNOLOGY             +");
         System.out.println("\t\t+                                                             +");
         System.out.println("\t\t+                                                             +");
         System.out.println("\t\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        
         System.out.println();
         System.out.println();
         System.out.println("___________________________________________________________________________________");
         System.out.println();
         
         while (true) {
        System.out.println("\t\t------COLLEGE ADMISSION SYSTEM-------");
        System.out.println("\t\tSign up as:");
        System.out.println("\t\t1. Student");
        System.out.println("\t\t2. Admin");
        System.out.println("\t\t3. Exit");
         
         int choice = scanner.nextInt();
         switch (choice) {
         case 1:
          System.out.println("\t\tPlease enter your choice ");
          System.out.println("\t\t1.Register as a new student");
          System.out.println("\t\t2.Suggest and Apply");
          System.out.println("\t\t3.Login");
          System.out.println("\t\t4.Update details");
          System.out.println("\t\t5.Send Enquiry");
          System.out.println("\t\t6.Enquiry Reply");
          System.out.println("\t\t7.Exit the System");
           			
           	int c = scanner.nextInt();
        	switch (c) {
            			case 1:
            				if (UserClass.registeredCount < UserClass.MAX_STUDENTS) {
            					obj.registerNewStudent(scanner);
            				} else {
            					System.out.println("\t\tMaximum students reached. Cannot register more.");
            				}
            				break;
            				
            			case 7:
            					break;
            					
            			case 2:
            				UserClass.suggestAndApply();
            				break;
            				
            			case 3:
            				UserClass.login(scanner);
            				break;
            				
            			case 4:
            				UserClass.update(scanner);
            				break;
            				
            			case 5:
            				UserClass.enquiry();
            				break;
            				
            			case 6:
            				post();
            				break;
            				
            			default:
            				System.out.println("\t\tInvalid choice. Please try again.");
            				break;
            				
            			}
            			break;
            		
         case 2:
          if (login()) {
        	    	
        	   	System.out.println("\t\t1.Display all student data\n\t\t2.Delete a student record\n\t\t3.Get report of student :\n\t\t4.Answer Enquiry ");
        	   	System.out.println("\t\tEnter choice");
       	    
             	int s=sc.nextInt();
        	    	switch(s) {
        	    	case 1:
        	        Student[] registeredStudents = UserClass.getRegisteredStudents();
        	        if (registeredStudents != null) {
        	            for (Student student : registeredStudents) {
        	                if (student != null) {
        	                    UserClass.displayStudentDetails(student);
        	                }
        	            }
        	        } else {
        	            System.out.println("\t\tNo registered students found.");
        	        }
        	        break;
        	        
        	    	case 2:
        	    		System.out.println("\t\tEnter student id");
        	    		int id=sc.nextInt();
        	    		deleteStudent(id);
        	    		break;
        	    		
        	    	case 3:
        	    		UserClass.generateReport();
        	    		break;
        	    		
        	    	case 4:
        	    		answer();
        	    		break;
        	    		
        	    	default:
        	    		System.out.println("Invalid");
        	    	}
        	    }else {
        	        System.out.println("\t\tAdmin login failed. Please try again.");
        	    }
        	    break;

     }
         }
    }
}








