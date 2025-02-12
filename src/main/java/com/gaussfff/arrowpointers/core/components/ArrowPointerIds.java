package com.gaussfff.arrowpointers.core.components;

import net.minecraft.world.level.block.state.properties.WoodType;

public enum ArrowPointerIds {
    OAK("oak", WoodType.OAK);

    private static final String STANDING_BLOCK_ID_POSTFIX = "standing";
    private static final String WALL_BLOCK_ID_POSTFIX = "wall";
    private static final String ID_POSTFIX = "arrow_pointer";

    private final String itemId;
    private final String standingBlockId;
    private final String wallBlockId;
    private final String blockEntityId;
    private final WoodType woodType;

    ArrowPointerIds(String id, WoodType woodType) {
        this.itemId = this.blockEntityId = makeId(id);
        this.standingBlockId = makeStandingBlockId(id);
        this.wallBlockId = makeWallBlockId(id);
        this.woodType = woodType;
    }

    private static String makeStandingBlockId(String id) {
        return String.format("%s_%s_%s", id, STANDING_BLOCK_ID_POSTFIX, ID_POSTFIX);
    }

    private static String makeWallBlockId(String id) {
        return String.format("%s_%s_%s", id, WALL_BLOCK_ID_POSTFIX, ID_POSTFIX);
    }

    private static String makeId(String id) {
        return String.format("%s_%s", id, ID_POSTFIX);
    }

    public String getItemId() {
        return itemId;
    }

    public String getStandingBlockId() {
        return standingBlockId;
    }

    public String getWallBlockId() {
        return wallBlockId;
    }

    public String getBlockEntityId() {
        return blockEntityId;
    }

    public WoodType getWoodType() {
        return woodType;
    }
}
