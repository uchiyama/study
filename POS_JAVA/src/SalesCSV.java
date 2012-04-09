import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SalesCSV {
	private static final String BRANCH_FILE = "/branch.lst";
	private static final String COMMODITY_FILE = "/commodity.lst";
	private static final String BRANCH_OUT_FILE = "/branch.out";
	private static final String COMMODITY_OUT_FILE = "/commodity.out";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SalesCSVFile file = new SalesCSVFile(args[0]);
		List<DefineFormat> branchList;
		List<DefineFormat> commodityList;
		CheckFormat checkFormat = new CheckFormat();

		try {
			List<String> branchStr = file.importDefineFile(BRANCH_FILE);
			List<String> commodityStr = file.importDefineFile(COMMODITY_FILE);
			branchList = createDefineFormatList(branchStr);
			commodityList = createDefineFormatList(commodityStr);
			boolean branchFlag = true, commodityFlag = true;

			for(DefineFormat branch : branchList){
				if(!checkFormat.isBranchFormat(branch)){
					System.out.println("支店フォーマットが違います。");
					branchFlag = false;
					break;
				}
			}
			if(branchFlag){
				file.exportFile(branchList, BRANCH_OUT_FILE,ExportType.BRANCH);
			}

			for(DefineFormat commodity : commodityList){
				if(!checkFormat.isCommodityFormat(commodity)){
					System.out.println("商品フォーマットが違います。");
					commodityFlag = false;
					break;
				}
			}
			if(commodityFlag){
				file.exportFile(commodityList, COMMODITY_OUT_FILE,ExportType.COMMODITY);
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	private static List<DefineFormat> createDefineFormatList(List<String> strList){
		List<DefineFormat> list = new ArrayList<DefineFormat>();
		for(String str : strList){
			String[] tempStrArray = str.split(",");
			DefineFormat commodity = new DefineFormat(tempStrArray[0],tempStrArray[1],0);
			list.add(commodity);
		}

		return list;
	}

}
