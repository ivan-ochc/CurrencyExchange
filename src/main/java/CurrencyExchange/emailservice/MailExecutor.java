package currencyexchange.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailExecutor {

    private TaskExecutor taskExecutor;

    MailExecutor (TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Autowired
    private MailMail mailSender;

    public void sendMailTo(final String to, final String subject, final String body) {
        taskExecutor.execute( new Runnable() {
            public void run() {
                mailSender.sendMailTo(to, subject, body);
            }
        });
    }

    public void sendMailToAll(final List<String> to, final String subject, final String body) {
        taskExecutor.execute( new Runnable() {
            public void run() {
                mailSender.sendMailToAll(to, subject, body);
            }
        });
    }

}
