var outputarea = document.getElementById("output");
var inputfield = document.getElementById("msg");
var userField = document.getElementById("user");
var passField= document.getElementById("pass");
var board = new Array;
var count=0;



function newUser(){
    createNewUser(userField.value)
}

function createNewUser(n) {
    var xhrUser=new XMLHttpRequest();
    xhrUser.open("get", "http://localhost:4567/newUser?user="+n,true);
    xhrUser.onload = () => {
     console.log(xhrUser.Response);
   
}
 xhrUser.send();
}

function onClick(){
    getMsg(inputfield.value);
    console.log("onClick end.")
}

function getMsg(n){
    console.log(n + " has reached getMsg function");
    var xhr= new XMLHttpRequest();
    xhr.open("get", "http://localhost:4567/sendXhr?msg="+n,true);
    xhr.onload = () => {
        console.log(xhr.response + " is xhr response");
        putInArray(xhr.response);
       
    }    
    xhr.send();
}

function putInArray(l){
console.log(l+" msg");
board[count]=l;
console.log(board[count]);
count=count+1;
gatherMsg(board);

}

function gatherMsg(arr){
    var i;
    var message= "";
    var c=107;
    var l;
    for (i = 0; i < arr.length; i++) { 
  
     l=c-arr[i].length
     for (x = 0; x < l; x++){
     arr[i]=arr[i]+" ";
}
      message= message + (arr[i]);
      console.log (arr[i] + " is the " + i +" msg in the array");
    }
    print(message);
}

function print(message){
    outputarea.value=message;
    inputfield.value="";

}
