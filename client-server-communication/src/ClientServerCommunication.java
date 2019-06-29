import java.io.*;

public class ClientServerCommunication {

    public static void main(String args[]) throws IOException {

        InputOutputOperation gettingUserInput = new InputOutputOperation();
        HttpRequest httpRequest = gettingUserInput.gettingInput();
        if (httpRequest == null) {
            System.out.println("please try again later");
            System.exit(0);
        }
        HttpConnection httpconection = new HttpConnection();
        HttpResponse httpResponse = httpconection.request(httpRequest);
        gettingUserInput.showingResponseInformation(httpResponse);
    }

}
