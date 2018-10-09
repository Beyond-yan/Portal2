<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!-- added by Cube @130629 -->
<%@ taglib uri="/WEB-INF/userTag/PortalPager.tld" prefix="ut"%>

<c:set var="sio" value="${pageContext.servletContext.serverInfo }" />
<c:set var="rusr" value="${pageContext.request.remoteUser }"/>
<c:set var="radr" value="${pageContext.request.remoteAddr }"/>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sch" value="${pageContext.request.scheme}"/>
<c:set var="snm" value="${pageContext.request.serverName}"/>
<c:set var="spt" value="${pageContext.request.serverPort}"/>
<c:set var="bth" value="${sch}://${snm}:${spt}${ctx}/"/>
