<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 	<script>
	/* $(document).ready(function() {
		alert("aaa");
		}); */
		alert("세션정보가 끊겼습니다. 다시 로그인 해주세요.");
		location = "logOut.bbs";
	</script> -->
</head>
<body>
 
 <div id="header-topbar-option-demo" class="page-header-topbar" style="width: 100%; height: 50px;" >
            <nav id="topbar" role="navigation" style="margin-bottom: 0;" data-step="3" class="navbar navbar-default navbar-static-top">
            <div class="navbar-header">
                <button type="button" data-toggle="collapse" data-target=".sidebar-collapse" class="navbar-toggle"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                <a id="logo" href="home.bbs" class="navbar-brand" style="padding-top: 17px;"><span class="fa fa-rocket"></span><span class="logo-text">APT Review</span><span style="display: none" class="logo-text-icon"></span></a></div>
            <div class="topbar-main">
                <ul class="nav navbar navbar-top-links navbar-right mbn">
                    <li id="topbar-chat" class="hidden-xs"><a href="logOut.bbs" data-step="4" data-intro="&lt;b&gt;Form chat&lt;/b&gt; keep you connecting with other coworker" data-position="left" class="btn-chat"><i class="fa fa-key"></i>Log in</a></li>
                    
                </ul>
            </div>
        </nav>
 </div>
</body>
</html>