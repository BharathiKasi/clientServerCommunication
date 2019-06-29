import java.util.*;

public class HttpRequest {

    private String url;
    private String httpRequestmethod;
    private String Payloadcontent;
    private String contentType;
    Map<String, String> addingHeader = new HashMap<>();

    void setUrl(String url) {

        this.url = url;
    }

    void setHttpRequestmethod(String method) {

        this.httpRequestmethod = method;
    }

    void setContentPayload(String contentPayload) {

        this.Payloadcontent = contentPayload;
    }

    void setPayloadContentType(String contentType) {
        this.contentType = contentType;
    }

    void addingHttpRequestHeader(String headerfield, String headerValue) {
        addingHeader.put(headerfield, headerValue);
    }

    Map<String, String> gettingHeader() {
        return addingHeader;
    }

    String getUrl() {
        return url;
    }

    String getContentPayload() {
        return Payloadcontent;
    }

    String getHttpRequestmethod() {
        return httpRequestmethod;
    }
}
