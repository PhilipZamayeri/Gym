import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Philip Zamayeri
 * Date: 2020-10-09
 * Time: 13:45
 * Project: Gym
 * Copyright: MIT
 */
public class Gym {
    private Path customerPath = Paths.get("src/customers.txt");
    private Path newPath = Paths.get("src/Visitors");
    private List<Customer> customerList = new ArrayList<>();
    //private List<Customer> visitorList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);


    private void gymDemo() {
        setupCustomerList(customerPath);
        System.out.println("Ange namn eller personnummer: ");
        String userInput = input.nextLine();
        //checkPerson(userInput);
        System.out.println(checkIfMember(userInput));
        //printList();
    }

    public String checkIfMember(String userInput) {
        if (userInput == null || userInput.trim().equalsIgnoreCase("exit")) {
            System.out.println("Programmet avslutas.");
            System.exit(0);
        }

        Customer customer = checkPerson(userInput.trim());
        if (customer == null) {
            if (userInput.isBlank()) {
                return "Du måste mata in ett namn eller personnummer.";
            } else {
                return "Personen finns ej i systemet.";
            }
        } else {
            if (customer.getActiveMembership()) {
                printToFile(customer);
                return customer.getName() + " är medlem.";
                //visitorList.add(customer);

            } else {
                return customer.getName() + " är inte längre medlem.";
            }
        }
    }

/*
    private void printList(){
        for (Customer c : customerList)
            System.out.println(c.getName() + "\n " + c.getIdNr() + "\n " + c.getDateOfLastPayment() + "\n");
    }*/

    private Customer checkPerson(String userInput) {
        for (Customer c : customerList) {
            if (c.getName().equalsIgnoreCase(userInput) || userInput.equals(c.getIdNr())) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> setupCustomerList(Path path) {
        try (Scanner sc = new Scanner(new File(String.valueOf(path))).useDelimiter(",|\n")) {

            String idNr;
            String name;
            LocalDate membership;

            while (sc.hasNext()) {
                idNr = sc.next();
                name = sc.next().trim();
                membership = LocalDate.parse(sc.next().trim());
                sc.nextLine();
                customerList.add(new Customer(idNr, name, membership));

            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);

        } catch (NoSuchElementException e){
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return customerList;
    }

    private void printToFile(Customer customer) {
        try (BufferedWriter addToList = new BufferedWriter(new FileWriter(String.valueOf(newPath), true))) {

            addToList.write("Namn: " + customer.getName() + ", personnummer: "
                    + customer.getIdNr() + ", besökt: " + LocalDate.now() + "\n" + "\n");
            addToList.flush();
            //addToList.close();

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        Gym g = new Gym();
        g.gymDemo();

    }
}
