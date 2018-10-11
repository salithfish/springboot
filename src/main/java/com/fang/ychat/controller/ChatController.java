package com.fang.ychat.controller;

import com.alibaba.fastjson.JSON;
import com.fang.ychat.message.BaseMessage;
import com.fang.ychat.message.ChatMessage;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 群聊模式
     * @param principal 当前用户
     * @param message 接收到的客户端的消息
     * @return 包装后的消息
     */
    @MessageMapping("/all")
    @SendTo("/topic/all")
    public String all(Principal principal,String message){
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setType("to_all");
        baseMessage.setContent(message);
        baseMessage.setSender(principal.getName());
        System.out.println(principal.getName()+"++++++++++++++++++++++++++++++++++");
        baseMessage.setSendTime(format.format(new Date()));
        System.out.println(JSON.toJSONString(baseMessage));
        return JSON.toJSONString(baseMessage);
    }

    /**
     * 私聊
     *
     */
    @MessageMapping("/chat")//接收发送到/app/chat的消息
    public void chat(Principal principal,String message){
        ChatMessage chatMessage = JSON.parseObject(message,ChatMessage.class);
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setType("to_one");
        baseMessage.setSender(principal.getName());
        System.out.println(principal.getName()+"++++++++++++++++++++++++++++++++++");
        baseMessage.setContent(chatMessage.getContent());
        baseMessage.setSendTime(format.format(new Date()));

        //转发包装后的消息至用户
        template.convertAndSendToUser(chatMessage.getReceiver(),"/topic/chat",JSON.toJSONString(baseMessage));

    }

}
