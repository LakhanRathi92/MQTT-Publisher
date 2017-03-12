package tryingMQTT3Jar;

import java.io.Serializable;

public class TableRow implements Serializable{
	private int uid;
	private short offset;
	private short size;
	
	public void createRow(int id, short off, short siz){
		uid = id;
		offset = off;
		size = siz;
	}
	
	public int getUID(){
		return uid;
	}
	public short getOffset(){
		return offset;
	}
	public short getSize(){
		return size;
	}
}