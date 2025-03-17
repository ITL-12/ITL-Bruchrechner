package dev.zanex.itlbruchrechner;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;

public class SimpleHttpServer {
    private final MainApplication mainApplication = new MainApplication();

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(24165), 0);
        server.createContext("/calculate", new CalculateHandler());
        server.setExecutor(null);
        server.start();
    }

    private class CalculateHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                setCorsHeaders(exchange);
                System.out.println(exchange.getRequestURI());
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                int num1 = Integer.parseInt(params.get("num1"));
                int den1 = Integer.parseInt(params.get("den1"));
                int num2 = Integer.parseInt(params.get("num2"));
                int den2 = Integer.parseInt(params.get("den2"));
                char op = params.get("op").charAt(0);

                Integer[] result = mainApplication.calcFraction(num1, den1, num2, den2, op);
                String response = result[0] + "," + result[1];

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else if ("OPTIONS".equals(exchange.getRequestMethod())) {
                setCorsHeaders(exchange);
                exchange.sendResponseHeaders(204, -1); // No Content
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }

        private void setCorsHeaders(HttpExchange exchange) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        }

        private Map<String, String> queryToMap(String query) {
            if (query == null) {
                return new HashMap<>();
            }
            return Arrays.stream(query.split("&"))
                    .map(param -> param.split("="))
                    .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));
        }
    }
}