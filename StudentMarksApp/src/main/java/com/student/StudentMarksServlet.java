package com.student;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class StudentMarksServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("studentName");
        String s1 = request.getParameter("subject1");
        String s2 = request.getParameter("subject2");
        String s3 = request.getParameter("subject3");

        try {
            // Validate the student's name
            if (name == null || name.trim().isEmpty()
                    || name.trim().length() > 100) {
                throw new IllegalArgumentException(
                    "Please enter a valid student name.");
            }

            // Convert and validate all three marks
            double mark1 = parseMark(s1);
            double mark2 = parseMark(s2);
            double mark3 = parseMark(s3);

            // Calculate total and average
            double total = mark1 + mark2 + mark3;
            double average = total / 3.0;

            // Assign grade
            String grade;

            if (average >= 90) {
                grade = "A";
            } else if (average >= 75) {
                grade = "B";
            } else if (average >= 60) {
                grade = "C";
            } else if (average >= 40) {
                grade = "D";
            } else {
                grade = "Fail";
            }

            // Send the calculated results to the result page
            request.setAttribute("studentName", escapeHtml(name.trim()));
            request.setAttribute("total", total);
            request.setAttribute("average", average);
            request.setAttribute("grade", grade);

            RequestDispatcher dispatcher =
                request.getRequestDispatcher("/result.jsp");

            dispatcher.forward(request, response);

        } catch (IllegalArgumentException e) {
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            response.getWriter().println(
                "<!DOCTYPE html><html><head><meta charset='UTF-8'>"
                + "<title>Invalid Input</title></head><body>"
                + "<h2>Invalid Input</h2><p>"
                + escapeHtml(e.getMessage())
                + "</p><a href='index.html'>Go Back</a>"
                + "</body></html>");
        }
    }

    private double parseMark(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "All three subject marks are required.");
        }

        try {
            double mark = Double.parseDouble(value);

            if (!Double.isFinite(mark) || mark < 0 || mark > 100) {
                throw new IllegalArgumentException(
                    "Marks must be between 0 and 100.");
            }

            return mark;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Please enter valid numeric marks.");
        }
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
