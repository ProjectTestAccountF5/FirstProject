package ShopView;

import java.util.List;

public class ProductInfo {
	private String prdName;
	private String price;
	private String dcprice;
	private String color;
	private String prdsize;
	private int qty;
	private int stock;
	private String tag;
	private double score;
	private String imgsrc;
	private String imgdetail;
	private int prodNum;
	private List<Integer> prdOrderNum;
	
	public List<Integer> getPrdOrderNum() {
		return prdOrderNum;
	}
	public void setPrdOrderNum(List<Integer> prdOrderNum) {
		this.prdOrderNum = prdOrderNum;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getImgdetail() {
		return imgdetail;
	}
	public void setImgdetail(String imgdetail) {
		this.imgdetail = imgdetail;
	}
	public int getProdNum() {
		return prodNum;
	}
	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDcprice() {
		return dcprice;
	}
	public void setDcprice(String dcprice) {
		this.dcprice = dcprice;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrdsize() {
		return prdsize;
	}
	public void setPrdsize(String prdsize) {
		this.prdsize = prdsize;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
}
