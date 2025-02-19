package moa;

public class SiteInfo {
    private String url;
    private String nameXpath;
    private String priceXpath;

    public SiteInfo(String url, String nameXpath, String priceXpath) {
        this.url = url;
        this.nameXpath = nameXpath;
        this.priceXpath = priceXpath;
    }

    public String getUrl() {
        return url;
    }

    public String getNameXpath() {
        return nameXpath;
    }

    public String getPriceXpath() {
        return priceXpath;
    }
}
