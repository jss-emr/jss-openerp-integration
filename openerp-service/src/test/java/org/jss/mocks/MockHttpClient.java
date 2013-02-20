package org.jss.mocks;


public class MockHttpClient extends org.jss.util.http.client.HttpClient {

    private RuntimeException exception = null;

    @Override
    public String post(String url, String formPostData) {
        if (exception != null) throw exception;
        return "";
    }

    public void throwException(RuntimeException exception) {
        this.exception = exception;
    }

    public void setResult(String result) {

    }

}
