package com.ems.operations;
import com.ems.service.*;
import com.ems.serviceimpl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ManagerOperations {
	public static void manageManager(BufferedReader br) throws NumberFormatException, IOException {
		ManagerService managerservice=new ManagerServiceImpl();
		ManagerOperations mgopr = new ManagerOperations();
		
			try {
			    while (true) {
			    	System.out.println("----------------------------");
			        System.out.println("Employee Management:");
			        System.out.println("1. Add Manager");
			        System.out.println("2. Update Manager");
			        System.out.println("3. Delete Manager");
			        System.out.println("4. View All Managers");
			        System.out.println("5. Search Manager");
			        System.out.println("0. Back");
			        System.out.println("----------------------------");
			
			        int choice = Integer.parseInt(br.readLine());
			        System.out.println("----------------------------");
			        switch (choice) {
			            case 0:
			                return;
			            case 1:
			            	mgopr.addManagers();
			                break;
			            case 2:
			            	System.out.println("Choose option: ");
			            	System.out.println("1. Update Title");
			            	System.out.println("2. Update Email");
			            	System.out.println("0. Back ");
			            	int ch = Integer.parseInt(br.readLine());
			            	
				            	switch(ch) {
				            		case 0: return;
				            		case 1: 
				            			mgopr.updateTitle();
				            			break;
				            		case 2:
				            			mgopr.updateEmail();
				            			break;
				            		default: System.out.println("Invalid choice. Please try again.");
				            	}
			                
			                break;
			            case 3:
			            	
			            	System.out.println("Enter Manager ID : ");
			            	String id2=br.readLine();
			            	managerservice.deleteManager(id2);
			                
			                break;
			            case 4:
			            	managerservice.getAllManagers();
			                break;
			            case 5:
			                mgopr.searchManager();
			                break;
			            default:
			                System.out.println("Invalid choice. Please try again.");
			        }
			    }
			}
			catch(NumberFormatException ne) {
				System.out.println(ne);
			}
			catch(IOException ie) {
				System.out.println(ie);
			}
		}
	
	public void addManagers() throws IOException {
		ManagerService managerservice=new ManagerServiceImpl();
		BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
		
		System.out.println("Enter Manager Id : ");
    	String id=br.readLine();
    	System.out.println("Enter Manager Name :");
    	String addname=br.readLine();
    	System.out.println("Enter Manager Title:");
    	String addtitle=br.readLine();
    	System.out.println("Enter Manager Email:");
    	String addemail=br.readLine();
    	managerservice.addManager(id, addname, addtitle, addemail);
        
	}
	
	public void updateTitle() throws IOException {
		ManagerService managerservice=new ManagerServiceImpl();
		BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
		System.out.println("Enter Manager ID : ");
		String id2=br.readLine();
		System.out.println("Enter New Title : ");
		String title=br.readLine();
		managerservice.updateManagerTitle(id2, title);
	}
	
	public void updateEmail() throws IOException {
		ManagerService managerservice=new ManagerServiceImpl();
		BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
		System.out.println("Enter Manager ID : ");
		String id3=br.readLine();
		System.out.println("Enter New Email : ");
		String email=br.readLine();
		managerservice.updateManagerEmail(id3, email);
	}

	
	public void searchManager() throws IOException {
		ManagerService managerservice=new ManagerServiceImpl();
		BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
		System.out.println("Enter Manager ID : ");
		String mgrid=br.readLine();
		managerservice.getManagerById(mgrid);
	}
	
}


