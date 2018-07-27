package ar.com.almundo.model;

public class Employee implements Comparable<Employee> {
	
	private Long id;
	private String name;
	private Position position;

	public Employee(String name, Position position) {
		super();
		this.name = name;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
	}
	
	public void answerCall(Call call) {
			
		try {
			
			System.out.println(getName() + " is answering Call " + call.getId());
			
			Thread.sleep(call.getTime());
			
			System.out.println(getName() + " has finished Call " + call.getId() + " - Time spent: " + call.getTime() + " milliseconds");
		
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
			
	}

	@Override
	public int compareTo(Employee otherEmployee) {
		
		return this.getPosition().getCallPriority().compareTo(otherEmployee.getPosition().getCallPriority());
	}
	

}
