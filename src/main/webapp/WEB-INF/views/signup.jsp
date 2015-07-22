<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<layout:front title="用户注册">
    <jsp:attribute name="css">

    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container">
            <div class="row margin100-t">
                <div class="col-md-4"></div>
                <div class="col-md-4 bordered padding20-lr padding20-tb">
                    <form method="post" action="${ctx}/register" >
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <input type="text" name="user.username" class="form-control" id="username" placeholder="输入您的用户名">
                        </div>
                        <div class="form-group">
                            <label for="username">真实姓名</label>
                            <input type="text" name="user.realname" class="form-control" id="username" placeholder="输入您的用户名">
                        </div>
                        <div class="form-group">
                            <label for="password">密码</label>
                            <input type="password" name="user.password" class="form-control" id="password" placeholder="输入您的密码">
                        </div>
                        <div class="form-group">
                            <label for="password">确认密码</label>
                            <input type="password" name="confirm_password" class="form-control" id="confirm_password" placeholder="再次输入您的密码">
                        </div>
                        <button type="submit" class="btn btn-default">注册</button>
                        <p>
                            已有账号，请 <a href="${ctx}/signin">登录</a>
                        </p>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script>
            (function () {

            })();
        </script>
    </jsp:attribute>
</layout:front>
