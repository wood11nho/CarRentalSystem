package Model;

import java.sql.ResultSet;
import java.sql.Statement;

public class Rent {
    private DBObject dbObject;
    private String orderNumber;
    private String startDate;
    private String endDate;
    private int kilometers;
    private String personalId;
    private String carRegNumber;
    public Rent(DBObject dbObject, String startDate, String endDate, int kilometers, String personalId, String carRegNumber){
        this.dbObject = dbObject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.kilometers = kilometers;
        this.personalId = personalId;
        this.carRegNumber = carRegNumber;
        this.orderNumber = "ORD" + String.format("%05d", getTotalNumberOfRents() + 1);
    }

    public Rent(DBObject dbObject, String orderNumber, String startDate, String endDate, int kilometers, String personalId, String carRegNumber){
        this.dbObject = dbObject;
        this.orderNumber = orderNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.kilometers = kilometers;
        this.personalId = personalId;
        this.carRegNumber = carRegNumber;
    }

    public String getPersonalId(){
        return personalId;
    }

    public int getTotalNumberOfRents() {
        try {
            Statement statement = dbObject.getConnection().createStatement();
            String sql = "SELECT COUNT(*) FROM rents";
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }


    // Method for inserting a new rent in the database
    public void insertRentInDB(){
        try{
            Statement statement = dbObject.getConnection().createStatement();
            // I want to insert in rents table, on following fields: startDate, endDate, kilometers, personalId, carRegNumber, orderNumber.
            // The table has an id autoincrement field, so I don't need to insert it.
            String sql = "INSERT INTO rents(startDate, endDate, kilometers, persIdentifNo, regNo, orderNumber) VALUES('" + startDate + "', '" + endDate + "', " + kilometers + ", '" + personalId + "', '" + carRegNumber + "', '" + orderNumber + "')";
            statement.execute(sql);
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String toString(){
        return "Order number: " + orderNumber + ", start date: " + startDate + ", end date: " + endDate + ", kilometers: " + kilometers + ", personal id: " + personalId + ", car registration number: " + carRegNumber;
    }
}
