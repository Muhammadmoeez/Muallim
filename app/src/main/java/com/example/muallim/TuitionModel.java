package com.example.muallim;

public class TuitionModel {

    String SchoolName;
    String ClassName;
    String NumberOfStudents;
    String City;
    String Location;
    String ContactNumber;
    String SecondContactNumber;
    String Gender;

    public TuitionModel() {
    }

    public TuitionModel(String schoolName, String className, String numberOfStudents, String city, String location, String contactNumber, String secondContactNumber, String gender) {
        SchoolName = schoolName;
        ClassName = className;
        NumberOfStudents = numberOfStudents;
        City = city;
        Location = location;
        ContactNumber = contactNumber;
        SecondContactNumber = secondContactNumber;
        Gender = gender;
    }


    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getNumberOfStudents() {
        return NumberOfStudents;
    }

    public void setNumberOfStudents(String numberOfStudents) {
        NumberOfStudents = numberOfStudents;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getSecondContactNumber() {
        return SecondContactNumber;
    }

    public void setSecondContactNumber(String secondContactNumber) {
        SecondContactNumber = secondContactNumber;
    }
}
