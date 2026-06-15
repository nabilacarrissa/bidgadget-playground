package com.bidgadget;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BidEngineTest {

    BidEngine.BidHandler handler = new BidEngine.BidHandler();

    @Test
    void testAcceptedNormal() {
        assertEquals("ACCEPTED_NORMAL",
                handler.calculateBidStatus(30000, 25000, 120, 8));
    }

    @Test
    void testTimeExtended() {
        assertEquals("ACCEPTED_AND_TIME_EXTENDED",
                handler.calculateBidStatus(30000, 25000, 30, 8));
    }

    @Test
    void testLowReputation() {
        assertEquals("ACCEPTED_PENDING_MANUAL_REVIEW",
                handler.calculateBidStatus(30000, 25000, 120, 2));
    }

    @Test
    void testSuspiciousBid() {
        assertEquals("REJECTED_SUSPICIOUS_ACTIVITY",
                handler.calculateBidStatus(40000, 25000, 45, 2));
    }

    @Test
    void testBidTooLow() {
        assertEquals("REJECTED_BID_TOO_LOW",
                handler.calculateBidStatus(20000, 25000, 120, 8));
    }

    @Test
    void testInvalidAmountZero() {
        assertEquals("REJECTED_INVALID_AMOUNT",
                handler.calculateBidStatus(0, 25000, 120, 8));
    }

    @Test
    void testInvalidAmountNegative() {
        assertEquals("REJECTED_INVALID_AMOUNT",
                handler.calculateBidStatus(-100, 25000, 120, 8));
    }

    @Test
    void testAuctionClosed() {
        assertEquals("REJECTED_AUCTION_CLOSED",
                handler.calculateBidStatus(30000, 25000, 0, 8));
    }

    @Test
    void testBidEqualCurrentHighest() {
        assertEquals("REJECTED_BID_TOO_LOW",
                handler.calculateBidStatus(25000, 25000, 120, 8));
    }

    @Test
    void testExactThresholdReputationHigh() {
        assertEquals("ACCEPTED_NORMAL",
                handler.calculateBidStatus(30000, 25000, 120, 5));
    }

    @Test
    void testTimeBoundaryExtension() {
        assertEquals("ACCEPTED_AND_TIME_EXTENDED",
                handler.calculateBidStatus(30000, 25000, 59, 8));
    }

    @Test
    void testSuspiciousExactBoundary() {
        double bid = 25000 * 1.5;

        assertEquals("ACCEPTED_PENDING_MANUAL_REVIEW",
                handler.calculateBidStatus(bid, 25000, 40, 2));
    }

    @Test
    void testReputationBoundary4() {
        assertEquals("ACCEPTED_PENDING_MANUAL_REVIEW",
                handler.calculateBidStatus(30000, 25000, 120, 4));
    }

    @Test
    void testReputationBoundary5() {
        assertEquals("ACCEPTED_NORMAL",
                handler.calculateBidStatus(30000, 25000, 120, 5));
    }

    @Test
    void testTimeBoundary60() {
        assertEquals("ACCEPTED_NORMAL",
                handler.calculateBidStatus(30000, 25000, 60, 8));
    }

    @Test
    void testBoundaryNotNull() {
        assertNotNull(
                handler.calculateBidStatus(35000, 25000, 60, 7));
    }

    @Test
    void testBoundaryEqualBid() {
        assertEquals("REJECTED_BID_TOO_LOW",
                handler.calculateBidStatus(25000, 25000, 50, 8));
    }

    @Test
    void testReputationLowFlow() {
        assertEquals("ACCEPTED_PENDING_MANUAL_REVIEW",
                handler.calculateBidStatus(30000, 25000, 50, 4));
    }

    @Test
    void testReputationHighFlow() {
        assertEquals("ACCEPTED_NORMAL",
                handler.calculateBidStatus(30000, 25000, 80, 6));
    }

    @Test
    void testInvalidNegative() {
        assertEquals("REJECTED_INVALID_AMOUNT",
                handler.calculateBidStatus(-1, 25000, 50, 8));
    }

    @Test
    void testAllBranches() {
        BidEngine.BidHandler handler = new BidEngine.BidHandler();

        // timeRemaining <= 0
        assertEquals("REJECTED_AUCTION_CLOSED",
            handler.calculateBidStatus(30000, 25000, 0, 8));

        // bid <= 0
        assertEquals("REJECTED_INVALID_AMOUNT",
            handler.calculateBidStatus(0, 25000, 50, 8));

        // bid lebih kecil
        assertEquals("REJECTED_BID_TOO_LOW",
            handler.calculateBidStatus(20000, 25000, 50, 8));

        // suspicious
        assertEquals("REJECTED_SUSPICIOUS_ACTIVITY",
            handler.calculateBidStatus(40000, 25000, 50, 2));

        // manual review
        assertEquals("ACCEPTED_PENDING_MANUAL_REVIEW",
            handler.calculateBidStatus(30000, 25000, 50, 2));

        // accepted normal
        assertEquals("ACCEPTED_NORMAL",
            handler.calculateBidStatus(30000, 25000, 120, 8));

        // time extended
        assertEquals("ACCEPTED_AND_TIME_EXTENDED",
            handler.calculateBidStatus(30000, 25000, 30, 8));
    }
    @Test
    void testBidEqualCurrent() {
        assertEquals("REJECTED_BID_TOO_LOW",
            handler.calculateBidStatus(25000, 25000, 50, 8));
    }
    @Test
    void testExactSuspiciousBoundary() {
        double bid = 25000 * 1.5;

        assertEquals("ACCEPTED_PENDING_MANUAL_REVIEW",
            handler.calculateBidStatus(bid, 25000, 50, 2));
    }
}