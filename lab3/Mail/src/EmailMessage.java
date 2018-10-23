
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;

public class EmailMessage {
    private String from; 		     //required (must be e-mail)
    private LinkedList<String> to;  //required at least one (must be e-mail)
    private String subject;		    //optional
    private String content;		    //optional
    private String mimeType;	    // optional
    private LinkedList<String> cc;	//optional
    private LinkedList<String> bcc; // optional

    protected EmailMessage(Builder b) {
            from = b.from;
            to = b.to;
            subject = b.subject;
            content = b.content;
            mimeType = b.mimeType;
            cc = b.cc;
            bcc = b.bcc;
    }

    public static Builder builder() {
        return new EmailMessage.Builder();
    }

    public static class Builder {

        private String from; 		                        //required (must be e-mail)
        private LinkedList<String> to = new LinkedList();  //required at least one (must be e-mail)
        private String subject;		                        //optional
        private String content;	                            	//optional
        private String mimeType;	                        // optional
        private LinkedList<String> cc = new LinkedList();	//optional
        private LinkedList<String> bcc = new LinkedList(); // optional


        Builder addFrom(String _from) {
            try {
                InternetAddress emailAddr = new InternetAddress(_from);
                emailAddr.validate();
            } catch (AddressException ex) {
                System.out.println("Błędny email nadawcy!");
                System.exit(0);
            }
            from = _from;
            return this;
        }
        Builder addTo(String _to) {
            try {
                InternetAddress emailAddr = new InternetAddress(_to);
                emailAddr.validate();
            } catch (AddressException ex) {
                System.out.println("Błędny email odbiorcy!");
                System.exit(0);
            }
            to.add(_to);
            return this;
        }
        Builder addSubject(String _subject) {
            subject = _subject;
            return this;
        }
        Builder addContent(String _content) {
            content = _content;
            return this;
        }
        Builder addMimeType(String _mimeType) {
            mimeType = _mimeType;
            return this;
        }
        Builder addCC(String _cc) {
            cc.add(_cc);
            return this;
        }
        Builder addBCC(String _bcc) {
            bcc.add(_bcc);
            return this;
        }

        EmailMessage build() {
            return new EmailMessage(this);
        }

    }

    public void send() {
        System.out.print("Podaj hasło: ");
        Scanner reader = new Scanner(System.in);
        String password = reader.nextLine();


        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session); // Create a default MimeMessage object.
            message.setFrom(new InternetAddress(from));
            for (int i = 0; i < to.size(); i++) {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to.get(0)));
            }
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            throw new RuntimeException(mex);
        }
    }


    public static void main(String[] args)  {
        EmailMessage wiadomosc = EmailMessage.builder()
                .addFrom("izabela.pachel@gmail.com")
                .addTo("izabela.pachel@gmail.com")
                .addSubject("Mail testowy")
                .addContent("Brak tresci")
                .build();

        wiadomosc.send();
    }


}