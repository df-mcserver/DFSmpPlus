package uk.co.nikodem.dFSmpPlus.Constants;

public class Enums {
    public enum UpdateResult {
        UPDATED,
        UPDATE_FAILED,
        DELETED,
        NULL
    }

    public enum UpdateType {
        CUSTOM_NAME,
//        LORE,
        DATA_COMPONENTS,
        ITEM_MODEL,
        ITEM_FLAGS,
        ATTRIBUTES,
        ITEM_TYPE,
        UPDATE_ID
    }

    public enum ToolLevel {
        NONE,
        WOODEN,
        STONE,
        COPPER,
        IRON,
        GOLDEN,
        DIAMOND,
        NETHERITE,
        CREATIVE
    }
}
