import java.io.FileNotFoundException;
import java.text.ParseException;

class Parser {
    static void parse(String act, Storage storage) throws FileNotFoundException, DukeIllegalDescriptionException {

                switch (Action.valueOf(act)) {
                    case list:
                        System.out.println("Here are the tasks in your list:\n");
                        for (int i = 0; i < TaskList.taskList.size(); i++) {
                            System.out.println(1 + i + "." + TaskList.taskList.get(i).toString());
                        }
                        break;
                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        Ui.sc.close();
                        break;
                    case done:
                        int n = Ui.sc.nextInt();
                        TaskList.taskList.get(n - 1).setDone();
                        System.out.println("Nice! I've marked this task as done:\n" + TaskList.taskList.get(n - 1).toString());
                        storage.saveData();
                        break;
                    case todo:
                        String tdDescription = Ui.sc.nextLine();
                        if (tdDescription.isBlank()) {
                            throw new DukeIllegalDescriptionException(act);
                        } else {
                            Task todo = new ToDo(tdDescription);
                            TaskList.taskList.add(todo);
                            System.out.println("Got it. I've added this task: \n" + todo.toString()
                                    + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
                            storage.saveData();
                        }
                        break;
                    case deadline:
                        String dlDetail = Ui.sc.nextLine();
                        int dlDivision = dlDetail.indexOf("/");
                        try {
                            String dlDescription = dlDetail.substring(0, dlDivision - 1);

                            String by = dlDetail.substring(dlDivision + 3);
                            Task dl = new Deadline(dlDescription, by);
                            TaskList.taskList.add(dl);
                            System.out.println("Got it. I've added this task: \n" + dl.toString()
                                    + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
                            storage.saveData();
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(act);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case event:
                        String eventDetail = Ui.sc.nextLine();
                        int eventDivision = eventDetail.indexOf("/");
                        try {
                            String eventDescription = eventDetail.substring(0, eventDivision - 1);
                            String at = eventDetail.substring(eventDivision + 3);

                            Task event = new Event(eventDescription, at);
                            TaskList.taskList.add(event);
                            System.out.println("Got it. I've added this task: \n" + event.toString()
                                    + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
                            storage.saveData();
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeIllegalDescriptionException(act);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case delete:
                        int d = Ui.sc.nextInt() - 1;
                        Task temp = TaskList.taskList.get(d);
                        TaskList.taskList.remove(d);
                        System.out.println("Noted. I've removed this task: \n" + temp.toString()
                                + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
                        storage.saveData();
                        break;
                }


    }
}

enum Action {
    list, bye, done, todo, deadline, event, delete
}
