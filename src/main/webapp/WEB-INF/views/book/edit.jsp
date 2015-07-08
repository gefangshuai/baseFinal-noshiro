<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<layout:front title="书籍编辑">
    <jsp:attribute name="css">

    </jsp:attribute>
    <jsp:attribute name="main">
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form action="/book/save" method="post">
                    <c:if test="${!empty(book.id)}">
                        <input type="text" value="${book.id}" name="book.id"/>
                    </c:if>
                    <div class="form-group">
                        <label for="name">书籍名称</label>
                        <input type="text" class="form-control" id="name" name="book.name" value="${book.name}">
                    </div>
                    <div class="form-group">
                        <label for="author">作者</label>
                        <input type="text" class="form-control" id="author" name="book.author" value="${book.author}">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
    </jsp:attribute>
</layout:front>
