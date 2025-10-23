public class Process extends BlankProcess {
    int waitingTime;        // Time process has been in queue without execution
    int timeInService;      // Time process has already been executed
    int completionTime;     // Time at which process is completed

    public int getWaitingTime() {
        return waitingTime;
    }

    private void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTimeInService() {
        return timeInService;
    }

    private void setTimeInService(int timeInService) {
        this.timeInService = timeInService;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    // Constructor
    public Process() {
    }

    // Constructor
    public Process(String id, int arrivalTime, int burstTime) {
        super(id, arrivalTime, burstTime);
        this.waitingTime = 0;
        this.timeInService = 0;
        this.completionTime = -1;
    }

    // Constructor
    public Process(String id, int arrivalTime, int burstTime, int priority) {
        super(id, arrivalTime, burstTime);
        this.waitingTime = 0;
        this.timeInService = 0;
        this.priority = priority;
        this.completionTime = -1;
    }

    // Copy Constructor
    public Process(Process other) {
        super(other.id, other.arrivalTime, other.burstTime);
        this.waitingTime = 0;
        this.timeInService = 0;
        this.priority = other.priority;
        this.completionTime = -1;
    }

    public void execute() {
        this.setTimeInService(this.getTimeInService() + 1);
        return;
    }

    public void waiting() {
        this.setWaitingTime(this.getWaitingTime() + 1);
        return;
    }

    public void finish(int completionTime) {
        this.setCompletionTime(completionTime);
        System.out.println("Process " + this.id + " (" + this.arrivalTime + ", " + this.burstTime + ")" +
                " finished at time " + this.completionTime);
        return;
    }

}
