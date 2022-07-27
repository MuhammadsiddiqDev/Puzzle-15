package uz.myisystem.lesson2.puzzle15.core;

public class UserData {
    private String name ;
    private int time ;
    private int step ;

    public UserData(String name, int time, int step) {
        this.name = name;
        this.time = time;
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", step=" + step +
                '}';
    }
}
