package com.fuzzywave.tetribattle.gameplay;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PieceTest {
    private Piece piece;

    @Before
    public void constructPiece() {
        this.piece = new Piece();
    }

    @Test
    public void testPiece() {

        assertEquals(BlockType.EMPTY, piece.getFirstBlock().getBlockType());
        assertEquals(BlockType.EMPTY, piece.getSecondBlock().getBlockType());
    }

    @Test
    public void testInitialize() throws Exception {
        piece.initialize(BlockType.BLUE, BlockType.GREEN);

        assertNotEquals(BlockType.EMPTY, piece.getFirstBlock().getBlockType());
        assertNotEquals(BlockType.RED, piece.getSecondBlock().getBlockType());

        assertEquals(BlockType.BLUE, piece.getFirstBlock().getBlockType());
        assertEquals(BlockType.GREEN, piece.getSecondBlock().getBlockType());
    }
}