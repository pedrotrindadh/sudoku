package model.enums;

public enum GameStatusEnum {
	
	NON_STARTED("Non started"),
	INCOMPLETE("Incomplete"),
	COMPLETE("Complete");
	
    private String label;

    GameStatusEnum(final String label){
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
}
