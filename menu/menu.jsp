<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page errorPage="../home/ExceptionHandler.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<title>TIMS:SB20091130:SCR10004:MENU PAGE</title>
<script type="text/javascript" src="../js/fsmenu.js"></script>
<link rel="stylesheet" type="text/css" href="../css/listmenu_h.css">
<!-- <link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css"> -->

<script>

function changePage(pagename, frmid, sId, ModuleName, FormName, GroupName, read_permission, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission)
{
	if(sId=="1")
	{
		var temp = pagename+"?formId="+frmid+"&ModuleName="+ModuleName+"&FormName="+FormName+"&GroupName="+GroupName+"&read_permission="+read_permission+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
	  	location.replace(temp);
	}
	else
	{
		var url = "../home/index1.jsp";
	    location.replace(url);
	} 
}

</script>

<jsp:useBean class="com.seipl.hazira.dlng.util.Menu_Bean" id="menu1" scope="page"/>
<%
	String sId="";
	
	if(session.getAttribute("username")==null||session.getAttribute("username")=="")
	{
	    sId="0";
	}  
	else
	{
	    sId="1";
	}  
			
    String memp_cd1 = "" + session.getAttribute("emp_cd");
    String memp_name2 = "" + session.getAttribute("userid");

	String mtemp1="MOD";
	String mtemp2="SUBMOD";
			
	menu1.setemp_nm(memp_name2);
	menu1.setOption(mtemp1);
	menu1.init();

	Vector mmodule_nm=menu1.getModuleName();
//	System.out.println(" *******$******  "+mmodule_nm);
	Vector FORM_CD = menu1.getFORM_CD();
	Vector FORM_NAME = menu1.getFORM_NAME();
			
	Vector mform_id=new Vector();
	Vector mform_name=new Vector();
	Vector mform_type=new Vector();
	Vector mapplication_path=new Vector();
    menu1.setOption(mtemp2);
    String mnm2="";
    String mdn="";
    int tval = 0;
            
    //sravan
    Vector ALW_ADD=new Vector();
	Vector ALW_DEL=new Vector();
	Vector ALW_UPD=new Vector();
	Vector ALW_GRANT=new Vector();
	Vector ALW_CHECK=new Vector();
	Vector ALW_AUTHORIZE=new Vector();
	Vector ALW_PRINT=new Vector();
	Vector ALW_VIEW=new Vector();
	
	Vector compare_form_id = new Vector();
	int restrict = 0;
	
//	System.out.println("mmodule_nm.size() = "+mmodule_nm.size());
%>

<table style="background-image: url('..\images\');background-color: white" width="100%" align="center" border="0">
	<tr class="rowHeading">
	<!-- <TD width="5%">
	<img src="../images/BIPL5.gif" width="40" height="40" />
		</TD>-->
	<!--	<TD width="5%">
	  	<img src="../images/LOGO/SHELL_Logo_3.gif" width="50" height="50" />
		</TD> -->
		<td width="">
		<ul class="menulist" id="listMenuRoot" >
			
			<%for (int ij = 0; ij < mmodule_nm.size();ij++)
			{
				mnm2=(String)mmodule_nm.elementAt(ij);
  			    mdn=mnm2;
				 
  			    int len1= mnm2.length();
				// System.out.println("mnm2 :: "+mnm2+"   lrn:"+len1); 
               		%>
				<!-- <li><a href="#">&nbsp;&nbsp;<font color="black" size="1.5px"><%=mnm2%> </font>&nbsp;&nbsp;</a>  -->	
					<!-- <li><a href="#"><font style="font-family:sans-serif;font-size:10px;color:#853F62"><b> <%=mnm2%> </b></font>&nbsp; -->
					<!--    <input type="image" src="../images/arrow_3.gif"> --> </a>
					
					<%
					   
					   menu1.setModule(mdn);
					   menu1.init();
					   
					   mform_id=menu1.getFormId();
					  // System.out.println();
					   if(mform_id.size()>0)
					   {
					   %>
					   <li><a href="#"><font style="font-family:sans-serif;font-size:10px;color:#853F62"><b><%=mnm2%> </b></font>&nbsp;
				   <input type="image" src="../images/arrow_3.gif">  </a>
					
					   
					   <%
					   }
					   else
					   { %>
						    <li><a href="#"><font style="font-family:sans-serif;font-size:10px;color:#853F62"><b> <%=mnm2%> </b></font>&nbsp;
					<!--    <input type="image" src="../images/arrow_3.gif"> --> </a>
						   
					<%  }
					   %>
					   
					   
					   <div  align="center" style="color:;background-color:#D42E12;text-decoration:underline;height:5px;width:<%=len1*5+56%>px;      "><FONT size=""> </FONT></DIV>
					<ul class="menulist1">
					   
					   
					   <%
					   
					  // System.out.println("Form id:::"+mform_id);
					   mform_name=menu1.getFormName();
					//   System.out.println("mform_name........ id:::"+mform_name);
					   mform_type=menu1.getFormType();
					   mapplication_path=menu1.getApplicationPath();
					   
					   ALW_ADD=menu1.getALW_ADD();
			           ALW_DEL=menu1.getALW_DEL();
			           ALW_UPD=menu1.getALW_UPD();
			           ALW_GRANT=menu1.getALW_GRANT();
			           ALW_CHECK=menu1.getALW_CHECK();
			           ALW_AUTHORIZE=menu1.getALW_AUTHORIZE();
			           ALW_PRINT=menu1.getALW_PRINT();
			           ALW_VIEW=menu1.getALW_VIEW();
			            
			               Vector groupId=menu1.getGroupId();
						   Vector groupName=menu1.getGroupName();
						   Vector groupIdF=menu1.getGroupIdF();
						   Vector groupNameF=menu1.getGroupNameF();
						   //System.out.println("ALW_ADD"+ALW_ADD);
						   //System.out.println("ALW_UPD"+ALW_UPD+"ALW_DEL"+ALW_DEL);
						   //System.out.println("ALW_ADD"+ALW_ADD);
						   //System.out.println("ALW_VIEW"+ALW_VIEW);
							//System.out.println(" Group Id ::: "+ groupId.size()+"and"+groupId);
						   //System.out.println("valueo f group id..."+compare_form_id.size()+"and"+compare_form_id);
					   		
						   int size2=0;
						   for (int tv1 = 0; tv1 < mform_id.size(); tv1++) 
						   {
							   String ft=""+mform_type.elementAt(tv1);
							   if(ft.equalsIgnoreCase("F"))
								{
									size2++;
								}
							}
						    
						   for(int h=0;h<groupIdF.size();h++) 
						   { 
							 	%>
								<!--<li>   comment this line for open tree  else compressed -->
								  <div  align="left" style="color:black;background-color:#F7D117;"><font style="color:#853F62;font-size:10px;" ><b>&nbsp; <%=groupNameF.elementAt(h)%> </b></font></div>
								<!--<ul>   comment this line for open tree  else compressed -->
								
								<%for(tval=0; tval<mform_id.size(); tval++) 
								{
									if(size2==0)//TO CHECK IF THERE ARE NO FORMS
									{
										
									}
									else
									{
										if(tval==0)//background-image:url(../images/splitbg.jpg);
										{
			                              %><div align="left" style="background-image:url(../images/img071.gif);color:white;">&nbsp;&nbsp;&nbsp;&nbsp;&#187;<font color="black" size="1px"><b>Forms</b></font></div> 
			                             <%
										}
									}	
										if(tval == size2)
										{
			                                  %><div align="left" style="background-image:url(../images/img071.gif);">&nbsp;&nbsp;&nbsp;&nbsp;&#187;<font color="black" size="1px"><b>Reports</b></font></div> <%
										}
										if(groupIdF.elementAt(h).toString().equalsIgnoreCase(groupId.elementAt(tval).toString()))
										{	
											String flag_form = "Y";	
											for(restrict=0; restrict<compare_form_id.size(); restrict++)
											{	
												if((""+compare_form_id.elementAt(restrict)).trim().equals((""+mform_id.elementAt(tval)).trim()))
												{	
													flag_form = "N";	
												}
											}
											if(flag_form.equals("Y"))
											{   %>
												<a href="#" style=" width:100%;border-bottom-style:solid;border-color:#F7D117;border-bottom-width:1.5px; " onclick="changePage('<%=mapplication_path.elementAt(tval)%>','<%=mform_id.elementAt(tval)%>','<%=sId%>','<%=mnm2%>','<%=mform_name.elementAt(tval)%>','<%=groupNameF.elementAt(h)%>','<%=ALW_VIEW.elementAt(tval)%>','<%=ALW_ADD.elementAt(tval)%>','<%=ALW_DEL.elementAt(tval)%>','<%=ALW_PRINT.elementAt(tval)%>','<%=ALW_UPD.elementAt(tval)%>','<%=ALW_GRANT.elementAt(tval)%>','<%=ALW_CHECK.elementAt(tval)%>','<%=ALW_AUTHORIZE.elementAt(tval)%>');">
												&nbsp;&nbsp;
												<font  style="font-family:sans-serif;font-size:10px;color:#853F62 "  ><b> <%=mform_name.elementAt(tval)%></b></font></a>
										<%	}
											%>
									<%	compare_form_id.add(""+mform_id.elementAt(tval)); 
										}
									}		%>
								 <!-- </ul>   comment this line for open tree  else compressed -->
								 <!-- </li>   comment this line for open tree  else compressed -->
						  <% }
							menu1.clearVectors();
						%>
					</ul>
					</li>
			<%}%>
			
		</ul>
		</td>
	<!--  	<TD width="9%">
		<img src="../images/LOGO/TOTAL_Logo.jpg" width="50" height="50" />
		</TD> -->
	</tr>
</table>

<%--
	for(int show=0; show<compare_form_id.size(); show++)
	{
		System.out.println("compare_form_id.elementAt("+show+") = "+compare_form_id.elementAt(show));
	}
--%>
<script type="text/javascript">
//<![CDATA[

// For each menu you create, you must create a matching "FSMenu" JavaScript object to represent
// it and manage its behaviour. You don't have to edit this script at all if you don't want to;
// these comments are just here for completeness. Also, feel free to paste this script into the
// external .JS file to make including it in your pages easier!

// Here's a menu object to control the above list of menu data:

var listMenu = new FSMenu('listMenu', false, 'display', 'block', 'none');

//listMenu.showDelay = 1;

//listMenu.switchDelay = 125;

//listMenu.hideDelay = 500;

//listMenu.cssLitClass = 'highlighted';

//listMenu.showOnClick = 1;
//listMenu.hideOnClick = true;
//listMenu.animInSpeed = 0.2;
//listMenu.animOutSpeed = 0.2;


// Now the fun part... animation! This script supports animation plugins you
// can add to each menu object you create. I have provided 3 to get you started.
// To enable animation, add one or more functions to the menuObject.animations
// array; available animations are:
//  * FSMenu.animSwipeDown is a "swipe" animation that sweeps the menu down.
//  * FSMenu.animFade is an alpha fading animation using tranparency.
//  * FSMenu.animClipDown is a "blind" animation similar to 'Swipe'.
// They are listed inside the "fsmenu.js" file for you to modify and extend :).

// I'm applying two at once to listMenu. Delete this to disable!

//listMenu.animations[listMenu.animations.length] = FSMenu.animFade;

//listMenu.animations[listMenu.animations.length] = FSMenu.animSwipeDown;

//listMenu.animations[listMenu.animations.length] = FSMenu.animClipDown;

// Finally, on page load you have to activate the menu by calling its 'activateMenu()' method.
// I've provided an "addEvent" method that lets you easily run page events across browsers.
// You pass the activateMenu() function two parameters:
//  (1) The ID of the outermost <ul> list tag containing your menu data.
//  (2) A node containing your submenu popout arrow indicator.
// If none of that made sense, just cut and paste this next bit for each menu you create.
 
var arrow = null;
if (document.createElement && document.documentElement)
{
  arrow = document.createElement('span');
 
  arrow.appendChild(document.createTextNode('>'));

 arrow.className = 'subind';
}

addEvent(window, 'load', new Function('listMenu.activateMenu("listMenuRoot",arrow)'));


</script>

