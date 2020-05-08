package SearchItems;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import SearchItems.DB.SelectDB;
import SearchItems.Service.*;

public class Controller implements Initializable {
	SelectDB selDB = new SelectDB();
	iDisplayOrder displayOrder = new DisplayOrderImpl();
	iKeywordSearch keywordSearch = new KeywordSearchImpl();
	iDetailSearch detailSearch = new DetailSearchImpl();
	private Parent root;
	private String keyword;
	private String searchCat = "";
	private String orderStr = "";
	private String priceOpt = "";
	private String sizeOpt = "";
	
	ICommonService comserv = new CommonServiceImpl();

	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void KeywordSearch(ActionEvent e) {
		root = comserv.getScene(e);
		
		keyword = keywordSearch.KeywordSearch(root);		
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
		
		MinPriceSet();
		MaxPriceSet();
	}

	public void SelectMenuCat() {
		searchCat = keywordSearch.SelectMenuCat(root);
	}

	public void PriceLowBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
	}

	public void PriceHighBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
	}

	public void NameOrdBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
	}

	public void DetailSearch() {
		priceOpt = detailSearch.PriceSearch(root);
		sizeOpt = detailSearch.SizeSearch(root);
		
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
	}
	
	public void MinPriceSet() {
		TextField minPriceTxt = (TextField) root.lookup("#minPrice");
		minPriceTxt.textProperty().addListener((obs,oldTxt,newTxt)->{
			minPriceTxt.setText(newTxt.replaceAll("[^0-9_]", ""));
		});
	}
	
	public void MaxPriceSet() {
	TextField maxPriceTxt = (TextField) root.lookup("#maxPrice");
	maxPriceTxt.textProperty().addListener((obs,oldTxt,newTxt)->{
		maxPriceTxt.setText(newTxt.replaceAll("[^0-9_]", ""));
	});
	}
}
