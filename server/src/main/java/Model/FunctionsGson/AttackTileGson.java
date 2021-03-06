package Model.FunctionsGson;

import Model.Civilization;
import Model.Member;
import Model.Tile;
import Model.Units.Unit;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class AttackTileGson {
    @Expose
    public Member member;
    @Expose
    public Unit attacker;
    @Expose
    public int destinationIndex;
    @Expose
    public Civilization civilization;
}
