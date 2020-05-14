package SearchItems.Service;

import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class KeywordSearchImpl implements iKeywordSearch{

	@Override
	public String KeywordSearch(Parent root) {
		String result = "에 대한 검색결과";
		BorderPane resultPane = (BorderPane) root.lookup("#resultPane");
		TextField inputTxt = (TextField) root.lookup("#inputKeyword");
		String keyword = inputTxt.getText();

		Label resultLbl = (Label) root.lookup("#resultClause");
		resultPane.setVisible(true);
		resultLbl.setText("'" + keyword + "'" + result);
		
		return keyword;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String SelectMenuCat(Parent root) {
		ComboBox<String> menuCat = (ComboBox<String>) root.lookup("#menuCat");
		String selectedCat = menuCat.getValue();
		String searchCat;
		switch (selectedCat) {
		case "전체":
			searchCat = "";
			break;
		case "신상품":
			searchCat = " AND tag='NEW' ";
			break;
		case "인기":
			searchCat = " AND tag='POPULAR' ";
			break;
		case "세일":
			searchCat = " AND tag='SALE' ";
			break;
		case "아우터":
			searchCat = " AND tag='OUTER' ";
			break;
		case "상의":
			searchCat = " AND tag='TOP' ";
			break;
		case "하의":
			searchCat = " AND tag='PANTS' ";
			break;
		case "잡화":
			searchCat = " AND tag='ACCESSORIES' ";
			break;
		default:
			searchCat = "";
			break;
		}
		return searchCat;
	}

}
