import java.util.Scanner;
import java.util.*;
import java.io.*;

public class TestScheduling{
	public static void main(String[] args){
		
		int burstTime;
		int arrivalTime;
		int timeQuantum;
		int totalProcesses;
		int schedulingType;
		int priority;
		int processID = 1;
		
		ArrayList<Process> CPUProcesses = new ArrayList<>();
		HashMap<Integer, Runnable> cpuAlgorithms = new HashMap<Integer, Runnable>(); 
		cpuAlgorithms.put(0, () ->  CPUScheduler.FCFS(CPUProcesses));
		cpuAlgorithms.put(1, () ->  CPUScheduler.pSJF(CPUProcesses));
			
		try{
			File f = new File("input.txt");
			FileReader fr = new FileReader(f);
			Scanner scheduleInput = new Scanner(fr);
			
			// total number of processes
			totalProcesses = scheduleInput.nextInt(); 
			//whether algorithm is pre-emptive/non pre-emptive
			schedulingType = scheduleInput.nextInt(); 
			//time quantum if applicable(otherwise 0)
			timeQuantum = scheduleInput.nextInt(); 

				
			while(scheduleInput.hasNextLine()){
				String currentProcess = scheduleInput.nextLine();
				if(currentProcess.length() > 1){
					String[] processAttributes = currentProcess.split(" ");
					arrivalTime = Integer.parseInt(processAttributes[0]); 
					burstTime = Integer.parseInt(processAttributes[1]);
					priority = Integer.parseInt(processAttributes[2]); 
					CPUProcesses.add(new Process(arrivalTime, burstTime, priority, processID));
					processID++;
				}
			}
			cpuAlgorithms.get(schedulingType).run();
			scheduleInput.close();
		}
		catch(IOException e){
			 System.out.println("Error reading the input file" + e);
		}   
	}
}