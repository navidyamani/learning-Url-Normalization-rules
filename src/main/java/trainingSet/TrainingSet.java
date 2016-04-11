package trainingSet;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;


/**
 * Created by navid
 */
public class TrainingSet {
    String name;
    private List<DuplicateCluster> cluster;

}

class DuplicateCluster {
    private String name;
    private List<String> malformedUrls;
    private List<CorrectUrl> correctUrls;

    public DuplicateCluster(String name, List<String> urlStrList) {
        this.name = name;
        for (String urlStr : urlStrList) {
            try {
                addCorrectUrl(new CorrectUrl(urlStr));
            } catch (MalformedURLException e) {
                e.printStackTrace();
                addMalformedUrl(urlStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                addMalformedUrl(urlStr);
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<CorrectUrl> getCorrectUrls() {
        return correctUrls;
    }

    public void setCorrectUrls(List<CorrectUrl> correctUrls) {
        this.correctUrls = correctUrls;
    }

    public void addCorrectUrl(CorrectUrl correctUrl) {
        this.correctUrls.add(correctUrl);
    }

    public List<String> getMalformedUrls() {
        return malformedUrls;
    }

    public void setMalformedUrls(List<String> malformedUrls) {
        this.malformedUrls = malformedUrls;
    }

    public void addMalformedUrl(String malformedUrl) {
        this.malformedUrls.add(malformedUrl);
    }

}

