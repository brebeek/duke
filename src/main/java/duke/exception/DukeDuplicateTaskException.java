package duke.exception;

import java.io.IOException;

public class DukeDuplicateTaskException extends IllegalArgumentException {
    public DukeDuplicateTaskException() {
        super("This task has been added already");
    }
}
