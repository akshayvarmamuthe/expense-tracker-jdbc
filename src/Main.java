import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            UserOperations user = new UserOperations();
        System.out.println("---------Expense Tracker---------");
        System.out.println("1. Add Expense\n2. Update Expense\n3. Delete Expense\n4. View All Expenses\n5. View Total Spendings\n6. View Expenses by Category\n7. Exit\n");
        Scanner sc = new Scanner(System.in);
        boolean flag=true;
        int choice=-1;
        while(flag){
            System.out.print("Enter choice: ");
            try {
                choice = sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid choice");
                sc.next();
                continue;
            }
            sc.nextLine();
            switch (choice)
            {
                case 1 -> {
                    System.out.print("Enter Amount: ");
                    try {
                        double amount = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter Category: ");
                        String category = sc.nextLine();
                        user.addExpense(amount, category);
                        System.out.println("Expense Added Successfully");
                    }
                    catch(InputMismatchException e){
                        System.out.println("Please enter a valid amount");
                        sc.nextLine();
                    }
                }
                case 2->{
                    try{
                        System.out.printf("%-5s %-15s %-10s%n","ID","Category","Amount");
                        user.viewAllExpenses();
                        System.out.print("Enter Expense ID to update: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Category to update: ");
                        String category = sc.nextLine();
                        System.out.print("Enter Amount to update: ");
                        double amount = sc.nextDouble();
                        int row=user.updateExpense(id, amount, category);
                        if(row>0) {
                            System.out.println("Expense Updated Successfully");
                        }
                        else{
                            System.out.println("Expense ID Not Found");
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Please enter a valid ID");
                        sc.nextLine();
                    }
                }
                case 3-> {
                    try {
                        System.out.printf("%-5s %-15s %-10s%n","ID","Category","Amount");
                        user.viewAllExpenses();
                        System.out.print("Enter Expense ID to delete: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        int row=user.deleteExpense(id);
                        if(row>0) {
                            System.out.println("Expense Deleted Successfully");
                        }
                        else{
                            System.out.println("Expense ID Not Found");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid ID");
                        sc.nextLine();
                    }
                }
                case 4->{
                    System.out.printf("%-5s %-15s %-10s%n","ID","Category","Amount");
                    user.viewAllExpenses();
                }
                case 5->{
                    System.out.print("Total Spendings: "+user.totalSpendings());
                    System.out.println();
                }
                case 6->{
                    System.out.print("Enter Category to search: ");
                    String category = sc.nextLine();
                    System.out.println("Spending on "+category+" : "+user.getCategorySpending(category));
                }
                case 7->{
                    System.out.println("Exitied...");
                    flag=false;
                }
                default -> {
                    System.out.println("Invalid Input");
                }
            }
        }

    }
}
