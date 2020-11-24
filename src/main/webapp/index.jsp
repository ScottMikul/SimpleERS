<!--Specify the document type.-->
<!DOCTYPE html>

<!--The root tag of an HTML document is "html"-->
<html>
    <!--We put metadata about our web page within the head tags-->
    <head>
        <meta charset="UTF-8"/>
        <title>Welcome To RevBook!</title>
        <!--We can use the link tag to specify the location of our stylesheet-->
        <link rel="stylesheet" href="./css/bootstrap.min.css"></link>
        <!--The better (more standard) way of including JS is by using the script tag and linking to an external JS file. While you can do this here, it is customary to place the script tag (with a linked source> at the bottom of the body so that you can make sure that all elements have been rendered before you attempt to manipulate them.-->
        <meta http-equiv="Cache-control" content="No-Cache">
    </head>
    <!--This is where we place the elements that are visible to the end user.-->
    <!--This is inline CSS; it's generally bad practice to do this as it doesn't scale well as our document gets more complex and requires more style.-->
    <body style="background-color: rgb(240, 243, 245);">
        <!--We use divs to "div"-ide the page into different sections.-->
        <div class="container">
                


          <div class="jumbotron d-flex justify-content-center mt-4 ">
                 <h2>Employee Reimbursement Login</h2>
			     <form id="form" method="post" action="/simpleERS/api/login">
                    <!--Web elements have attributes (e.g. href, src, style, type, etc. We use these attributes to specify information about those elements.-->
                    <div class="m-2"><input type="text" placeholder="Username" name="username" /></div>
                    <div class="m-2"><input type="password" placeholder="Password" name="password" /></div>
                    <button class= "m-2">Login</button>
                </form>
        </div>
        </div>

<!--         <div id="testDiv">
        </div> -->
        <!--We are referencing an external JS file.-->
        <script src="./scripts/script.js"></script>
    </body>
</html>