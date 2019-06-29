import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.*;
import java.util.*;

public class HttpConnection {

    public HttpResponse request(HttpRequest httpRequest) {

        HttpResponse response = new HttpResponse();
        StringBuilder stringbuilder = new StringBuilder();
        InputStream inputStream;
        int readbytes;
        try {
            URL url = new URL(httpRequest.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpRequest.getHttpRequestmethod());
            Map<String, String> addingHeader = httpRequest.gettingHeader();
            for (Map.Entry<String, String> obj : addingHeader.entrySet()) {
                connection.setRequestProperty(obj.getKey(), obj.getValue());
            }

            if (!(httpRequest.getHttpRequestmethod().equals("GET") || httpRequest.getHttpRequestmethod().equals("HEAD") || httpRequest.getHttpRequestmethod().equals("OPTIONS"))) {
                try {
                    if (!(httpRequest.getContentPayload() == null)) {
                        connection.setDoOutput(true);
                        System.out.println(connection.getDoOutput());
                        OutputStream outputStream = connection.getOutputStream();
                        byte[] bytes = httpRequest.getContentPayload().getBytes();
                        outputStream.write(bytes);
                    }

                    response.setResponsecode(connection.getResponseCode());
                    if (response.getResponsecode() == 200 || connection.getResponseCode() == 201) {
                        inputStream = connection.getInputStream();
                    } else {
                        System.out.println(connection.getResponseCode());
                        inputStream = connection.getErrorStream();
                    }
                    while ((readbytes = inputStream.read()) != -1) {
                        stringbuilder.append((char) readbytes);
                    }
                    response.setResponseMessage(stringbuilder);
                    return response;

                } catch (IOException e) {
                    System.out.println("sorry unable to connect to server either your url is wrong or server error");
                    System.out.println("please try again later");
                    System.exit(0);
                }
            } else {
                try {
                    response.setResponsecode(connection.getResponseCode());
                    if (response.getResponsecode() == 200 || response.getResponsecode() == 201) {
                        inputStream = connection.getInputStream();
                    } else {
                        inputStream = connection.getErrorStream();
                    }
                    while ((readbytes = inputStream.read()) != -1) {
                        stringbuilder.append((char) readbytes);
                    }
                    response.setResponseMessage(stringbuilder);
                    return response;
                } catch (IOException e) {
                    System.out.println("you got the this error  " + e);
                }
            }
        } catch (Exception e) {
            System.out.println("you got this " + e);
            System.out.println("please try again later");
            System.exit(0);
        }
        return response;
    }

}

