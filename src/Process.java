
public class Process {
	
		private int PID;
		private int arrivalTime = 0;
		private int beginTime = 0;
		private int burstTime = 0;
		private int remainingTime = 0;
		private int endTime = 0;
		private int finalBurstTime = 0;
		
		//Constructor
		Process(int PID, int burstTime, int arrivalTime){
			this.PID = PID;
			this.burstTime = burstTime;
			this.arrivalTime = arrivalTime;
		}
		
		//Getter and setter methods
		public int getPID() {
			return this.PID;
		}
		public int getArrivalTime() {
			return this.arrivalTime;
		}
		public int getBeginTime() {
			return this.beginTime;
		}
		public void setBeginTime(int beginTime) {
			this.beginTime = beginTime;
		}
		public int getBurstTime() {
			return this.burstTime;
		}
		public void setBurstTime(int burstTime) {
			this.burstTime = burstTime;
		}
		public int getRemainingTime() {
			return this.remainingTime;
		}
		public void setRemainingTime(int remainingTime) {
			this.remainingTime = remainingTime;
		}
		public int getEndTime() {
			return this.endTime;
		}
		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}
		public int getFinalBurstTime() {
			return this.finalBurstTime;
		}
		public void setFinalBurstTime(int burstTime) {
			this.finalBurstTime = burstTime;
		}

}
