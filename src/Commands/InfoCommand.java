package Commands;

import java.util.Collection;

public class InfoCommand implements Command{
    private String functionName = "info";
    private String information = "какая то информация";
    @Override
    public void PrintInfo(){
        System.out.println(functionName + " - " + information);
    }
    @Override
    public void execute() {

        System.out.println();
    }
}
