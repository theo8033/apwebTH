/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatserverth;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.MultipartConfigElement;
import static spark.Spark.*;

public class Main {

 final static ArrayList<String> board = new ArrayList<>();


    public static void main(String[] args) {
        staticFiles.location("static/");

        //before("/sendMsg", (req, res)->{System.out.println("req url: " +req.url());});
        get("/getMsg", (req, res) -> getMsg(req));
        //url request getMsg goes to getMSg function
        get("/login", (req, res) -> login(req));
        //url request login goes to login function 
        post("/sendMsg",(req, res) -> sendMsg(req));
        //url request sendMsg goes to SendMsg function also post method
        
    
    }


    public static String getMsg(spark.Request req){
        
        
        Context ctx = getCtx(req);
        List<String> userMsg;
        String msgs="";
       
            
                userMsg = board.subList(ctx.numRead, board.size());
                ctx.numRead=board.size();
                //calculates number of msgs read, keeps track of msgs read. 
            
              for (int i=0; i<userMsg.size(); i++){
                  msgs=msgs+" "+userMsg.get(i);
                  System.out.println(msgs);
              }
    
                return msgs;
     
        //returns the unread msgs. 
    
    }
    
    public static String login(spark.Request req){
       Context ctx = getCtx(req);
       ctx.name=req.queryParams("user");
       String welcome="Welcome, "+ctx.name+".";
       if (ctx.name==null){
           halt(401, "thats not a name...");
           return "";
       }
       else{
           int subtract=109-welcome.length();
           for (int i=0; i<subtract; i++){
          welcome=welcome+" ";
          }
        return (welcome);
       }
    }
    //puts usernames into ctx class 


    public static String sendMsg(spark.Request req){ //will also have a paramater for username
      verifyLoggedIn(req);
      //must be logged in to send msgs
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(System.getProperty("java.io.tmpdir"));
        req.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
      
      Context ctx = getCtx(req);
      String user=ctx.name;
      String message;
      message=user + ": "+ req.queryParams("msg");
      int length=109-message.length();
      
      for (int i=0; i<length; i++){
          message=message+" ";
          }
    synchronized(board){  
    
     board.add(message);
    }
      //adds msg to msg array  
           
     return req.session().id();
         //message will be auth+": " + req.queryParams("msg")


    }

    public static void verifyLoggedIn(spark.Request req){
        Context ctx = getCtx(req);
        if (ctx.name==null){
            halt(401,"Login");
            
        }
    }
    //checks if loged in. 
    
    



    public static Context getCtx(spark.Request req){
        Context ctx=req.session().attribute("context");
        if (ctx == null){
            ctx=new Context();
            req.session().attribute("context", ctx);
        }
        return ctx;
    }


   // public static String newUser(spark.Request req){

    //[}



}