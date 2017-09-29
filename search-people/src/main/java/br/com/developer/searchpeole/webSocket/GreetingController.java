package br.com.developer.searchpeole.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
    	String str = "{idPeople}@{lat}@{long}@{lastUpdateUser}";
    	System.out.println(str);
        return str;
    }

}
