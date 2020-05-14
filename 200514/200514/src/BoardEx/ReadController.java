package BoardEx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import BoardEx.DB.BoardDBManageImpl;
import BoardEx.DB.IBoardDBManage;
import BoardEx.DB.ListController;
import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Window;

public class ReadController implements Initializable{
	ICommonService comserv = new CommonServiceImpl();
	ListController lstctrler = new ListController();
	IBoardDBManage dbmanager = new BoardDBManageImpl();
	public static String readnum;
	private Parent root;
	@FXML
	HTMLEditor contentHtml;
	@FXML
	TextField titleTxt;
	@FXML
	Button rewriteBtn;
	static public int boardstate;
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		readnum = lstctrler.setNum();
		System.out.println(readnum);
		List<String> readList = new ArrayList<String>();
		readList = dbmanager.ReadProc();
		titleTxt.setText(readList.get(2));
		contentHtml.setHtmlText(readList.get(7));
	}
	public void setBoardstate(int boardstate) {
		this.boardstate = boardstate;
	}
	public void RewriteProc(ActionEvent e) {
		if(contentHtml.isDisable()) {
			titleTxt.setEditable(true);
		contentHtml.setDisable(false);
		rewriteBtn.setText("완료");
		return;
		}
		if(!contentHtml.isDisable()) {
			titleTxt.setEditable(false);
			contentHtml.setDisable(true);
			rewriteBtn.setText("수정");
			String title = titleTxt.getText();
			String rewriteHtml = contentHtml.getHtmlText();
			dbmanager.UpdateBoard(readnum, title, rewriteHtml);
			return;
		}
	}
	public void ListBtnProc(ActionEvent e) {
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		Parent root = comserv.Load("../BoardEx/DB/BoardListEx.fxml");
		borderPane.setBottom(null);
		borderPane.setCenter(root);
		Window window = borderPane.getScene().getWindow();
		window.sizeToScene();
		ListController lstctrler = new ListController();
		lstctrler.setBoardState(boardstate);
	}
	public void DeleteBoardProc(ActionEvent e) {
		dbmanager.DeleteBoard(readnum);
		
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		root = (Parent)borderPane;
		BorderPane bp = (BorderPane)root;
		bp.setLeft(null);
		bp.setCenter(comserv.Load("../BoardEx/DB/BoardListEx.fxml"));
		bp.getScene().getWindow().sizeToScene();
		System.out.println("딜리트프록"+boardstate);
		ListController lstctrler = new ListController();
		lstctrler.setRoot(this.root);
		lstctrler.setBoardState(boardstate);
	}
	
	public void UpdateBoardProc(ActionEvent e) {
		dbmanager.DeleteBoard(readnum);
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		Parent root = comserv.Load("../BoardEx/DB/BoardListEx.fxml");
		borderPane.setTop(null);
		borderPane.setBottom(null);
		borderPane.setCenter(root);
		Window window = borderPane.getScene().getWindow();
		
	
	}

}
