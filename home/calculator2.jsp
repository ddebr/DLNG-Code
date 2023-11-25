<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<html>
<head>
  <title>A simple JavaScript calculator</title>
  <script language='JavaScript'>
     Default  = "0";
     Current = "0";
     Operation = 0;
     MaxLength = 30;

function AddDigit(dig) {
   if (Current.indexOf("!") == -1) {
      if ((eval(Current) == 0) && (Current.indexOf(".") == -1)) {
         Current = dig;
      } else {
         Current = Current + dig;
      }
      Current = Current.toLowerCase();
   } else {
      Current = "Hint! Press 'AC'";
   }
   if (Current.indexOf("e0") != -1) {
      var epos = Current.indexOf("e");
      Current = Current.substring(0,epos+1) + Current.substring(epos+2);
   }
   if (Current.length > MaxLength) {
      Current = "Too long";
   }
   document.Calculator.Display.value = Current;
}

function Dot() {
   if ( Current.length == 0) {
      Current = "0.";
   } else {
      if (( Current.indexOf(".") == -1) && (Current.indexOf("e") == -1)) {
         Current = Current + ".";
      }
   }
  document.Calculator.Display.value = Current;
}

function DoExponent()
{
   if(Current.indexOf("e") == -1)
   {
      Default = Current;
      //Current = Current + "e0";
      Current = "";
      document.Calculator.Display.value = Current;
      //Current = "";
      Operation = 5;
   }
}

function PlusMinus() {
   if(Current.indexOf("e") != -1) {
      var epos = Current.indexOf("e-");
      if (epos != -1) {
         Current = Current.substring(0,1+epos) + Current.substring(2+epos);
      } else { epos = Current.indexOf("e");
         Current = Current.substring(0,1+epos) + "-" + Current.substring(1+epos);
      }
   } else {
      if ( Current.indexOf("-") == 0 ) {
         Current = Current.substring(1);
      } else {
         Current = "-" + Current;
      }
      if ((eval(Current) == 0) && (Current.indexOf(".") == -1 )) {
         Current = "0"; }
   }
   document.Calculator.Display.value = Current;
}

function Clear() {
   Current = "0";
   document.Calculator.Display.value = Current;
}

function AllClear() {
   Current = "0";
   Operation = 0;
   Default = "0";
   document.Calculator.Display.value = Current;
}

function Operate(op) {
   if (Operation != 0) { Calculate(); };
   if (op.indexOf("*") > -1) { Operation = 1; };
   if (op.indexOf("/") > -1) { Operation = 2; };
   if (op.indexOf("+") > -1) { Operation = 3; };
   if (op.indexOf("-") > -1) { Operation = 4; };

   Default = Current;

   Current = "";
   document.Calculator.Display.value = Current;
}

function Calculate() {
   if (Operation == 1) { Current = eval(Default) * eval(Current); };
   if (Operation == 2) {
      if (eval(Current) != 0) {
         Current = eval(Default) / eval(Current)
      } else {
         Current = "Divide by zero";
      }
   }
   if (Operation == 3) { Current = eval(Default) + eval(Current); };
   if (Operation == 4) { Current = eval(Default) - eval(Current); };
   
   if (Operation == 5)
   { 
   		//alert("Default = "+Default+",  Current = "+Current);
   		Current = Math.pow(eval(Default),eval(Current));
   };
   
   Operation = 0;
   Default = "0";
   Current = Current + "";
   if (Current.indexOf("Infinity") != -1) {
      Current = "Value too big";
   }
   if (Current.indexOf("NaN") != -1) {
      Current = "I don't understand";
   }
   document.Calculator.Display.value = Current;
}

function FixCurrent() {
   Current = document.Calculator.Display.value;
   Current = "" + parseFloat(Current);
   if (Current.indexOf("NaN") != -1) {
      Current = "I don't understand";
   }
   document.Calculator.Display.value = Current;
}
   </script>

</head>
<body>
<hr><div align="center">
<FORM name="Calculator">
<table width="40%" height="40%" border="4" bgcolor="#FFEBCD"><tr>
<td colspan="2" align="center">
<b><font face="Verdana, Arial, Helvetica" color="#000080" size="3">
Calculator<br></font>
<font face="Courier" size="5">
<input type="text" maxlength="40" size="35" name="Display" onChange="FixCurrent()">
</font></b>
</td></tr>
<tr><td width="65%" align="center">
<table><tr>
  <td><input type="button" name="seven" value="   7    " OnClick="AddDigit('7')"></td>
  <td><input type="button" name="eight" value="   8    " OnClick="AddDigit('8')"></td>
  <td><input type="button" name="nine" value="   9    " OnClick="AddDigit('9')"></td>
</tr><tr>
  <td><input type="button" name="four" value="   4    " OnClick="AddDigit('4')"></td>
  <td><input type="button" name="five" value="   5    " OnClick="AddDigit('5')"></td>
  <td><input type="button" name="six" value="   6    " OnClick="AddDigit('6')"></td>
</tr><tr>
  <td><input type="button" name="one" value="   1    " OnClick="AddDigit('1')"></td>
  <td><input type="button" name="two" value="   2    " OnClick="AddDigit('2')"></td>
  <td><input type="button" name="three" value="   3    " OnClick="AddDigit('3')"></td>
</tr><tr>
  <td><input type="button" name="plusmin" value="  +/-  " OnClick="PlusMinus()"></td>
  <td><input type="button" name="one" value="   0    " OnClick="AddDigit('0')"></td>
  <td><input type="button" name="two" value="    .    " OnClick="Dot()"></td>
</tr>
</table>
</td>
<td width="35%" align="center">
<table><tr>
  <td><input type="button" name="clear" value="    C     " OnClick="Clear()"></td>
  <td><input type="button" name="AC" value="   AC    " OnClick="AllClear()"></td>
</tr><tr>
  <td><input type="button" name="mul" value="     *     " OnClick="Operate('*')"></td>
  <td><input type="button" name="div" value="     /      " OnClick="Operate('/')"></td>
</tr><tr>
  <td><input type="button" name="add" value="    +     " OnClick="Operate('+')"></td>
  <td><input type="button" name="sub" value="     -      " OnClick="Operate('-')"></td>
</tr><tr>
  <td><input type="button" name="result" value="     =    " OnClick="Calculate()"></td>
  <td align="right"><input type="button" name="exp" value="  x ^ y  " OnClick="DoExponent()"></td>
</tr></table>
</td>
</tr></table>
</FORM>
</div>
</body>
</html>