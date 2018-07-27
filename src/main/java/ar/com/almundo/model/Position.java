package ar.com.almundo.model;

public enum Position {
	
	OPERATOR(0, "Operador"), SUPERVISOR(1, "Supervisor"), DIRECTOR(2, "Director");
	
	private String description;
	private Integer callPriority;
	
	public String getDescription() {
		return this.description;
	}
	
	public Integer getCallPriority() {
		return callPriority;
	}
	
	Position(Integer callPriority, String description){
		this.description = description;
		this.callPriority = callPriority;
	}
}
