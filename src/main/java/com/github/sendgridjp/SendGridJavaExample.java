package com.github.sendgridjp;

import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

public class SendGridJavaExample {
  public static void main(String[] args) throws FileNotFoundException, SendGridException, IOException {
    String apiKey            = System.getenv("API_KEY");

    String[] tos             = System.getenv("TOS").split(",",0);
    String from              = System.getenv("FROM");

    SendGrid.Email email = new SendGrid.Email();
    email.addSmtpApiTo(tos);
    email.addSubstitution("fullname", new String[] { "田中 太郎", "佐藤 次郎", "鈴木 三郎" });
    email.addSubstitution("familyname", new String[] { "田中", "佐藤", "鈴木" });
    email.addSubstitution("place", new String[] { "office", "home", "office" });
    email.addSection("office", "中野");
    email.addSection("home", "目黒");
    email.addCategory("category1");
    email.setFrom(from);
    email.setFromName("送信者名");
    email.setSubject("[sendgrid-java-example] フクロウのお名前はfullnameさん");
    email.setText("familyname さんは何をしていますか？\r\n 彼はplaceにいます。");
    email.setHtml("<strong> familyname さんは何をしていますか？</strong><br />彼はplaceにいます。");
    File file = new File("./gif.gif");
    email.addAttachment("owl.gif", file);

    SendGrid sendgrid = new SendGrid(apiKey);
    SendGrid.Response response = sendgrid.send(email);
    System.out.println(response.getMessage());
  }
}
