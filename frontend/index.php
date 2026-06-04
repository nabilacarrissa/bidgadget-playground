<?php
$auctions_data = file_get_contents('../backend/data/auctions.json');
$auctions = json_decode($auctions_data, true);
?>
<!DOCTYPE html>
<html>

<head>
    <title>BidGadget - Lelang Elektronik</title>
</head>

<body>
    <h1 data-testid="main-title">Daftar Lelang BidGadget</h1>
    <div id="auction-list">
        <?php foreach ($auctions as $auction): ?>
            <div class="auction-item" data-testid="auction-item-<?php echo $auction['id']; ?>">
                <h2><?php echo $auction['item_name']; ?></h2>
                <p>Harga Tertinggi: $<span id="highest-bid-<?php echo $auction['id']; ?>"><?php echo $auction['current_highest_bid']; ?></span></p>
                <a href="bid.php?auction_id=<?php echo $auction['id']; ?>&item=<?php echo $auction['item_name']; ?>" data-testid="btn-bid-<?php echo $auction['id']; ?>">Ikut Lelang</a>
            </div>
        <?php endforeach; ?>
    </div>
</body>

</html>