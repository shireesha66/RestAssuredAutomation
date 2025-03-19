package testData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUsers {
	private String accountno;
	private String departmentno;
	private String salary;
	private String pincode;
	private String userId;
	private String Id;
	
	public AddUsers(String accountno, String departmentno, String salary, String pincode) {
		this.accountno = accountno;
		this.departmentno = departmentno;
		this.salary = salary;
		this.pincode = pincode;
	}
	//@JsonProperty("wrapper")
	public String getAccountno() {
		return accountno;
	}
	//@JsonProperty("wrapper")
	public String getdepartmentno() {
		return departmentno;
		
	}
	//@JsonProperty("wrapper")
	public String getsalary() {
		return salary;
		
	}
	//@JsonProperty("wrapper")
	public String getpincode() {
		return pincode;
	}
	//@JsonProperty("wrapper")
	public String getuserId() {
		return pincode;
	}
	//@JsonProperty("wrapper")
	public String getId() {
		return pincode;
	}

}
