package ShopView.Service;

import java.io.IOException;

import CommonService.ICommonService;
import ShopView.Data.IProductManage;
import ShopView.Data.ProductManageImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ShopMainServiceImpl implements ShopMainService{
	
	private ProductManageImpl manImpl;
	private ICommonService comServ;
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
