package com.njdge.fastbuilder.profile;

import org.bukkit.Material;
import org.bukkit.block.Block;

import static com.njdge.fastbuilder.utils.Tasks.runLater;

public class BlockClearAnimation {
    private static final long DURATION = 15L;
    
    public static void sequential(PlayerProfile profile) {
        int totalBlocks = profile.getPlacedBlocks().size();
        if (totalBlocks == 0) return;

        double interval = (double) DURATION / totalBlocks;
        int[] counter = {0};
        
        profile.getPlacedBlocks().forEach(location -> {
            Block block = location.getBlock();
            long delay = Math.round(counter[0] * interval);
            runLater(() -> block.setType(Material.AIR), delay);
            counter[0]++;
        });
    }

    public static void clear(PlayerProfile profile) {
        profile.getPlacedBlocks().forEach(location -> {
            Block block = location.getBlock();
            runLater(() -> block.setType(Material.AIR), 0);
        });
    }
}
