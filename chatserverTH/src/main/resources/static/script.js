var outputarea = document.getElementById("output");
//handle for output field 
var inputfield = document.getElementById("msg");
//handel for msg input field 
var userField = document.getElementById("user");
///handle for username input field 


function send(){
toUrl(inputfield.value);
}
//button onclick function, sends msg value to new function 

function login(){
    toLogin(userField.value);
}
//button onclick function  sends username value to new function 
function toGetMsgs(){
    
        request({url: "getMsg", method: "GET"})
                .then(data => {
                    print(data);
                })
                .catch(error => {
                   console.log ("a pigeon has died on the journey");
                });
    
}
//sends url and method to request function then prints data (msgs) to website
function toLogin(n){
    request({url:"login?user="+n, method:"GET"})
        .then(data => {
            print("welcome, "+ data);
            setInterval(toGetMsgs, 100);
        })
        .catch(erroor => {
            console.log("Error: ");
        })
}  
//sends request to login with the username (n). then prints welcome message and lets the loggged in person see msgs (toGetmsgs)

function toUrl(n){
    var data= new FormData();
    data.append('msg', n);
    request({url: "sendMsg", method:"post", body:data})
    .then(result => {
        session = result;
    })
    .catch(error => {
        console.log("Error: " + error);
    });

}
//gets the msg and puts it into a post method in the request



function request(obj) {
    return new Promise((resolve, reject) => {
        let xhr = new XMLHttpRequest();
        xhr.open(obj.method || "GET", obj.url);

        xhr.onload = () => {
            if (xhr.status >= 200 && xhr.status < 300) {
                resolve(xhr.response);
            } else {
                reject(xhr.statusText);
            }
        };
        xhr.onerror = () => reject(xhr.statusText);

        xhr.send(obj.body);
    });
};

//the main request method that all request go thru 



function print(a){
if (a!=="[]"){
outputarea.value+=a;

  }
}
//prints to text box in website. 






console.log("end script.")
