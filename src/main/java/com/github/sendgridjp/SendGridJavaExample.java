package com.github.sendgridjp;

import java.lang.*;
import java.io.File;
import com.github.sendgrid.SendGrid;
import com.sendgrid.smtpapi.SMTPAPI;
import java.io.FileNotFoundException;

public class SendGridJavaExample {
  public static void main(String[] args) throws FileNotFoundException {
    String sendgridUsername  = System.getenv("SENDGRID_USERNAME");
    String sendgridPassword  = System.getenv("SENDGRID_PASSWORD");
    String[] tos              = System.getenv("TOS").split(",",0);
    String from               = System.getenv("FROM");

    SMTPAPI smtpapi = new SMTPAPI();
    smtpapi.setTos(tos);
    String[] fullnames = { "田中 太郎", "佐藤 次郎", "鈴木 三郎" };
    for (String fullname : fullnames) {
        smtpapi.addSubstitution("fullname", fullname);
    }
    String[] familynames = { "田中", "佐藤", "鈴木" };
    for (String familyname : familynames) {
        smtpapi.addSubstitution("familyname", familyname);
    }
    String[] places = { "office", "home", "office" };
    for (String place : places) {
        smtpapi.addSubstitution("place", place);
    }
    smtpapi.addSection("office", "中野");
    smtpapi.addSection("home", "目黒");
    smtpapi.addCategory("カテゴリ1");

    SendGrid sendgrid = new SendGrid(sendgridUsername, sendgridPassword);
    sendgrid.addTo(from);	// SMTPAPIのsetTos()を使用しているため、実際にはこのアドレスにはメールは送信されない
    sendgrid.setFrom(from);
    sendgrid.setFromName("送信者名");
    sendgrid.setSubject("[sendgrid-java-example] フクロウのお名前はfullnameさん");
    sendgrid.setText("familyname さんは何をしていますか？\r\n 彼はplaceにいます。");
    sendgrid.setHtml("<strong> familyname さんは何をしていますか？</strong><br />彼はplaceにいます。");
    sendgrid.addHeader("x-smtpapi", smtpapi.jsonString());
    sendgrid.setReplyTo("no-reply@owl.com");
    sendgrid.addFile(new File("./gif.gif"));

    String response = sendgrid.send();
    System.out.println(response);
  }
}
