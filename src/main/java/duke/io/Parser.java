package duke.io;

import duke.actionstack.DukeActionStack;
import duke.command.*;
import duke.exception.DukeDuplicateTaskException;
import duke.exception.DukeIllegalActionException;
import duke.exception.DukeIllegalDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.LinkedList;

/**
 * Makes sense of user's input and react in accordance.
 */
public class Parser {

    /**
     * @param act     keyword indicating the intended operation from user input
     * @param storage storage object to interact with
     * @throws FileNotFoundException
     * @throws DukeIllegalDescriptionException
     * @throws DukeIllegalActionException
     */
    public static String parse(String act, Storage storage) throws FileNotFoundException,
            DukeIllegalDescriptionException, DukeIllegalActionException, DukeDuplicateTaskException {
        String response = "";
        if(DuplicateChecker.checkDuplication(act)) {
            throw new DukeDuplicateTaskException();
        }
        try {
            switch (Action.valueOf(act.split(" ")[0].toUpperCase())) {
            case LIST:
                response = List.list();
                break;
            case DONE:
                response = Done.done(act, storage);
                break;
            case TODO:
                response = AddTodo.addTodo(act, storage);
                break;
            case DEADLINE:
                response = AddDeadline.addDeadline(act, storage);
                break;
            case EVENT:
                response = AddEvent.addEvent(act, storage);
                break;
            case DELETE:
                response = Delete.delete(act, storage);
                break;
            case FIND:
                response = Find.find(act, storage);
                break;
            case CLEAR:
                response = Clear.clear();
                break;
            default:
                response = "No command detected!";
                break;
            }
            DukeActionStack.pushAction(act);
        } catch (IllegalArgumentException e) {
            throw new DukeIllegalActionException();
        }
        return response;
    }
}

/**
 * Predefined commands.
 */
enum Action {
    LIST, DONE, TODO, DEADLINE, EVENT, DELETE, FIND, CLEAR
}
