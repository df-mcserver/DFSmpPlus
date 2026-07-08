package uk.co.nikodem.dFSmpPlus.Data.Player;

import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.LastDeathInformation;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerData {
    public PlayerAccessoryData playerAccessoryData;
    public LastDeathInformation lastDeathInformation;
    public boolean locatorBarEnabled = false;
    public boolean veinMinerEssenceEnabled = true;
    public boolean autosmeltEssenceEnabled = true;
    public boolean hasVacuumSoundEnabled = true;
    public Integer lifeCrystals;
    public List<String> armourSetsWithSetBonusesWorn = new ArrayList<>();

    // id, info
    public HashMap<String, WaypointInformation> waypoints = new HashMap<>();
}
