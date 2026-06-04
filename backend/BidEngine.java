import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class BidEngine{

    public static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/process-bid", new BidHandler());
        server.setExecutor(null);
        server.start();
    }

    static class BidHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException{
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                double bidAmount = extractDouble(body, "\"bid_amount\"");
                int userId = (int) extractDouble(body, "\"user_id\"");

                double currentHighest = 25000.0;
                int timeRemaining = 45;
                int userReputation = (userId == 1) ? 8 : 2;

                String status = calculateBidStatus(bidAmount, currentHighest, timeRemaining, userReputation);

                String response = "{\"status\": \"" + status + "\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }

        private double extractDouble(String json, String key){
            String searchKey = key + ":";
            int startIndex = json.indexOf(searchKey);
            if (startIndex == -1) return 0.0;
            startIndex += searchKey.length();
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) endIndex = json.indexOf("}", startIndex);
            String valueStr = json.substring(startIndex, endIndex).trim();
            return Double.parseDouble(valueStr);
        }

        public String calculateBidStatus(double bidAmount, double currentHighest, int timeRemaining, int userReputation){
            if (timeRemaining > 0) {
                if (bidAmount > currentHighest) {
                    if (userReputation < 5) {
                        if (bidAmount > currentHighest * 1.5) {
                            return "REJECTED_SUSPICIOUS_ACTIVITY";
                        } else {
                            return "ACCEPTED_PENDING_MANUAL_REVIEW";
                        }
                    } else {
                        if (timeRemaining < 60) {
                            return "ACCEPTED_AND_TIME_EXTENDED";
                        } else {
                            return "ACCEPTED_NORMAL";
                        }
                    }
                } else {
                    if (bidAmount <= 0) {
                        return "REJECTED_INVALID_AMOUNT";
                    } else {
                        return "REJECTED_BID_TOO_LOW";
                    }
                }
            } else {
                return "REJECTED_AUCTION_CLOSED";
            }
        }
    }
}