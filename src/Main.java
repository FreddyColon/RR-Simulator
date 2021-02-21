import java.util.Scanner;
public class Main {
	private static Process p[];
	//create processes using math.random
	private static void generateProcess(int numTask) {
		p = new Process[numTask];
		for (int i = 0; i < numTask; i++) {
			int burstTime = 5 + (int)(Math.random() * ((5 - 5) + 1));
			int arrivalTime = 1 + (int)(Math.random() * ((20 - 5) + 1));
			p[i] = new Process(i, burstTime, arrivalTime);

		}
	}
	//sort processes by arrival times
	private static Process[] sortP(Process[] p, int numTasks) {
		for (int i = 0; i < numTasks; i++) {
			for (int j = i + 1; j < numTasks; j++) {
				if (p[i].getArrivalTime() > p[j].getArrivalTime()) {
					Process temp = p[i];
					p[i] = p[j];
					p[j] = temp;

				}
			}
		}
		return p;
	}
	
	private static void printProcessInformation(int numTasks) {
		for (int i = 0; i < numTasks; i++) {
			System.out.println("PID: " + p[i].getPID());
			System.out.println("Arrival Time: " + p[i].getArrivalTime());
			System.out.println("Burst Time: " + p[i].getBurstTime()+ "\n");
			
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Enter the number of processes to be created: ");
		Scanner in = new Scanner(System.in);
		int quantum = 4;
		int numTasks = in.nextInt();
		in.close();
		generateProcess(numTasks);
		sortP(p, numTasks);
		printProcessInformation(numTasks);
		//run RR algorithm
		RR roundrobin = new RR(quantum, p);
		roundrobin.run();
		
	}
}
