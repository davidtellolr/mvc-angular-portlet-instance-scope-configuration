<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />"></div>

<%
ExampleConfiguration configuration = (ExampleConfiguration) GetterUtil.getObject(
    renderRequest.getAttribute(ExampleConfiguration.class.getName()));

String favoriteColor = configuration.favoriteColor();
%>

<script type="text/javascript">
var exampleDTM_param='<%= favoriteColor %>';
</script>
<aui:script require="<%= mainRequire %>" >
	main.default('#<portlet:namespace />');
</aui:script>