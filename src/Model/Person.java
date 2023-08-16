package Model;

import java.time.LocalDate;

public class Person {
    // It should contain the fields: personal identification number, first name, last name,
    // date of birth, gender, emitted year of driving license
    private String personalIdentificationNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int emittedYearOfDrivingLicense;

    // Constructor
    public Person(String personalIdentificationNumber, String firstName, String lastName, Gender gender, int emittedYearOfDrivingLicense) {
        this.personalIdentificationNumber = personalIdentificationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emittedYearOfDrivingLicense = emittedYearOfDrivingLicense;
    }

    // Getters and setters

    public String getPersonalIdentificationNumber() {
        return personalIdentificationNumber;
    }

    public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
        this.personalIdentificationNumber = personalIdentificationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getEmittedYearOfDrivingLicense() {
        return emittedYearOfDrivingLicense;
    }

    public void setEmittedYearOfDrivingLicense(int emittedYearOfDrivingLicense) {
        this.emittedYearOfDrivingLicense = emittedYearOfDrivingLicense;
    }

    // toString


    @Override
    public String toString() {
        return "Person{" +
                "personalIdentificationNumber='" + personalIdentificationNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", emittedYearOfDrivingLicense=" + emittedYearOfDrivingLicense +
                '}';
    }
}
