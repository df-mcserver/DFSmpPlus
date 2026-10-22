package uk.co.nikodem.dFSmpPlus.Data.Global.Data;

import uk.co.nikodem.dFSmpPlus.Data.Global.Data.Types.ArbitraryItemStorage;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.Types.BackpackItemStorage;

import java.util.HashMap;
import java.util.Map;

public class GlobalData {
    public Map<String, Map<String, Long>> vampireSwordMappings = new HashMap<>();
    public Map<String, BackpackItemStorage> backpackMappings = new HashMap<>();
    public Map<String, ArbitraryItemStorage> autotoolMappings = new HashMap<>();
}
