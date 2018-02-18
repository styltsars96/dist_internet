<title>Διαδικασίες</title>
<script>

function getProcesses(){
	var table = document.getElementById("processes");
	table.innerHTML="<tr><th>Μοντέλο</th><th>Αρ. Κυκλοφορίας</th><th>Κατάσταση αμαξιού</th><th>Κατάστση Διαδικασίας</th><th>Κατάστημα</th><th>Επιβράβευση</th></tr>";
var ajax= new XMLHttpRequest();
var url = location.origin+"/Dist_Front/process" ;
ajax.open("GET",url,true);
ajax.send(null);
ajax.onreadystatechange = function() {
	if (ajax.readyState == 4) {
		if (ajax.status == 200) { 
			if(ajax.response!=null){
				
				var result = JSON.parse(ajax.response);
				var processList=result.processList;
				for (var i=0;i<processList.length;i++){
					var tr = document.createElement("tr");
			
					var td1 = document.createElement("td");tr.appendChild(td1);
					td1.innerHTML=processList[i].car.model;
					var td2 = document.createElement("td");tr.appendChild(td2);
					td2.innerHTML=processList[i].car.licencePlate;
					var td3 = document.createElement("td");tr.appendChild(td3);
					if(processList[i].car.status==null){
						td3.innerHTML='Δεν καθορίστηκε!';
					}else{
					td3.innerHTML=processList[i].car.status;
					}
					var td4 = document.createElement("td");tr.appendChild(td4);
					td4.innerHTML=processList[i].status;

					var shop = document.createElement("td");tr.appendChild(shop);
					shop.innerHTML = processList[i].shop.id;
					
					var td5= document.createElement("td");tr.appendChild(td5);
					if(processList[i].car.discount==null){
						td5.innerHTML='Δεν καθορίστηκε!';
					}else{
						td5.innerHTML=processList[i].car.discount;
					};
					var pist= document.createElement("td");tr.appendChild(pist);
					if(processList[i].status=="Completed"){
						pist.innerHTML="<a href=\"print.jsp?car='"+processList[i].car.licencePlate+"'&cost="+processList[i].car.discount+"\">Πιστοποιητικό</a>";
						
					}
					table.appendChild(tr);
				};
			}
		}
	}
}
}
</script>

</head>
<body>

<h3>Όνομα χρήστη: <%= session.getAttribute("name")%></h3>
<p> Παρακάτω είναι όλες οι διαδικασίες σας:</p>
<table border=1 id='processes' class="secondary">
</table>
<input type="button" value="REFRESH!" onclick="getProcesses()"/>
<br>
Αν θες να κάνεις νέα αίτηση απόσυρσης μπες <a href="newProcess.jsp">εδώ</a>.
<script>getProcesses()</script>

<form action="/Dist_Front/logout" method="POST">

<input type="submit" value="Logout!">
</form>
</body>
</html>