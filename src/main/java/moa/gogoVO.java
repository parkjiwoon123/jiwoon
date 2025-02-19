package moa;

public class gogoVO {
    private int id;
    private String name;
    private double price;
    private String url;
    private String site;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public gogoVO(int id, String name, double price, String url, String site) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.url = url;
		this.site = site;
	}
	@Override
	public String toString() {
		return "gogoVO [id=" + id + ", name=" + name + ", price=" + price + ", url=" + url + ", site=" + site + "]";
	}

    public gogoVO() {
		// TODO Auto-generated constructor stub
	}
}
