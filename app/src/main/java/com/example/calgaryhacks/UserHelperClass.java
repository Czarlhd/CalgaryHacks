package com.example.calgaryhacks;

import java.util.ArrayList;

public class UserHelperClass {
    String username_mcgill, password_mcgill, username_fb, password_fb;
    String major, year, name;
    ArrayList<String> courses;

    public UserHelperClass(String username_mcgill, String password_mcgill, String username_fb, String password_fb) {
        this.username_mcgill = username_mcgill;
        this.password_mcgill = password_mcgill;
        this.username_fb = username_fb;
        this.password_fb = password_fb;
    }

    public  UserHelperClass(String name, ArrayList<String> c, String m, String y){
        this.major=m;
        this.year=y;
        this.name=name;
        this.courses=c;
    }

    public UserHelperClass() {
    }

    public String getMajor(){
        return this.major;
    }

    public String getYear(){ return this.year;}

    public ArrayList<String> getCourses(){ return this.courses;}

    public String getUsername_mcgill() {
        return username_mcgill;
    }

    public void setUsername_mcgill(String username_mcgill) {
        this.username_mcgill = username_mcgill;
    }

    public String getPassword_mcgill() {
        return password_mcgill;
    }

    public void setPassword_mcgill(String password_mcgill) {
        this.password_mcgill = password_mcgill;
    }

    public String getUsername_fb() {
        return username_fb;
    }

    public void setUsername_fb(String username_fb) {
        this.username_fb = username_fb;
    }

    public String getPassword_fb() {
        return password_fb;
    }

    public void setPassword_fb(String password_fb) {
        this.password_fb = password_fb;
    }
}
