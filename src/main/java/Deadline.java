public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }
}