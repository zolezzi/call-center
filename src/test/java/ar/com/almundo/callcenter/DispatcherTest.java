package ar.com.almundo.callcenter;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import ar.com.almundo.model.Call;
import ar.com.almundo.model.Employee;
import ar.com.almundo.model.Position;
import ar.com.almundo.service.Dispatcher;

public class DispatcherTest {

	
	@Test
	public void test10CallsCurrencies() throws InterruptedException{
		
		PriorityBlockingQueue<Employee> employees = createEmployees();
		
		Dispatcher dispatcher = new Dispatcher(10, employees);

		for(int i = 0; i < 10; i++) {
			dispatcher.dispatchCall(new Call(i));
		}
		
		int callsTotal = dispatcher.close(10L);
		
		assertEquals(10, callsTotal);
	}
	
	@Test
	public void test20CallsCurrencies() throws InterruptedException{
		
		PriorityBlockingQueue<Employee> employees = createEmployees();
		
		Dispatcher dispatcher = new Dispatcher(10, employees);

		for(int i = 0; i < 20; i++) {
			dispatcher.dispatchCall(new Call(i));
		}
		
		int callsTotal = dispatcher.close(20L);
		
		assertEquals(20, callsTotal);
	}
	
	@Test
	public void testMoreEmployeesThanCalls() throws InterruptedException {
		
		PriorityBlockingQueue<Employee> employees = createEmployees();
		
		Dispatcher dispatcher = new Dispatcher(3, employees);

		for(int i = 0; i < 3; i++) {
			dispatcher.dispatchCall(new Call(i));
		}
		
		int callsTotal = dispatcher.close(15L);
		
		assertEquals(3, callsTotal);
		
	}
	
	private PriorityBlockingQueue<Employee> createEmployees(){
		
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
