package ar.com.almundo.callcenter;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import ar.com.almundo.model.Call;
import ar.com.almundo.model.Employee;
import ar.com.almundo.model.Position;

public class EmployeeTest {
	
	
	@Test
	public void testEmployeePriorityOperatorIsLessThanDirector() {
		/**
		 * Test de operador con un director y evalua la prioridad de atender una llamada en este caso da -1 porque 
		 * el priority del empleado de puesto de tipo operador es menor al del director
		 * 
		 * */
		Employee operator = new Employee("Ricardo", Position.OPERATOR);
		Employee director = new Employee("Gonzalo", Position.DIRECTOR);
		
		int value = operator.compareTo(director);
		
		assertEquals(value, -1);
	}
	
	@Test
	public void testQueueTakeEmployeeWithPiority() throws InterruptedException {
		/**
		 * Test de operador con un director optiene un empleado de la queue y compara 
		 * si es el empleado con prioridad de llamada 0 en este caso seria el operador
		 * 
		 * */
		
		Employee director = new Employee("Ricardo",Position.DIRECTOR);
		Employee operador = new Employee("Gonzalo", Position.OPERATOR);
		
		PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<Employee>();
		
		employees.add(director);
		employees.add(operador);
		
		Employee employeeResult = employees.take();
		
		System.out.println(employeeResult);
		
		assertEquals(new Integer(0), employeeResult.getPosition().getCallPriority());
	}
	
	@Test
	public void testEmployeeTypeDirectorAnswerCall() {
		/**
		 * Test de director atiende una llamada.
		 * 
		 * */
		
		Employee employee = new Employee("Gonzalo", Position.DIRECTOR);
		employee.answerCall(new Call(1));
	}

}
