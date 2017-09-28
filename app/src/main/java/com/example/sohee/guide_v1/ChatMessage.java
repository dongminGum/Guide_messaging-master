package com.example.sohee.guide_v1;

import java.util.Date;
public class ChatMessage {

    private String messageText;
    private String messageUser;
    private long messageTime;

    public ChatMessage(String messageText, String messageUser ){
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime = new Date().getTime();
    }

    public ChatMessage() {

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    //
//        public boolean left;
//        public String message;
//
//        public ChatMessage(boolean left, String message) {
//            super();
//            this.left = left;
//            this.message = message;
//        }
//
//
//
//    public String body, sender, receiver, senderName;
//    public String Date, Time;
//    public String msgid;
//    public boolean isMine;// Did I send the message.
//
//    public ChatMessage(String Sender, String Receiver, String messageString,
//                       String ID, boolean isMINE) {
//        body = messageString;
//        isMine = isMINE;
//        sender = Sender;
//        msgid = ID;
//        receiver = Receiver;
//        senderName = sender;
//    }
//
//    public void setMsgID() {
//
//        msgid += "-" + String.format("%02d", new Random().nextInt(100));
//        ;
//    }
}
