package Commands;

import java.util.Collection;

public class InfoCommand implements Command{

    @Override
    public void PrintInfo(){
        String information = "какая то информация";
        String functionName = "info";
        System.out.println(functionName + " - " + information);
    }
    @Override
    public void execute() {

        System.out.println();
    }

    @Override
    public void execute(String[] args) {

    }
}
