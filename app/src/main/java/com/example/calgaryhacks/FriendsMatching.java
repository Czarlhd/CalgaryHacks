package com.example.calgaryhacks;

import java.util.ArrayList;

public class FriendsMatching {
    private ArrayList<UserHelperClass> users;
    private UserHelperClass currUser;
    private ArrayList<UserHelperClass> friends;

    public FriendsMatching(UserHelperClass u, ArrayList<UserHelperClass> users) {
        this.currUser = u;
        this.users=users;
    }

    public ArrayList<UserHelperClass> coursesMatching() {
        ArrayList<String> courseListUsers = new ArrayList<>();
        ArrayList<String> courseListCurrUser = currUser.getCourses();
        ArrayList<UserHelperClass> userToReturn = new ArrayList<UserHelperClass>();


        for (int i = 0; i < users.size(); i++) {
            int count = 0;
            for (int j = 0; j < courseListCurrUser.size(); j++) {
                if (courseListUsers.contains(courseListCurrUser.get(j))) {
                    userToReturn.add(users.get(i));
                    count++;
                }
            }

        }
        return userToReturn;
    }
    public ArrayList<UserHelperClass> majorMatching () {
        ArrayList<UserHelperClass> usersToReturn = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMajor().equals(currUser.getMajor())) {
                usersToReturn.add(users.get(i));
            }
        }
        return usersToReturn;
    }

    public ArrayList<UserHelperClass> yearMatching () {
        ArrayList<UserHelperClass> usersToReturn = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getYear().equals(currUser.getYear())) {
                usersToReturn.add(users.get(i));
            }
        }
        return usersToReturn;
    }
    public ArrayList<ArrayList<UserHelperClass>> noFilterMatching () {
        ArrayList<ArrayList<UserHelperClass>> usersToReturn = new ArrayList<>();
        usersToReturn.add(majorMatching());
        usersToReturn.add(coursesMatching());
        usersToReturn.add(yearMatching());
        return usersToReturn;
    }

    public void addFriends (UserHelperClass friend){
        this.friends.add(friend);
    }

    public void removeFriends (UserHelperClass friend) {this.friends.remove(friend);}

    public ArrayList<UserHelperClass> getFriends () {
        return friends;
    }

    public ArrayList<UserHelperClass> getUsers () {
        return users;
    }
}

