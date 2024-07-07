import java.sql.ResultSet;
import java.util.Scanner;

public class Dashboard {
    Dashboard(String name){
        int choice;
        Scanner scan = new Scanner(System.in);
        Conn con = new Conn();

        System.out.println("                       Welcome " + name);
        System.out.println("<-------------------------------------------------------------->");
        System.out.println("1. Choose "+"1"+" to check balance");
        System.out.println("2. Choose "+"2"+" for deposit");
        System.out.println("3. Choose "+"3"+" for withdrawal");
        System.out.println("4. Choose "+"4"+" for Exit");
        System.out.println("<-------------------------------------------------------------->");

        while(true) {
            choice = scan.nextInt();
            scan.nextLine();
            if (choice == 1) {
                System.out.println("                          CHECK BALANCE");
                System.out.println("<-------------------------------------------------------------->");
                System.out.println("Enter your pin: ");
                int pin = scan.nextInt();
                scan.nextLine();
                try{
                    ResultSet rs = con.st.executeQuery("select balance from login,accounts where pin = '"+pin+"';");
                    rs.absolute(1);
                    System.out.println("<-------------------------------------------------------------->");
                    System.out.println("Your account balance is: " + rs.getInt("balance"));
                    System.out.println("<-------------------------------------------------------------->");
                }
                catch (Exception e){
                    System.out.println(e);
                }
                break;
            } else if (choice == 2) {
                System.out.println("                           DEPOSIT");
                System.out.println("<-------------------------------------------------------------->");
                System.out.println("Enter your pin: ");
                int pin = scan.nextInt();
                scan.nextLine();
                try{
                    ResultSet rs = con.st.executeQuery("select balance from login,accounts where pin = '"+pin+"';");
                    rs.absolute(1);
                    int balance = rs.getInt("balance");
                    System.out.println("Enter the amount you want to deposit: ");
                    int deposit = scan.nextInt();
                    scan.nextLine();
                    balance += deposit;
                    con.st.executeQuery("update accounts set balance = '"+balance+"' where id = (select id from logIn where pin = '"+pin+"');");

                    System.out.println("<-------------------------------------------------------------->");
                    System.out.println("                        DEPOSIT SUCCESSFUL");
                    ResultSet rs1 = con.st.executeQuery("select balance from login,accounts where pin = '"+pin+"';");
                    rs1.absolute(1);
                    System.out.println("<-------------------------------------------------------------->");
                    System.out.println("Your account balance is: " + rs1.getInt("balance"));
                    System.out.println("<-------------------------------------------------------------->");

                    con.con.close();
                }
                catch (Exception e){
                    System.out.println(e);
                }
                break;
            } else if (choice == 3) {
                System.out.println("                         WITHDRAWAL");
                System.out.println("<-------------------------------------------------------------->");
                System.out.println("Enter your pin: ");
                int pin = scan.nextInt();
                scan.nextLine();
                try{
                    ResultSet rs = con.st.executeQuery("select balance from login,accounts where pin = '"+pin+"';");
                    rs.absolute(1);
                    int balance = rs.getInt("balance");
                    System.out.println("Enter the amount you want to withdraw: ");
                    int withdraw = scan.nextInt();
                    scan.nextLine();
                    if(balance > withdraw) {
                        balance -= withdraw;
                        con.st.executeQuery("update accounts set balance = '" + balance + "' where id = (select id from logIn where pin = '" + pin + "');");

                        System.out.println("<-------------------------------------------------------------->");
                        System.out.println("                      WITHDRAWAL SUCCESSFUL");
                        ResultSet rs1 = con.st.executeQuery("select balance from login,accounts where pin = '"+pin+"';");
                        rs1.absolute(1);
                        System.out.println("<-------------------------------------------------------------->");
                        System.out.println("Your account balance is: " + rs1.getInt("balance"));
                        System.out.println("<-------------------------------------------------------------->");

                        con.con.close();
                    }
                    else{
                        System.out.println("Insufficient Balance");
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }
                break;
            } else if (choice == 4) {//Exit
                System.out.println("                   THANKS FOR VISITING");
                System.out.println("<-------------------------------------------------------------->");
                scan.close();
                return;
            } else {
                System.out.println("INVALID CH0ICE");
            }
            scan.close();
        }
    }
}
