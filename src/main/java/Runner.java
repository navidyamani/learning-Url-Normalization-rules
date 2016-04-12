

import patternTree.*;
import trainingSet.UrlDecomposer;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Created by navid
 */

public class Runner {

    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {


        PatternTree patternTree = new PatternTree(new PatternTreeMaker().makePatternTree(null,null,null));



//        runUrlDecomposer();

    }

    private static void runUrlDecomposer() throws MalformedURLException, UnsupportedEncodingException {
        UrlDecomposer Decomposer = new UrlDecomposer();
        String url = "https://www1.surreycc.gov.uk.谷歌:9090/aclcoursefinder/vermögensberater/txt_csedets.asp?cse=21162&name=ST%20JOHN'S%20AMBULANCE%20HALL,%20HASLEMERE&vensid=94&grpkey=37&grpname=HASLEMERE&venphone=01483%20518558#top";


        System.out.println("\n\n"+Decomposer.decompose(url));

    }

}
