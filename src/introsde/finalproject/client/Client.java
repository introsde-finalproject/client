package introsde.finalproject.client;

import introsde.finalproject.processcentric.Service;
import introsde.finalproject.processcentric.ProcessCentricService;

import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
 
public class Client {
    private static Scanner in = new Scanner(System.in);
    
    private static Service processCentric = new ProcessCentricService().getServiceImplPort();

    private static final Long id = 5L;

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu(){
        clearScreen();
        for (String notification : processCentric.getUserNotifications(id)) {
        	System.out.println(notification);	
        }
        System.out.println("\n");
        System.out.println("1. Personal info");
        System.out.println("2. Current goals");
        System.out.println("3. Enter goal progress");
        System.out.println("\n0. Exit");
        System.out.println();
        
        boolean quit = false;
        int menuItem = 0;
        do {
            try {
                System.out.println("Choose menu item: ");
                menuItem = in.nextInt();       
            } catch(Exception e) {
                in.nextLine();
                System.out.println("Invalid choice. Enter correct number.");
                timePauseAndClearScreen();
                mainMenu();
            }
            switch (menuItem) {
            case 1:
                clearScreen();
                personalDataMenu();
                break;
            case 2:
                clearScreen();
                currentGoalMenu();
                break;
            case 3:
                clearScreen();
                goalProgressMenu();
                break;
            case 0:
                exitMenu();
                quit = true;
                break;
            default:
                System.out.println("Invalid choice. Enter correct number.");
                clearScreen();
                mainMenu();
                break;
            }
        } while (!quit);
    }

    public static void personalDataMenu(){
    	List<String> userInfo = processCentric.getUserInfo(id);
    	for (String elem : userInfo) {
    		System.out.println(elem);
    	}
        
        System.out.println();
        System.out.println("1. Update personal info");
        System.out.println("2. Update measures");
        System.out.println("3. Measure history");
        System.out.println();
        System.out.println("0. Back to previous menu");

        int menuItem = 0;
        try {
            System.out.println("\nChoose menu item: ");
            menuItem = in.nextInt();       
        } catch(Exception e) {
            in.nextLine();
            clearScreen();
            personalDataMenu();
        }

		if (menuItem == 1){
		    clearScreen();
		    updateUserInfoMenu();
		} else  if (menuItem == 2){
		    clearScreen();
		    updatePersonMeasuresMenu();
		} else  if (menuItem == 3){
		    clearScreen();
		    measureHistoryMenu();
		} else  if (menuItem == 0){
		    clearScreen();
		    mainMenu();
		} else {
		    clearScreen();
		    personalDataMenu();
		}
    }

    public static void updateUserInfoMenu(){
        System.out.println();
        System.out.println("1. Change first name");
        System.out.println("2. Change last name");
        System.out.println("3. Change birthdate");
        System.out.println();
        System.out.println("0. Back to previous menu");

        int menuItem = 0;
        try {
            System.out.println("\nChoose menu item: ");
            menuItem = in.nextInt();   
            in.nextLine();
        } catch(Exception e) {
            in.nextLine();
            clearScreen();
            updateUserInfoMenu();
        }

        if (menuItem == 1){
            String firstname = "";
            while (!firstname.matches("[a-zA-Z]+")) {
                System.out.println("Input new first name. It can contain only letters.");
                firstname = in.nextLine();
            }
            System.out.println();
            System.out.println(processCentric.updateUserInfo(id, "firstname", firstname));
            timePauseAndClearScreen();
            personalDataMenu();
        } else if (menuItem == 2){
            String lastname = "";
            while (!lastname.matches("[a-zA-Z]+")){
                System.out.println("Input new last name. It can contain only letters.");
                lastname = in.nextLine();
            }
            System.out.println();
            System.out.println(processCentric.updateUserInfo(id, "lastname", lastname));
            timePauseAndClearScreen();
            personalDataMenu();
        } else  if (menuItem == 3){
            String birthdate = "";
            while (!birthdate.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
                System.out.println("Input new birthdate. Format: dd/MM/yyyy");
                birthdate = in.nextLine();
            }
            System.out.println();
            System.out.println(processCentric.updateUserInfo(id, "birthdate", birthdate));
            timePauseAndClearScreen();
            personalDataMenu();
        } else  if (menuItem == 0){
            clearScreen();
            personalDataMenu();
        } else {
            clearScreen();
            updateUserInfoMenu();
        }
    }

    public static void updatePersonMeasuresMenu(){
        System.out.println();
        System.out.println("1. Change height");
        System.out.println("2. Change weight");
        System.out.println();
        System.out.println("0. Back to previous menu");  

        int menuItem = 0;
        try {
            System.out.println("\nChoose menu item: ");
            menuItem = in.nextInt();      
            in.nextLine(); 
        } catch(Exception e) {
            in.nextLine();
            clearScreen();
            updatePersonMeasuresMenu();
        }
        
        boolean success = false;
        if (menuItem == 1) {
            while(!success) {
                try{
                    System.out.println("Input height:");
                    double height = in.nextDouble();
                    in.nextLine();
                    System.out.println();
                    System.out.println(processCentric.updateUserInfo(id, "height", String.valueOf(height)));
                    timePauseAndClearScreen();
                    personalDataMenu();
                    success = true;
                } catch(Exception e) {
                    in.nextLine();
                    System.out.println("Invalid data format. Please try again.");
                }
            }
        } else if (menuItem == 2) {
            success = false;
            while(!success){
                try{
                    System.out.println("Input new weight:");
                    double weight = in.nextDouble(); 
                    in.nextLine();
                    System.out.println();
                    System.out.println(processCentric.updateUserInfo(id, "weight", String.valueOf(weight)));
                    timePauseAndClearScreen();
                    personalDataMenu();
                    success = true;
                } catch(Exception e) {
                    in.nextLine();
                    System.out.println("Invalid data format. Please try again.");
                }
            }
        } else if (menuItem == 0) {
            clearScreen();
            personalDataMenu();
        } else {
            clearScreen();
            updatePersonMeasuresMenu();
        }         
    }

    public static void measureHistoryMenu() {
        for (String history : processCentric.getUserMeasureHistory(id)) {
            System.out.println(history);
        }
        
        System.out.println();
        System.out.println("0. Back to previous menu");
        System.out.println("\nChoose menu item: ");
        int menuItem = 0;
        try {
            menuItem = in.nextInt();       
            in.nextLine();
        } catch(Exception e) {
            in.nextLine();
            clearScreen();
            measureHistoryMenu();
        }

        if (menuItem == 0) {
            clearScreen();
            personalDataMenu();
        } else {
            clearScreen();
            measureHistoryMenu();
        } 
    }

    public static void currentGoalMenu(){
        List<String> goalsList = processCentric.getUserGoals(id);
        HashMap<String,String> goals = new HashMap<>();
        for (String goalString : goalsList) {
            goals.put(goalString.split(":")[0], goalString.split(":")[1]);
        }

        System.out.println("Details about current goals:");
        System.out.println();
        System.out.println("Steps: " + goals.get("steps"));
        System.out.println("Sleeping hours: " + goals.get("sleep"));
        System.out.println("Water: " + goals.get("water"));
        System.out.println();

        System.out.println("1. Edit goal");
        System.out.println("2. Delete goal");
        System.out.println();
        System.out.println("0. Back to previous menu");  

        int menuItem = 0;
        try {
            System.out.println("\nChoose menu item: ");
            menuItem = in.nextInt();   
            in.nextLine();    
        } catch(Exception e) {
            in.nextLine();
            clearScreen();
            currentGoalMenu();
        }
        boolean success = false;
        if (menuItem == 1){
            editGoalMenu();
        } else  if (menuItem == 2){
            deleteGoalMenu();
        } else  if (menuItem == 0){
            clearScreen();
            mainMenu();
        } else {
            clearScreen();
            currentGoalMenu();
        }         
    }

    public static void editGoalMenu(){
        clearScreen();
        System.out.println("Which goal do you want to edit?");
        System.out.println();
        System.out.println("1. Steps per day");
        System.out.println("2. Sleeping hours per day");
        System.out.println("3. Liters of water per day");
        System.out.println();
        System.out.println("0. Back to previous menu");

        int menuItem = 0;
        try {
            System.out.println("\nChoose menu item: ");
            menuItem = in.nextInt();   
            in.nextLine();    
        } catch(Exception e) {
            in.nextLine();
            clearScreen();
            editGoalMenu();
        }

        if (menuItem == 1){
            boolean success = false;
            while(!success){
                try{
                    System.out.println("Please, input new amount of steps that you want to walk daily:");
                    int steps = in.nextInt();
                    in.nextLine();
                    System.out.println();
                    System.out.println(processCentric.updateUserGoal(id, "steps", String.valueOf(steps)));
                    timePauseAndClearScreen();
                    currentGoalMenu();
                    success = true;
                } catch(Exception e) {
                    in.nextLine();
                    System.out.println("Invalid data format. Please try again.");
                }
            }
        } else  if (menuItem == 2){
            boolean success = false;
            while(!success) {
                try{
                    System.out.println("Please, input new amount of hours that you want to sleep daily:");
                    double hours = in.nextDouble();
                    in.nextLine();
                    System.out.println();
                    System.out.println(processCentric.updateUserGoal(id, "sleep", String.valueOf(hours)));
                    timePauseAndClearScreen();
                    currentGoalMenu();
                    success = true;
                } catch(Exception e) {
                    in.nextLine();
                    System.out.println("Invalid data format. Please try again.");
                }
            }
        } else  if (menuItem == 3){
            boolean success = false;
            while(!success){
                try{
                    System.out.println("Please, input new amount of litres that you want to drink daily:");
                    double litres = in.nextDouble();
                    in.nextLine();
                    System.out.println();
                    System.out.println(processCentric.updateUserGoal(id, "water", String.valueOf(litres)));
                    timePauseAndClearScreen();
                    currentGoalMenu();
                    success = true;
                } catch(Exception e) {
                    in.nextLine();
                    System.out.println("Invalid data format. Please try again.");
                }
            }
        } else  if (menuItem == 0){
            clearScreen();
            currentGoalMenu();
        } else {
            clearScreen();
            editGoalMenu();
        }
    }

    public static void deleteGoalMenu(){
        clearScreen();
        System.out.println("Which goal do you want to delete?");
        System.out.println();
        System.out.println("1. Steps per day");
        System.out.println("2. Sleeping hours per day");
        System.out.println("3. Liters of water per day");
        System.out.println();
        System.out.println("0. Back to previous menu");

        int menuItem = 0;
        try {
            System.out.println("\nChoose menu item: ");
            menuItem = in.nextInt();   
            in.nextLine();    
        } catch(Exception e) {
            in.nextLine();
            deleteGoalMenu();
        }

        if (menuItem == 1){
            System.out.println();
            System.out.println(processCentric.deleteUserGoal(id, "steps"));
            timePauseAndClearScreen();  
            currentGoalMenu();
        } else  if (menuItem == 2){
            System.out.println();
            System.out.println(processCentric.deleteUserGoal(id, "sleep"));
            timePauseAndClearScreen();  
            currentGoalMenu();
        } else  if (menuItem == 3){
            System.out.println();
            System.out.println(processCentric.deleteUserGoal(id, "water"));
            timePauseAndClearScreen();  
            currentGoalMenu();
        } else  if (menuItem == 0){
            clearScreen();
            currentGoalMenu();
        } else {
            clearScreen();
            deleteGoalMenu();
        }
    }
 
    public static void goalProgressMenu(){
        clearScreen();
        System.out.println("Please, choose the goal");
        System.out.println();
        System.out.println("1. Steps per day");
        System.out.println("2. Sleeping hours per day");
        System.out.println("3. Liters of water per day");
        System.out.println();
        System.out.println("0. Back to previous menu");
        System.out.println();

        List<String> goalsList = processCentric.getUserGoals(id);
        HashMap<String,String> goals = new HashMap<>();
        for (String goal : goalsList) {
            goals.put(goal.split(":")[0], goal.split(":")[1]);
        }

        int menuItem = 0;
        boolean chosen = false;
        while(!chosen) {
            try {
                System.out.println("Choose menu item: ");
                menuItem = in.nextInt();       
                in.nextLine();
                if (menuItem == 1 && goals.get("steps").equals("n/a") || 
                    menuItem == 2 && goals.get("sleep").equals("n/a") ||
                    menuItem == 3 && goals.get("water").equals("n/a")) {

                    System.out.println("Goal is not defined, please choose correct goal.");
                } else {
                    chosen = true;
                }
            } catch(Exception e) {
                in.nextLine();
                goalProgressMenu();
            }    
        }

        boolean success = false;
        if (menuItem == 1){
            while(!success){
                try{
                    System.out.println("Please, insert amount of steps that you walked today:");
                    int steps = in.nextInt();
                    in.nextLine();
                    System.out.println();
                    System.out.println(processCentric.updateGoalProgress(id, "steps", String.valueOf(steps)));
                    timePauseAndClearScreen();
                    goalProgressMenu();
                    success = true;
                } catch(Exception e) { 
                    in.nextLine();
                    System.out.println("Invalid data format. Please try again.");
                }
            }
        } else  if (menuItem == 2){
            while(!success){
                try{
                    System.out.println("Please, input new amount of hours that you slept today:");
                    double hours = in.nextDouble();
                    in.nextLine();
                    System.out.println();
                    System.out.println(processCentric.updateGoalProgress(id, "sleep", String.valueOf(hours)));
                    timePauseAndClearScreen();
                    goalProgressMenu();
                    success = true;
                } catch(Exception e) {
                    in.nextLine();
                    System.out.println("Invalid data format. Please try again.");
                }
            }
        } else  if (menuItem == 3){
            while(!success){
                try{
                    System.out.println("Please, input new amount of litres that you drank today:");
                    double litres = in.nextDouble();
                    in.nextLine();
                    System.out.println();
                    System.out.println(processCentric.updateGoalProgress(id, "water", String.valueOf(litres)));
                    timePauseAndClearScreen();
                    goalProgressMenu();
                    success = true;
                } catch(Exception e) { 
                    in.nextLine();
                    System.out.println("Invalid data format. Please try again.");
                }
            }
        } else  if (menuItem == 0){
            clearScreen();
            mainMenu();
        } else {
            clearScreen();
            goalProgressMenu();
        }  
    }

    public static void exitMenu(){
        System.out.println("Are you sure that you want to exit? y/n");
        char charChoise = in.next().charAt(0);
        if (charChoise == 'y'){
            clearScreen();
            System.exit(0);
        } else  if (charChoise == 'n'){
            clearScreen();
            mainMenu();
        } else {
            exitMenu();
        }
    }

    public static void timePauseAndClearScreen(){
        timePause();
        clearScreen();
    }

    public static void timePause(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ie) {

        } 
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
    }
}