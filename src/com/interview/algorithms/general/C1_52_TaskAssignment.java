package com.interview.algorithms.general;

import com.interview.basics.sort.QuickSorter;
import com.interview.basics.sort.Sorter;

/**
 * Created with IntelliJ IDEA.
 * User: stefanie
 * Date: 8/28/14
 * Time: 4:46 PM
 *
 * Have M memory, given a set of task, each have need request R[i] memory for handling, and O[i] memory to store the result (O[i] < R[i]).
 * Write code to assign the task as a sequence to make sure all the task can be done,
 * return a empty assignment if whatever sequence can't be fulfill these requirement. [Google]
 * There assume the task can only be done in sequence, not parallel.
 *
 * A very smart solution to sort the task by their memory diff (request - output) in asc.
 * then scan the task seq to check if M can fulfill the seq
 */
public class C1_52_TaskAssignment {
    static Sorter<Task> SORTER = new QuickSorter<Task>();

    static class Task implements Comparable<Task> {
        int number;
        Integer request;
        Integer output;
        Integer diff;

        Task(int number, Integer request, Integer output) {
            this.number = number;
            this.request = request;
            this.output = output;
            this.diff = request - output;
        }

        @Override
        public int compareTo(Task task) {
            return 0 - diff.compareTo(task.diff);
        }
    }

    public static int[] assign(int M, int[] request, int[] output) {
        Task[] tasks = new Task[request.length];
        for (int i = 0; i < request.length; i++) tasks[i] = new Task(i, request[i], output[i]);
        SORTER.sort(tasks);

        int[] seq = new int[request.length];
        for (int i = 0; i < tasks.length; i++) {
            if (M < tasks[i].request) return new int[0];
            seq[i] = tasks[i].number;
            M -= tasks[i].output;
        }
        return seq;
    }
}
