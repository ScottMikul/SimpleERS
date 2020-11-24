window.onload = function(){

	getEmployeeAccountInfo();
}
	function getEmployeeAccountInfo() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	let jsonObj = JSON.parse( this.responseText);
	    	console.log(jsonObj);
	         document.getElementById("first").value = jsonObj.firstName;
	         document.getElementById("last").value = jsonObj.lastName;
	         document.getElementById("password").value = jsonObj.password;
	         document.getElementById("username").value = jsonObj.username;
	         document.getElementById("email").value = jsonObj.email;
	    }
	  };
	  xhttp.open("GET", "http://localhost:8080/simpleERS/api/info", true);
	  xhttp.send();
}