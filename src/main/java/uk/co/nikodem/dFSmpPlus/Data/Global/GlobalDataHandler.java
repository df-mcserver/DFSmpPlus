package uk.co.nikodem.dFSmpPlus.Data.Global;

import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.DataHandler;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.GlobalData;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.TelemetryData;

import java.io.File;
import java.io.IOException;

public class GlobalDataHandler extends DataHandler {
    public GlobalDataHandler(DFSmpPlus plugin) {
        super(plugin);
    }

    public final String primaryGlobalDataFileName = "primary";
    public final String telemetryGlobalDataFileName = "telemetry";

    public GlobalData getGlobalData() {
        File file = getFileInDirectory(primaryGlobalDataFileName, true);
        if (!file.exists()) return new GlobalData();
        GlobalData data = this.gson.fromJson(readFileAsString(file), GlobalData.class);
        if (data == null) data = new GlobalData();
        return data;
    }

    public TelemetryData getTelemetryData() {
        File file = getFileInDirectory(telemetryGlobalDataFileName, true);
        if (!file.exists()) return new TelemetryData();
        TelemetryData data = this.gson.fromJson(readFileAsString(file), TelemetryData.class);
        if (data == null) data = new TelemetryData();
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

    public void writeTelemetryData(TelemetryData telemetryData) {
        File file = getFileInDirectory(telemetryGlobalDataFileName, true);
        if (!file.exists()) {
            try {
                createFileInDirectory(telemetryGlobalDataFileName, true);
                writeStringToFile(file, gson.toJson(telemetryData));
            } catch (IOException e) {
                plugin.getLogger().info("Failed to create "+file.getAbsolutePath()+"!");
            }
        } else {
            writeStringToFile(file, gson.toJson(telemetryData));
        }
    }

    @Override
    public String getFolderName() {
        return "global";
    }
}
