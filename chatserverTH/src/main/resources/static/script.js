



console.log ("loading...");
var outputarea = document.getElementById("output");
var inputfield = document.getElementById("num");

function print (s) {
outputarea.value += s +"\n";
}
function test(){
console.log("computing...")
factorial(inputfield.value);
}

function factorial(n){
var xhr= new XMLHttpRequest();
xhr.open("get", "http://localhost:4567/factorial?number="+n, true);
xhr.onload = () => {

    print("factorial of "+ n + " is " + xhr.response);

console.log("computed.");
};
xhr.onerror = () => {
    console.log("error:" + xhr.statusText);
};
xhr.send();

}

function test2() {
console.log("stressing...")
i = 0;
while (i++<1000){
factorial(i);

}

}


function kill(){
console.log ("cleared text field.");
}



console.log ("complete.");
