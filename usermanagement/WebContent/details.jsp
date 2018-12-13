<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head><title>User management. Details</title></head>
<body>
   
    First name: ${user.firstName}<br>
    Last name: ${user.lastName}<br>
    Date of birth: <fmt:formatDate value="${user.dateOfBirth}" type="date" dateStyle="medium"/><br>
  
    <form action="<%=request.getContextPath()%>/details" method="get">
        <input type="submit" name="okButton" value="Ok">
    </form>
    <c:if test="${requestScope.error != null}">
        <script>
            alert("${requestScope.error}")
        </script>
    </c:if>
</body>
</html>