package main.java.com.my.Demo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Main {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws IOException {

        request(Request.GET_ALL_EMPLOYEES, null);
        System.out.println("GET DONE");

        request(Request.GET_EMPLOYEE_BY_ID, 1);
        System.out.println("GET DONE");

        request(Request.POST_CREATE_NEW_EMPLOYEE, null, new Employee("test", 123, 23));
        System.out.println("POST DONE");

        request(Request.PUT_UPDATE_EMPLOYEE_BY_ID, 21, new Employee("test123", 123, 23));
        System.out.println("PUT DONE");

        request(Request.DELETE_EMPLOYEE_BY_ID, 2);
        System.out.println("DELETE DONE");
    }

    public static void request(Request req, Integer id) throws IOException {
        request(req, id, null);
    }

    public static void request(Request req, Integer id, Employee employee) throws IOException {
        HttpURLConnection con = Util.getCon(req.getMethodType(), id != null ? req.getUrlWithId(id) : req.getUrl());
        if (employee != null) {
            Util.out(new Gson().toJson(employee), con);
        }
        int responseCode = con.getResponseCode();
        System.out.println(req.getMethodType() + " Response Code :: " + responseCode);
        Util.in(responseCode, con);
    }

    private static class Util {

        public static HttpURLConnection getCon(String typeOfRequest, String address) throws IOException {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(typeOfRequest);
            con.setRequestProperty("User-Agent", USER_AGENT);
            return con;
        }

        public static void in(int responseCode, HttpURLConnection con) throws IOException {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response);
            } else {
                System.out.println("request not worked");
            }
        }

        public static void out(String data, HttpURLConnection con) throws IOException {
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            os.close();
        }
    }

}
