package ShopView.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ShopView.ProductInfo;
import ShopView.ShopMainController;
import ShopView.Data.IProductManage;
import ShopView.Data.ProductManageImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ShopMainServiceImpl implements ShopMainService{
	
	private ProductManageImpl manImpl;
	private CommonService comServ;
	private ShopDetailsService shopDetailServ;
	private IProductManage prodManage;
	@Override
	public Parent OpenPrdDetails() {	//버튼 클릭시 실행
		System.out.println("============MainService===========");
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("../shopDetailsView.fxml"));
		Parent form = null;
		try {
			System.out.println(loader.getLocation());
			form = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return form;
	}
	

}
