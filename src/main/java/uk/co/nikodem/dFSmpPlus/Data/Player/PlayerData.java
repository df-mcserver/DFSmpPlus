package uk.co.nikodem.dFSmpPlus.Data.Player;

import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.LastDeathInformation;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;

import java.util.HashMap;

public class PlayerData {
    public PlayerAccessoryData playerAccessoryData;
    public LastDeathInformation lastDeathInformation;
    public boolean locatorBarEnabled = false;
    public Integer lifeCrystals;

    // id, info
    public HashMap<String, WaypointInformation> waypoints = new HashMap<>();
}
