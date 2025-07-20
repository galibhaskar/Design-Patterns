/*
    Problem:
        - The Command Pattern is a behavioral design pattern that turns a request 
            into a separate object, allowing you to decouple the code that 
            issues the request from the code that performs it.
    
    Command Pattern:
        - mainly used for logging, undo/redo, issuing voice commands,....

    Components:
        - Receivers
        - Command Interface
        - Concrete Commands
        - Invokers
        - Client

 */


// Receiver
import java.util.List;
import java.util.Stack;

class Light{
    public void turnOff(){
        System.out.println("Light turned off");
    }

    public void turnOn(){
        System.out.println("Light turned on");
    }
}

class Fan{
    public void turnOff(){
        System.out.println("Fan turned off");
    }

    public void turnOn(){
        System.out.println("Fan turned on");
    }
}

interface Command{
    void execute();

    void undo();
}

// Concrete Command
class LightOnCommand implements Command{
    private Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }

}

// Concrete Command
class LightOffCommand implements Command{
    private Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

// Concrete Command
class FanOnCommand implements Command{
    private Fan fan;

    public FanOnCommand(Fan fan){
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOn();
    }

    @Override
    public void undo() {
        fan.turnOff();
    }

}

// Concrete Command
class FanOffCommand implements Command{
    private Fan fan;

    public FanOffCommand(Fan fan){
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOff();
    }

    @Override
    public void undo() {
        fan.turnOn();
    }

}

class MacroCommand implements Command{
    private List<Command> commands;

    public MacroCommand(List<Command> commands){
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("Macro command executing...");
       for(Command command: commands){
        command.execute();
       }
    }

    @Override
    public void undo() {
        // undo in reverse order
        System.out.println("Macro command undo...");
        for(Command command: commands.reversed()){
            command.undo();
        }
    }

}

// Invokers
class RemoteControl{
    private Command[] remoteCommands;

    private Stack<Command> commandsHistory;

    public RemoteControl(int totalCommands){
        remoteCommands = new Command[totalCommands];

        commandsHistory = new Stack<>();
    }

    public void setCommand(int slot, Command command){
        remoteCommands[slot] = command;
    }

    public void pressButton(int slot){
        if(remoteCommands[slot]!=null){
            remoteCommands[slot].execute();

            commandsHistory.push(remoteCommands[slot]);
        } else {
            System.out.println("No command assigned to the slot:"+slot);
        }
    }

    public void pressUndo(){
        if(commandsHistory.isEmpty()){
            System.out.println("Invalid undo operation");
        }

        Command lastCommand = commandsHistory.pop();

        lastCommand.undo();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        // receivers
        Light light = new Light();
        Fan fan = new Fan();

        // commands
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);

        Command fanOnCommand = new FanOnCommand(fan);
        Command fanOffCommand = new FanOffCommand(fan);

        RemoteControl remoteControl = new RemoteControl(5);
        remoteControl.setCommand(0, lightOnCommand);
        remoteControl.setCommand(1, lightOffCommand);
        remoteControl.setCommand(2, fanOnCommand);
        remoteControl.setCommand(3, fanOffCommand);

        Command sleepCommand = new MacroCommand(List.of(lightOffCommand, fanOnCommand));
        remoteControl.setCommand(4, sleepCommand);

        remoteControl.pressButton(0);
        remoteControl.pressButton(2);
        remoteControl.pressUndo();
        remoteControl.pressUndo();
        remoteControl.pressButton(4);
        remoteControl.pressUndo();
    }
}
