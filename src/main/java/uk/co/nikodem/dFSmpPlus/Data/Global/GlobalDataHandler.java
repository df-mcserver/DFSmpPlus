package uk.co.nikodem.dFSmpPlus.Data.Global;

import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.DataHandler;

import java.io.File;
import java.io.IOException;

public class GlobalDataHandler extends DataHandler {
    public GlobalDataHandler(DFSmpPlus plugin) {
        super(plugin);
    }

    public final String primaryGlobalDataFileName = "primary";

    public GlobalData getGlobalData() {
        File file = getFileInDirectory(primaryGlobalDataFileName, true);
        if (!file.exists()) return new GlobalData();
        GlobalData data = this.gson.fromJson(readFileAsString(file), GlobalData.class);
        if (data == null) data = new GlobalData();
        return data;
    }

    public void writeGlobalData(GlobalData globalData) {
        File file = getFileInDirectory(primaryGlobalDataFileName, true);
        if (!file.exists()) {
            try {
                createFileInDirectory(primaryGlobalDataFileName, true);
                writeStringToFile(file, gson.toJson(globalData));
            } catch (IOException e) {
                plugin.getLogger().info("Failed to create "+file.getAbsolutePath()+"!");
            }
        } else {
            writeStringToFile(file, gson.toJson(globalData));
        }
    }

    @Override
    public String getFolderName() {
        return "global";
    }
}
