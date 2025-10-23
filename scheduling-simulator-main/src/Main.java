import java.util.LinkedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * This models the execution of processes on a CPU.
 *
 * @author "Anna Brötzner, Malmö university"
 * @since 2.0
 */

public class Main {
    public static void main(String[] args) {
        LinkedList<Process> allProcesses = loadProcesses(System.getProperty("user.dir") + "/data/rr_ex1.txt");

        SchedulingSchemes fcfs = new SchedulingSchemes("FCFS", false);

        run(allProcesses, fcfs);
    }

    // By default, the run()-method is called with quantum = 1.
    public static void run(LinkedList<Process> listOfProcesses,
                           SchedulingSchemes scheme) {
        run(listOfProcesses, scheme, 1);
    }

    /** To do for VG:
     * Implement the usage of quantum.
     * run() should work for any (feasible) positive integer quantum.
     */
    public static void run(LinkedList<Process> listOfProcesses,
                           SchedulingSchemes scheme,
                           int quantum) {
        int n = listOfProcesses.size();
        LinkedList<Process> queue = new LinkedList<>();
        int index = 0;

        Process pNext = new Process();
        int completed = 0;
        int time = 0;

        /**
         * The following loop runs one time step at a time, until all processes are finished.
         *
         * To do:
         * Implement the functionalities necessary to handle the queue of processes.
         *
         * Important:
         * - Do not modify the variable time! It is supposed to mimic the time steps, one per iteration.
         * - Please do not add additional print-statements for your hand-in
         *   as this makes the verification of your output more difficult.
         */
        while(completed < n) {
            // Set the scheduling scheme
            Function<LinkedList<Process>, Process> schedulingScheme;
            switch(scheme.name) {
                default:
                    schedulingScheme = SchedulingSchemes::fcfs;
                    break;
            }

            // Add the processes that arrive now to the process list
            while (index < n && listOfProcesses.get(index).arrivalTime <= time) {
                queue.add(listOfProcesses.get(index));
                index++;
            }

            /** To do:
             * Among all processes in the queue, find the process that shall be executed next
             * according to the chosen scheduling scheme.
             */

            // During this time step, the process is executed
            /** To do:
             * Execute the process you have found.
             */
            time++;

            // All other processes need to wait
            /** To do:
             * Set the other processes into waiting state.
             */

            /** To do:
             * If the process is finished, mark it as completed.
             *
             * Make sure to not mix this up with time++, in order to set the correct timestamp for finishing a process.
             * E.g., a process that starts at time 0 and needs 2 time periods to execute shall finish at time 2.
             *
             */
        }
    }

    // To import processes from file
    /** To do:
     * If you implement priority scheduling, adapt this
     * to fit the format of data/prioritized_processes.txt
     * each line contains 4 parameters, separated by semicolons:
     * id; arrivalTime; burstTime; priority
     */
    private static LinkedList<Process> loadProcesses(String path) {
        LinkedList<Process> processes = new LinkedList<>();
        Process process;
        String id;
        int arrivalTime;
        int burstTime;

        try {
            File fileObject = new File(path);
            Scanner sc = new Scanner(fileObject);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                data = data.replaceAll("\\s", "");
                String[] fields = data.split(";");
                if (fields.length >= 3) {
                    id = fields[0];
                    arrivalTime = Integer.parseInt(fields[1]);
                    burstTime = Integer.parseInt(fields[2]);
                    process = new Process(id, arrivalTime, burstTime);
                    processes.add(process);
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found error.");
            e.printStackTrace();
        }

        return processes;
    }
}
