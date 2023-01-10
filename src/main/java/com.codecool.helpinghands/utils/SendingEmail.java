package com.codecool.helpinghands.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendingEmail {

    @Autowired
    private static JavaMailSender mailSender;

    public static void sendSimpleEmail(String toEmail,  String userName, KindOfEmail kindOfEmail, String eventName, String guestName, String reportReason, String eventURL){
        String mailText =  "";
        String mailTitle = "";

        switch (kindOfEmail){
            case AFTER_REGISTRATION -> {
                mailText = "Dear " + userName + "! You have just registered on Helping hands. Thank you for using our service. Good luck!";
                mailTitle = "Welcome in our application!";
            }

            case AFTER_INCORRECT_LOGIN -> {
                mailText = "Dear " + userName + "! Because you tried to log in to our application 3 times and all 3 times were unsuccessful - the login option has been blocked for you. To unlock it click on this link and try again. Good luck!";
                mailTitle = "Attention! Incorrect login.";
            }

            case AFTER_CREATION_EVENT -> {
                mailText = "Hello " + userName + "! Congratulations! Moments ago you created an event on our platform. Now " + eventName + " shows up in the list of all events.";
                mailTitle = "You created new event! :)";
            }

            case AFTER_ATTEND_THE_EVENT -> {
                mailText = "Hello " + userName + "! You are now a member of the " + eventName + " event!";
                mailTitle = "Attend the event.";
            }

            case YOU_WERE_INVITED -> {
                QRGenerator.generate(eventURL, eventName);
                mailText = "Hello " + guestName + "! You were invited to the event " + eventName + " by " + userName + "! <p>Link to event: " + eventURL;
                mailText += "<p><hr><img src='/Users/vladyslavshyian/Rock/untitled/src/main/java/com/aga/QRCodes/" + eventName + ".png'";
                mailTitle = "You were invited to event.";
            }

            case EVENT_REMINDER -> {
                mailText = "Hello " + userName + "! A reminder that the event " + eventName + " you are taking part will start tomorrow. Go to the website and check out the details.";
                mailTitle = "Remind that the event will start.";
            }

            case REPORT_AN_EVENT -> {
                toEmail = "aga.admin@gmail.com";
                mailText = "Attention! Registered complaint about the event. " + userName + " reported the event " + eventName + "! Reason: \"" + reportReason + "\" .";
                mailTitle = "Remind that the event will start.";
            }
        }

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("helping_hands@gmail.com");
        message.setTo(toEmail);
        message.setText(mailText);
        message.setSubject(mailTitle);

        mailSender.send(message);
        System.out.println("Mail send...");
    }
}
