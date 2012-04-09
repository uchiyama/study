
public class CheckFormat {

	public boolean isBranchFormat(DefineFormat branch){
		if(String.valueOf(branch.getCode()).length() == 3 &&
				isProhibitionString(branch.getName())){
			return true;
		}
		return false;

	}

	public boolean isCommodityFormat(DefineFormat commodity){
		if(commodity.getCode().length() == 8 &&
				commodity.getCode().matches("[0-9a-zA-Z]+") &&
				isProhibitionString(commodity.getName())){
			return true;
		}
		return false;

	}

	private boolean isProhibitionString(String str){
		if(str.indexOf(",") == -1 && str.indexOf("\n") == -1){
			return true;
		}
		return false;

	}
}
