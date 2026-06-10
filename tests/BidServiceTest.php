<?php

use PHPUnit\Framework\TestCase;

require_once __DIR__ . '/../frontend/BidService.php';

class BidServiceTest extends TestCase
{
    public function testPayloadCreation()
    {
        $service = new BidService();

        $result = $service->sendBid(
            101,
            1,
            30000
        );

        $expected = json_encode([
            "auction_id" => 101,
            "user_id" => 1,
            "bid_amount" => 30000
        ]);

        $this->assertEquals(
            $expected,
            $result
        );
    }
}