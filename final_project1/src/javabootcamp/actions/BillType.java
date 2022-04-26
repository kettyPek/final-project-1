package javabootcamp.actions;

public enum BillType {

	BANK,
	PHONE_COMPANY,
	WATER_COMPANY,
	ELECTRIC_COMPANY;
	
	public static BillType getBillType(int selection) {
		switch(selection){
		case 1:
			return BillType.BANK;
		case 2:
			return BillType.PHONE_COMPANY;
		case 3:
			return BillType.WATER_COMPANY;
		case 4: 
			return BillType.ELECTRIC_COMPANY;
		default:
		return null;
		}
	}
	
	public static void diaplayBillTypes() {
		System.out.println("1 - bank");
		System.out.println("2 - phone company");
		System.out.println("3 - water company");
		System.out.println("4 - electric company");
	}
}


