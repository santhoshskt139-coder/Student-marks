
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Result</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5f9;
            padding: 40px 15px;
        }

        .result {
            max-width: 450px;
            margin: auto;
            padding: 25px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 12px #cccccc;
        }

        h2 {
            text-align: center;
            color: #243b64;
        }

        p {
            padding: 10px;
            background: #f5f7fa;
        }

        a {
            display: inline-block;
            margin-top: 15px;
            color: #2456a6;
        }
    </style>
</head>

<body>
    <div class="result">
        <h2>Student Result</h2>

        <p>
            Student Name:
            <strong>${studentName}</strong>
        </p>

        <p>
            Total Marks:
            <strong>${total} / 300</strong>
        </p>

        <p>
            Average Marks:
            <strong><%= String.format(java.util.Locale.US,
                "%.2f", ((Number)request.getAttribute("average"))
                .doubleValue()) %></strong>
        </p>

        <p>
            Final Grade:
            <strong>${grade}</strong>
        </p>

        <a href="index.html">Calculate Another Result</a>
    </div>
</body>
</html>
