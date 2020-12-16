import java.util.Collections;
import java.util.ArrayList;
import java.io.*;
import java.util.*; 

public class CPUScheduler{
	// First Come First Serve scheduling algorithm implementation
	public static void FCFS(ArrayList<Process> incomingProcesses){
		   int cpuTime = 0;
		   // sort processes by arrival times to the CPU
		   Collections.sort(incomingProcesses, new SortByArrivalTime()); 
		   try 
		   {
				FileWriter writer = new FileWriter("output.txt");  
				PrintWriter outFile = new PrintWriter(writer);
				for (int i=0; i < incomingProcesses.size(); i++){
					outFile.print(cpuTime + " ");
					cpuTime += incomingProcesses.get(i).getBurstTime();
					outFile.print(cpuTime + " ");
					outFile.print(incomingProcesses.get(i).getProcessID());
					outFile.print("\n");
				}
			
				outFile.close();
			}
			catch(Exception e) {
				System.out.println("Error writing" + e);
			}       
    }
	// Preemptive Shortest Job First Scheduling Algorithm  
	public static void pSJF(ArrayList<Process> arrivingProcesses){
		int totalProcesses = arrivingProcesses.size();
		int currentCPUTime = 0;
		int totalProcessesCompleted = 0;
		int burst;
		int processStartTime;
		int processTimeOnCPU;
		
		//sort arriving processes by their arrival times
		Collections.sort(arrivingProcesses, new SortByArrivalTime());
		PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(totalProcesses, new SortByBurstTime());
		ArrayList<Integer> GanttChart = new ArrayList<Integer>();
		Process currentProcessInExecution = new Process();
		
		
		
		File outputFile = new File("output.txt");
		
		while(totalProcessesCompleted < totalProcesses){
			if(!arrivingProcesses.isEmpty()){
				if(arrivingProcesses.get(0).getArrivalTime() == currentCPUTime){
					if(GanttChart.isEmpty()){
						currentProcessInExecution = arrivingProcesses.get(0);
						GanttChart.add(currentCPUTime);
						arrivingProcesses.remove(arrivingProcesses.get(0));
					}
					else{
						readyQueue.add(arrivingProcesses.get(0));
						arrivingProcesses.remove(arrivingProcesses.get(0));
						Process lowestBurstTime = readyQueue.peek();
						if(lowestBurstTime.getBurstTime() < currentProcessInExecution.getBurstTime()){
							readyQueue.add(currentProcessInExecution);
							GanttChart.add(currentCPUTime);
							GanttChart.add(new Integer(currentProcessInExecution.getProcessID()));
							currentProcessInExecution = readyQueue.poll();
							GanttChart.add(currentCPUTime);
						}	
					}
				}
			}
			else{
				if(!readyQueue.isEmpty()){
					Process lowestBurstTimeInQueue = readyQueue.peek();	
					if(lowestBurstTimeInQueue.getBurstTime() < currentProcessInExecution.getBurstTime()){
						readyQueue.add(currentProcessInExecution);
						GanttChart.add(currentCPUTime);
						GanttChart.add(new Integer(currentProcessInExecution.getProcessID()));
						GanttChart.add(currentCPUTime);
						currentProcessInExecution = readyQueue.poll();
					}
				}
			}
			
			if(totalProcessesCompleted < totalProcesses){
				currentCPUTime++;	
				burst = currentProcessInExecution.getBurstTime();
				burst--;
				currentProcessInExecution.setBurstTime(burst);
			}
			
			
			if(currentProcessInExecution.getBurstTime() == 0){
				GanttChart.add(new Integer(currentCPUTime));
				GanttChart.add(new Integer(currentProcessInExecution.getProcessID()));
				totalProcessesCompleted++;
				if(totalProcessesCompleted < totalProcesses){
					GanttChart.add(new Integer(currentCPUTime));
				}
				currentProcessInExecution = readyQueue.poll();	
			}
		}
		try{
			FileWriter writer = new FileWriter("output.txt");  
			PrintWriter outFile = new PrintWriter(writer);
			for(int i = 0; i < GanttChart.size(); i++){   
				if(i % 3 == 0){
					outFile.print("\n");
				}
				outFile.print(GanttChart.get(i) + " "); 
			}
			outFile.close();
		}
		catch(Exception e) {
			System.out.println("Error writing" + e);
		}		
	}
}	
			
			
			

		
				
			
			
		
			
			
			
			
			

			
			
			






			
				
				
				
				
				
				
				
				
				
				
				
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
					