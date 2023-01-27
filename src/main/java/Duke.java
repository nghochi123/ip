import java.util.Scanner;

public class Duke {

  private static TaskManager taskManager = new TaskManager();

  public static void greet() {
    Util.printText("Hello! I'm Obama\n" + "\tWhat can I do for you?");
  }

  public static void confirmAddTask(Task task) {
    Util.printText(
      "Got it. I've added this task:\n\t" +
      task +
      String.format(
        "\nNow you have %d tasks in the list.",
        taskManager.getNumberOfTasks()
      )
    );
  }

  public static void chat() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      String ret = sc.nextLine();
      switch (ret.split(" ")[0]) {
        case "bye":
          break;
        case "list":
          taskManager.printList();
          break;
        case "mark":
          int index = Integer.parseInt(ret.split(" ")[1]) - 1;
          taskManager.markDone(index);
          Util.printText(
            "Nice! I've marked this task as done:\n\t" +
            taskManager.getTask(index)
          );
          break;
        case "unmark":
          index = Integer.parseInt(ret.split(" ")[1]) - 1;
          taskManager.markUndone(index);
          Util.printText(
            "OK, I've marked this task as not done yet:\n\t" +
            taskManager.getTask(index)
          );
          break;
        case "todo":
          String description = ret.substring(4);
          Todo todo = new Todo(description);
          taskManager.addTask(todo);
          confirmAddTask(todo);
          break;
        case "deadline":
          String fullString = ret.substring(8);
          description = fullString.split(" /by ")[0];
          String by = fullString.split(" /by ")[1];
          Deadline deadline = new Deadline(description, by);
          taskManager.addTask(deadline);
          confirmAddTask(deadline);
          break;
        case "event":
          fullString = ret.substring(5);
          description = fullString.split(" /")[0];
          String from = fullString.split(" /")[1].substring(5);
          String to = fullString.split(" /")[2].substring(3);
          Event event = new Event(description, from, to);
          taskManager.addTask(event);
          confirmAddTask(event);
          break;
        default:
          Util.printText("Please input an actual command.");
      }
    }
  }

  public static void exit() {
    Util.printText("Bye. Hope to see you again soon!");
  }

  public static void main(String[] args) {
    String LOGO =
      """
 .----------------.  .----------------.  .----------------.  .----------------.  .----------------. 
| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
| |     ____     | || |   ______     | || |      __      | || | ____    ____ | || |      __      | |
| |   .'    `.   | || |  |_   _ \\    | || |     /  \\     | || ||_   \\  /   _|| || |     /  \\     | |
| |  /  .--.  \\  | || |    | |_) |   | || |    / /\\ \\    | || |  |   \\/   |  | || |    / /\\ \\    | |
| |  | |    | |  | || |    |  __'.   | || |   / ____ \\   | || |  | |\\  /| |  | || |   / ____ \\   | |
| |  \\  `--'  /  | || |   _| |__) |  | || | _/ /    \\ \\_ | || | _| |_\\/_| |_ | || | _/ /    \\ \\_ | |
| |   `.____.'   | || |  |_______/   | || ||____|  |____|| || ||_____||_____|| || ||____|  |____|| |
| |              | || |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
 '----------------'  '----------------'  '----------------'  '----------------'  '----------------' 
                                                          
      """;
    System.out.println("Hello from\n" + LOGO);
    greet();
    chat();
    exit();
  }
}
