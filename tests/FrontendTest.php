<?php

use PHPUnit\Framework\TestCase;

class FrontendTest extends TestCase
{
    public function testIndexPage()
    {
        ob_start();
        include __DIR__ . '/../frontend/index.php';
        $output = ob_get_clean();

        $this->assertStringContainsString(
            'Daftar Lelang BidGadget',
            $output
        );
    }

    public function testBidPage()
    {
        $_GET['auction_id'] = 101;
        $_GET['item'] = 'MacBook';

        ob_start();
        include __DIR__ . '/../frontend/bid.php';
        $output = ob_get_clean();

        $this->assertStringContainsString(
            'Form Penawaran',
            $output
        );
    }
}