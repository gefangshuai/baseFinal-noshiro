<%@tag pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<%@attribute name="property" required="true" description="用户属性" %>
<c:choose>
    <c:when test="${empty(loginUser)}">
        用户没有登录！
    </c:when>
    <c:when test="${property eq 'realname'}">
        ${loginUser.realname}
    </c:when>
    <c:when test="${property eq 'username'}">
        ${loginUser.username}
    </c:when>
    <c:otherwise>
        ${loginUser}
    </c:otherwise>
</c:choose>