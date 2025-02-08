package com.njdge.fastbuilder.profile;

import org.bukkit.Material;
import org.bukkit.block.Block;

import static com.njdge.fastbuilder.utils.Tasks.runLater;

public class BlockClearAnimation {
    private static boolean isClearing = false;

    public static boolean isClearing() {
        return isClearing;
    }

    public static void sequential(PlayerProfile profile) {
        int totalBlocks = profile.getPlacedBlocks().size();
        if (totalBlocks == 0) return;

        isClearing = true;

        int[] counter = {0};
        profile.getPlacedBlocks().forEach(location -> {
            Block block = location.getBlock();
            long delay = (long) (totalBlocks * ((double) counter[0] / totalBlocks));
            
            runLater(() -> {
                block.setType(Material.AIR);
                if (++counter[0] == totalBlocks) {
                    isClearing = false;
                }
            }, delay);

            counter[0]++;
        });
    }

    public static void clear(PlayerProfile profile) {
        int totalBlocks = profile.getPlacedBlocks().size();
        if (totalBlocks == 0) return;

        isClearing = true;

        int[] counter = {0};
        profile.getPlacedBlocks().forEach(location -> {
            Block block = location.getBlock();
            runLater(() -> {
                block.setType(Material.AIR);
                if (++counter[0] == totalBlocks) {
                    isClearing = false;
                }
            }, 0);

            counter[0]++;
        });
    }
}