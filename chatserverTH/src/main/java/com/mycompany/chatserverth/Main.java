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
        get("/hello", (req, res) -> "Hello World");
        get("/factorial", (req, res) -> factorial(req));
       // get("/message", (req, res) -> messager(req));
        
    }
    public static String factorial(spark.Request req){
        int num;
        try{ 
            num=Integer.parseInt(req.queryParams("number"));
        } catch (Exception e) {
            return "ERROR";
        }
        double res = 1;
        for (int i =1; i <=num; i++){
            res *=i;
        }
        return "" + res;
       
    }
    
  
    
}