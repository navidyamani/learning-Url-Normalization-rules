package trainingSet;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;

public class CorrectUrl {
    private String urlStr;
    private LinkedHashMap<String, String> keyValuePairs;
    //private List<> belongToNodeList;

    public CorrectUrl(String urlStr) throws MalformedURLException, UnsupportedEncodingException {
        this.urlStr = urlStr;
        this.keyValuePairs = new UrlDecomposer().decompose(urlStr);
    }



}
