W treści zadania zaproponowane jest, żeby skorzystać ze wzorca observer do aktualizacji mapy.
Stworzony poprzednio observer (IPositionChangeObserver) jest jednak niewystarczający,
gdyż informuje jedynie o zmianie pozycji zwierzęcia, a nie o jego ruchu (czyli też obrocie).
Z tego powodu dodany został IMotionObserver, który jest implementowany przez klasę Animal.
Działa on analogicznie do wcześniejszego observera, ale informuje o każdej zmianie stanu zwierzęcia.
Co do samego observera, to jest on połączony pomiędzy zwierzęciem a GUI. Odpowiednie observery
dodawane są do zwierząt poprzez ich listę z klasy SimulationEngine. Zmiana stanu zwierzęcia
aktywuje odpowiednią metodę w App, która aktualizuje mapę.

Grafiki użyte w programie występują podwójnie w folderze resources ze względu na wsteczną
kompatybilność z wizualizacją programu przez swing (chociaż nie jestem pewien, czy będzie ona
działała poprawnie)