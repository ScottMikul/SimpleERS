window.onload = function(){

	getEmpRequests();
}
	function getEmpRequests() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log(this);
			var results = JSON.parse(this.responseText);
			console.log(results);
			
			results.forEach(element => {
				var row = document.createElement("tr");
					var description = document.createElement("td");
					description.classList.add("table-light");
					
					description.innerText = element.description;
					
					row.append(description);
					
					var amount = document.createElement("td");
					amount.classList.add("table-light");
					amount.innerText = element.amount;
					row.append(amount);
					
					var image = document.createElement("td");
					image.classList.add("table-light");
					
					if(element.hasImage){
						var imageLink = document.createElement("a");
						imageLink.setAttribute("href","/simpleERS/views/reimbursementimage.html?id=" + element.id ) ;
						imageLink.innerText = "view"
						image.appendChild(imageLink);
					}
					else {
						var imageLink = document.createElement("a");
						image.innerText = "none";
						image.appendChild(imageLink);
					}
					row.append(image);
					
					var status = document.createElement("td");
					status.classList.add("table-light");
					
					status.innerText = element.status;
					row.append(status);


				console.log(row);
				document.getElementById("tbody").appendChild(row);
			});
	    }
	  };
	  xhttp.open("GET", "/simpleERS/api/employeerr", true);
	  //xhttp.open("GET", "https://jsonplaceholder.typicode.com/todos/1", true);
	  xhttp.send();
}