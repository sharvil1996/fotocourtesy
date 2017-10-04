<%-- <%@ page language="java" contentType="text/html;charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome | FotoCourtesty</title>
<link href="css/photographerStyle.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="imgs/favicon.ico">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
</head>
<body>	
<%@ include file="photographerHeader.jsp"%>
<section class="dashboardview">
	<span class="pid hidden"><%= photographerBean.getUsername() %><!-- Wilbert14 --></span>
	<div style="background-image: url(imgs/photographer.jpg);background-position: center center;background-size: cover;">
		<div class="container" style="padding: 10px;padding-top: 150px;">
			<div class="jumbotext photographername nomargin noblock"></div>&nbsp;&nbsp;&nbsp;<h3 class="whitetext noblock hidden">(<span class="xp">2</span> <span class="yearsexperience">Year(s) Experience)</span></h3>
			<h3 class="whitetext tjustify desc"></h3>
		</div>
	</div>
	<div class="imageviewer">
		<div class="back"></div>
		<img class="image"/>
		<div class="closebutton">
			<img src="imgs/Close.svg"/>
		</div>
	</div>
	<div class="backgrey statblock" style="display: none;">
		<div class="container" style="padding-bottom: 0">
			<h1 class="bold statistics">Statistics</h1>
			<div class="row">
				<div class="cm6">
					<div class="whitebg shadow" style="padding: 20px">
						<h1 class="nomargin pageviews">0</h1>
						<h3 class="maincolor viewstoyourprofile">View(s) to Your Profile</h3>
					</div>
				</div>
				<div class="cm6">
					<div class="whitebg shadow" style="padding: 20px">
						<h1 class="nomargin albumviews">0</h1>
						<h3 class="maincolor viewstoyouralbums">View(s) to Your Albums</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="backgrey noalbum" style="display: none;">
		<div class="container">
			<h1 class="bold youralbums">Your Albums</h1>
			<div class="whitebg shadow">
				<br><br><br>
				<h1 class="noalbumsfound">No Albums Found</h1>
				<br>
				<div class="tcenter"><a href="photographerAlbum.jsp" class="button shadow manage">Manage</a></div>
				<br><br>
			</div>
		</div>
	</div>
	<div class="backgrey albumList" style="display: none;">
		<div class="container">
			<h1 class="bold youralbums">Your Albums</h1>
			<div class="row albumcontainer">
			</div>
			<br>
			<div class="tcenter"><a href="photographerAlbum.jsp" class="button shadow manage">Manage</a></div>
		</div>
	</div>
	<div class="backgrey nocategory" style="display: none;">
		<div class="container" style="padding-top: 0;">
			<h1 class="bold yourpricing">Your Pricing</h1>
			<div class="whitebg shadow">
				<br><br><br>
				<h1 class="nocategoriesfound">No Categories Found</h1>
				<br>
				<div class="tcenter"><a href="photographerCategory.jsp" class="button shadow manage">Manage</a></div>
				<br><br>
			</div>
		</div>
	</div>
	<div class="backgrey categoryList" style="display: none;">
		<div class="container" style="padding-top: 0;">
			<h1 class="bold yourpricing">Your Pricing</h1>
			<div class="row categoryListBlock">
			</div>
			<br>
			<div class="tcenter"><a href="photographerCategory.jsp" class="button shadow manage">Manage</a></div>
		</div>
	</div>
	<div>
		<div class="container contactdetails" style="display: none;">
			<h1 class="bold yourcontactdetail">Your Contact Detail</h1>
			<div class="row">
				<div class="cm4">
					<h3 class="tcenter maincolor bold address">Address</h3>
					<h3 class="addresspg tcenter"></h3>
				</div>
				<div class="cm4">
					<h3 class="tcenter maincolor bold email">Email</h3>
					<h3 class="emailpg tcenter"></h3>
				</div>
				<div class="cm4">
					<h3 class="tcenter maincolor bold contactno">Contact No.</h3>
					<h3 class="cntno tcenter"></h3>
				</div>
			</div>
			<br>
			<div class="tcenter">
				<div class="button editprofilebutton editprofile" style="margin-bottom: 10px;">Edit Profile</div>
				<div class="button changepassbutton changepassword">Change Password</div>
			</div>
		</div>
	</div>
</section>
<section class="editprofileblock backgrey" style="display: none;">
	<br><br>
	<div class="row">
		<div class="cm2"></div>
		<div class="cm8">
			<div class="form">
				<h1 class="editprofile">Edit Profile</h1>
				<div style="box-sizing: border-box;overflow: hidden;">
					<div class="row">
						<div class="cm6 nomargin">
							<label class="firstname">First Name</label>
							<input type="text" class="fnbox">
						</div>
						<div class="cm6 nomargin">
							<label class="lastname">Last Name</label>
							<input type="text" class="lnbox">
						</div>
					</div>
					<div class="row">
						<div class="cm6 nomargin">
							<label class="address">Address</label>
							<input type="text" class="addressbox">
						</div>
						<div class="cm6 nomargin">
							<label class="description">Description</label>
							<input type="text" class="descbox">
						</div>
					</div>
					<div class="row">
						<div class="cm6 nomargin">
							<label class="mobilenumber1">Mobile Number 1</label>
							<input type="text" class="cntnobox">
						</div>
						<div class="cm6 nomargin">
							<label class="mobilenumber2">Mobile Number 2</label>
							<input type="text" class="cnt1nobox">
						</div>
					</div>
					<div class="row visible">
						<div class="cm6 nomargin">
							<label class="city">City</label>
							<div class="sugcontainer">
								<input type="text" class="citysearchbox sugrep">
								<ul class="sugbox citysugbox">
								</ul>
								<input type="text" class="citybox sugtarget" style="display: none;">
							</div>
						</div>
						<div class="cm6 nomargin">
							<label class="experience">Experience</label>
							<input type="text" class="xpbox">
						</div>
					</div>
					<div class="tcenter" style="margin-top: 125px;margin-bottom: 30px;">
						<button type="button" class="button updateButton update">Update</button>
						<button type="button" class="button cancelprofilebutton cancel">Cancel</button>
					</div>
					<div class="tcenter"><label class="error emptyError allfieldsarerequired" style="margin-top:20px;">All fields are required</label></div><br>
				</div>
			</div>
		</div>
		<div class="cm2"></div>
	</div>
	<br><br>
</section>

<section class="changepassblock backgrey" style="display: none;">
	<br><br>
	<div class="row">
		<div class="cm4"></div>
		<div class="cm4">
			<div class="form">
				<h1 class="changepassword">Change Password</h1>
				<div style="box-sizing: border-box;overflow: hidden;">
					<label class="oldpassword">Old Password</label>
					<div class="passwordbox">
						<input type="password" class="oldpassbox">
						<div><img src="imgs/eye.svg"/></div>
					</div>
					<label class="newpassword">New Password</label>
					<div class="passwordbox">
						<input type="password" class="newpassbox">
						<div><img src="imgs/eye.svg"/></div>
					</div>
					<br>
					<div class="tcenter">
						<button type="button" class="button updatepassButton change">Change</button>
						<button type="button" class="button cancelpassbutton cancel">Cancel</button>
					</div>
					<div class="tcenter"><label class="error emptyError allfieldsarerequired" style="margin-top:20px;">All fields are required</label></div><br>
				</div>
			</div>
		</div>
		<div class="cm4"></div>
	</div>
	<br><br>
</section>
<%@ include file="photographerFooter.jsp"%>
<div class="fullloader"><img src="imgs/loading.svg"/></div>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/data.js"></script>
<script type="text/javascript" src="js/photographerCode.js"></script>
<script type="text/javascript" src="js/photographerHome.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="js/photographerLang.js"></script>
</body>
</html>

 --%>
 
 <%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1" /> -->
<meta name="viewport"
	content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi" />

<title>Welcome | FotoCourtesty</title>
<link href="css/photographerStyle.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="imgs/favicon.ico">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900"
	rel="stylesheet">
<link rel="icon" type="image/png" href="imgs/favicon.png"
	sizes="256x256">
</head>
<body>
	<%@ include file="photographerHeader.jsp"%>
	<section class="dashboardview"> <span class="pid hidden"><%=photographerBean.getUsername()%><!-- Wilbert14 --></span>
	<div
		style="background-image: url(imgs/photographer.jpg); background-position: center center; background-size: cover;">
		<div class="container" style="padding: 10px; padding-top: 150px;">
			<div class="jumbotext photographername nomargin noblock"></div>
			&nbsp;&nbsp;&nbsp;
			<h3 class="whitetext noblock hidden">
				(<span class="xp">2</span> <span class="yearsexperience">Year(s)
					Experience)</span>
			</h3>
			<h3 class="whitetext tjustify desc"></h3>
		</div>
	</div>
	<div class="imageviewer">
		<div class="back"></div>
		<img class="image" />
		<div class="closebutton">
			<img src="imgs/Close.svg" />
		</div>
	</div>
	<div class="backgrey statblock" style="display: none;">
		<div class="container" style="padding-bottom: 0">
			<h1 class="bold statistics">Statistics</h1>
			<div class="row">
				<div class="cm6">
					<div class="whitebg shadow" style="padding: 20px">
						<h1 class="nomargin pageviews">0</h1>
						<h3 class="maincolor viewstoyourprofile">View(s) to Your
							Profile</h3>
					</div>
				</div>
				<div class="cm6">
					<div class="whitebg shadow" style="padding: 20px">
						<h1 class="nomargin albumviews">0</h1>
						<h3 class="maincolor viewstoyouralbums">View(s) to Your
							Albums</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="backgrey noalbum" style="display: none;">
		<div class="container">
			<h1 class="bold youralbums">Your Albums</h1>
			<div class="whitebg shadow">
				<br>
				<br>
				<br>
				<h1 class="noalbumsfound">No Albums Found</h1>
				<br>
				<div class="tcenter">
					<a href="photographerAlbum.jsp" class="button shadow manage">Manage</a>
				</div>
				<br>
				<br>
			</div>
		</div>
	</div>
	<div class="backgrey albumList" style="display: none;">
		<div class="container">
			<h1 class="bold youralbums">Your Albums</h1>
			<div class="row albumcontainer"></div>
			<br>
			<div class="tcenter">
				<a href="photographerAlbum.jsp" class="button shadow manage">Manage</a>
			</div>
		</div>
	</div>
	<div class="backgrey nocategory" style="display: none;">
		<div class="container" style="padding-top: 0;">
			<h1 class="bold yourpricing">Your Pricing</h1>
			<div class="whitebg shadow">
				<br>
				<br>
				<br>
				<h1 class="nocategoriesfound">No Categories Found</h1>
				<br>
				<div class="tcenter">
					<a href="photographerCategory.jsp" class="button shadow manage">Manage</a>
				</div>
				<br>
				<br>
			</div>
		</div>
	</div>
	<div class="backgrey categoryList" style="display: none;">
		<div class="container" style="padding-top: 0;">
			<h1 class="bold yourpricing">Your Pricing</h1>
			<div class="row categoryListBlock"></div>
			<br>
			<div class="tcenter">
				<a href="photographerCategory.jsp" class="button shadow manage">Manage</a>
			</div>
		</div>
	</div>
	<div>
		<div class="container contactdetails" style="display: none;">
			<h1 class="bold yourcontactdetail">Your Contact Detail</h1>
			<div class="row">
				<div class="cm4">
					<h3 class="tcenter maincolor bold address">Address</h3>
					<h3 class="addresspg tcenter"></h3>
				</div>
				<div class="cm4">
					<h3 class="tcenter maincolor bold email">Email</h3>
					<h3 class="emailpg tcenter"></h3>
				</div>
				<div class="cm4">
					<h3 class="tcenter maincolor bold contactno">Contact No.</h3>
					<h3 class="cntno tcenter"></h3>
				</div>
			</div>
			<br>
			<div class="tcenter">
				<div class="button editprofilebutton editprofile"
					style="margin-bottom: 10px;">Edit Profile</div>
				<div class="button changepassbutton changepassword">Change
					Password</div>
			</div>
		</div>
	</div>
	</section>
	<section class="editprofileblock backgrey" style="display: none;">
	<br>
	<br>
	<div class="row">
		<div class="cm2"></div>
		<div class="cm8">
			<div class="form">
				<h1 class="editprofile">Edit Profile</h1>
				<div style="box-sizing: border-box; overflow: hidden;">
					<div class="row">
						<div class="cm6 nomargin">
							<label class="firstname">First Name</label> <input type="text"
								class="fnbox">
						</div>
						<div class="cm6 nomargin">
							<label class="lastname">Last Name</label> <input type="text"
								class="lnbox">
						</div>
					</div>
					<div class="row">
						<div class="cm6 nomargin">
							<label class="address">Address</label> <input type="text"
								class="addressbox">
						</div>
						<div class="cm6 nomargin">
							<label class="description">Description</label> <input type="text"
								class="descbox">
						</div>
					</div>
					<div class="row">
						<div class="cm6 nomargin">
							<label class="mobilenumber1">Mobile Number 1</label> <input
								type="text" class="cntnobox">
						</div>
						<div class="cm6 nomargin">
							<label class="mobilenumber2">Mobile Number 2</label> <input
								type="text" class="cnt1nobox">
						</div>
					</div>
					<div class="row visible">
						<div class="cm6 nomargin">
							<label class="city">City</label>
							<div class="sugcontainer">
								<input type="text" class="citysearchbox sugrep">
								<ul class="sugbox citysugbox">
								</ul>
								<input type="text" class="citybox sugtarget"
									style="display: none;">
							</div>
						</div>
						<div class="cm6 nomargin">
							<label class="experience">Experience</label> <input type="text"
								class="xpbox">
						</div>
					</div>
					<div class="tcenter"
						style="margin-top: 125px; margin-bottom: 30px;">
						<button type="button" class="button updateButton update">Update</button>
						<button type="button" class="button cancelprofilebutton cancel">Cancel</button>
					</div>
					<div class="tcenter">
						<label class="error emptyError allfieldsarerequired"
							style="margin-top: 20px;">All fields are required</label>
					</div>
					<br>
				</div>
			</div>
		</div>
		<div class="cm2"></div>
	</div>
	<br>
	<br>
	</section>
	<section class="changepassblock backgrey" style="display: none;">
	<form method="post" action="PhotographerChangePasswordServlet">
	<input type="hidden" name="pid" value="<%=photographerBean.getPhotographersId()%>">
 		<br>
		<br>

		<div class="row">
			<div class="cm4"></div>
			<div class="cm4">
				<div class="form">
					<h1 class="changepassword">Change Password</h1>
					<div style="box-sizing: border-box; overflow: hidden;">
						<label class="oldpassword">Old Password</label>
						<div class="passwordbox">
							<input type="password" class="oldpassbox" name="oldpassbox">
							<div>
								<img src="imgs/eye.svg" />
							</div>
						</div>
						<label class="newpassword">New Password</label>
						<div class="passwordbox">
							<input type="password" class="newpassbox" name="newpassbox">
							<div>
								<img src="imgs/eye.svg" />
							</div>
						</div>
						<br>
						<div class="tcenter">
						<input type="submit" class="button updatepassButton change" value="Change">
							<!-- <button type="button" class="button updatepassButton change">Change</button> -->
							<button type="button" class="button cancelpassbutton cancel">Cancel</button>
						</div>
						<div class="tcenter">
							<label class="error emptyError allfieldsarerequired"
								style="margin-top: 20px;">All fields are required</label>
						</div>
						<br>
					</div>
				</div>
			</div>
			<div class="cm4"></div>
		</div>
		<br>
		<br>
	</form>
	</section>
	<%@ include file="photographerFooter.jsp"%>
	<div class="fullloader">
		<img src="imgs/loading.svg" />
	</div>
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/data.js"></script>
	<script type="text/javascript" src="js/photographerCode1.js"></script>
	<script type="text/javascript" src="js/photographerHome.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="js/photographerLang.js"></script>
</body>
</html>

 