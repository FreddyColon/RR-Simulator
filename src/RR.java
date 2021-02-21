import java.util.LinkedList;
import java.util.Queue;
public class RR {
	private int quantum;
	private Queue<Process> readyQueue;
	Process[] process;
	int numOfProcess;
	int time = 0;
	// create Process array task control block
	RR(int quantum, Process[] process) {
		this.process = process;
		this.quantum = quantum;
		numOfProcess = process.length;
		readyQueue = new LinkedList<Process>();
		System.out.println("Quantum = " + this.quantum);
	}
	// set the remaining time of all processes to burst time since no process has
	// been scheduled yet
	private void setParam() {
		for (int i = 0; i < this.numOfProcess; i++) {
			this.process[i].setRemainingTime(this.process[i].getBurstTime());
			this.process[i].setFinalBurstTime(this.process[i].getBurstTime());
		}
	}

	public void run() {
		setParam();
		int count = 0;
		while (process[0].getArrivalTime() >= time) { // load first process into ready queue
			if (process[0].getArrivalTime() == time) {
				readyQueue.add(process[0]);
				System.out.println("First task arrived at " + time);
				count++;
				break;
			} else {
				time++;
				System.out.println("NO TASK AVAILABLE: advancing time by 1 to " + time);

			}
		}
		Process p = readyQueue.poll();
		while (p.getRemainingTime() != 0) { 
			// if true then this is the first time the process is being scheduled
			if (p.getRemainingTime() == p.getFinalBurstTime()) 
			{ 
				p.setBeginTime(time);
			}
			//the process can be completed in the given quantum
			if (p.getBurstTime() <= quantum)  
			{									   
				System.out.println("	Process " + p.getPID() + " scheduled at " + time);
				time += p.getBurstTime();
				System.out.println("Process " + p.getPID() + " COMPLETES at " + time);
				p.setRemainingTime(0);
				if (count < this.numOfProcess) {
					if (process[count].getArrivalTime() <= time) {
						this.process[count].setRemainingTime(this.process[count].getBurstTime());
						readyQueue.add(process[count]);
						System.out.println("TASK " + process[count].getPID() + " ARRIVES");
						count++;
					}
				}
				p.setEndTime(time);
				if (!readyQueue.isEmpty()) {
					p = readyQueue.poll();
				} else if (count < this.numOfProcess) {
					while (process[count].getArrivalTime() >= time) { // load all processes into ready queue
						if (process[count].getArrivalTime() == time) {
							this.process[count].setRemainingTime(this.process[count].getBurstTime());
							readyQueue.add(process[count]);
							System.out.println("TASK " + process[count].getPID() + " ARRIVES");
							p = readyQueue.poll();
							count++;
							break;
						} else {
							time++;
							System.out.println("NO TASK AVAILABLE: advancing time by 1 to " + time);

						}
					}
				}
			} else // burst time is greater than quantum, execute and store back into ready queue
			{
				p.setRemainingTime(p.getBurstTime() - quantum);
				System.out.print(
						"	Process " + p.getPID() + " started at time " + time + " ends at ");
				time += quantum;
				System.out.println(time);
				System.out.println("		!!!! Still needs " + p.getRemainingTime() + " time !!!!");
				int bt = p.getBurstTime() - quantum;
				p.setBurstTime(bt);

				// check if new process in available to add to readyQueue at current time
				if (count < this.numOfProcess) {
					if (process[count].getArrivalTime() <= time) {
						this.process[count].setRemainingTime(this.process[count].getBurstTime());
						readyQueue.add(process[count]);
						System.out.println("TASK " + process[count].getPID() + " ARRIVES");
						count++;
					}

				}
				readyQueue.add(p);
				if (readyQueue.isEmpty()) {
					if (count < this.numOfProcess) {
						while (process[count].getArrivalTime() >= time) { 
						//advance clock until time is equal to the arrival time of the next process
							if (process[count].getArrivalTime() == time) {
								readyQueue.add(process[count]);
								System.out.println("TASK " + process[count].getPID() + " ARRIVES");
								count++;
								break;
							} else {
								time++;
								System.out.println("NO TASK AVAILABLE: advancing time by 1 to " + time);

							}
						}
					}
				}
				p = readyQueue.poll();
			}
		}

	}
}