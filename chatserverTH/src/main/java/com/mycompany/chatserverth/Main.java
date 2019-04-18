/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatserverth;

import static spark.Spark.*;

public class Main {
    

    public static void main(String[] args) {
        staticFiles.location("static/");
        get("/sendXhr", (req, res) -> sendXhr(req));
        get("/newUser", (req, res) -> newUser(req));
  
       // get("/User", (req, res) -> auth(req));

        
    }
    public static String sendXhr(spark.Request req){ //will also have a paramater for username
        String message;
      
        message=req.queryParams("msg");
   
        return message; //message will be auth+": " + req.queryParams("msg")
        
       
    }
    
   
    
   // public static String newUser(spark.Request req){
       
    //[}
    
  
    
}