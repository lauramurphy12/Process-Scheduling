import java.util.Comparator; 

class Process
{
	private int arrivalTime;
	private int burstTime;
	private int priority;
	private int processID;
	private int startOnCPU;
	
	public Process(int arrivalTime, int burstTime, int priority, int processID){
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
		this.processID = processID;
	}
	public void setArrivalTime(int arrival){
		arrivalTime=arrival;
    }
	public int getArrivalTime(){
		return arrivalTime;
    }
	
	public void setBurstTime(int bt){
		burstTime=bt;
    }
	public int getBurstTime(){
		return burstTime;
    }
	
	public void setProcessID(int pID){
		processID=pID;
    }
	public int getProcessID(){
		return processID;
    }
	public void setPriority(int p){
		priority=p;
    }
	public int getPriority(){
		return priority;
    }
	public void setStartTimeOnCPU(int start){
		startOnCPU=start;
    }
	public int getStartTimeOnCPU(){
		return startOnCPU;
    }
	
	 public String toString(){
		return "CPU Process(process ID="+processID+"arrivalTime="+arrivalTime+"BurstTime="+burstTime+"priority="+priority+")";
	}
	
	public boolean equals(Process p){
		return this.arrivalTime==p.arrivalTime && this.burstTime == p.burstTime && this.priority == p.priority && this.processID == p.processID;
	}
	
}

class SortByArrivalTime implements Comparator<Process> 
{ 
    // Sort processes by their arrivals at the CPU
    public int compare(Process a, Process b) 
    { 
        return a.arrivalTime - b.arrivalTime; 
    } 
} 
 
class SortByBurstTime implements Comparator<Process> 
{ 
    // Sort processes by their CPU burst times
    public int compare(Process a, Process b) 
    { 
        return a.burstTime - b.burstTime; 
    } 
} 

 
