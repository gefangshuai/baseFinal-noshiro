<%@tag pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<%@attribute name="login" fragment="true" description="登录后显示的内容" %>
<%@attribute name="nologin" fragment="true" description="没有登录显示的内容" %>
<c:if test="${!empty(loginUser)}">
    <jsp:invoke fragment="login" />
</c:if>
<c:if test="${empty(loginUser)}">
    <jsp:invoke fragment="nologin" />
</c:if>