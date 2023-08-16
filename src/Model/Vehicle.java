package Model;

public class Vehicle {
    // It should contain the fields: registration number, type, manufacturing year, brand, color. Type must be an enum
    private String registrationNumber;
    private VehicleType type;
    private int manufacturingYear;
    private String brand;
    private String color;

    // Constructor
    public Vehicle(String registrationNumber, VehicleType type, int manufacturingYear, String brand, String color) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.brand = brand;
        this.color = color;
    }

    // Getters and setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // toString
    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", type=" + type +
                ", manufacturingYear=" + manufacturingYear +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
