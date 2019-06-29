public class HttpResponse {
    private int responsecode;
    private StringBuilder responseMessage;


    void setResponsecode(int responsecode) {

        this.responsecode = responsecode;
    }

    void setResponseMessage(StringBuilder responseMessage) {

        this.responseMessage = responseMessage;
    }

    int getResponsecode() {

        return responsecode;
    }

    StringBuilder getResponseMessage() {

        return responseMessage;
    }
}


