<%@tag pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<%@ attribute name="menu" description="菜单" required="true" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">测试项目</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li <c:if test="${empty(menu) || menu eq 'index'}">class="active"</c:if>>
                    <a href="${ctx}/user">用户管理</a>
                </li>
                <li <c:if test="${menu eq 'book'}">class="active"</c:if>><a href="${ctx}/book">书籍管理</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <wt:check-login>
                    <jsp:attribute name="login">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                欢迎：<wt:login-user property="realname"/>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">个人中心</a></li>
                                <li class="divider"></li>
                                <li><a href="${ctx}/logout">退出</a></li>
                            </ul>
                        </li>
                    </jsp:attribute>
                    <jsp:attribute name="nologin">
                        <li>
                            <a href="${ctx}/signin">请登录</a>
                        </li>
                    </jsp:attribute>
                </wt:check-login>
            </ul>
        </div>
    </div>
</nav>