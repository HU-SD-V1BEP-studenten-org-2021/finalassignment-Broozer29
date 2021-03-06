package model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;


public class User implements Principal{
  private String userName;
  private String userPassword;
  private String role;
  private static List<User> userList = new ArrayList<User>();

  public User(String username, String password, String role) {
    this.userName = username;
    this.userPassword = password;
    this.role = role;
  }

  public static String validateLogin(String username, String password) {
    for (User u : userList) {
      if (u.getUserName().equals(username) && u.getUserPassword().equals(password)) {
        return u.getRole();
      }
    }
    return null;
  }

  public static boolean registerUser(User user) {
    boolean voegUserToe = true;
    for (User u : userList) {
      if (u.getUserName().equals(user.getUserName())) {
        voegUserToe = false;
      }
    }
    if (voegUserToe) {
      User.userList.add(user);
    }
    
    return voegUserToe;
  }
  
  public static User getSpecificUser(String username) {
    for (User u : userList) {
      if (u.getUserName().equals(username)) {
        return u;
      }
    }
    return null;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String getName() {
    return this.userName;
  }
}
