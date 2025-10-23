public class BlankProcess {
    String id;          // The processes identification
    int arrivalTime;    // Time when the process is added to the queue
    int burstTime;      // Time required for execution
    int priority;       // Priority of the process

    public int getArrivalTime() {
        return arrivalTime;
    }

    private void setArrivalTime(int arrival_time) {
        this.arrivalTime = arrival_time;
    }

    public int getServiceTime() {
        return burstTime;
    }

    private void setServiceTime(int service_time) {
        this.burstTime = service_time;
    }
    public int getPriority() {
        return priority;
    }

    private void setPriority(int service_time) {
        this.priority = service_time;
    }

    // Constructor
    public BlankProcess() {
    }

    // Constructor
    public BlankProcess(String id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    // Constructor
    public BlankProcess(String id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    // Copy Constructor
    public BlankProcess(BlankProcess other) {
        this.id = other.id;
        this.arrivalTime = other.arrivalTime;
        this.burstTime = other.burstTime;
        this.priority = other.priority;
    }

}
