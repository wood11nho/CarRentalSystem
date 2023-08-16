package Model;

import java.util.List;

public interface IVehicleRentalSystem {
    void viewVehiclesSortedByYear();
    void viewVehiclesSortedByBrand();
    List<Vehicle> searchVehiclesByType(String type);
    void viewHistoryOfAllRents();
    void viewHistoryOfRentsByPersonalId(String personalId);
    void rentVehicle(String personalId, String carRegNumber, String startDate, String endDate, int kilometers);
    List<Vehicle> getAvailableVehicles();
    void getRentsFromDB();
}
