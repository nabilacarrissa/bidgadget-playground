<?php

use PHPUnit\Framework\TestCase;

class IntegrationTest extends TestCase
{
    public function testJavaEndpoint()
    {
        // Payload yang dikirim dari PHP ke Java
        $payload = json_encode([
            "auction_id" => 101,
            "user_id" => 1,
            "bid_amount" => 30000
        ]);

        // Endpoint Java
        $ch = curl_init('http://localhost:8000/process-bid');

        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);

        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            'Content-Length: ' . strlen($payload)
        ]);

        // Kirim request
        $response = curl_exec($ch);

        // Ambil HTTP status
        $statusCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

        curl_close($ch);

        // Validasi HTTP 200
        $this->assertEquals(
            200,
            $statusCode,
            "Endpoint Java tidak mengembalikan HTTP 200"
        );

        // Decode JSON response
        $json = json_decode($response, true);

        // Pastikan response valid JSON
        $this->assertNotNull(
            $json,
            "Response bukan JSON yang valid"
        );

        // Pastikan field status ada
        $this->assertArrayHasKey(
            "status",
            $json,
            "Field status tidak ditemukan pada response"
        );

        // Pastikan status berisi nilai yang valid
        $this->assertTrue(
            str_contains($json["status"], "ACCEPTED") ||
            str_contains($json["status"], "REJECTED")
        );
    }
}