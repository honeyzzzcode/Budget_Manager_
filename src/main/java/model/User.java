package model;

import java.sql.Timestamp;



public class User {



  public Integer getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getUserName() {
    return userName;
  }
  public String getPassword() {
    return password;
  }

  public String getPhone() {
    return phone;
  }
  public String getEmail() {
    return email;
  }
  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }
  public Float getBudget() {
    return budget;
  }
  Integer id;
  String name;
  String userName;
  String password;
  Float  budget;
  String phone;
  String email;
  Timestamp createdAt;
  Timestamp updatedAt;

  public User(String name, String userName, String password, Float budget,String phone, String email) {
    this.name = name;
    this.userName = userName;
    this.password = password;
    this.budget = budget;
    this.phone = phone;
    this.email = email;

  }

  public User(Integer id, String name, String userName, String phone, String email,Float budget,
      Timestamp createdAt, Timestamp updatedAt) {
    this.id = id;
    this.name = name;
    this.userName = userName;
    this.phone = phone;
    this.email = email;
    this.budget= budget;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

}
