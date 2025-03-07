package duke.command;

import duke.exception.DukeIllegalDescriptionException;
import duke.exception.DukeIllegalTimeFormatException;
import duke.io.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class AddDeadline extends Command {

    /**
     * Adds a deadline task to list and returns a notification.
     * @param act command string
     * @param storage storage of current duke instance
     * @return notification string
     * @throws DukeIllegalDescriptionException DukeIllegalDescriptionException
     * @throws FileNotFoundException FileNotFoundException
     */
    public static String addDeadline(String act, Storage storage) throws DukeIllegalDescriptionException {
        try {
            String dlDetail = act.substring(9);
            int dlDivision = dlDetail.indexOf("/");
            String dlDescription = dlDetail.substring(0, dlDivision - 1);

            String by = dlDetail.substring(dlDivision + 3);
            Task dl = new Deadline(dlDescription, by);
            TaskList.taskList.add(dl);
            String response = ("Got it. I've added this task: \n" + dl.toString()
                    + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
            storage.saveData();
            return response;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(act);
        } catch (ParseException e) {
            throw new DukeIllegalTimeFormatException();
        }
    }
}
