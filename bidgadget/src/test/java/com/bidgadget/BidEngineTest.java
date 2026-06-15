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
    void testInvalidAmount() {
        assertEquals("REJECTED_INVALID_AMOUNT",
                handler.calculateBidStatus(0, 25000, 120, 8));
    }

    @Test
    void testAuctionClosed() {
        assertEquals("REJECTED_AUCTION_CLOSED",
                handler.calculateBidStatus(30000, 25000, 0, 8));
    }

    @Test
    void testNegativeBid() {
        assertEquals("REJECTED_INVALID_AMOUNT",
                handler.calculateBidStatus(-100, 25000, 60, 8));
    }

    @Test
    void testBoundaryCase() {
        assertNotNull(
                handler.calculateBidStatus(35000, 25000, 60, 7));
    }

    @Test
    void testExactThresholdReputationHigh() {
        assertEquals("ACCEPTED_NORMAL",
                handler.calculateBidStatus(30000, 25000, 120, 5));
    }

    @Test
    void testExactTimeBoundaryForExtension() {
        assertEquals("ACCEPTED_AND_TIME_EXTENDED",
                handler.calculateBidStatus(30000, 25000, 59, 8));
    }

    @Test
    void testSuspiciousEdgeCaseBorderline() {
        double current = 25000;
        double bid = current * 1.5;

        assertEquals("ACCEPTED_PENDING_MANUAL_REVIEW",
                handler.calculateBidStatus(bid, current, 40, 2));
    }

    @Test
    void testZeroReputationNormalPath() {
        assertEquals("ACCEPTED_PENDING_MANUAL_REVIEW",
                handler.calculateBidStatus(26000, 25000, 100, 0));
    }

    @Test
    void testHighBidButLowTimeReputationMix() {
        assertEquals("REJECTED_SUSPICIOUS_ACTIVITY",
                handler.calculateBidStatus(50000, 25000, 20, 3));
    }
}