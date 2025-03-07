package Collections;

public class Event {
    private long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long minAge;
    private EventType eventType; //Поле не может быть null

    public void Event(long id, String name, long minAge, EventType eventType){
        this.id = id;
        this.name = name;
        this.minAge = minAge;
        this.eventType = eventType;
    }
    public void CreateEvent(long id, String name, long minAge, EventType eventType){
        boolean IdFlag = false;
        boolean NameFlag = false;
        boolean AgeFlag = false;
        boolean EventFlag = false;
        if (id >= 1){
            IdFlag = true;
        }
        else {
            System.out.println("id event error");
        }

        if (name != null){
            NameFlag = true;
        }
        else {
            System.out.println("name event error");
        }

        if (minAge >= 0 ){
            AgeFlag = true;
        }
        else {
            System.out.println("age event error");
        }

        if (eventType != null){
            EventFlag = true;
        }
        else {
            System.out.println("type event error");
        }
        if (IdFlag && NameFlag && AgeFlag && EventFlag){
            Event(id, name, minAge, eventType);
        }
    }
    public void PrintEvent(){
        System.out.println();
    }
}
