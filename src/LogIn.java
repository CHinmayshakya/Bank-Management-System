import java.sql.ResultSet;
import java.util.Scanner;

public class LogIn {
    public String name;
    LogIn() {
        Conn con = new Conn();
        int choice = -1;
        Scanner scan = new Scanner(System.in);
        System.out.println("                   WELCOME TO OUR BANK");
        while(true){

            System.out.println("<-------------------------------------------------------------->");
            System.out.println("1. Choose "+"1"+" for LogIn");
            System.out.println("2. Choose "+"2"+" for SignUp");
            System.out.println("3. Choose "+"3"+" for Exit");
            System.out.println("<-------------------------------------------------------------->");
            if (scan.hasNext()) {
                choice = scan.nextInt();
                scan.nextLine();
            }
            System.out.println("<-------------------------------------------------------------->");
            if(choice == 1){ //LogIn
                int pin;
                //Input
                System.out.println("                          LOGIN");
                System.out.println("<-------------------------------------------------------------->");
                System.out.println("Please Enter your username and pin:");
                System.out.println("Enter your username: ");
                name = scan.nextLine();
                System.out.println("Enter your pin: ");
                pin = scan.nextInt();
                scan.nextLine();
                //Verification
                boolean allLetters = name.chars().allMatch(Character::isLetter);
                if(pin < 1000 || pin > 9999  || !allLetters){
                    System.out.println("<-------------------------------------------------------------->");
                    System.out.println("INVALID INPUT");
                }
                else {
                    try{
                        ResultSet result = con.st.executeQuery("select name from logIn where pin = '"+pin+"';");
                        if(!result.isBeforeFirst()){
                            System.out.println("<-------------------------------------------------------------->");
                            System.out.println("Please create an account before logging In");
                            System.out.println("<-------------------------------------------------------------->");
                            continue;
                        }
                        else {
                            result.absolute(1);
                            String check = result.getString("name");
                            if (check.equalsIgnoreCase(name)) {
                                System.out.println("                       LOGGED IN");
                                System.out.println("<-------------------------------------------------------------->");
                                con.con.close();
                                new Dashboard(name);
                            }
                        }

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                }
            }
            else if (choice == 2) { //SignUp
                new SignUp();
                break;
            }
            else if (choice == 3) {//Exit
                System.out.println("THANKS FOR VISITING");
                scan.close();
                return;
            }
            else {
                System.out.println("INVALID CH0ICE");
            }

        }
        scan.close();
    }

    public static void main(String[] args) {
        new LogIn();
    }
}
