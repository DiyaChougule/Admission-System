package collegeadmission;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;


//Student Class to declare all the variables and return its type 
class Student {
	public String firstName;
	public String lastName;
	public int age;
	public String phone;
	public String email;
	public int id;
	public double percentage;
	public String course;
	public String password;
	public Date regdate;

//Constrctor to the Class Student
	public Student(String firstName, String lastName, int age, String phone, String email, int id, double percentage,String password,Date regdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.id = id;
		this.percentage = percentage;
		this.course = " ";
		this.password=password;
		this.regdate=regdate;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getId() {
		return id;
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public boolean verifyPassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}
	
	public Date getregdate() {
		return regdate;
	}

}
//Class to show the User Class
public class UserClass {
	public static String n;
	public static final int MAX_STUDENTS = 10;
	public static Student[] registeredStudents = new Student[MAX_STUDENTS];
	public static int registeredCount = 0;
	static Scanner scanner = new Scanner(System.in);
	
//Method to generate hash ID
	public static int generateHash(int id) {
		return id % MAX_STUDENTS;
	}
	
//To check if the number is valid
	public static boolean isValidPhoneNumber(String phone) {
		return (phone.matches("\\d{10}") || phone.matches("^[987]\\d{9}$"));
	}
	
//Method to register a new student in the HashTable
	public void registerNewStudent(Scanner scanner) {
		System.out.print("\t\tEnter your percentage: ");
		double percentage = scanner.nextDouble();
		
		if (percentage < 50) {
			System.out.println("\t\tYou are not eligible for registration. Your percentage is less than 50.");
			return;
		}
		
		System.out.print("\t\tEnter student full name: ");
		scanner.nextLine();
		String fullName = scanner.nextLine();

		String[] nameParts = fullName.split(" ", 2);

		if (nameParts.length != 2) {
			System.out.println("\t\tPlease enter your full name with a space in between your first and last name.");
			return;
		}
		
		System.out.print("\t\tEnter student age: ");
		int age = scanner.nextInt();

		if (age < 18 || age > 21) {
			System.out.println("\t\tStudent age must be between 18 and 21 to register.");
			return;
		}

		System.out.print("\t\tEnter student phone number: ");
		String phone = scanner.next();

		if (!isValidPhoneNumber(phone)) {
			System.out.println("\t\tInvalid phone number. Phone number must be 10 digits or start with '9', '8', or '7'.");
			return;
		}

		System.out.print("\t\tEnter student email ID: ");
		String email = scanner.next();

		System.out.print("\t\tEnter student ID: ");
		int id = scanner.nextInt();

		System.out.print("\t\tSet a password: ");
		String password = scanner.next();

		int hashKey = generateHash(id);

		if (registeredStudents[hashKey] == null) {
			Date regdatetime=new Date();
			Student newStudent = new Student(nameParts[0], nameParts[1], age, phone, email, id, percentage, password,regdatetime);
	        newStudent.setCourse("Applied course:"); // Assigning a course for demonstration
	        registeredStudents[hashKey] = newStudent;
	        System.out.println("\t\tStudent " + nameParts[0] + " " + nameParts[1] + " registered successfully with ID: " + id);
	        registeredCount++;
	        
		}
		 else {
		        // If there's already a student in the slot, you have a collision.
		        // Check if a student with the same ID already exists in the collision chain.
		        while (registeredStudents[hashKey] != null) {
		            if (registeredStudents[hashKey].getId() == id) {
		                System.out.println("A student with the same ID is already registered. Please choose a different ID.");
		                return;
		            }
		            hashKey = (hashKey + 1) % MAX_STUDENTS;
		        }
	}
		}
	
//Method to Apply for Course and also give suggestion
	public static void suggestAndApply() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\tPlease enter your ID:");
		boolean flag = false;
		int studentID = sc.nextInt();

		double studentPercentage = 0;
		String suggestedCourse = "";

		// Search for the student in the registered students array
		for (Student student : registeredStudents) {
			if (student != null && student.getId() == studentID) {
				studentPercentage = student.getPercentage();
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("\t\tStudent Id does not exist");
			return;
		}

		if (studentPercentage >= 90) {
			suggestedCourse = "\t\tComputer Science\n, \t\tInformation Technology\n, \t\tElectronics And Communication System\n, \t\tMechanical";
		} else if (studentPercentage > 80 && studentPercentage < 90) {
			suggestedCourse = "\t\tInformation Technology\n, \t\tElectronics And Communication System\n, \t\tMechanical";
		} else if (studentPercentage > 70 && studentPercentage < 80) {
			suggestedCourse = "\t\tElectronics And Communication System, Mechanical";
		} else if (studentPercentage > 50 && studentPercentage < 70) {
			suggestedCourse = "\t\tMechanical";
		}

		System.out.println("\t\tYour percentage: " + studentPercentage);
		System.out.println("\t\tSuggested courses: " + suggestedCourse);

		System.out.println("\t\tDo you want to apply for any one course from this list?");
		String ans = sc.next();

		if (ans.equals("yes")) {
	        System.out.println("Enter the course you want to apply for:");
	        String selectedCourse = sc.next(); 	
	        boolean validCourse = suggestedCourse.contains(selectedCourse);
	        
	        if (validCourse) {
	            for (Student student : registeredStudents) {
	                if (student != null && student.getId() == studentID) {
	                    student.setCourse(selectedCourse);
	                    System.out.println("Applied for course: " + selectedCourse);
	                    break;
	                }
	            }
	        } else {
	        	 System.out.println("Invalid course selection based on suggested courses.");
	        }
		} }
	  
//To return Registered Student 
	 public static Student[] getRegisteredStudents() {
	        return registeredStudents;
	    }
	
//Method for login of the student and display the information
	public static void login(Scanner scanner) {
		System.out.println("\t\tPlease enter your ID:");
		int studentID = scanner.nextInt();

		for (Student student : registeredStudents) {
			if (student != null && student.getId() == studentID) {
				System.out.println("\t\tEnter your password:");
				String password = scanner.next();

				if (student.verifyPassword(password)) {
					System.out.println("\t\tLogin successful!");
					displayStudentDetails(student);
					return;
				} else {
					System.out.println("\t\tIncorrect password. Please try again.");
					return;
				}
			}
		}
		System.out.println("\t\tStudent with the entered ID not found.");
	}
	
//To update details of the students 
	public static void update(Scanner scanner) {
		
		System.out.println("Please enter your ID:");
		int studentID = scanner.nextInt();
		
		for (Student student : registeredStudents) {
			if (student != null && student.getId() == studentID) {
				System.out.println("Enter your password:");
				String password = scanner.next();

				if (student.verifyPassword(password)) {
					System.out.println("\t\tLogin successful!");
					System.out.println("\t\tSelect the information you want to update:");
					System.out.println("1. First Name");
					System.out.println("2. Last Name");
					System.out.println("3. Age");
					System.out.println("4. Phone");
					System.out.println("5. Email");
					System.out.println("6. Percentage");
					System.out.println("7. Password");

					int choice = scanner.nextInt();
					scanner.nextLine(); // Consume newline character

					switch (choice) {
					case 1:
						System.out.print("\t\tEnter updated first name: ");
						student.firstName = scanner.nextLine();
						break;
					case 2:
						System.out.print("\t\tEnter updated last name: ");
						student.lastName = scanner.nextLine();
						break;
					case 3:
						System.out.print("\t\tEnter updated age: ");
						student.age = scanner.nextInt();
						break;
					case 4:
						System.out.print("\t\tEnter updated phone number: ");
						student.phone = scanner.next();
						break;
					case 5:
						System.out.print("\t\tEnter updated email: ");
						student.email = scanner.next();
						break;
					case 6:
						System.out.print("\t\tEnter updated percentage: ");
						student.percentage = scanner.nextDouble();
						break;
					case 7:
						System.out.print("\t\tEnter updated password: ");
						student.password = scanner.next();
						break;
					default:
						System.out.println("\t\tInvalid choice.");
						System.out.println("\t\tUpdated successfully");
					}
				}	
				 else {
					System.out.println("\t\tIncorrect password. Please try again.");
					
				}
			}
			else if(student != null && student.getId() != studentID) {
				System.out.println("\t\tStudent with the entered ID not found.");
				
			}
	}}
	
//To display data of the student
	public static void displayStudentDetails(Student student) {
		System.out.println("\t\tStudent Details:");
		System.out.println("\t\tID: " + student.getId());
		System.out.println("\t\tName: " + student.getFirstName() + " " + student.getLastName());
		System.out.println("\t\tAge: " + student.getAge());
		System.out.println("\t\tPhone: " + student.getPhone());
		System.out.println("\t\tEmail: " + student.getEmail());
		System.out.println("\t\tPercentage: " + student.getPercentage());
		System.out.println("\t\tCourse: " + student.getCourse());
		System.out.println("\t\tRegistration Date and Time :"+ student.getregdate());

		
	}
	
//To generate report according to particular feild
	public static void generateReport()
	{		
// Create a copy of the registeredStudents array to avoid modifying the original array
	    Student[] copyStudents = Arrays.copyOf(registeredStudents, registeredStudents.length);

  // Sort the array by percentage in descending order
	   Arrays.sort(copyStudents, (s1, s2) -> {
	       if (s1 != null && s2 != null) {
	            return Double.compare(s2.getPercentage(), s1.getPercentage());
		        }
		        return 0;
		    });

		    // Display student details in the sorted order
		    System.out.println("\t\tName\t\t\tPercentage\t\t\tApplied Course:\t\t\tdate and time of registration");
		    for (Student student : copyStudents) {
		        if (student != null && !student.getCourse().equals(" ")) {
		            System.out.println("\t\t"+student.getFirstName() + " " + student.getLastName() +
		                    "\t\t"+student.getPercentage() + "\t\t\t"+student.getCourse()+"\t\t\t"+student.getregdate());
		        }
		    }
	}
//To  post enquiry about the college	
	public static void enquiry() {
		System.out.println("\t\tEnter your enquiry");
		n=scanner.nextLine();
		System.out.println("\t\tThanks for interest,we will answer soon ");
	}
}