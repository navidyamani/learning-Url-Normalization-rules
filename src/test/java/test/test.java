package test;

import patternTree.PatternTreeMaker;
import trainingSet.UrlDecomposer;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
//import java.util.HashMap;
//import java.util.Map;
import java.util.*;


/**
 * Created by navid
 */

public class test {

    @Test
    public void basicUrlDecomposerTest() throws MalformedURLException, UnsupportedEncodingException {
        UrlDecomposer kvpc = new UrlDecomposer();

        String urlStr = "HTTP://wWw.Example.cOm/";
        HashMap<String, String> urlMap = new HashMap<>();
        urlMap.put("protocol", "http");
        urlMap.put("auth0", "com");
        urlMap.put("auth1", "example");
        urlMap.put("auth2", "www");
        urlMap.put("port", "80");
        Assert.assertEquals(kvpc.decompose(urlStr), urlMap);
    }


    @Test
    public void fullFeatureUrlDecomposerTest() throws MalformedURLException, UnsupportedEncodingException {
        UrlDecomposer decomposer = new UrlDecomposer();
        String url1 = "http://wWw.Schönesdresden.DE:9090/%7EuserName/a%c2%b1b/Ex/search-results.jsp?Ne=292&N=461+21";

        HashMap<String, String> urlMap1 = new HashMap<>();
        urlMap1.put("protocol", "http");
        urlMap1.put("port", "9090");
        urlMap1.put("auth0", "de");
        urlMap1.put("auth1", "schönesdresden");
        urlMap1.put("auth2", "www");
        urlMap1.put("path0", "~userName");
        urlMap1.put("path1", "a±b");
        urlMap1.put("path2", "Ex");
        urlMap1.put("path3", "search-results.jsp");
        urlMap1.put("&N", "461 21");
        urlMap1.put("&Ne", "292");
        Assert.assertEquals(decomposer.decompose(url1), urlMap1);

        String defaultPort = "https://example.com/";
        Assert.assertEquals((decomposer.decompose(defaultPort)).get("port"),"443");

        String ceShiUrl = "http://例子.测试";
        Assert.assertEquals((decomposer.decompose(ceShiUrl)).get("auth0"),"测试");

        String dokimeUrl = "http://παράδειγμα.δοκιμή";
        Assert.assertEquals((decomposer.decompose(dokimeUrl)).get("auth0"),"δοκιμή");

        String url3 = "http://example.com/?1=a&&2=b&&&3=c&=d&&=e&&=f";
        Assert.assertEquals(decomposer.decompose(url3).get("&1"), "a");
        Assert.assertEquals(decomposer.decompose(url3).get("&&2"), "b");
        Assert.assertEquals(decomposer.decompose(url3).get("&&&3"), "c");
        Assert.assertEquals((decomposer.decompose(url3)).get("&"),"d");
        Assert.assertEquals((decomposer.decompose(url3)).get("&&"),"f");

        String url4 = "http://example.com/?1=a;2=b";
        Assert.assertEquals((decomposer.decompose(url4)).get("&1"),"a;2=b");

        String url5 = "http://example.com/?1=a&1=b";
        Assert.assertEquals((decomposer.decompose(url5)).get("&1"),"b");

        String url6 = "http://example.com/?=";
        Assert.assertNull((decomposer.decompose(url6)).get("&"));

        String nullUrl = null;
        //TODO

        String malformedUrl = "this is a malformed URL!";
        //TODO

//        Assert.assertThrows(decomposer.decompose(malformedUrl));


    }

    @Test
    public void testSortByValue()
    {
        Random random = new Random(System.currentTimeMillis());
        Map<String, Double> testMap = new HashMap<>(1000);
        for(int i = 0 ; i < 1000 ; ++i) {
            testMap.put( "SomeString" + random.nextInt(), random.nextInt()+.01);
        }

        testMap = PatternTreeMaker.sortMapByValue(testMap );
        Assert.assertEquals( 1000, testMap.size() );

        Double previous = null;
        for(Map.Entry<String, Double> entry : testMap.entrySet()) {
            Assert.assertNotNull( entry.getValue() );
            if (previous != null) {
                Assert.assertTrue( entry.getValue() >= previous );
            }
            previous = entry.getValue();
        }
    }

}

