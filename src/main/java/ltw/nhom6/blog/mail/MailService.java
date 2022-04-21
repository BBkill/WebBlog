package ltw.nhom6.blog.mail;

import ltw.nhom6.blog.mail.dto.MailRequestDto;

public interface MailService {
    void sendMail (MailRequestDto mailRequestDTO);
}
