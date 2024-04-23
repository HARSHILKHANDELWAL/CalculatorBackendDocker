//package CalculatorController;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.*;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//@WebServlet("/CalculatorController")
//public class CalculatorController extends HttpServlet {
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
////        System.out.println(request.getReader());
//        ObjectMapper objectMapper = null;
//        StringBuilder sb = new StringBuilder();
//        BufferedReader br = request.getReader();
//        String str = "";
//        Result result = new Result();
//        boolean flag = false;
//        ArthmeticEvaluator ae = new ArthmeticEvaluator();
//        int i = 0;
//        while ((str = br.readLine()) != null) {
//            sb.append(str);
//        }
//
//        System.out.println(sb);
//        objectMapper = new ObjectMapper();
//
//        String[] resultArrayAsArray = new String[0];
//        StringBuilder finalString = null;
//        try {
//            // Parse the JSON string into a JsonNode
//            JsonNode jsonNode = objectMapper.readTree(String.valueOf(sb));
//            // Extract the 'value' property from each object and form a list
//            List<String> resultArray = new ArrayList<>();
//            Iterator<JsonNode> elements = jsonNode.elements();
//            while (elements.hasNext()) {
//                JsonNode element = elements.next();
//                JsonNode type = element.get("type");
//                String type1 = type.toString();
////                System.out.println(type1+" type1");
//
//
//                if (type1.equals("\"NUMBER\"")) {
//
//                    if (ae.isInRange(-100000, 100000, element.get("value").asText())) {
//                        resultArray.add(element.get("value").asText());
//
//                    } else {
//                        flag = true;
//
//                    }
//
//                } else if (type1.equals("\"OPERATOR\"")) {
//
//                    String operation = element.get("value").asText();
//                    if (operation.equals("Addition") || operation.equals("Subtraction") || operation.equals("Multiply") || operation.equals("Divide")) {
//                        resultArray.add(element.get("value").asText());
//
//                    } else {
//                        flag = true;
//
//                    }
//
//
//                } else {
////                    JsonNode value = element.get("value");
//////                    System.out.println(value);
////                    resultArray.add(element.get("value").asText());
//                }
//            }
//
//            // Convert the list to an array if needed
//            resultArrayAsArray = resultArray.toArray(new String[0]);
//            finalString = new StringBuilder();
//            // Print the result
//
//            for (String value : resultArrayAsArray) {
//                String ans = null;
//                System.out.println(value.getClass().getSimpleName());
//
////                if (value.equals("Addition")) ans = value.replace("Addition", "+");
////                if (value.equals("Subtraction")) ans = value.replace("Subtraction", "-");
////                if (value.equals("Divide")) ans = value.replace("Divide", "/");
////                if (value.equals("Multiply")) ans = value.replace("Multiply", "x");
//
//                ans= checkTheValue(value);
//
//                if (ans != null) {
//                    finalString.append(ans);
//                    ans = null;
//                } else finalString.append(value);
//            }
//
//
//        } catch (Exception e) {
//            System.out.println("Error is there in request body");
////            e.printStackTrace();
//        }
////        String finalResult = String.valueOf(ae.calculate(String.valueOf(finalString)));
//        String finalResult = String.valueOf(ae.calculate(request));
//
//        Gson gson = new GsonBuilder().create();
//
//        response.setContentType("application/json");
//
////        result.setType("NUMBER");
//        if (flag) result.setValue("Please Check all the given values are correct or not");
//        else result.setValue(finalResult);
//
//
//        String data = gson.toJson(result);
//
//        System.out.println("Result: " + finalResult);
//        response.getWriter().write(data);
//
//    }
//
//    String checkTheValue(String value){
//        String ans=null;
//        if (value.equals("Addition")) ans = value.replace("Addition", "+");
//        if (value.equals("Subtraction")) ans = value.replace("Subtraction", "-");
//        if (value.equals("Divide")) ans = value.replace("Divide", "/");
//        if (value.equals("Multiply")) ans = value.replace("Multiply", "x");
//        return ans;
//    }
//}


//        ExpressionEvaluation ee = new ExpressionEvaluation();
//        String finalResult = String.valueOf(ae.evaluateArithmeticExpression(finalString.toString()));


//            System.out.println(Arrays.toString(resultArrayAsArray));
//            System.out.println(finalString);

package CalculatorController;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/CalculatorController")
public class CalculatorController extends HttpServlet {
    static double initialValue = 0.0;
ArthmeticEvaluator arthmeticEvaluator=new ArthmeticEvaluator();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        String data;
        Result result = new Result();
        Gson gson = new Gson();
        response.setContentType("JSON");

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        try {

            Button[] buttons = gson.fromJson(requestBody.toString(), Button[].class);

            String answer = String.valueOf(arthmeticEvaluator.calculate(buttons));

            System.out.println(answer);
            PrintWriter out = response.getWriter();

            result.setValue(answer);
             data = gson.toJson(result);
            response.getWriter().write(data);
            initialValue = 0;
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch(Exception e){
            System.out.println("Please Check the syntax of request body and values");
            result.setValue("Please Check the syntax of request body and values");
            data = gson.toJson(result);
            response.getWriter().write(data);
            initialValue = 0;
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
