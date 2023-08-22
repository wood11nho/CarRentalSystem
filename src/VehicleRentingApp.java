import Model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class VehicleRentingApp{
    // Declare scanner
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        // Initialize the vehicle rental system
        VehicleRentalSystem vehicleRentalSystem = VehicleRentalSystem.getInstance();
        // Read the data from files
        readCarsFromFile("vehicles.txt");
        readPeopleFromFile("persoane.txt");
        // Show the menu
        do{
            System.out.println("1. View vehicles sorted by year");
            System.out.println("2. View vehicles sorted by brand");
            System.out.println("3. Search vehicles by type");
            System.out.println("4. View history of all rents");
            System.out.println("5. View history of rents by personal id");
            System.out.println("6. Rent a vehicle");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    vehicleRentalSystem.viewVehiclesSortedByYear();
                    break;
                case 2:
                    vehicleRentalSystem.viewVehiclesSortedByBrand();
                    break;
                case 3:
                    String type = getTypeFromKeyBoard();
                    vehicleRentalSystem.searchVehiclesByType(type).forEach(System.out::println);
                    break;
                case 4:
                    vehicleRentalSystem.viewHistoryOfAllRents();
                    break;
                case 5:
                    System.out.println("Enter personal id: ");
                    String personalId = scanner.nextLine();
                    vehicleRentalSystem.viewHistoryOfRentsByPersonalId(personalId);
                    break;
                case 6:
                    System.out.println("Enter personal id: ");
                    String personalId1 = scanner.nextLine();
                    System.out.println("The available cars are: ");
                    vehicleRentalSystem.getAvailableVehicles().forEach(System.out::println);
                    System.out.println("Enter registration number of the car you want to rent: ");
                    String registrationNumber = scanner.nextLine();
                    System.out.println("Enter start date: ");
                    String startDate = scanner.nextLine();
                    System.out.println("Enter end date: ");
                    String endDate = scanner.nextLine();
                    System.out.println("Enter kilometers: ");
                    int kilometers = scanner.nextInt();
                    vehicleRentalSystem.rentVehicle(personalId1, registrationNumber, startDate, endDate, kilometers);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while(true);
    }

    public static String getTypeFromKeyBoard(){
        String type;
        System.out.println("Enter type of vehicle: ");
        do{
            type = scanner.nextLine();
            for (VehicleType vehicleType : VehicleType.values()) {
                if (vehicleType.toString().equals(type)) {
                    return type;
                }
            }
            System.out.println("Invalid type. Try again: ");
        }
        while(true);
    }

    public static void readPeopleFromFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                String personalIdentificationNumber = values[0].trim();
                String lastName = values[1].trim();
                String firstName = values[2].trim();
                String gender = values[3].trim().toUpperCase();
                int emittedYearOfDrivingLicense = Integer.parseInt(values[4].trim());
                Person auxPerson = new Person(personalIdentificationNumber, firstName, lastName, Gender.valueOf(gender), emittedYearOfDrivingLicense);
                VehicleRentalSystem.getInstance().addPerson(auxPerson);
//                System.out.println(auxPerson);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCarsFromFile(String fileName){
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                String registrationNumber = values[0].trim();
                String vehicleType = values[1].trim();
                String brand = values[2].trim();
                int manufacturingYear = Integer.parseInt(values[3].trim());
                String color = values[4].trim();
                Vehicle auxVehicle = new Vehicle(registrationNumber, VehicleType.valueOf(vehicleType), manufacturingYear, brand, color);
                VehicleRentalSystem.getInstance().addVehicle(auxVehicle);
//                System.out.println(auxVehicle);
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}