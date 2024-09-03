package precognox.apptest.application_test.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import precognox.apptest.application_test.service.XmlProcessorService;

import java.io.IOException;
import java.util.*;

public class DataProcessorServlet extends HttpServlet implements Servlet {

    private final XmlProcessorService xmlProcessorService = new XmlProcessorService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filePath = request.getParameter("filePath");
        String filter = request.getParameter("filter");
        String sortOrder = request.getParameter("sortOrder");
        String sortBy = request.getParameter("sortBy");

        // Process XML file
        Map<String, Integer> nameFrequencyMap = xmlProcessorService.processXmlFile(filePath);

        // Filter and sort the results based on user input
        if (filter != null && !filter.isEmpty()) {
            nameFrequencyMap.entrySet().removeIf(entry -> !entry.getKey().startsWith(filter));
        }

        if ("frequency".equals(sortOrder)) {
            nameFrequencyMap = sortByFrequency(nameFrequencyMap, sortBy);
        }

        // Set the results as a request attribute
        request.setAttribute("results", nameFrequencyMap);
        request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
    }



    private Map<String, Integer> sortByFrequency(Map<String, Integer> map, String sortBy) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        // Sort based on the sortBy parameter (asc or desc)
        if ("asc".equalsIgnoreCase(sortBy)) {
            list.sort(Map.Entry.comparingByValue());
        } else {
            list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        }

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
