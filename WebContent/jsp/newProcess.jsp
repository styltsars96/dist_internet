
<title>Νέα απόσυρση</title>

</head>
<body>
<h2>Νέα απόσυρση!</h2>
<form method="POST" action="/Dist_Front/delivery" accept-charset="UTF-8">
<table>
<tr><td>Τοποθεσία</td>

<tr><td>Longitude:</td><td><input type="number" step="any" name="lon"/></td></tr>
<tr><td>Latitude:</td><td><input type="number" step="any" name="lat"/></td></tr>

<tr><td>Αμάξι:</td>
<tr><td>Μοντέλο:</td><td><input type="text" name="model"/></td></tr>
<tr><td>Αρ. Κυκλοφορίας:</td><td><input type="text" name="plate"/></td></tr>
<tr><td>Καύσιμο:</td><td>	<input type="radio" name="fuel" value="Αέριο"/>Αέριο
							<input type="radio" name="fuel" value="Πετρέλαιο"/>Πετρέλαιο
							<input type="radio" name="fuel" value="Βενζίνη"/>Βενζίνη
							</td></tr>
<tr><td><input type="submit" value="Αποθήκευση!"/></td></tr>
</table>
</form>
</body>
</html>