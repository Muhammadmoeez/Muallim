package com.example.muallim;

public class AdminModel {

    String ConfirmPassword;
    String ContactNumber;
    String  Email;
    String Name;
    String Password;
    String ProfileImageURL;
    String Role;

    public AdminModel() {
    }

    public AdminModel(String confirmPassword, String contactNumber, String email, String name, String password, String profileImageURL, String role) {
        ConfirmPassword = confirmPassword;
        ContactNumber = contactNumber;
        Email = email;
        Name = name;
        Password = password;
        ProfileImageURL = profileImageURL;
        Role = role;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getProfileImageURL() {
        return ProfileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        ProfileImageURL = profileImageURL;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
