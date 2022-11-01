package agh.ics.oop;

import java.util.Arrays;
import java.util.List;

public class OptionsParser {
    public MoveDirection[] parse (String[] strings){
        List<String> possibleAction = Arrays.asList("f", "forward", "b", "backward", "r", "right", "l", "left");
        List <String> args = Arrays.asList(strings);
        List <String> filtered = args.stream().filter(possibleAction::contains).toList();
        List <MoveDirection> parsed = filtered.stream().map(str -> switch(str) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            default -> MoveDirection.LEFT;
        }).toList();
        return parsed.toArray(new MoveDirection[0]);
    }
}