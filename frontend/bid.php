<?php
$response_message = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $auction_id = $_POST['auction_id'];
    $user_id = $_POST['user_id'];
    $bid_amount = $_POST['bid_amount'];

    $payload = json_encode([
        "auction_id" => (int)$auction_id,
        "user_id" => (int)$user_id,
        "bid_amount" => (float)$bid_amount
    ]);

    $ch = curl_init('http://localhost:8000/process-bid');
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
    curl_setopt($ch, CURLOPT_HTTPHEADER, [
        'Content-Type: application/json',
        'Content-Length: ' . strlen($payload)
    ]);

    $result = curl_exec($ch);
    curl_close($ch);

    $response_message = $result;
}
?>
<!DOCTYPE html>
<html>

<head>
    <title>BidGadget - Form Penawaran</title>
    <script src="assets/app.js" defer></script>
</head>

<body>
    <div data-testid="item-header">
        <h1>Form Penawaran: <?php echo $_GET['item']; ?></h1>
    </div>

    <?php if ($response_message): ?>
        <div id="status-message" data-testid="status-message">
            <?php echo $response_message; ?>
        </div>
    <?php endif; ?>

    <form id="bidForm" >
        <input type="hidden" id="auction_id" name="auction_id" value="<?php echo $_GET['auction_id']; ?>">

        <input type="hidden" id="user_id" name="user_id" value="1" data-testid="hidden-user-id">

        <label for="bid_amount">Jumlah Penawaran ($):</label>
        <input type="number" id="bid_amount" name="bid_amount" data-testid="input-bid-amount">

        <button type="submit" id="submitBid" data-testid="btn-submit-bid">Kirim Penawaran</button>
    </form>
    <p id="error-msg" style="color:red;" data-testid="error-message"></p>
</body>

</html>