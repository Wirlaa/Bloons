package agh;

/**
 * Interface responsible for interacting with the map.
 */
// interfejs -> klasa abstrakcyjna ogarniajaca wiekszosc rzeczy -> klasa implementujaca dany typ mapy
public interface IMap {
    // zmienna ścieżka - zdefiniowana jako środki kwadratów? lista, mapa czy coś innego?
    // wejscia i wyjscia balonów - w sciezce czy osobno?
    // kolekcja przechowujaca aktualne balony i ich pozycje (pewnie mapa)
    // wzorzec obserwator zeby trackowac pozycje balonow
    // kolekcja przechowujaca wiezyczki i ich pozycje (znowu mapa) ewentualnie przeniesc to do playera, ktory ma dostep do sklepu?
}
