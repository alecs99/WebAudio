<html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="icon" type="image/png" href="assets/favicon.ico"/>
<link rel="stylesheet" href="style/mainStyle.css">
<link rel="stylesheet" href="style/loginStyle.css">
<title>Login</title>
<body>
<div class="container">
    <h1 class="text-center text-capitalize">Welcome!</h1>
    <div id="login-box" class="col-md-12">
        <form id="login-form"  action="loginServlet" method="post">
            <h4 class="text-center">Please log in!</h4>
            <div class="col-md-12">
                <div class="form-group">
                    <label>Username:</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroupPrepend">@</span>
                        </div>
                    <input type="text" name="username" class="form-control" placeholder="Enter your username">
                    </div>
                </div>
                <div class="form-group mb-15">
                    <label>Password:</label>
                    <input type="password" name="password" class="form-control" placeholder="Enter your password">
                </div>
                <button type="submit" class="btn btn-outline-primary rounded">Login</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>