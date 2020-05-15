package BoardEx.DB;

public class Board {
	private int num;

	private String tag;
	private String title;
	private String writer;
	private String date;
	private int view;
	private int like;
	private String content;
	private int boardstate;
	private int prdnum;
	private int boardtype;
	
	public int getBoardtype() {
		return boardtype;
	}
	public void setBoardtype(int boardtype) {
		this.boardtype = boardtype;
	}
	public int getPrdnum() {
		return prdnum;
	}
	public void setPrdnum(int prdnum) {
		this.prdnum = prdnum;
	}
	public int getBoardstate() {
		return boardstate;
	}
	public void setBoardstate(int boardstate) {
		this.boardstate = boardstate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
}
