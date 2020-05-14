package SearchItems.Service;

import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;

public class DisplayOrderImpl implements iDisplayOrder {

	@Override
	public String DisplayOrder(Parent root) {
		ToggleButton priceLowBtn = (ToggleButton) root.lookup("#priceLow");
		ToggleButton priceHighBtn = (ToggleButton) root.lookup("#priceHigh");
		ToggleButton prdNameBtn = (ToggleButton) root.lookup("#prdNameOrd");
		String orderStr;

		if (priceLowBtn.isSelected()) {
			priceLowBtn.setSelected(false);
			orderStr = " ORDER BY price ASC";
			priceLowBtn.setFont(new Font(14));
			priceHighBtn.setFont(new Font(12));
			prdNameBtn.setFont(new Font(12));
		} else if (priceHighBtn.isSelected()) {
			priceHighBtn.setSelected(false);
			orderStr = " ORDER BY price DESC";
			priceLowBtn.setFont(new Font(12));
			priceHighBtn.setFont(new Font(14));
			prdNameBtn.setFont(new Font(12));
		}else if(prdNameBtn.isSelected()) {
			prdNameBtn.setSelected(false);
			orderStr = " ORDER BY prdName ASC";
			priceLowBtn.setFont(new Font(12));
			priceHighBtn.setFont(new Font(12));
			prdNameBtn.setFont(new Font(14));
		}else
			orderStr = "";
		return orderStr;
	}

}
