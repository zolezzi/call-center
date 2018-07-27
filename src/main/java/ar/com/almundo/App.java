package ar.com.almundo;

import java.util.concurrent.PriorityBlockingQueue;

import ar.com.almundo.model.Call;
import ar.com.almundo.model.Employee;
import ar.com.almundo.model.Position;
import ar.com.almundo.service.Dispatcher;

public class App {
	
	private static final int MAX_CONCURRENT_DISPATCHER_TASKS = 10;
	
	public static void main( String[] args ) throws InterruptedException {
		System.out.println( "INITIALIZE CALL-CENTER" );
		
        PriorityBlockingQueue<Employee> employees = createEmployeeList();
       
        Dispatcher dispatcher = new Dispatcher(MAX_CONCURRENT_DISPATCHER_TASKS, employees);
        
        for(int i = 0; i<10 ; i++) {
        	dispatcher.dispatchCall(new Call(i));
        }

        dispatcher.shutdownExecutor();

	}
	
	private static PriorityBlockingQueue<Employee> createEmployeeList(){
		PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<Employee>();

		//7 operadores
		employees.add(new Employee("Ricardo Lopez", Position.OPERATOR));
		employees.add(new Employee("Jose Gonzalez", Position.OPERATOR));
		employees.add(new Employee("Sebastian Wainraich", Position.OPERATOR));
		employees.add(new Employee("Miguel Flores", Position.OPERATOR));
		employees.add(new Employee("Juan Sanchez", Position.OPERATOR));
		employees.add(new Employee("Morty Smith", Position.OPERATOR));		
		employees.add(new Employee("Matt Torres", Position.OPERATOR));
		
		//2 supervisores
		employees.add(new Employee("Maria Valdez", Position.SUPERVISOR));
		employees.add(new Employee("Jesica Diaz",Position.SUPERVISOR));
		
		//1 director
		employees.add(new Employee("Nestor Luna", Position.DIRECTOR));
		
		return employees;
	}

}
