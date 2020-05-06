package SearchItems;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import SearchItems.DB.SelectDB;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class Controller implements Initializable {
	SelectDB selDB = new SelectDB();
	private Parent root;
	private String orderStr = "";
	private String keyword;
	private String searchCat = "";
	private String priceOpt = "";
	
	ICommonService comserv = new CommonServiceImpl();

	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void KeywordSearch(ActionEvent e) {
		String result = "�� ���� �˻����";
		root = comserv.getScene(e);
		
		BorderPane resultPane = (BorderPane) root.lookup("#resultPane");
		TextField inputTxt = (TextField) root.lookup("#inputKeyword");
		keyword = inputTxt.getText();

		Label resultLbl = (Label) root.lookup("#resultClause");
		resultPane.setVisible(true);
		resultLbl.setText("'" + keyword + "'" + result);
		
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt);
		
		MinPriceSet();
		MaxPriceSet();
	}

	@SuppressWarnings("unchecked")
	public void SelectMenuCat() {
		ComboBox<String> menuCat = (ComboBox<String>) root.lookup("#menuCat");
		String selectedCat = menuCat.getValue();
		switch (selectedCat) {
		case "��ü":
			searchCat = "";
			break;
		case "�Ż�ǰ":
			searchCat = " AND tag='NEW' ";
			break;
		case "�α�":
			searchCat = " AND tag='POPULAR' ";
			break;
		case "����":
			searchCat = " AND tag='SALE' ";
			break;
		case "�ƿ���":
			searchCat = " AND tag='OUTER' ";
			break;
		case "����":
			searchCat = " AND tag='TOP' ";
			break;
		case "����":
			searchCat = " AND tag='PANTS' ";
			break;
		case "��ȭ":
			searchCat = " AND tag='ACCESSORIES' ";
			break;
		default:
			break;
		}
	}

	public void PriceLowBtn() {
		orderStr = " ORDER BY price ASC";
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt);
		ToggleButton priceLowBtn = (ToggleButton) root.lookup("#priceLow");
		ToggleButton priceHighBtn = (ToggleButton) root.lookup("#priceHigh");
		ToggleButton prdNameBtn = (ToggleButton) root.lookup("#prdNameOrd");
		priceLowBtn.setFont(new Font(18));
		priceHighBtn.setFont(new Font(15));
		prdNameBtn.setFont(new Font(15));
	}

	public void PriceHighBtn() {
		orderStr = " ORDER BY price DESC";
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt);
		ToggleButton priceLowBtn = (ToggleButton) root.lookup("#priceLow");
		ToggleButton priceHighBtn = (ToggleButton) root.lookup("#priceHigh");
		ToggleButton prdNameBtn = (ToggleButton) root.lookup("#prdNameOrd");
		priceLowBtn.setFont(new Font(15));
		priceHighBtn.setFont(new Font(18));
		prdNameBtn.setFont(new Font(15));
	}

	public void NameOrdBtn() {
		orderStr = " ORDER BY prdName ASC";
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt);
		ToggleButton priceLowBtn = (ToggleButton) root.lookup("#priceLow");
		ToggleButton priceHighBtn = (ToggleButton) root.lookup("#priceHigh");
		ToggleButton prdNameBtn = (ToggleButton) root.lookup("#prdNameOrd");
		priceLowBtn.setFont(new Font(15));
		priceHighBtn.setFont(new Font(15));
		prdNameBtn.setFont(new Font(18));
	}

	public void DetailSearch() {
		TextField minPriceTxt = (TextField) root.lookup("#minPrice");
		TextField maxPriceTxt = (TextField) root.lookup("#maxPrice");
		
		String minTxt = minPriceTxt.getText();
		String maxTxt = maxPriceTxt.getText();

		if (!minTxt.isEmpty() && !maxTxt.isEmpty()) {
			int minPrice = Integer.parseInt(minTxt);
			int maxPrice = Integer.parseInt(maxTxt);
			priceOpt = " AND price>" + minPrice + " AND price<" + maxPrice;
		} else if (!minTxt.isEmpty() && maxTxt.isEmpty()) {
			int minPrice = Integer.parseInt(minTxt);
			priceOpt = " AND price>" + minPrice;
		} else if (!maxTxt.isEmpty() && minTxt.isEmpty()) {
			int maxPrice = Integer.parseInt(maxTxt);
			priceOpt = " AND price<" + maxPrice;
		} else
			return;
		
		
		
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt);
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
