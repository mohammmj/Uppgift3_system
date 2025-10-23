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
        Process pNext = new Process();

        // add your code here

        return pNext;
    }

    // Shortest Job Next
    public static Process sjn(LinkedList<Process> queue) {
        Process pNext = new Process();

        // add your code here

        return pNext;
    }

    // Shortest Remaining Time
    public static Process srt(LinkedList<Process> queue) {
        Process pNext = new Process();

        // add your code here

        return pNext;
    }

    // Round Robin
    public static Process rr(LinkedList<Process> queue) {
        Process pNext = new Process();

        // add your code here

        return pNext;
    }

    /** To do for VG:
     * Also implement Priority Scheduling.
     */
    // Priority Scheduling
    public static Process ps(LinkedList<Process> queue) {
        Process pNext = new Process();

        // add your code here

        return pNext;
    }
}
