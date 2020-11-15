package com.akib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final CarList carList = new CarList();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadDatabase();
        handleProgram();
        writeDatabase();
        scanner.close();
    }

    private static void handleProgram(){
        boolean quit = false;
        while(!quit){
            printInstructions();
            if (scanner.hasNextInt()){
                int input = scanner.nextInt();
                scanner.nextLine();
                switch (input){
                    case 1:
                        handleSubprogram();
                        break;
                    case 2:
                        addCar();
                        break;
                    case 3:
                        deleteCar();
                        break;
                    case 4:
                        quit = true;
                        break;
                    default:
                        System.out.println("please enter number between 1-4");
                        break;
                }
            }else{
                System.out.println("The program only takes numbers as input please enter a number");
                scanner.nextLine();
            }
        }

    }

    private static void deleteCar() {
        System.out.println("   Enter registration number of the car:");
        System.out.print("   ");
        String registrationNumber = scanner.nextLine();
        if (carList.deleteCar(registrationNumber)){
            System.out.println("   Car removed from the database successfully");
        }else{
            System.out.println("   Car could not be removed from the database" +
                    "\n   Check your registration number and try again");
        }

    }

    private static void addCar() {
        System.out.println("   Enter registration number of the car:");
        System.out.print("   ");
        String registrationNumber = scanner.nextLine();
        System.out.println("   Enter year of manufacture:");
        System.out.print("   ");
        while (!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println("   Please enter a valid year");
            System.out.print("   ");
        }
        int yearMade = scanner.nextInt();
        scanner.nextLine();
        String [] colors = new String[3];
        System.out.println("   Enter primary color of the car:");
        System.out.print("   ");
        colors[0] = scanner.nextLine();
        System.out.println("   Enter secondary color of the car(if any):");
        System.out.print("   ");
        colors[1] = scanner.nextLine();
        System.out.println("   Enter third color of the car (if any):");
        System.out.print("   ");
        colors[2] = scanner.nextLine();
        System.out.println("   Enter the manufacturer of the car:");
        System.out.print("   ");
        String manufacture = scanner.nextLine();
        System.out.println("   Enter model of the car:");
        System.out.print("   ");
        String model = scanner.nextLine();
        System.out.println("   Enter price of the car:");
        System.out.print("   ");
        while(!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println("   Please enter a valid price");
            System.out.print("   ");
        }
        int price = scanner.nextInt();
        scanner.nextLine();
        Car car = new Car(registrationNumber,yearMade,colors,manufacture,model,price);
        if (carList.addCar(car)){
            System.out.println("   Car added to the database successfully!");
        }else{
            System.out.println("   Car could not added to the database");
        }
    }

    private static void handleSubprogram() {
        boolean quit = false;
        while(!quit){
            printSubmenuInstructions();
            System.out.print("   ");
            if (scanner.hasNextInt()){
                int input = scanner.nextInt();
                scanner.nextLine();
                switch (input){
                    case 1:
                        searchByRegNumber();
                        break;
                    case 2:
                        searchByManufacturer();
                        break;
                    case 3:
                        quit = true;
                        break;
                    default:
                        System.out.println("please enter number between 1-3");
                        break;
                }
            }else{
                System.out.println("The program only takes numbers as input please enter a number");
                scanner.nextLine();
            }
        }
    }

    private static void searchByManufacturer() {
        System.out.println("     Enter the manufacturer of the car:");
        System.out.print("     ");
        String manufacturer = scanner.nextLine();
        System.out.println("     Enter the model of the car:\n" +
                "     Options: a.Exact model(e.g. 'Corolla')         b.any(to view all models)");
        System.out.print("     ");
        String model = scanner.nextLine();
        List<Car> cars = carList.searchCar(manufacturer,model);
        for (Car c : cars){
            System.out.println("     " + c.toString());
        }
    }

    private static void searchByRegNumber() {
        System.out.println("     Enter the registration number of the car");
        System.out.print("     ");
        String regNumber = scanner.nextLine();
        Car car = carList.searchCar(regNumber);
        if (car != null){
            System.out.println("     Found the car. Car details:");
            System.out.println("     " + car.toString());
            return;
        }
        System.out.println("     Could not find the car you are looking for\n" +
                "     Check your registration number and try again");
    }

    private static void printSubmenuInstructions() {
        System.out.println("   What do you want to do? please select accordingly\n" +
                "   1->Search by registration number\n" +
                "   2->Search by manufacturer & model\n" +
                "   3->Exit to main menu" );
    }

    private static void printInstructions(){
        System.out.println("What do you want to do? please select accordingly\n" +
                "1->Search car\n" +
                "2->Add car\n" +
                "3->Delete car\n" +
                "4->Exit the program" );
    }

    private static void loadDatabase(){
        try(BufferedReader input = Files.newBufferedReader(Path.of("cars.txt"))){
            String line;
            while ((line = input.readLine()) != null){
                String [] fields = line.split(",");
                String registrationNumber = fields[0];
                int yearMade = Integer.parseInt(fields[1]);
                String [] colors = new String[3];
                System.arraycopy(fields, 2, colors, 0, 3);
                String manufacture = fields[5];
                String model = fields[6];
                int price = Integer.parseInt(fields[7]);
                Car car = new Car(registrationNumber,yearMade,colors,manufacture,model,price);
                carList.addCar(car);

            }
        }catch (IOException e){
            System.out.println("Unable to read file, Please validate the database file");
        }
    }
    private static void writeDatabase(){
        try(BufferedWriter output = Files.newBufferedWriter(Path.of("cars.txt"))){
            for (Car car: carList.getCars()){
                String line = car.toString();
                output.write(line);
                output.newLine();
            }
        }catch (IOException e){
            System.out.println("Unable to read file, Please validate the database file");
        }
    }
}
