package com.bidgadget;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BidEngineTest {

    BidEngine.BidHandler handler =
        new BidEngine.BidHandler();

    @Test
    void testAcceptedNormal() {

        String result =
            handler.calculateBidStatus(
                30000,
                25000,
                120,
                8
            );

        assertEquals(
            "ACCEPTED_NORMAL",
            result
        );
    }

    @Test
    void testBidTooLow() {

        String result =
            handler.calculateBidStatus(
                20000,
                25000,
                120,
                8
            );

        assertEquals(
            "REJECTED_BID_TOO_LOW",
            result
        );
    }

    @Test
    void testAuctionClosed() {

        String result =
            handler.calculateBidStatus(
                30000,
                25000,
                0,
                8
            );

        assertEquals(
            "REJECTED_AUCTION_CLOSED",
            result
        );
    }

    @Test
    void testSuspiciousBid() {

        String result =
            handler.calculateBidStatus(
                40000,
                25000,
                45,
                2
            );

        assertEquals(
            "REJECTED_SUSPICIOUS_ACTIVITY",
            result
        );
    }

    @Test
    void testAcceptedAndTimeExtended() {

        String result =
            handler.calculateBidStatus(
                30000,
                25000,
                45,
                8
            );

        assertEquals(
            "ACCEPTED_AND_TIME_EXTENDED",
            result
        );
    }
}