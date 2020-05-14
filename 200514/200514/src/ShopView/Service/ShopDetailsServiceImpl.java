package ShopView.Service;

import java.util.List;

import ShopView.Data.IProductManage;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShopDetailsServiceImpl implements ShopDetailsService{
	
	private IProductManage prodManage;
	@Override
	public void AddComboBox(Parent form, List<String> items, String cmbName) {	//콤보박스 추가
		ComboBox<String> 	cmb =(ComboBox<String>)form.lookup(cmbName);
		
		if(cmb!=null) {
			for(String item:items)
				cmb.getItems().add(item);
		}
	}

	@Override
	public int isComboBox(Parent DetailsFrom, String cmbName) {	//콤보박스 체크했는지 ?
		ComboBox<String> 	cmb =(ComboBox<String>)DetailsFrom.lookup(cmbName);
		
		if(cmb==null) {System.out.println(cmb);return 0;}
		else if(!cmb.isPressed()/*cmb.getValue().toString()==null*/) {
			try {
				System.out.println(cmb.getValue().toString());
			} catch (NullPointerException e) {
				System.out.println("비어있음");
				cmb.requestFocus();
				return 0;
			}
		}
		return 1;
	}

	@Override
	public void AddTitleLbl(Parent form, String prdName, String lblName) {	//상세페이지 제품명
		Label lbl =(Label)form.lookup(lblName);
		lbl.setText(prdName);
	}

	@Override
	public void AddScoreLbl(Parent form, double score, String lblName) {	//별점
		Label lbl =(Label)form.lookup(lblName);
		String point ="";
		for(int i=1;i<=score;i++) {
			point+="★";
		}
		if(score%1!=0)
			point+="☆";
		
		lbl.setText(point);
	}

	@Override
	public void AddColorLbl(Parent form, String color, String lblName) {	//색상라벨
		Label lbl =(Label)form.lookup(lblName);
		lbl.setStyle("-fx-background-color : "+color+";");
	}

	@Override
	public void AlmoSoldOutLbl(Parent form, int stock, String lblName) {
		Label lbl =(Label)form.lookup(lblName);
		if(stock<5)
			lbl.setStyle("-fx-opacity: 1;"
					+ "-fx-border-color: darkred;");
		else
			lbl.setStyle("-fx-opacity: 0;");
	}

	@Override
	public void AddPriceLbl(Parent form, String price, String dcprice, String priceLbl, String dcpriceLbl) {
		Label won =(Label)form.lookup(priceLbl);
		Label dcwon =(Label)form.lookup(dcpriceLbl);
		won.setText(price+"won");
		if(dcprice.length()<2) {
//			System.out.println("할인x");
			dcwon.setStyle("-fx-opacity: 0;");
		}
		else
			dcwon.setText("→ "+dcprice+" won");
	}

	@Override
	public void AddImgView(Parent form, String imgSrc, String imgName) {
		ImageView img =new ImageView();
		img =(ImageView)form.lookup(imgName);
		img.setImage(new Image(imgSrc));
	}

}
