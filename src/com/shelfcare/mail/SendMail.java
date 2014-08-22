package com.shelfcare.mail;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

import com.jscape.inet.smtp.Smtp;
import com.jscape.inet.email.EmailMessage;

public class SendMail
{
  public static void main(String args[])
  {
    try
    {
      Smtp smtp = new Smtp("localhost");
      EmailMessage message = new EmailMessage();
      message.setTo("sivagnanam.a@zohocorp.com");
      message.setFrom("siva@siva-2356");
      message.setSubject("First email...");
      message.setBody("Hi");
      smtp.connect();
      System.out.println("check1");
      smtp.send(message);
      smtp.disconnect();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }
}