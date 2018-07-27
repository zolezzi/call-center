package ar.com.almundo.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import ar.com.almundo.model.Call;
import ar.com.almundo.model.Employee;

public class Dispatcher {
	
	private ExecutorService executorService;
	
	private PriorityBlockingQueue<Employee> employees;
	
	private int calls;
	
	public Dispatcher(int cantCurrenciesCalls, PriorityBlockingQueue<Employee> employees){
	
		this.executorService = Executors.newFixedThreadPool(cantCurrenciesCalls);
		this.employees = employees;
		this.calls = 0;
	}
	
	public void dispatchCall(Call call) throws InterruptedException {
		Employee employee = this.employees.take();
		executorService.submit(assignCall(employee,call));
	}
	
	private Runnable assignCall(Employee employee, Call call) {
		Runnable runnable = new Runnable() {

			@Override
            public void run() {
    			calls++;
    			employee.answerCall(call);
    			employees.add(employee);
            }

		};
		return runnable;
	}
	
	public void shutdownExecutor() {
		executorService.shutdown();
	}
	
	public int getCallsTotal() {
		return calls;
	}
	
	public int close(Long timeout) {
		
		executorService.shutdown();
       
		try {
            if (!executorService.awaitTermination(timeout, TimeUnit.SECONDS)) {
            	System.out.println("The call center is interrupted and calls left unfinished!");
            		executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
        	System.out.println("Interrupted!");
        }

		return calls;
	
	}
}
