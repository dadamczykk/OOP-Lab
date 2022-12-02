package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OptionsParser {
    public MoveDirection[] parse (String[] strings) throws IllegalArgumentException{
//        List<String> possibleAction = Arrays.asList("f", "forward", "b", "backward", "r", "right", "l", "left");
        List <String> args = Arrays.asList(strings);
//        for (String arg : args){
//            if !(possibleAction.contains(arg)){
//                throw new IllegalArgumentException(arg + " nie jest poprawnym argumentem ruchu");
//            }
//        }
//        List <String> filtered = args.stream().filter(possibleAction::contains).toList();
        List <MoveDirection> parsed = args.stream().map(str -> switch(str) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(str + " nie jest poprawnym argumentem ruchu");
        }).toList();
        return parsed.toArray(new MoveDirection[0]);
    }
}