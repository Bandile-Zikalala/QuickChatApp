package com.mycompany.quickchatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 
/**
 *
 * @author makhu
 */
//login validation for QUICKCHATAPP
public class Login {
    //data I'm going to store
    String savedUsername;
    String savedPassword;
    String savedCellNumber;
    String firstName;
    String lastName;
    private String last;
    private String first;
    
    //methy 1
     boolean checkUserName(String userName){ //checking whether the username is or less than 5 and has an underscore
         
         return userName.contains("_")&& userName.length() <=5;
     }
     //checking if all the required thingies for the password are there oh the characters whatsoever.
     //Methy 2
      boolean checkPasswordComplexity(String password){
         boolean hasLength= password.length() >=8;
         boolean hasCapital= false;
         boolean hasNumber= false;
         boolean hasSpecial= false;
         
         for(int i= 0; i < password.length(); i++){
             char c= password.charAt(i);
             if
                     (Character.isUpperCase(c)){
                 hasCapital= true;    
             }
             if (Character.isDigit(c)){
                 hasNumber=true;
             }
             if (!Character.isLetterOrDigit(c)){
                 hasSpecial= true;
             }
         }
         return hasLength && hasCapital && hasNumber && hasSpecial;//if it has all the required thingies it will return this 
     }
      //Methy 3
      //Here I'll be checking if the number starts with +27
      //& has 10 characters + 2 so 12.
      boolean checkCellPhoneNumber(String cellNumber){
          //so here basically I'm saying if the number starts with +27 and is 12 characters aaaah its ncaah!! 
          //Kazi ubani owafika nalesiZulu esibhedayo la egoli,kodwa ngisola amapedi.
          return cellNumber.startsWith("+27")&& cellNumber.length()==12;
      }
      //ayke sezizongikhalela ngempelake manje, kodwa ngoba ngumzulu mina ayy bafana ngingenhla kakhulu!!
      // sesika IEC sya register manje
      String registerUser(String username, String password, String cellNumber){
          //soqale sibheke igama elisetshenziswayo
          if (!checkUserName(username)){
              return "Username is not correctly formatted, please ensure that your username containts an underscore and "
                      + "is no more than five characters in length"; //awuzwe nje isingisi esingaka ngingedwa
          }
          //manje sengibheka i password aka igamalokwendula
          if (!checkPasswordComplexity(password)){
              return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
          }
          //makulunge konke lemininingwane izodlala kwibhokisi lesikhumbuzo
      this.savedUsername= username;
      this.savedPassword= password;
      this.savedCellNumber= cellNumber;
      
      return " User has been registered successfully.";
}
      //Methy 5
      //Login Method
      public void 
              setSavedCredentilas(String username, String password, String fisrt, String last){
                  this.savedUsername= username;
                  this.savedPassword= password;
                  this.firstName= fisrt;
                  this.lastName= last;
              }
              
      boolean loginUser(String enteredUsername, String enteredPassword){
          //to see if what they entered matches with what I saved
          return enteredUsername.equals(this.savedUsername) && enteredPassword.equals(this.savedPassword);
      }
      String returnLoginStatus (String enteredUsername, String enteredPassword){
          if(loginUser(enteredUsername, enteredPassword)){
              return "Welcome" + firstName + "  "  + lastName + ",it is great to see you again.";
          }else{
              return "Username or password is incorrect, please try again.";
          }
      }

    void setSavedCredentials(String username, String password, String first, String last) {
        this.savedUsername = username;
        this.savedPassword = password;
        this.firstName = first;
        this.lastName = last;
    }
      }