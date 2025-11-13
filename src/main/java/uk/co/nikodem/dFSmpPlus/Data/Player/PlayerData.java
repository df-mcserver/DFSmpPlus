package uk.co.nikodem.dFSmpPlus.Data.Player;

import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerData {
    public List<String> equippedAccessories = new ArrayList<>();
    public boolean locatorBarEnabled = false;
    public Integer lifeCrystals;

    // id, information
    public HashMap<String, WaypointInformation> waypoints = new HashMap<>();
}
