package BoardEx.DB;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class TableRowDataModel{
	private IntegerProperty numList;
	private StringProperty tagList;
	private StringProperty titleList;
	private StringProperty writerList;
	private StringProperty dateList;
	private IntegerProperty viewList;
	private IntegerProperty likeList;

	public TableRowDataModel(IntegerProperty numList , StringProperty tagList, StringProperty titleList, 
			StringProperty writerList,StringProperty dateList, IntegerProperty viewList, IntegerProperty likeList) {
		this.numList = numList;
		this.tagList = tagList;
		this.titleList = titleList;
		this.writerList = writerList;
		this.dateList = dateList;
		this.viewList = viewList;
		this.likeList = likeList;
	}  
	public IntegerProperty numListProperty() {
		return numList;
	}
	public StringProperty tagListProperty() {
		return tagList;
	}
	public StringProperty titleListProperty() {
		return titleList;
	}
	public StringProperty writerListProperty() {
		return writerList;
	}
	public StringProperty dateListProperty() {
		return dateList;
	}
	public IntegerProperty viewListProperty() {
		return viewList;
	}
	public IntegerProperty likeListProperty() {
		return likeList;
	}
}


