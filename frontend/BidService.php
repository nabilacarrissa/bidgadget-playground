<?php

class BidService
{
    public function sendBid($auction_id, $user_id, $bid_amount)
    {
        $payload = json_encode([
            "auction_id" => (int)$auction_id,
            "user_id" => (int)$user_id,
            "bid_amount" => (float)$bid_amount
        ]);

        return $payload;
    }
}