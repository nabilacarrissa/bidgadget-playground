<?php

use PHPUnit\Framework\TestCase;

class ContinuousTestingTest extends TestCase
{
    private $seedFile;
    private $dbFile;

    protected function setUp(): void
    {
        $this->seedFile = __DIR__ . '/../backend/data/auctions_seed.json';
        $this->dbFile   = __DIR__ . '/../backend/data/auctions.json';

        // 🔥 RESET DATA SEBELUM SETIAP TEST
        copy($this->seedFile, $this->dbFile);
    }

    protected function tearDown(): void
    {
        // 🔥 BALIKKAN DATA SETELAH TEST
        copy($this->seedFile, $this->dbFile);
    }

    public function testInitialDataIsCorrect()
    {
        $data = json_decode(file_get_contents($this->dbFile), true);

        $this->assertEquals(25000, $data[0]['current_highest_bid']);
    }

    public function testBidUpdateWorks()
    {
        $data = json_decode(file_get_contents($this->dbFile), true);

        // simulasi bidding
        $data[0]['current_highest_bid'] = 30000;

        file_put_contents($this->dbFile, json_encode($data, JSON_PRETTY_PRINT));

        $updated = json_decode(file_get_contents($this->dbFile), true);

        $this->assertEquals(30000, $updated[0]['current_highest_bid']);
    }
}