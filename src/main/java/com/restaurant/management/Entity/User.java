package com.restaurant.management.Entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "pass_Word")
    private String passWord;

    @Column(name = "phone_No")
    private String phoneNo;

    @Transient
    private boolean logIn_Status;
    
    
   public User(String name, String userName, String passWord, String phoneNo) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNo = phoneNo;
         this.logIn_Status = false;
    }
    
   public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public boolean isLogIn_Status() {
    return logIn_Status;
}

public void setLogIn_Status(boolean logIn_Status) {
    this.logIn_Status = logIn_Status;
}

public User()
   {
       this.name = "";
        this.userName = "";
        this.passWord = "";
        this.phoneNo = "";
   }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
     public void setLogIn_status(boolean status)
    {
       this.logIn_Status = status;
    }
     
     public boolean getLogIn_status()
    {
      return this.logIn_Status;
    }

   abstract public User createUser();
   
}