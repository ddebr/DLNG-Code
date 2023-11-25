<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
a {
 color:white;
 }
</style>
<script type="text/javascript" src="../js/ui/jquery-1.6.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
// Create two variable with the names of the months and days in an array
var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ]; 
var dayNames= ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]

// Create a newDate() object
var newDate = new Date();
// Extract the current date from Date object
newDate.setDate(newDate.getDate());
// Output the day, date, month and year    
$('#Date').html(dayNames[newDate.getDay()] + ", " + newDate.getDate() + ' ' + monthNames[newDate.getMonth()] + ' ' + newDate.getFullYear());

setInterval( function() {
	// Create a newDate() object and extract the seconds of the current time on the visitor's
	var seconds = new Date().getSeconds();
	// Add a leading zero to seconds value
	$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
	},1000);
	
setInterval( function() {
	// Create a newDate() object and extract the minutes of the current time on the visitor's
	var minutes = new Date().getMinutes();
	// Add a leading zero to the minutes value
	$("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
    },1000);
	
setInterval( function() {
	// Create a newDate() object and extract the hours of the current time on the visitor's
	var hours = new Date().getHours();
	// Add a leading zero to the hours value
	$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
    }, 1000);
	
}); 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TIMS 2.0</title>

<link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css">

</head>
<form method="POST">
<body bgcolor="#7E984D">
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
      	<tr><td align="right" style="color:black;background-color:#F7D117;text-decoration:underline;">
		      <font size="1" color=black ><b>Copyright©2011-12<a href="mailto:sbhowmick@barodainformatics.com">
			      <font color=lightblue >BIPL</font></a>, India. All Rights Reserved. &nbsp;&nbsp;&nbsp;
<!--			      201205081823-->
						201508011450 ver 2.0
			      </b>
			  </font>
	  	</td>
		<td align="right" style="color:black;background-color:#F7D117;text-decoration:underline;">
				<font size="1"> 
	      		<b id="Date" style="color: black;"></b></font>
				<font color="red" size="2"> &nbsp;|&nbsp;<b id="hours"></b>: <b id="min"></b>: <b id="sec"></b>&nbsp;|&nbsp;</font>
		</td></tr>
</table>


    
</body>
</form>  
</html>