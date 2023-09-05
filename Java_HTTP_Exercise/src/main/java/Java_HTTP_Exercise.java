import classe.Group;
import classe.Student;
import classe.User;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dto.StudentHobbyDto;
import dto.UserDto;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

// simple.json 출력
class MyHandler2 implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        // 현재 작업 경로는 프로젝트 폴더 자체이다. 때문에 상대경로로 ./src 부터 진행해야 함.
        String path = "./src/main/java/data/Simple.json";

        Gson gson = new Gson();
        User user = gson.fromJson(new FileReader(path), User.class);
        String userJson = gson.toJson(user);

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, userJson.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(userJson.getBytes());
        os.close();
    }
}

// Array.json 의 score 합 구하기
class MyHandler3 implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String path = "./src/main/java/data/Array.json";

        Gson gson = new Gson();
        User[] users = gson.fromJson(new FileReader(path), User[].class);

        int result = 0;
        for(User u : users){
            result += u.getScore();
        }

        String response = "{\"sum\":" + result + "}";

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

// Array.json 의 모든 이름 출력
class MyHandler4 implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String path = "./src/main/java/data/Array.json";

        Gson gson = new Gson();
        User[] users = gson.fromJson(new FileReader(path), User[].class);

        Map<String, List<String>> m = new HashMap<>();
        m.put("names", new ArrayList<String>());
        for (User u : users){
            m.get("names").add(u.getName());
        }

        String response = gson.toJson(m);

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

// Array.json 의 모든 id, 이름 출력
class MyHandler5 implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String path = "./src/main/java/data/Array.json";

        Gson gson = new Gson();
        User[] users = gson.fromJson(new FileReader(path), User[].class);

        List<UserDto> list = new ArrayList<>();
        for (User u : users){
            list.add(new UserDto(u.getId(), u.getName()));
        }

        String response = gson.toJson(list);

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

// Nested.json 에서 학생 이름, 취미 가져오기
class MyHandler6 implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String path = "./src/main/java/data/Nested.json";

        Gson gson = new Gson();
        Group group = gson.fromJson(new FileReader(path), Group.class);

        List<StudentHobbyDto> list = new ArrayList<>();
        List<Student> students = group.getStudents();
        for (Student s : students){
            list.add(new StudentHobbyDto(s.getName(), s.getHobby()));
        }

        String response = gson.toJson(list);

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

public class Java_HTTP_Exercise {
    public static void main(String[] args) throws IOException{
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/data", new MyHandler());
        server.createContext("/simple", new MyHandler2());
        server.createContext("/sum", new MyHandler3());
        server.createContext("/names", new MyHandler4());
        server.createContext("/info", new MyHandler5());
        server.createContext("/nested-names", new MyHandler6());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port: " + port);
    }
}
