import java.io.BufferedInputStream;
import java.io.*;
import java.net.*;
import java.util.*;


public class InputOutputOperation {

    Scanner sc = new Scanner(System.in);
    HttpRequest httpRequest;

    public HttpRequest gettingInput() throws MalformedURLException {

        String url = gettingUrl();
        if (url == null) {
            return null;
        }

        String request_method = getingRequestMethod();
        if (request_method == null) {
            return null;
        }
        String contentPayload = getPayloadContent(request_method);
        String payloadContentType = null;
        if (contentPayload == null) {
            if (!(request_method.equals("GET") || request_method.equals("HEAD") || request_method.equals("OPTIONS") || request_method.equals("TRACE"))) {
                return null;
            }

        } else {
            payloadContentType = getPayloadContentType();
        }
        int maximumCheck = gettingAdditionalHeader();
        {
            if (maximumCheck == 5) {
                return null;
            }
        }
        httpRequest = new HttpRequest();
        httpRequest.setUrl(url);
        httpRequest.setHttpRequestmethod(request_method);
        httpRequest.setContentPayload(contentPayload);
        httpRequest.setPayloadContentType(payloadContentType);

        return httpRequest;
    }

    public String gettingUrl() {

        int maximumcheck = 1;
        while (true) {
            System.out.println("Enter the URL ");
            String url = sc.next();
            boolean status = validatingUrl(url);
            if (status) {
                return url;
            } else {
                if (maximumcheck == 5) {
                    System.out.println("your maximum tries is finished");
                    break;
                }
                System.out.println("you have " + (5 - maximumcheck) + "  chance");
                System.out.println("please enter the valid url--(eg)--https://www.google.com");
                maximumcheck++;
            }
        }
        return null;
    }


    public boolean validatingUrl(String url) {

        try {
            new URL(url).toURI();
            return true;

        } catch (Exception e) {

            return false;
        }
    }


    public String getingRequestMethod() {

        int maximumcheck = 1;
        while (true) {
            System.out.println("Enter the request method");
            String request_method = sc.next();
            request_method = request_method.toUpperCase();
            if ((request_method.equals("GET") || request_method.equals("PUT") || request_method.equals("POST") || request_method.equals("DELETE") || request_method.equals("CONNECT") || request_method.equals("TRACE") || request_method.equals("OPTIONS") || request_method.equals("HEAD"))) {
                return request_method;
            } else {
                if (maximumcheck == 5) {
                    System.out.println("your maximum try is finished");
                    break;
                }
                System.out.println(" invalid request method");
                System.out.println("you have " + (5 - maximumcheck) + " chance");
                maximumcheck++;
            }
        }
        return null;
    }


    public String getPayloadContent(String request_method) {

        String contentPayload = null;
        int userInput = 0;
        if (!(request_method.equals("GET") || request_method.equals("HEAD") || request_method.equals("OPTIONS") || request_method.equals("TRACE"))) {

            int maximumcheck = 1;
            while (true) {
                try {
                    System.out.println("do you want to add the content");
                    System.out.println("1.yes");
                    System.out.println("2.No");
                    userInput = sc.nextInt();
                    sc.nextLine();
                    if (userInput == 1) {
                        System.out.println("enter the payload content");
                        contentPayload = sc.nextLine();
                        //System.out.println("enter the content type ");
                        //String contentType = sc.nextLine();
                        //System.out.println(contentType);
                        httpRequest.setContentPayload(contentPayload);
                        //httpRequest.setContentType(contentType);
                        break;
                    } else if (userInput == 2) {
                        contentPayload = "notnull";
                        break;
                    } else {
                        maximumcheck++;
                        if (maximumcheck == 5) {
                            System.out.println("your maximum try is finished");
                            break;
                        }
                        System.out.println("please enter the number either 1 of 2");
                        System.out.println("you have  " + (5 - maximumcheck) + "  chance");
                    }
                } catch (Exception e) {
                    sc.nextLine();
                    maximumcheck++;
                    if (maximumcheck == 5) {
                        System.out.println("sorry your maximum try is finshed");
                        break;
                    }
                    System.out.println("your input is invalid ");
                    System.out.println("you have " + (5 - maximumcheck) + "chance");
                }
            }
        }
        return contentPayload;
    }

    public String getPayloadContentType() {

        String payloadContentType;
        while (true) {
            System.out.println("please enter the payload content type");
            payloadContentType = sc.nextLine();
            break;
        }
        return payloadContentType;

    }

    public int gettingAdditionalHeader() {

        int maximumcheck = 1;
        while (true) {
            try {
                System.out.println("Do you want to add headers");
                System.out.println("1.yes");
                System.out.println("2.No");
                int userInput = sc.nextInt();
                sc.nextLine();
                if (userInput < 1 || userInput > 2) {
                    maximumcheck++;
                    if (maximumcheck == 5) {
                        System.out.println("your maximum try is finished");
                        break;
                    }
                    System.out.println("the number should be 1 or 2");
                    System.out.println("you have " + (5 - maximumcheck) + " chance");
                    continue;
                }
                switch (userInput) {
                    case 1:
                        gettingHeaderFieldAndbValue();
                        break;
                    case 2:
                        break;
                }
            } catch (Exception e) {
                sc.nextLine();
                maximumcheck++;
                if (maximumcheck == 5) {
                    System.out.println("sorry your maximum try is finished");
                    break;
                }
                System.out.println("your input is invalid ");
                System.out.println("you have " + (5 - maximumcheck) + " chance");
                continue;
            }
            break;
        }
        return maximumcheck;
    }

    public void gettingHeaderFieldAndbValue() {

        int userInput = 0;
        int maximumcheck;
        while (true) {
            System.out.println("enter the Header field ");
            String headerfield = sc.nextLine();
            System.out.println("enter the header value");
            String headerValue = sc.nextLine();
            httpRequest.addingHttpRequestHeader(headerfield, headerValue);
            maximumcheck = 1;
            while (true) {
                try {
                    System.out.println("do you want to add one more header");
                    System.out.println("1.yes");
                    System.out.println("2.No");
                    userInput = sc.nextInt();
                    sc.nextLine();
                    if (userInput == 1) {
                        break;
                    } else if (userInput < 1 || userInput > 2) {
                        maximumcheck++;
                        if (maximumcheck == 5) {
                            System.out.println("your maximum try is finished");
                            break;
                        }
                        System.out.println("the number should be 1 or 2");
                        System.out.println("you have " + (5 - maximumcheck) + " chance");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    sc.nextLine();
                    maximumcheck++;
                    if (maximumcheck == 5) {
                        System.out.println("your maximum try is finished");
                        break;
                    }
                    System.out.println("sorry your entered invalid input");
                    System.out.println("you have " + (5 - maximumcheck) + " chance");
                }
            }
            if (userInput == 2 || maximumcheck == 5) {
                break;
            }

        }
    }

    public void showingResponseInformation(HttpResponse httpResponse) {

        System.out.println(" respose CODE is " + httpResponse.getResponsecode());
        System.out.println(" response content is   ");
        System.out.println(httpResponse.getResponseMessage());
        //}
    }


}
