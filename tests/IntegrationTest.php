

// use PHPUnit\Framework\TestCase;

// class IntegrationTest extends TestCase
// {
//     public function testJavaEndpoint()
//     {
//         $payload = json_encode([
//             "auction_id" => 101,
//             "user_id" => 1,
//             "bid_amount" => 30000
//         ]);

//         $ch = curl_init(
//             'http://localhost:8000/process-bid'
//         );

//         curl_setopt(
//             $ch,
//             CURLOPT_RETURNTRANSFER,
//             true
//         );

//         curl_setopt(
//             $ch,
//             CURLOPT_POST,
//             true
//         );

//         curl_setopt(
//             $ch,
//             CURLOPT_POSTFIELDS,
//             $payload
//         );

//         curl_setopt(
//             $ch,
//             CURLOPT_HTTPHEADER,
//             [
//                 'Content-Type: application/json'
//             ]
//         );

//         $response =
//             curl_exec($ch);

//         $status =
//             curl_getinfo(
//                 $ch,
//                 CURLINFO_HTTP_CODE
//             );

//         curl_close($ch);

//         $this->assertEquals(
//             200,
//             $status
//         );

//         $this->assertStringContainsString(
//             "status",
//             $response
//         );
//     }
// }