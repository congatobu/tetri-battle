package com.fuzzywave.tetribattle.gameplay.statemachine;

import com.badlogic.gdx.math.Rectangle;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BlockDropStateTest {

    private static GameInstance gameInstance;
    private static BlockDropState blockDropState;
    private static GemConstructionState gemConstructionState;

    @BeforeClass
    public static void oneTimeSetUp() {
        BlockDropStateTest.gameInstance = new GameInstance(new Rectangle(0, 0, 0, 0));
        BlockDropStateTest.blockDropState = new BlockDropState();
        BlockDropStateTest.gemConstructionState = new GemConstructionState();
    }

    @Before
    public void setUp() throws Exception {
        gameInstance.clear();
    }

    @Test
    public void testDropGem() throws Exception {

        gameInstance.getBlock(2, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(2, 2).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 2).setBlockType(BlockType.BLUE);

        gemConstructionState.constructGems(gameInstance, BlockType.BLUE);

        blockDropState.dropBlocks(gameInstance);

        assertEquals(BlockType.BLUE, gameInstance.getBlock(2, 0).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(3, 0).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(2, 1).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(3, 1).getBlockType());

        assertEquals(0,gameInstance.getGem(gameInstance.getBlock(2,0).getGemId()).y);
        assertEquals(0,gameInstance.getGem(gameInstance.getBlock(3,0).getGemId()).y);
        assertEquals(0,gameInstance.getGem(gameInstance.getBlock(2,1).getGemId()).y);
        assertEquals(0,gameInstance.getGem(gameInstance.getBlock(3,1).getGemId()).y);
    }

    @Test
    public void testDontDropGem() throws Exception {
        gameInstance.getBlock(2, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(2, 2).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 2).setBlockType(BlockType.BLUE);

        gameInstance.getBlock(2, 0).setBlockType(BlockType.RED);

        gemConstructionState.constructGems(gameInstance, BlockType.BLUE);

        assertEquals(BlockType.BLUE, gameInstance.getBlock(2, 1).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(3, 1).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(2, 2).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(3, 2).getBlockType());

        assertEquals(BlockType.RED, gameInstance.getBlock(2, 0).getBlockType());

        assertEquals(1,gameInstance.getGem(gameInstance.getBlock(2,1).getGemId()).y);
        assertEquals(1,gameInstance.getGem(gameInstance.getBlock(3,1).getGemId()).y);
        assertEquals(1,gameInstance.getGem(gameInstance.getBlock(2,2).getGemId()).y);
        assertEquals(1,gameInstance.getGem(gameInstance.getBlock(3,2).getGemId()).y);
    }

    @Test
    public void testDropGemAndBlock() throws Exception {

        gameInstance.getBlock(3, 4).setBlockType(BlockType.RED);

        gameInstance.getBlock(2, 2).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 2).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(2, 3).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 3).setBlockType(BlockType.BLUE);

        gameInstance.getBlock(2, 1).setBlockType(BlockType.RED);

        gemConstructionState.constructGems(gameInstance, BlockType.BLUE);

        blockDropState.dropBlocks(gameInstance);

        assertEquals(BlockType.RED, gameInstance.getBlock(3, 3).getBlockType());

        assertEquals(BlockType.BLUE, gameInstance.getBlock(2, 1).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(3, 1).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(2, 2).getBlockType());
        assertEquals(BlockType.BLUE, gameInstance.getBlock(3, 2).getBlockType());

        assertEquals(BlockType.RED, gameInstance.getBlock(2, 0).getBlockType());

        assertEquals(1, gameInstance.getGem(gameInstance.getBlock(2, 1).getGemId()).y);
        assertEquals(1, gameInstance.getGem(gameInstance.getBlock(3, 1).getGemId()).y);
        assertEquals(1, gameInstance.getGem(gameInstance.getBlock(2, 2).getGemId()).y);
        assertEquals(1, gameInstance.getGem(gameInstance.getBlock(3, 2).getGemId()).y);
    }
}