
public class DefineFormat {

	protected String code;
	protected String name;
	protected int totalAmount;

	DefineFormat(String code, String name, int amount){
		this.code = code;
		this.name = name;
		this.totalAmount = amount;
	}

	public void setCode(String code){
		this.code = code;
	}

	public void setName(String name){
		this.name = name;
	}

	public void sumTotalAmount(int amount){
		this.totalAmount = this.totalAmount + amount;
	}

	public String getCode(){
		return this.code;
	}

	public String getName(){
		return this.name;
	}

	public int getTotalAmount(){
		return this.totalAmount;
	}
}
