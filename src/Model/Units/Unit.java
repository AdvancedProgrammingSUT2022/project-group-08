package Model.Units;

import Model.Civilization;
import Model.Tile;
import Model.Node;

import java.util.ArrayList;

public class Unit {
    private Civilization civilization;
    private Tile origin;
    private int health;
    private int MP;   // it shows mp for a unit and its final.
    private int mp;  // it changes every time unit moves.


    private int duration;
    private int goldCost;
    private int movesLeft;

    private boolean isOnSleep;
    private boolean isOnWarFooting;//Amade bash
    private boolean isOnBoost;
    private boolean isOnBoostTillRecover;
    private ArrayList<Node> path = new ArrayList<>(); //the shortest way, it also contains origin and destination

    public Unit(Civilization civilization, Tile origin, int health, int MP, int mp, int duration, int goldCost, boolean isCivilian) {
        this.civilization = civilization;
        this.origin = origin;
        this.health = health;
        this.MP = MP;
        this.mp = mp;
        this.duration = duration;
        this.goldCost = goldCost;
        this.isCivilian = isCivilian;
    }

    public int getConstantMP() {
        return MP;
    }

    public void setConstantMP(int MP) {
        this.MP = MP;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setPath(ArrayList<Node> path) {
        this.path = path;
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    public Tile getOrigin() {
        return origin;
    }

    private boolean isOnDeployment;//esteghrar dar shahr //TODO... agar isOndeployment true bashe moghe defa ghodrat ziad mishe
    private boolean isCivilian;


    private Tile destination;//if == null -> null

    public int getGoldCost() {
        return goldCost;
    }

    public void setOnWarFooting(boolean onWarFooting) {
        isOnWarFooting = onWarFooting;
    }

    public void setOnBoostTillRecover(boolean onBoostTillRecover) {
        isOnBoostTillRecover = onBoostTillRecover;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setOnBoost(boolean onBoost) {
        isOnBoost = onBoost;
    }

    public boolean isCivilian() {
        return isCivilian;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setOnSleep(boolean onSleep) {
        isOnSleep = onSleep;
    }

    public void setOnDeployment(boolean onDeployment) {
        isOnDeployment = onDeployment;
    }

    public Tile getDestination() {
        return destination;
    }

    public void setOrigin(Tile origin) {
        this.origin = origin;
    }

    public void setDestination(Tile destination) {
        this.destination = destination;
    }
}
