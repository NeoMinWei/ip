import java.util.Scanner;
public class Neo {
    public static void printList(Task[] list) {
        int listIndex = 1;

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.print((listIndex) + ". ");
            System.out.println(list[i]);
            listIndex++;
        }
    }

    public static void markTask(String line, Task[] list) {
        String[] words = line.split(" ");
        int listIndex = Integer.parseInt(words[1]);
        int listArrayIndex = listIndex - 1;

        list[listArrayIndex].setDone(true);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("    " + list[listArrayIndex]);
    }

    public static void unmarkTask(String line, Task[] list) {
        String[] words = line.split(" ");
        int listIndex = Integer.parseInt(words[1]);
        int listArrayIndex = listIndex - 1;

        list[listArrayIndex].setDone(false);
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("    " + list[listArrayIndex]);
    }

    public static void handleEvent(String line, Task[] list) {
        try {
            addEvent(line, list);
        } catch (NeoException e) {
            e.printException();
        }
    }
    public static void catchFormatError(String line) throws NeoException{
        if (!line.contains("/from")) {
            throw new NeoException("/from", false);
        }
        if (!line.contains("/to")) {
            throw new NeoException("/to", false);
        }
    }

    public static void catchEmptyDescription(String field, String description) throws NeoException{
        if (description.isBlank()) {
            throw new NeoException(field, true);
        }
    }
    public static void addEvent(String line, Task[] list) throws NeoException{
        catchFormatError(line);

        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        int fromStringLength = 5;
        int toStringLength = 3;
        int eventStringLength = 5;

        String description = line.substring(eventStringLength, fromIndex).trim();
        String from = line.substring(fromIndex + fromStringLength, toIndex).trim();
        String to = line.substring(toIndex + toStringLength).trim();

        catchEmptyDescription("description", description);
        catchEmptyDescription("/from", from);
        catchEmptyDescription("/to", to);

        int taskArrayIndex = Task.getTotalTasks();

        list[taskArrayIndex] = new Event(description, from, to);
        list[taskArrayIndex].printAddedTask();
    }

    public static void addDeadline(String line, Task[] list) {
        int byIndex = line.indexOf("/by");
        int byStringLength = 3;
        int deadlineStringLength = 8;

        String description = line.substring(deadlineStringLength + 1, byIndex - 1);
        String by = line.substring(byIndex + byStringLength + 1);

        int taskArrayIndex = Task.getTotalTasks();

        list[taskArrayIndex] = new Deadline(description, by);
        list[taskArrayIndex].printAddedTask();
    }

    public static void addTodo(String line, Task[] list) {
        int todoStringLength = 4;

        String description = line.substring(todoStringLength + 1);

        int taskArrayIndex = Task.getTotalTasks();

        list[taskArrayIndex] = new Todo(description);
        list[taskArrayIndex].printAddedTask();
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Neo.");
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] list = new Task[100];
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(list);
                line = in.nextLine();
            } else if (line.startsWith("mark")) {
                try {
                    markTask(line, list);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Please give the index of which task to mark.");
                }
                line = in.nextLine();
            } else if (line.startsWith("unmark")) {
                try {
                    unmarkTask(line, list);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Please give the index of which task to unmark.");
                }
                line = in.nextLine();
            } else if (line.startsWith("event")) {
                handleEvent(line, list);
                line = in.nextLine();
            } else if (line.startsWith("deadline")) {
                addDeadline(line, list);
                line = in.nextLine();
            } else {
                addTodo(line, list);
                line = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
