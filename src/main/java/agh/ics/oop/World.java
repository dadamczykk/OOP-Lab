package agh.ics.oop;

import java.util.Arrays;
import java.util.List;


public class World {
    public static List<String> filterInput(List<String> args){
        List<String> possibleAction = Arrays.asList("f", "b", "r", "l");
        return args.stream().filter(possibleAction::contains).toList();
    }

    public static List<Direction> convToDirectionStream(List<String> strings){
        return strings.stream().map(str -> switch (str) {
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> Direction.FORWARD;
        }).toList();
    }

    public static void runStream(List<Direction> directionList){
        directionList.forEach( // also can use stream().forEach
                direction -> {
                    String message = switch (direction) {
                        case FORWARD -> "do przodu";
                        case BACKWARD -> "do tylu";
                        case LEFT -> "w lewo";
                        case RIGHT -> "w prawo";
                    };
                    System.out.println("Zwierzak idzie " + message);
                }
        );
    }

    public static void main(String[] args) {
        System.out.println("system wystartowal");
        List<String> input = Arrays.asList(args);
        List<Direction> actions = convToDirectionStream(filterInput(input));
        runStream(actions);
        System.out.println("system zakonczyl dzialanie");
    }

    // Code used in standard solution (non-stream())
//    public static void run(Direction[] args) {
//        for (Direction arg : args) {
//            String message = switch (arg) {
//                case FORWARD -> "Do przodu";
//                case BACKWARD -> "Do tylu";
//                case LEFT -> "W lewo";
//                case RIGHT -> "W prawo";
//            };
//            System.out.println(message + ",");
//
//        }
//    }
//    public static Direction[] convToDirection(String[] args){
//        Direction[] dirList = new Direction[args.length];
//        int i = 0;
//        for (String arg : args){
//            switch (arg) {
//                case "f" -> dirList[i] = Direction.FORWARD;
//                case "b" -> dirList[i] = Direction.BACKWARD;
//                case "r" -> dirList[i] = Direction.RIGHT;
//                case "l" -> dirList[i] = Direction.LEFT;
//                default -> i--;
//            }
//            i++;
//        }
//        return Arrays.copyOfRange(dirList,0, i);
//    }
}
