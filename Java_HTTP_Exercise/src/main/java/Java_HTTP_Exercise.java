import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


class MyHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String response = "{\"userId\":\"132\", "
                        + "\"userPw\":\"password\","
                        + "\"userInfo\":{"
                            + "\"age\":50,"
                            + "\"sex\":\"male\""
                            + "}"
                        + "}";
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

class MyHandler2 implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{

    }
}

public class Java_HTTP_Exercise {


    public static void main(String[] args) throws IOException{
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/data", new MyHandler());
        server.createContext("/app", new MyHandler2());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port: " + port);
    }
}
