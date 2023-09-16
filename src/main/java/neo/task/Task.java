package neo.task;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int totalTasks = 0;


    public static int getTotalTasks() {
        return totalTasks;
    }

    public static void incrementTotalTasks() {
        Task.totalTasks++;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        incrementTotalTasks();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String formatTask() {
        int status;
        if (this.getStatusIcon().equals("X")) {
            status = 1;
        } else {
            status = 0;
        }
        return status + " | " + this.description;
    }
    public void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
        System.out.print("Now you have " + getTotalTasks());
        if (getTotalTasks() == 1) {
            System.out.println(" task in the list.");
        } else {
            System.out.println(" tasks in the list.");
        }
    }
}
