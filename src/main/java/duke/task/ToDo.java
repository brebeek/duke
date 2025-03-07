package duke.task;

/**
 * To-do task with description.
 */
public class ToDo extends Task {

    /**
     *Constructor of To-do task.
     * @param description description of to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
