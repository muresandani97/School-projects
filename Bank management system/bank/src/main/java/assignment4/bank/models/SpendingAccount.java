package assignment4.bank.models;

@SuppressWarnings("serial")
public class SpendingAccount extends Account implements java.io.Serializable {
	private float comission;

	public SpendingAccount(int id, float comission) {
		super(id, 0);
		this.comission = comission;
	}

	public float getComission() {
		return this.comission;
	}

	public void setComission(float comission) {
		this.comission = comission;
	}

	public float computeTaxes(int sum) {
		return (comission / 100) * sum;
	}
}
