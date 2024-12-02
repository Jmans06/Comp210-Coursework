package assn07;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // infite loop to go back to "Enter master password"
        while (true){
            System.out.println("Enter Master Password");
            String passEnter=scanner.nextLine();
            if (passwordManager.checkMasterPassword(passEnter)){
                break;
            }
        }


        // loop to read and execute commands until "Exit" is entered
        while (true){
            String n= scanner.nextLine();
            if (n.equals("Exit")){
                break;
            }
            else if (n.equals("New password")){
                String K=scanner.nextLine();
                String V= scanner.nextLine();
                passwordManager.put(K, V);
                System.out.println("New password added");
            }
            else if (n.equals("Get password")){
                String K=scanner.nextLine();
                if (passwordManager.get(K)==null){
                    System.out.println("Account does not exist");
                }
                else{
                    System.out.println(passwordManager.get(K));
                }
            }
            else if (n.equals("Delete account")){
                String K=scanner.nextLine();
                if (passwordManager.get(K)==null){
                    System.out.println("Account does not exist");
                }
                else{
                    passwordManager.remove(K);
                    System.out.println("Account deleted");
                }
            }
            else if (n.equals("Check duplicate password")){
                String V=scanner.nextLine();
                if (passwordManager.checkDuplicates(V).isEmpty()){
                    System.out.println("No account uses that password");
                }
                else{
                    System.out.println("Websites using that password:");
                    for (String f:passwordManager.checkDuplicates(V)){
                        System.out.println(f);
                    }
                }
            }
            else if (n.equals("Get accounts")){
                System.out.println("Your accounts:");
                for (String f:passwordManager.keySet()){
                    System.out.println(f);
                }
            }
            else if (n.equals("Generate random password")){
                int num= scanner.nextInt();
                System.out.println(passwordManager.generatesafeRandomPassword(num));
            }
            else{
                System.out.println("Command not found");
            }
        }
    }
}
