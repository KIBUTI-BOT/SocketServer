package com.kibuti.socketserver.WebSocket.Controller;

import com.kibuti.socketserver.WebSocket.Model.Greeting;
import com.kibuti.socketserver.WebSocket.Model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage helloMessage) {
        return new Greeting("Hello, " + helloMessage.getName() + "!");
    }

    /*
         Something to note

         1. The @MessageMapping annotation ensures that if a message is sent to the /hello destination, the greeting() method is called.
         2. The return value is broadcast to all subscribers to /topic/greetings as specified in the @SendTo annotation.

     */
}
