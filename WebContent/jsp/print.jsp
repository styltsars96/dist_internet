<title>Έκδοση Πιστοποιητικού</title>
</head>
<body>
<h3>Πιστοποιητικό</h3>
<table>
<tr>
<th>Όνομα:</th><td><%=request.getSession().getAttribute("name") %></td>
</tr>
<tr><th>ΑΦΜ:</th><td><%=request.getSession().getAttribute("afm") %></td>
</tr>
<tr>
<th>Αυτοκίνητο:</th><td><%= request.getParameter("car") %></td>
<tr>
<tr>
<th>Επιβράβευση:</th><td><%= request.getParameter("cost") %> €</td>
</tr>
</table>
<script>print();</script>
</body>
</html>