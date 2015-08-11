<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<layout:front title="编辑学生">
    <jsp:attribute name="main">
        <div class="container">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form action="save" method="post">
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <input type="text" class="form-control" id="username" name="student.username">
                        </div>
                        <div class="form-group">
                            <label for="email">邮&nbsp;&nbsp;件</label>
                            <input type="text" class="form-control" id="email" name="student.email">
                        </div>
                        <div class="form-group">
                            <label for="school">学&nbsp;&nbsp;校</label>
                            <input type="text" class="form-control" id="school" name="student.school">
                        </div>
                        <button type="submit" class="btn btn-block btn-primary">提交</button>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </jsp:attribute>
</layout:front>