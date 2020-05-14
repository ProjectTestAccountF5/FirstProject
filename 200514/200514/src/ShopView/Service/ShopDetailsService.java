package ShopView.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ShopView.ProductInfo;
import javafx.scene.Parent;

public interface ShopDetailsService {
	public void AddComboBox(Parent form,List<String> items, String cmbName);
	public int isComboBox(Parent detailsFrom, String cmbName);
//	public String getComboBoxString(Parent detailsForm, String cmbName);
	public void AddTitleLbl(Parent form, String prdName, String lblName);
	public void AddScoreLbl(Parent form, double score, String lblName);
	public void AddColorLbl(Parent form, String color, String lblName);
	public void AlmoSoldOutLbl(Parent form, int stock, String lblName);
	public void AddPriceLbl(Parent form, String price, String dcprice, String priceLbl, String dcpriceLbl);
	public void AddImgView(Parent form, String imgSrc, String imgName);
//	public List<ProductInfo> ProdInfoListMap(String prdName);
//	public boolean OpenDetailsProc(ProductInfo proInfo);
//	public Map<String, Object> getProInfo(String prdName);
}
