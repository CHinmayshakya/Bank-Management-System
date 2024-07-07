import java.sql.ResultSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SignUp {
    SignUp(){
        System.out.println("                        SIGNUP FORM");
        System.out.println("<-------------------------------------------------------------->");

        String name;
        long phoneNo;
        String city;
        int pin;
        int id;

        Scanner scan = new Scanner(System.in);
        System.out.println("Please Fill Up Your Details:");
        System.out.println("Enter your username: ");
        name = scan.nextLine();
        System.out.println("Enter your city name: ");
        city = scan.nextLine();
        System.out.println("Enter your phoneNo: ");
        phoneNo = scan.nextLong();
        scan.nextLine();


        Conn con = new Conn();
        try{
            ResultSet rs = con.st.executeQuery("select count(id) from details where phoneNo = '"+phoneNo+"';");
            rs.absolute(1);
            int check = rs.getInt("count(id)");

            if(check != 0){
                System.out.println("<-------------------------------------------------------------->");
                System.out.println("                     User Already Exist");
                System.out.println("<-------------------------------------------------------------->");
            }
            else{
                pin = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
                id =  ThreadLocalRandom.current().nextInt(1, 999 + 1);
                con.st.executeUpdate("insert into logIn values('"+id+"','"+name+"','"+pin+"');");
                con.st.executeUpdate("insert into details values('"+id+"','"+id+"','"+name+"','"+phoneNo+"','"+city+"');");
                con.st.executeUpdate("insert into accounts values('"+id+"','"+id+"','"+0+"');");
                System.out.println("<-------------------------------------------------------------->");
                System.out.println("                     Account Created");
                System.out.println("<-------------------------------------------------------------->");
                System.out.println("Your Account pin no is: " + pin);
                System.out.println("<-------------------------------------------------------------->");
                System.out.println("                     Please LogIn Again");
                System.out.println("<-------------------------------------------------------------->");
                con.con.close();
                new LogIn();
                scan.close();
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
