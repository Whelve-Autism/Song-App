package controllers;

import models.Album;
import models.Song;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 此类用于将专辑信息和歌曲信息发送到指定邮箱。
 * This class is used to send album and song information to a specified email address.
 *
 * @author Fan Xinkang
 * @version 6.0
 * @since version 6.0
 */
public class EmailManager {

    /**
     * 渲染邮件内容。
     * Render email content.
     *
     * @param albums 专辑列表。
     *               List of albums.
     * @param recipients 收件人列表。
     *                   List of recipients.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public void sendEmail(ArrayList<Album> albums, ArrayList<String> recipients) {
        StringBuilder emailBody = new StringBuilder("<html><body><h2>Albums and Songs Table</h2><table border='1'>");
        emailBody.append("<tr><th>Album Name</th><th>Album Description</th><th>Song Name</th><th>Song Artist</th><th>Length (seconds)</th><th>Published Date</th></tr>");

        /*
          遍历专辑信息，渲染表格。
          Traverse the album information and render the table.
         */
        for (Album album : albums) {
            for (Song song : album.getSongs()) {
                emailBody.append("<tr>")
                        .append("<td>").append(album.getAlbumName()).append("</td>")
                        .append("<td>").append(album.getAlbumDescription()).append("</td>")
                        .append("<td>").append(song.getSongName()).append("</td>")
                        .append("<td>").append(song.getSongArtist()).append("</td>")
                        .append("<td>").append(song.getLength()).append("</td>")
                        .append("<td>").append(song.getPublishDate()).append("</td>")
                        .append("</tr>");
            }
        }

        /*
          添加表格结束标签。
          Add table end tag.
         */
        emailBody.append("</table></body></html>");
        try {
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

            /*
              配置邮箱信息。
              Config email information.
             */
            Properties props = System.getProperties();

            /*
              配置邮件服务器。
              Configure the email server.
             */
            props.setProperty("mail.smtp.host", "smtp.qq.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");

            /*
              配置邮件服务器端口。
              Configure the email server port.
             */
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");

            /*
              鉴权信息。
              Authentication information.
             */
            props.setProperty("mail.smtp.auth", "true");

            /*
              建立邮件会话。
              Establish email session.
             */
            Session session = Session.getDefaultInstance(props, new Authenticator() {

                /*
                  身份认证。
                  Authentication.
                 */
                protected PasswordAuthentication getPasswordAuthentication() {

                    /*
                      设置账户和授权码。
                      Set account and authorization code.
                     */
                    return new PasswordAuthentication("2303085802@qq.com", "nefgniwnwhiadhhi");
                }
            });

            /*
              建立邮件对象。
              Establish email object.
             */
            MimeMessage message = new MimeMessage(session);

            /*
              设置邮件的发件人。
              Set the sender's email address.
             */
            message.setFrom(new InternetAddress("2303085802@qq.com"));

            /*
              设置邮件的收件人。
              Set the recipient's email address.
             */
            InternetAddress[] address = new InternetAddress[recipients.size()];
            for (int i = 0; i < recipients.size(); i++) {
                address[i] = new InternetAddress(recipients.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, address);

            /*
              设置邮件的主题。
              Set the subject of the email.
             */
            message.setSubject("Song information.");

            /*
              文本部分。
              Text part.
             */
            message.setContent(emailBody.toString(), "text/html;charset=UTF-8");
            message.saveChanges();

            /*
              发送邮件。
              Send email.
             */
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 * End of controllers.EmailManager Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */