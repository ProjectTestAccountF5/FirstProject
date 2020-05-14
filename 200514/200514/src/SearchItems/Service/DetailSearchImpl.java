package SearchItems.Service;

import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class DetailSearchImpl implements iDetailSearch{

	@Override
	public String PriceSearch(Parent root) {
		TextField minPriceTxt = (TextField) root.lookup("#minPrice");
		TextField maxPriceTxt = (TextField) root.lookup("#maxPrice");
		
		String minTxt = minPriceTxt.getText();
		String maxTxt = maxPriceTxt.getText();
		String priceOpt;
		
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
		}else
			priceOpt = "";
		
		return priceOpt;
	}

	@Override
	public String SizeSearch(Parent root) {
		CheckBox smallBox = (CheckBox) root.lookup("#small");
		CheckBox mediumBox = (CheckBox) root.lookup("#medium");
		CheckBox largeBox = (CheckBox) root.lookup("#large");
		CheckBox xlargeBox = (CheckBox) root.lookup("#xlarge");
		
		String sizeOpt;
		sizeOpt = " AND (prdsize like '%Free%'";
		if(smallBox.isSelected())
			sizeOpt = sizeOpt + " OR prdsize like '%S%'";
		if(mediumBox.isSelected())
			sizeOpt = sizeOpt + " OR prdsize like '%M%'";
		if(largeBox.isSelected())
			sizeOpt = sizeOpt + " OR prdsize like '%L%'";
		if(xlargeBox.isSelected())
			sizeOpt = sizeOpt + " OR prdsize like '%XL%'";
		sizeOpt = sizeOpt + ") ";
		return sizeOpt;
	}

}
