import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class SalesCSVFile {
	private static final String RECORD_FILE = ".rcd";

	private String path;

	SalesCSVFile(String path){
		this.path = path;
	}

	public static class OutType{
		public static final int BRANCH = 0;
		public static final int COMMODITY = 1;
	}

	public void setPath(String path){
		this.path = path;
	}

	public List<String> importDefineFile(String fileName) throws IOException{
		File file = new File(this.path+fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<String> strArray = new ArrayList<String>();
		String lineStr = br.readLine();
		while(lineStr != null){
			strArray.add(lineStr);
			lineStr = br.readLine();
		}

		return strArray;
	}

	public List<String[]> importRecordFile() throws IOException{
		File dir = new File(this.path);
	    File[] files = dir.listFiles();
	    BufferedReader br;
		List<String[]> strArray = new ArrayList<String[]>();
	    for(File file : files){
	    	if(file.getName().indexOf(RECORD_FILE) != -1){
		    	br = new BufferedReader(new FileReader(file));
		    	String lineStr = br.readLine();
		    	String[] tempStr = new String[3];
		    	int i = 0;
				while(lineStr != null){
					tempStr[i] = lineStr;
					lineStr = br.readLine();
					i++;
				}

				strArray.add(tempStr);
	    	}
	    }

	    return strArray;
	}

	public boolean exportFile(List<DefineFormat> defineFormatList, String fileName, int type) throws IOException{
		List<String[]> sales = importRecordFile(),
						printStr = new ArrayList<String[]>();
		for(DefineFormat defineFormat : defineFormatList){
			for(String[] strArray : sales){
				if(defineFormat.getCode().equals(strArray[type])){
					defineFormat.sumTotalAmount(Integer.parseInt(strArray[2]));
				}
			}
			if(defineFormat.getTotalAmount() != 0){
				printStr.add(new String[]{defineFormat.getCode(),defineFormat.getName(),String.valueOf(defineFormat.getTotalAmount())});
			}
		}

		File file = new File(path,fileName);

		try{
			file.createNewFile();
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			for(String[] str : printStr){

				pw.println(str[0]+","+str[1]+","+str[2]);
			}
			pw.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
