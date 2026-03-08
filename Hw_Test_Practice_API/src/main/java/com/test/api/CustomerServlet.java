package com.test.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.CustomerService;

@WebServlet("/api/customer")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "POST");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String method)
            throws IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        String name = "";
        String birthDate = "";

        if ("GET".equals(method)) {
            name = request.getParameter("name");
            birthDate = request.getParameter("birthDate");
        } else if ("POST".equals(method)) {
            BufferedReader reader = request.getReader();
            String requestBody = reader.lines().collect(Collectors.joining());

            name = extractValue(requestBody, "name");
            birthDate = extractValue(requestBody, "birthDate");
        }

        if (name == null) name = "";
        if (birthDate == null) birthDate = "";

        String jsonResponse;

        if (name.isEmpty() || birthDate.isEmpty()) {
            jsonResponse = "{"
                    + "\"customerNo\":\"\","
                    + "\"result\":\"INVALID_REQUEST\""
                    + "}";
        } else {
            String customerNo = customerService.findCustomerNo(name, birthDate);

            if (!customerNo.isEmpty()) {
                jsonResponse = "{"
                        + "\"customerNo\":\"" + customerNo + "\","
                        + "\"result\":\"SUCCESS\""
                        + "}";
            } else {
                jsonResponse = "{"
                        + "\"customerNo\":\"\","
                        + "\"result\":\"NOT_FOUND\""
                        + "}";
            }
        }

        response.getWriter().write(jsonResponse);
    }

    private String extractValue(String json, String key) {
        String target = "\"" + key + "\"";
        int keyIndex = json.indexOf(target);
        if (keyIndex == -1) {
            return "";
        }

        int colonIndex = json.indexOf(":", keyIndex);
        int firstQuote = json.indexOf("\"", colonIndex + 1);
        int secondQuote = json.indexOf("\"", firstQuote + 1);

        if (firstQuote == -1 || secondQuote == -1) {
            return "";
        }

        return json.substring(firstQuote + 1, secondQuote);
    }
}