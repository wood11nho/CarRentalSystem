package Model;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VehicleRentalSystem implements IVehicleRentalSystem {
    private static VehicleRentalSystem instance = null;
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Rent> rents = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private DBObject dbObject = new DBObject();

    private VehicleRentalSystem() {
        getRentsFromDB();
    }

    public static VehicleRentalSystem getInstance() {
        if (instance == null) {
            instance = new VehicleRentalSystem();
        }
        return instance;
    }

    @Override
    public void viewVehiclesSortedByYear() {
        System.out.println("Vehicles sorted by year:");
        vehicles.stream().sorted((v1, v2) -> v2.getManufacturingYear() - v1.getManufacturingYear()).forEach(System.out::println);
    }

    @Override
    public void viewVehiclesSortedByBrand() {
        System.out.println("Vehicles sorted by brand:");
        vehicles.stream().sorted(Comparator.comparing(Vehicle::getBrand)).forEach(System.out::println);
    }

    @Override
    public List<Vehicle> searchVehiclesByType(String type) {
        return vehicles.stream().filter(v -> v.getType().toString().equals(type)).toList();
    }

    @Override
    public void viewHistoryOfAllRents() {
        System.out.println("All rents:");
        for (Rent rent : rents) {
            System.out.println(rent);
        }
    }

    @Override
    public void viewHistoryOfRentsByPersonalId(String personalId) {
        System.out.println("Rents for personal id " + personalId + ":");
        for (Rent rent : rents) {
            if (rent.getPersonalId().equals(personalId)) {
                System.out.println(rent);
            }
        }
    }

    @Override
    public void rentVehicle(String personalId, String carRegNumber, String startDate, String endDate, int kilometers) {
        Rent rent = new Rent(dbObject, startDate, endDate, kilometers, personalId, carRegNumber);
        rents.add(rent);
        rent.insertRentInDB();
    }
    public void addVehicle(Vehicle vehicle){

        vehicles.add(vehicle);
    }

    public void addPerson(Person person){
        persons.add(person);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            boolean isAvailable = true;
            try{
                Statement statement = dbObject.getConnection().createStatement();
                String sql = "SELECT * FROM rents WHERE regNo = '" + vehicle.getRegistrationNumber() + "'";
                statement.execute(sql);
                if(statement.getResultSet().next()){
                    isAvailable = false;
                }
            } catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
            if(isAvailable){
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    @Override
    public void getRentsFromDB(){
        try{
            Statement statement = dbObject.getConnection().createStatement();
            String sql = "SELECT * FROM rents";
            statement.execute(sql);
            while(statement.getResultSet().next()){
                String startDate = statement.getResultSet().getString("startDate");
                String endDate = statement.getResultSet().getString("endDate");
                int kilometers = statement.getResultSet().getInt("kilometers");
                String personalId = statement.getResultSet().getString("persIdentifNo");
                String carRegNumber = statement.getResultSet().getString("regNo");
                String orderNumber = statement.getResultSet().getString("orderNumber");
                Rent rent = new Rent(dbObject, orderNumber, startDate, endDate, kilometers, personalId, carRegNumber);
//                System.out.println(rent);
                rents.add(rent);
            }
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
