package trainingSet;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by navid
 */
public class UrlDecomposer {

    public LinkedHashMap<String, String> decompose(String urlStr) throws MalformedURLException, UnsupportedEncodingException {

        LinkedHashMap<String, String> kvPair = new LinkedHashMap<>();
        URL aURL = new URL(urlStr);
        kvPair.put("protocol", aURL.getProtocol());
        kvPair.put("port", aURL.getPort() != -1 ? Integer.toString(aURL.getPort()) : Integer.toString(aURL.getDefaultPort()));
        kvPair.putAll(splitAuthority(aURL));
        kvPair.putAll(splitPath(aURL));
        kvPair.putAll(splitQuery1(aURL));

        return kvPair;
    }

    /***
     * parse authority into key value pairs
     *
     * @param url a normalized url
     * @return authority key value pairs
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> splitAuthority(URL url) throws UnsupportedEncodingException {
        Map<String, String> authority_pairs = new LinkedHashMap<String, String>();
        String authority = url.getHost();
        String[] pairs = authority.split("\\.");
        Collections.reverse(Arrays.asList(pairs));
        for (int i = 0; i < pairs.length; i++) {
            if (!pairs[i].isEmpty())
                authority_pairs.put("auth" + i, URLDecoder.decode(pairs[i].toLowerCase(), "UTF-8"));
        }

        return authority_pairs;
    }

    /***
     * parse path into key value pairs
     *
     * @param url a normalized url
     * @return path key value pairs
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> splitPath(URL url) throws UnsupportedEncodingException {
        Map<String, String> path_pairs = new LinkedHashMap<String, String>();
        String path = url.getPath();

        String[] pairs = path.split("/");
        int counter = 0;
        for (String pair : pairs) {
            if (!pair.isEmpty()) {
                path_pairs.put("path" + counter, URLDecoder.decode(pair, "UTF-8"));
                counter++;
            }
        }

        return path_pairs;
    }

    /***
     * parse a query string into key value pairs, This function handles parameters with no value as well
     *
     * @param url a normalized url
     * @return query key value pairs
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> splitQuery1(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        if (query != null) {
            String prefix = "";
            for (String pair : query.split("&")) {
                int idx = pair.indexOf("=");

                if (idx == -1 && pair.length() == 0) {
                    prefix += "&";
                } else {
//                    idx += prefix.length();
                    final String key = idx > 0 ? prefix + URLDecoder.decode(pair.substring(0, idx), "UTF-8") : prefix + "";
                    final String value = pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                    query_pairs.put("&" + key, value);
                    prefix = "";
                }

//                String[] parts = pair.split("=");
//                if (parts.length > 1) {
//                    query_pairs.put(URLDecoder.decode(parts[0], "UTF-8"), URLDecoder.decode(parts[1], "UTF-8"));
//                }

//            int idx = pair.indexOf("=");
//            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }

        }


        return query_pairs;
    }

    /***
     * parse a query string into its individual components, This function handles multiple parameters with the same key and parameters with no value as well.
     *
     * @param url a normalized url
     * @return key and list of values pairs
     * @throws UnsupportedEncodingException
     */
    public static Map<String, List<String>> splitQuery2(URL url) throws UnsupportedEncodingException {
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;
    }
}
