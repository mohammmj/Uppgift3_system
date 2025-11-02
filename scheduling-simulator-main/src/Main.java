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
        System.out.println("Current directory: " + System.getProperty("user.dir"));

        LinkedList<Process> fcfs_ex1 = loadProcesses(System.getProperty("user.dir") + "/data/fcfs_ex1.txt");
        LinkedList<Process> fcfs_ex2 = loadProcesses(System.getProperty("user.dir") + "/data/fcfs_ex2.txt");
        LinkedList<Process> fcfs_wikipedia = loadProcesses(System.getProperty("user.dir") + "/data/fcfs_wikipedia.txt");

        LinkedList<Process> sjn_ex1 = loadProcesses(System.getProperty("user.dir") + "/data/sjn_ex1.txt");
        LinkedList<Process> sjn_wikipedia = loadProcesses(System.getProperty("user.dir") + "/data/sjn_wikipedia.txt");

        LinkedList<Process> srt_ex1 = loadProcesses(System.getProperty("user.dir") + "/data/srt_ex1.txt");
        LinkedList<Process> srt_wikipedia = loadProcesses(System.getProperty("user.dir") + "/data/srt_wikipedia.txt");

        LinkedList<Process> rr_ex1 = loadProcesses(System.getProperty("user.dir") + "/data/rr_ex1.txt");
        LinkedList<Process> rr_ex2 = loadProcesses(System.getProperty("user.dir") + "/data/rr_ex2.txt");
        LinkedList<Process> rr_wikipedia = loadProcesses(System.getProperty("user.dir") + "/data/rr_wikipedia.txt");

        LinkedList<Process> ps_ex1 = loadProcesses(System.getProperty("user.dir") + "/data/prioritized_processes.txt");

        LinkedList<Process> processes = loadProcesses(System.getProperty("user.dir") + "/data/processes.txt");

        SchedulingSchemes fcfs1 = new SchedulingSchemes("FCFS", false);
        SchedulingSchemes fcfs2 = new SchedulingSchemes("FCFS", false);
        SchedulingSchemes fcfsWikipedia = new SchedulingSchemes("FCFS", false);

        SchedulingSchemes sjn1 = new SchedulingSchemes("SJN", false);
        SchedulingSchemes sjnWikipedia = new SchedulingSchemes("SJN", false);

        SchedulingSchemes srt1 = new SchedulingSchemes("SRT", true);
        SchedulingSchemes srtWikipedia = new SchedulingSchemes("SRT", true);

        SchedulingSchemes rr1 = new SchedulingSchemes("RR", true);
        SchedulingSchemes rr2 = new SchedulingSchemes("RR", true);
        SchedulingSchemes rrWikipedia = new SchedulingSchemes("RR", true);

        SchedulingSchemes ps1 = new SchedulingSchemes("PS", false);

        SchedulingSchemes processes_ex = new SchedulingSchemes("RR", true);

        //run(fcfs_ex1, fcfs1);
        //run(fcfs_ex2, fcfs2);
        //run(fcfs_wikipedia, fcfsWikipedia);

        //run(sjn_ex1, sjn1);
        //run(sjn_wikipedia, sjnWikipedia);

        //run(srt_ex1, srt1);
        //run(srt_wikipedia, srtWikipedia);

        run(rr_ex1, rr1);
        //run(rr_ex2, rr2);
        //run(rr_wikipedia, rrWikipedia);

        //run(ps_ex1, ps1);

        //run(processes, processes_ex);



    }

    // By default, the run()-method is called with quantum = 1.
    public static void run(LinkedList<Process> listOfProcesses, SchedulingSchemes scheme) {
        run(listOfProcesses, scheme, 1);
    }

    /** To do for VG:
     * Implement the usage of quantum.
     * run() should work for any (feasible) positive integer quantum.
     */
    public static void run(LinkedList<Process> listOfProcesses, SchedulingSchemes scheme, int quantum) {

        int n = listOfProcesses.size();
        LinkedList<Process> queue = new LinkedList<>();
        int index = 0;
        int completed = 0;
        int time = 0;
        Process pNext = null;
        Process currentProcess = null;
        int quantumUsed = 0;

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
        Function<LinkedList<Process>, Process> schedulingScheme;
        switch(scheme.name) {
            case "SJN":
                schedulingScheme = SchedulingSchemes::sjn;
                break;
            case "SRT":
                schedulingScheme = SchedulingSchemes::srt;
                break;
            case "RR":
                schedulingScheme = SchedulingSchemes::rr;
                break;
            case "PS":
                schedulingScheme = SchedulingSchemes::ps;
                break;
            default:
                schedulingScheme = SchedulingSchemes::fcfs;
                break;
        }

        System.out.println("Starting " + scheme.getName() + " Scheduling...\n");

        while(completed < n) {

            // Add the processes that arrive now to the process list
            while (index < n && listOfProcesses.get(index).arrivalTime <= time) {
                queue.add(listOfProcesses.get(index));
                index++;
            }

            /** To do:
             * Among all processes in the queue, find the process that shall be executed next
             * according to the chosen scheduling scheme.
             */

            if (scheme.name.equals("RR")) {
                // Round Robin logic (unchanged)
                if (currentProcess == null || quantumUsed >= quantum || currentProcess.getTimeInService() >= currentProcess.burstTime) {
                    if (!queue.isEmpty()) {
                        pNext = schedulingScheme.apply(queue);
                        quantumUsed = 0;
                        currentProcess = pNext;
                    } else {
                        pNext = null;
                        currentProcess = null;
                    }
                } else {
                    pNext = currentProcess;
                }
            } else if (scheme.preemptive) {
                // Preemptive algorithms (SRT) - re-evaluate every time step
                pNext = schedulingScheme.apply(queue);
                currentProcess = pNext;
            } else {
                // Non-preemptive algorithms (FCFS, SJN, PS) - only select when no current process
                if (currentProcess == null || currentProcess.getTimeInService() >= currentProcess.burstTime) {
                    pNext = schedulingScheme.apply(queue);
                    currentProcess = pNext;
                } else {
                    pNext = currentProcess;
                }
            }

            // During this time step, the process is executed
            /** To do:
             * Execute the process you have found.
             */
            if (pNext != null) {
                pNext.execute();
                if (scheme.name.equals("RR")) {
                    quantumUsed++;
                }
            }

            // All other processes need to wait
            /** To do:
             * Set the other processes into waiting state.
             */
            for (Process p : queue) {
                if (p != pNext && p.getTimeInService() < p.burstTime && p.getArrivalTime() <= time) {
                    p.waiting();
                }
            }

            /** To do:
             * If the process is finished, mark it as completed.
             *
             * Make sure to not mix this up with time++, in order to set the correct timestamp for finishing a process.
             * E.g., a process that starts at time 0 and needs 2 time periods to execute shall finish at time 2.
             *
             */
            if (pNext != null && pNext.getTimeInService() == pNext.burstTime) {
                pNext.finish(time + 1);
                queue.remove(pNext);
                completed++;

                if (scheme.name.equals("RR")) {
                    currentProcess = null;
                    quantumUsed = 0;
                }
            }
            time++;
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
        int priority = 0;

        try {
            File fileObject = new File(path);
            Scanner sc = new Scanner(fileObject);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                data = data.replaceAll("\\s", "");
                String[] fields = data.split(";");
                if (fields.length >= 4) {
                    id = fields[0];
                    arrivalTime = Integer.parseInt(fields[1]);
                    burstTime = Integer.parseInt(fields[2]);
                    priority = Integer.parseInt(fields[3]);
                    process = new Process(id, arrivalTime, burstTime, priority);
                    processes.add(process);
                }
                else if (fields.length >= 3) {
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
