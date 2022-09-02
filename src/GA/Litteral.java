package GA;

public class Litteral {
	
	private int number;//le numero du litteral
	private int value;//la valeur du litteral
	
	//Constructor
	public Litteral(int number,int value) {
		this.number = number;
		this.value = value;
	}
	
	//Getters
	public int getNumber() {
		return number;
	}
	
	public int getValue() {
		return value;
	}
	
	//retourne la valeur du litteral dans la clause
	public int getValue(int value) {
		
		if(this.value == value) {
			return 1;
		}
		
		if(value == 1) {
			return this.value;
		}else {
			if(this.value == 1) {
				return 0;
			}else {
				return 1;
			}
		}
	}
}
