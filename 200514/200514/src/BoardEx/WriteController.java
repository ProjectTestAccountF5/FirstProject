package BoardEx;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import BoardEx.DB.Board;
import BoardEx.DB.BoardDBManageImpl;
import BoardEx.DB.IBoardDBManage;
import BoardEx.DB.ListController;
import BoardWrite.Service.BoardWriteServiceImpl;
import BoardWrite.Service.IBoardWriteService;
import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Popup;

public class WriteController implements Initializable{
	private static final String TableView = "";
	IBoardWriteService writeserv;
	ICommonService comServ;
	ListController lstCtrler;
	private Parent root;
	final int TAG = 0;
	final int TITLE = 1;
	final int WRITER = 2;
	final int CONTENT = 3;
	static int boardstate;
	private Popup popup;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	public void setBoardState(int boardstate) {
		this.boardstate=boardstate;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comServ = new CommonServiceImpl();
		writeserv = new BoardWriteServiceImpl();
		lstCtrler = new ListController();
	}
	private boolean isCheck(Map<String, TextField> txtFldMap, String []txtFldIdArr) {
		if(comServ.isEmpty(txtFldMap, txtFldIdArr)) {
			System.out.println("비어 있어요");
			return false;
		}
		return true;
	}
	public void WriteProc(ActionEvent e) {
		Parent root = (Parent)e.getSource();
		TextField Title = (TextField)root.getScene().getRoot().lookup("#titleTxt");
		System.out.println("제목 : " + Title.getText());
		HTMLEditor html = (HTMLEditor)root.getScene().getRoot().lookup("#contentHtml");
		System.out.println("HTML : " + html.getHtmlText());
		BoardProc(e);
	}
	public void CancelProc(ActionEvent e) {
		BorderPane borderPane = (BorderPane)comServ.getScene(e);
		System.out.println("보드스테이트" + boardstate);
		root = (Parent)borderPane;
		System.out.println("취소 : " + root);
		BorderPane bp = (BorderPane)root;
		bp.setLeft(null);
		bp.setCenter(comServ.Load("../BoardEx/DB/BoardListEx.fxml"));
		bp.getScene().getWindow().sizeToScene();
		ListController lstctrler =new ListController();
		lstctrler.setRoot(root);
		lstctrler.setBoardState(boardstate);
	}
	public void BoardProc(ActionEvent e) { // 작성글의 데이터를 BoardDB에 입력
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		root = comServ.getScene(e);
		String []txtFldIdArr = {"#tagTxt", "#titleTxt", "#writerTxt"};
		Map<String, TextField> txtFldMap = 
				comServ.getTextFieldInfo(root, txtFldIdArr);
		HTMLEditor content = (HTMLEditor)comServ.getScene(e).lookup("#contentHtml");
		
		if(!isCheck(txtFldMap, txtFldIdArr))
			return;
		
		Board board = new Board();
		board.setTag(txtFldMap.get(txtFldIdArr[TAG]).getText());
		board.setTitle((txtFldMap.get(txtFldIdArr[TITLE]).getText()));
		board.setWriter((txtFldMap.get(txtFldIdArr[WRITER]).getText()));
		board.setDate(format1.format(time).substring(0,10));
		board.setView(0);
		board.setLike(0);
		board.setContent(content.getHtmlText());
		board.setBoardstate(boardstate);
		
		if(writeserv.BoardProc(board))
		{	//comserv.ErrorMsg("게시글", "성공", "게시글 작성 성공");
		
		StackPane stackpane = new StackPane();
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		Label txtlbl1 = new Label("성공");
		txtlbl1.setFont(new Font(24));
		Label txtlbl2 = new Label("게시글 작성 성공");
		txtlbl2.setFont(new Font(24));
		Button failBtn = new Button("확인");
		failBtn.setFont(new Font(24));
		failBtn.setOnAction(event->{
			Parent poproot = comServ.getScene(event);
			Popup popscene = (Popup)poproot.getScene().getWindow();
			popscene.hide();
		});
		vbox.getChildren().addAll(txtlbl1,txtlbl2,failBtn);
		stackpane.getChildren().add(vbox);
		popup = comServ.showAlertPopUp(root.getScene(), "게시글", stackpane);
		
			CancelProc(e);
		}
		else
		{	//comServ.ErrorMsg("게시글","실패","관리자에게 문의하세요.");
		StackPane stackpane = new StackPane();
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		Label txtlbl1 = new Label("실패");
		txtlbl1.setFont(new Font(24));
		Label txtlbl2 = new Label("관리자에게 문의하세요.");
		txtlbl2.setFont(new Font(24));
		Button failBtn = new Button("확인");
		failBtn.setFont(new Font(24));  
		failBtn.setOnAction(event->{
			Parent poproot = comServ.getScene(event);
			Popup popscene = (Popup)poproot.getScene().getWindow();
			popscene.hide();
		});
		vbox.getChildren().addAll(txtlbl1,txtlbl2,failBtn);
		stackpane.getChildren().add(vbox);
		popup = comServ.showAlertPopUp(root.getScene(), "게시글", stackpane);
		CancelProc(e);
		}
	}
}
