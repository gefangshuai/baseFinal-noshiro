<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<layout:front title="书籍列表">
    <jsp:attribute name="css">

    </jsp:attribute>
    <jsp:attribute name="main">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading" style="height: 50px;">
                        <form class="input-group col-sm-3 pull-left" method="get" action="/book/page">
                            <input type="text" class="form-control input-sm" name="key" placeholder="Search for...">
                            <span class="input-group-btn">
                                <button class="btn btn-default btn-sm" type="submit">Go!</button>
                            </span>
                        </form>
                        <a href="${ctx}/book/edit/0" class="pull-right">新增</a>
                    </div>
                    <div class="panel-body">
                        <table class="table">
                            <thread>
                                <tr>
                                    <th width="80">序号</th>
                                    <th width="250">书名</th>
                                    <th width="250">作者</th>
                                    <th width="120">操作</th>
                                </tr>
                            </thread>
                            <tbody>
                            <c:forEach items="${books}" var="book" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td>${book.name}</td>
                                    <td>${book.author}</td>
                                    <td>
                                        <a href="/book/edit/${book.id}">编辑</a> | <a
                                            href="/book/delete/${book.id}">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <div class="panel-footer">
                        <ul class="pagination">
                            <c:forEach begin="1" step="1" end="${bookPage.totalPage}" varStatus="i">
                                <li><a href="?page=${i.index}">${i.index}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </jsp:attribute>
</layout:front>
