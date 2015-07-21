<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<layout:front title="测试项目">
    <jsp:attribute name="css">

    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="alert alert-danger text-center">
                        <h1>请求的页面未授权！</h1>
                    </div>
                </div>
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
