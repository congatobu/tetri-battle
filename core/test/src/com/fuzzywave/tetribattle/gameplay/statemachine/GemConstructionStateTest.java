package com.fuzzywave.tetribattle.gameplay.statemachine;

import com.badlogic.gdx.math.Rectangle;
import com.fuzzywave.tetribattle.gameplay.BlockType;
import com.fuzzywave.tetribattle.gameplay.GameInstance;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GemConstructionStateTest {

    private static GameInstance gameInstance;
    private static GemConstructionState gemConstructionState;

    @BeforeClass
    public static void oneTimeSetUp() {
        GemConstructionStateTest.gameInstance = new GameInstance(new Rectangle(0, 0, 0, 0));
        GemConstructionStateTest.gemConstructionState = new GemConstructionState();
    }

    @Before
    public void setUp() throws Exception {
        gameInstance.clear();
    }

    @Test
    public void testBasic() throws Exception {

        gameInstance.getBlock(2, 0).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 0).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(2, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 1).setBlockType(BlockType.BLUE);

        gemConstructionState.constructGems(gameInstance, BlockType.BLUE);
        assertEquals(0, gameInstance.getBlock(2, 0).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 0).getGemId());
        assertEquals(0, gameInstance.getBlock(2, 1).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 1).getGemId());
    }

    @Test
    public void testMergingWithExistingGem() throws Exception {

        // first,  create first group of blocks.
        gameInstance.getBlock(2, 0).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 0).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(2, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(2, 2).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 2).setBlockType(BlockType.BLUE);

        gemConstructionState.constructGems(gameInstance, BlockType.BLUE);

        // then, create two additional blocks.
        gameInstance.getBlock(2, 3).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 3).setBlockType(BlockType.BLUE);

        gemConstructionState.constructGems(gameInstance, BlockType.BLUE);

        assertEquals(0, gameInstance.getBlock(2, 0).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 0).getGemId());
        assertEquals(0, gameInstance.getBlock(2, 1).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 1).getGemId());
        assertEquals(0, gameInstance.getBlock(2, 2).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 2).getGemId());
        assertEquals(0, gameInstance.getBlock(2, 3).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 3).getGemId());

    }

    @Test
    public void testExistingGem() throws Exception {

        // first,  create first group of blocks.and test gem construction.
        gameInstance.getBlock(2, 0).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 0).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(2, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(2, 2).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(3, 2).setBlockType(BlockType.BLUE);

        gemConstructionState.constructGems(gameInstance, BlockType.BLUE);

        assertEquals(0, gameInstance.getBlock(2, 0).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 0).getGemId());
        assertEquals(0, gameInstance.getBlock(2, 1).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 1).getGemId());
        assertEquals(0, gameInstance.getBlock(2, 2).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 2).getGemId());


        // then, create second group of blocks.
        gameInstance.getBlock(4, 0).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(5, 0).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(4, 1).setBlockType(BlockType.BLUE);
        gameInstance.getBlock(5, 1).setBlockType(BlockType.BLUE);

        gemConstructionState.constructGems(gameInstance, BlockType.BLUE);
        assertEquals(0, gameInstance.getBlock(2, 0).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 0).getGemId());
        assertEquals(0, gameInstance.getBlock(2, 1).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 1).getGemId());
        assertEquals(0, gameInstance.getBlock(2, 2).getGemId());
        assertEquals(0, gameInstance.getBlock(3, 2).getGemId());

        assertEquals(1, gameInstance.getBlock(4, 0).getGemId());
        assertEquals(1, gameInstance.getBlock(5, 0).getGemId());
        assertEquals(1, gameInstance.getBlock(4, 1).getGemId());
        assertEquals(1, gameInstance.getBlock(5, 1).getGemId());

    }


}
