package SearchItems.Service;

import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class KeywordSearchImpl implements iKeywordSearch{

	@Override
	public String KeywordSearch(Parent root) {
		String result = "�� ���� �˻����";
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
			searchCat = "";
			break;
		}
		return searchCat;
	}

}
