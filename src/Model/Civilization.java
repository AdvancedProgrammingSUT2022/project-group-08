package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Civilization {
    private City capital;
    private int science;
    private ArrayList<Technology> technologies;
    private int gold;
    private int goldPerTurn;
    private int happiness;
    private ArrayList<Tile> tiles;
    private ArrayList<City> cities;
    private HashMap<Civilization,Resource> trades;

    private Technology workingOn;//if == null -> have to choose
    private HashMap<Technology,int> technologyEarnedPercent;
}
