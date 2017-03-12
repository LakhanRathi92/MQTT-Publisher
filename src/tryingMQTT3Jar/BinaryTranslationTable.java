package tryingMQTT3Jar;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Creates binary translation table for publisher
 *   
 *   */

public class BinaryTranslationTable implements Serializable{
	public ArrayList<TableRow> table = new ArrayList<TableRow>();	
	
	public void addElement(TableRow rowAdd){
		table.add(rowAdd);				
	}
}
