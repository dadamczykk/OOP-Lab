package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");

        Animal testAnimal = new Animal();
        testAnimal.move(MoveDirection.RIGHT);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        System.out.println(testAnimal);

        Animal finalAnimal = new Animal();
        MoveDirection[] commands = new OptionsParser().parse(args);

        for (MoveDirection command : commands) {
            finalAnimal.move(command);
        }
        System.out.println(finalAnimal);
        System.out.println("system zakonczyl dzialanie");
    }
}
// Odpowiedź do punktu 10.
// Można stworzyć klasę, która będzie reprezentacją mapy, na której poruszają się zwierzaki.
// W tej klasie każda pozycja może być osobnym obiektem z informacją, czy jest zajęta przez
// zwierzaka. Za każdym razem, gdy zwierzak będzie się przemieszczał, sprawdzane będzie,
// czy pozycja, na którą idzie, jest już zajęta (albo czy na drodze, którą pokonuje zwierzak,
// jest zajęta pozycja — zależnie od tego, co chcemy osiągnąć).
