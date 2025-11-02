import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SchedulingSchemes {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPreemptive() {
        return preemptive;
    }

    public void setPreemptive(Boolean preemptive) {
        this.preemptive = preemptive;
    }

    String name;
    Boolean preemptive;

    public SchedulingSchemes(String name, Boolean preemptive) {
        this.name = name;
        this.preemptive = preemptive;
    }

    /** To do:
     * Modify the following functions to implement the scheduling schemes.
     * Each function should return the process that is to be executed next according to the scheme.
     */

    // First-Come First-Served
    public static Process fcfs(LinkedList<Process> queue) {

        if (queue.isEmpty()) {
            return null;
        }
        return queue.getFirst();
    }

    // Shortest Job Next
    public static Process sjn(LinkedList<Process> queue) {

        if (queue.isEmpty()) {
            return null;
        }
        Process pNext = queue.getFirst();

        for (Process p : queue) {
            if (p.burstTime < pNext.burstTime) {
                pNext = p;
            }
            else if (p.burstTime == pNext.burstTime && p.arrivalTime < pNext.arrivalTime) {
                pNext = p;
            }
        }

        return pNext;
    }

    // Shortest Remaining Time
    public static Process srt(LinkedList<Process> queue) {

        if (queue.isEmpty()) {
            return null;
        }
        Process pNext = queue.getFirst();

        int shortestRemainingTime = pNext.burstTime - pNext.getTimeInService();

        for (Process p : queue) {
            int remainingTime = p.burstTime - p.getTimeInService();
            if (remainingTime < shortestRemainingTime) {
                shortestRemainingTime = remainingTime;
                pNext = p;
            }
            else if (remainingTime == shortestRemainingTime) {
                if (p.getArrivalTime() < pNext.getArrivalTime()) {
                    shortestRemainingTime = p.burstTime - p.getTimeInService();
                    pNext = p;
                }
            }
        }
        return pNext;
    }

    // Round Robin
    public static Process rr(LinkedList<Process> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.getFirst();
    }

    /** To do for VG:
     * Also implement Priority Scheduling.
     */
    // Priority Scheduling
    public static Process ps(LinkedList<Process> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        Process pNext = queue.getFirst();
        int bestPriority = pNext.getPriority();

        for (Process p : queue) {
            int currentPariority = p.getPriority();

            if (currentPariority < bestPriority) {
                bestPriority = currentPariority;
                pNext = p;
            }
            else if (currentPariority == bestPriority) {
                if (p.getArrivalTime() < pNext.getArrivalTime()) {
                    pNext = p;
                }
            }
        }

        return pNext;
    }
}
