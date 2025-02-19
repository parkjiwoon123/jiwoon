package moa;

public class Product {
    private int productId;
    private int userId;
    private String link;
    private String site;
    private String productName;

public Product() {
	// TODO Auto-generated constructor stub
}

public Product(int productId, int userId, String link, String site, String productName) {
	super();
	this.productId = productId;
	this.userId = userId;
	this.link = link;
	this.site = site;
	this.productName = productName;
}

public int getProductId() {
	return productId;
}

public void setProductId(int productId) {
	this.productId = productId;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getLink() {
	return link;
}

public void setLink(String link) {
	this.link = link;
}

public String getSite() {
	return site;
}

public void setSite(String site) {
	this.site = site;
}

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

@Override
public String toString() {
	return "Product [productId=" + productId + ", userId=" + userId + ", link=" + link + ", site=" + site
			+ ", productName=" + productName + "]";
}

}
