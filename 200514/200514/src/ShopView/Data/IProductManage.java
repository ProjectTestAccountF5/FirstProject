package ShopView.Data;

import java.util.List;

import ShopView.ProductInfo;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public interface IProductManage {
	public List<ProductInfo> getProductInfo(int prodNum);
	
	public List<VBox> InitialDisplay(String orderStr);

	public void DisplayOrder(String orderStr);
}
