package com.endterm.project.websocketEntities;

import com.endterm.project.entities.MyUser;

import javax.persistence.*;

@Entity
@Table(name="chat_notification")
public class ChatNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatNotificationId;
    @ManyToOne
    @JoinColumn(name="sender_id")
    private MyUser senderNotification;

    public ChatNotification() {
    }


    public Integer getChatNotificationId() {
        return chatNotificationId;
    }

    public void setChatNotificationId(Integer chatNotificationId) {
        this.chatNotificationId = chatNotificationId;
    }

    public MyUser getSenderNotification() {
        return senderNotification;
    }

    public void setSenderNotification(MyUser senderNotification) {
        this.senderNotification = senderNotification;
    }
}
