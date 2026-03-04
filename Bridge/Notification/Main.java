package Bridge.Notification;

import java.util.List;

import Bridge.Notification.Abstraction.AlertNotification;
import Bridge.Notification.Abstraction.InfoNotification;
import Bridge.Notification.Abstraction.Notification;
import Bridge.Notification.Abstraction.WarningNotification;
import Bridge.Notification.Implentation.Email;
import Bridge.Notification.Implentation.NotificationSender;
import Bridge.Notification.Implentation.SMS;
import Bridge.Notification.Implentation.Slack;

public class Main {
    public static void main(String[] args) {

        NotificationSender email = new Email();
        NotificationSender sms   = new SMS();
        NotificationSender slack = new Slack("https://hooks.slack.com/xxx");

        // Info via Email
        Notification info = new InfoNotification(email);
        info.send("dev@company.com", "Deployment completed successfully");

        System.out.println();

        // Warning via SMS
        Notification warning = new WarningNotification(sms);
        warning.send("+1-555-0100", "CPU usage above 80%");

        System.out.println();

        // Switch channel at runtime
        warning.switchSender(slack);
        warning.send("#ops-alerts", "Memory usage above 90%");

        System.out.println();

        // Critical alert → ALL channels simultaneously
        Notification alert = new AlertNotification(email,
            List.of(email, sms, slack));
        alert.send("oncall-engineer", "🔥 Production DB is DOWN!");
    }
}

// --- ℹ️  INFO Notification ---
//   📧 EMAIL → To: dev@company.com
//      Subject: ℹ️ Info
//      Body: Deployment completed successfully

// --- ⚠️  WARNING Notification ---
//   📱 SMS → To: +1-555-0100
//      Message: ⚠️ Warning: CPU usage above 80%

//   🔄 Switching channel: SMS → Slack
// --- ⚠️  WARNING Notification ---
//   💬 SLACK → Channel: #ops-alerts
//      *⚠️ Warning*
//      Memory usage above 90%

// --- 🚨 CRITICAL ALERT — Broadcasting to ALL channels ---
//   📧 EMAIL → To: oncall-engineer
//      Subject: 🚨 CRITICAL ALERT
//      Body: 🔥 Production DB is DOWN!

//   📱 SMS → To: oncall-engineer
//      Message: 🚨 CRITICAL ALERT: 🔥 Production DB is DOWN!

//   💬 SLACK → Channel: oncall-engineer
//      *🚨 CRITICAL ALERT*
//      🔥 Production DB is DOWN!