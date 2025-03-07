package Commands;

import Util.QueueManager;

public class HistoryCommand implements Command {
    private final QueueManager manager;

    public HistoryCommand(QueueManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        System.out.println("История команд: " + manager.getQueue());
    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public void PrintInfo() {
        String information = "показывает 13 последних команд";
        String functionName = "history";
        System.out.println(functionName + " - " + information);
    }
}
