package com.example.muallim;

public class TeacherModel {

    String ProfileImageURLTeacher;
    String UserName;
    String Email;
    String Password;
    String ConfirmPassword;
    String SecondContactNumber;
    String ContactNumber;
    String Address;
    String Age;
    String CNIC;
    String Qualification;
    String SubjectsYouCan;
    String Experience;
    String PreferableTeachingArea;
    String ClassYouCanTeach;
    String Gender;
    String HowDidYouFindUs;
    String Route;
    String Role;
    String Description;

    public TeacherModel() {
    }

    public TeacherModel(String profileImageURLTeacher, String userName, String email, String password, String confirmPassword, String secondContactNumber, String contactNumber, String address, String age, String CNIC, String qualification, String subjectsYouCan, String experience, String preferableTeachingArea, String classYouCanTeach, String gender, String howDidYouFindUs, String route, String role, String description) {
        ProfileImageURLTeacher = profileImageURLTeacher;
        UserName = userName;
        Email = email;
        Password = password;
        ConfirmPassword = confirmPassword;
        SecondContactNumber = secondContactNumber;
        ContactNumber = contactNumber;
        Address = address;
        Age = age;
        this.CNIC = CNIC;
        Qualification = qualification;
        SubjectsYouCan = subjectsYouCan;
        Experience = experience;
        PreferableTeachingArea = preferableTeachingArea;
        ClassYouCanTeach = classYouCanTeach;
        Gender = gender;
        HowDidYouFindUs = howDidYouFindUs;
        Route = route;
        Role = role;
        Description = description;
    }

    public String getProfileImageURLTeacher() {
        return ProfileImageURLTeacher;
    }

    public void setProfileImageURLTeacher(String profileImageURLTeacher) {
        ProfileImageURLTeacher = profileImageURLTeacher;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getSecondContactNumber() {
        return SecondContactNumber;
    }

    public void setSecondContactNumber(String secondContactNumber) {
        SecondContactNumber = secondContactNumber;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getSubjectsYouCan() {
        return SubjectsYouCan;
    }

    public void setSubjectsYouCan(String subjectsYouCan) {
        SubjectsYouCan = subjectsYouCan;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getPreferableTeachingArea() {
        return PreferableTeachingArea;
    }

    public void setPreferableTeachingArea(String preferableTeachingArea) {
        PreferableTeachingArea = preferableTeachingArea;
    }

    public String getClassYouCanTeach() {
        return ClassYouCanTeach;
    }

    public void setClassYouCanTeach(String classYouCanTeach) {
        ClassYouCanTeach = classYouCanTeach;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getHowDidYouFindUs() {
        return HowDidYouFindUs;
    }

    public void setHowDidYouFindUs(String howDidYouFindUs) {
        HowDidYouFindUs = howDidYouFindUs;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
