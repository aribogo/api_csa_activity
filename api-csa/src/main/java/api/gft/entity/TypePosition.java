package api.gft.entity;

public enum TypePosition {

	CAMPER("Camper"), WORKER("Worker"), HIVE("Hive"), GARDENER("Gardener"), FARMER("Farmer"), MANAGER("Manager");

	private final String displayValue;

	private TypePosition(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	
	public static TypePosition getEnum(String displayValue) {
		for(TypePosition p: TypePosition.values()) {
			if(p.getDisplayValue().equalsIgnoreCase(displayValue)) {
				return p;
			}
		}
		return null;
	}
}
