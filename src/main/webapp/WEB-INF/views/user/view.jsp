<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<layout:front title="用户信息">
    <jsp:attribute name="css">

    </jsp:attribute>
    <jsp:attribute name="main">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <dl class="dl-horizontal">
                    <dt>Id</dt>
                    <dd>${user.id}</dd>
                    <dt>Username</dt>
                    <dd>${user.username}</dd>
                    <dt>Realname</dt>
                    <dd>${user.realname}</dd>
                    <dt>Email</dt>
                    <dd>${user.email}</dd>
                    <dt>Password</dt>
                    <dd>${user.password}</dd>
                </dl>
            </div>
        </div>
    </div>
    </jsp:attribute>
</layout:front>
