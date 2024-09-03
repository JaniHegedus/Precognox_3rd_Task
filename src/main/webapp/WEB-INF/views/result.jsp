<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<h2>Results:</h2>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Frequency</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Assuming you have a map of names and frequencies stored in the request scope as an attribute named "results"
        Map<String, Integer> resultsMap = (Map<String, Integer>) request.getAttribute("results");

        if (resultsMap != null) {
            // Convert the map's entry set to a list
            List<Map.Entry<String, Integer>> resultsList = new ArrayList<>(resultsMap.entrySet());

            for (Map.Entry<String, Integer> entry : resultsList) {
                String name = entry.getKey();
                Integer frequency = entry.getValue();
    %>
    <tr>
        <td><%= name %></td>
        <td><%= frequency %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2">No data available</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
