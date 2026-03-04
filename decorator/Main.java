package decorator;

import decorator.decorater.EmailDecorator;
import decorator.decorater.SlackDecorator;
import decorator.decorater.SmsDecorator;
import decorator.decorater.WhatsAppDecorator;

// Main.java
public class Main {
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   DECORATOR PATTERN — NOTIFICATIONS  ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // ─── Scenario 1: Email only ──────────────────────
        System.out.println("━━━ Scenario 1: Email Only ━━━\n");

        Notifier emailOnly = new EmailDecorator(
                new BaseNotifier("Alice"),
                "alice@company.com");
        emailOnly.send("Your report is ready.");

        // ─── Scenario 2: Email + SMS ─────────────────────
        System.out.println("\n━━━ Scenario 2: Email + SMS ━━━\n");

        Notifier emailAndSms = new SmsDecorator(
                new EmailDecorator(
                        new BaseNotifier("Bob"),
                        "bob@company.com"),
                "+1-555-0101");
        emailAndSms.send("Your order has been shipped!");

        // ─── Scenario 3: Email + SMS + Slack ─────────────
        System.out.println("\n━━━ Scenario 3: Email + SMS + Slack ━━━\n");

        Notifier fullStack = new SlackDecorator(
                new SmsDecorator(
                        new EmailDecorator(
                                new BaseNotifier("DevOps Team"),
                                "devops@company.com"),
                        "+1-555-0911"),
                "#alerts");
        fullStack.send("⚠️ Server CPU usage exceeded 95%!");

        // ─── Scenario 4: ALL channels ─────────────────────
        System.out.println("\n━━━ Scenario 4: ALL Channels (Critical Alert) ━━━\n");

        Notifier allChannels = new WhatsAppDecorator(
                new SlackDecorator(
                        new SmsDecorator(
                                new EmailDecorator(
                                        new BaseNotifier("On-Call Engineer"),
                                        "oncall@company.com"),
                                "+1-555-0199"),
                        "#critical-alerts"),
                "+1-555-0199");
        allChannels.send("🔥 CRITICAL: Production database is DOWN!");

        // ─── Scenario 5: Dynamic decoration at runtime ───
        System.out.println("\n━━━ Scenario 5: Dynamic — User Preferences ━━━\n");

        // Simulate reading user preferences from DB
        boolean prefEmail = true;
        boolean prefSms = false;
        boolean prefSlack = true;
        boolean prefWhatsApp = true;

        Notifier dynamic = new BaseNotifier("Charlie");

        if (prefEmail)
            dynamic = new EmailDecorator(dynamic, "charlie@email.com");
        if (prefSms)
            dynamic = new SmsDecorator(dynamic, "+1-555-0102");
        if (prefSlack)
            dynamic = new SlackDecorator(dynamic, "#charlie-alerts");
        if (prefWhatsApp)
            dynamic = new WhatsAppDecorator(dynamic, "+1-555-0102");

        dynamic.send("Your subscription is expiring in 3 days.");
    }
}
// ```

// ---

// ### Output
// ```
// ╔══════════════════════════════════════╗
// ║ DECORATOR PATTERN — NOTIFICATIONS ║
// ╚══════════════════════════════════════╝

// ━━━ Scenario 1: Email Only ━━━

// ┌─────────────────────────────────────
// │ 🔔 Notification for : Alice
// │ 📝 Message : Your report is ready.
// │ 📣 Channels : [ Base ] + Email
// └─────────────────────────────────────
// 📧 EMAIL sent
// To : alice@company.com
// Subject : Notification Alert
// Body : Your report is ready.

// ━━━ Scenario 2: Email + SMS ━━━

// ┌─────────────────────────────────────
// │ 🔔 Notification for : Bob
// │ 📝 Message : Your order has been shipped!
// │ 📣 Channels : [ Base ] + Email + SMS
// └─────────────────────────────────────
// 📧 EMAIL sent
// To : bob@company.com
// Body : Your order has been shipped!
// 📱 SMS sent
// To : +1-555-0101
// Text: Your order has been shipped!

// ━━━ Scenario 3: Email + SMS + Slack ━━━

// ┌─────────────────────────────────────
// │ 🔔 Notification for : DevOps Team
// │ 📝 Message : ⚠️ Server CPU usage exceeded 95%!
// │ 📣 Channels : [ Base ] + Email + SMS + Slack
// └─────────────────────────────────────
// 📧 EMAIL sent ...
// 📱 SMS sent ...
// 💬 SLACK sent
// Channel : #alerts
// Message : *Alert* — ⚠️ Server CPU usage exceeded 95%!

// ━━━ Scenario 4: ALL Channels (Critical Alert) ━━━

// ┌─────────────────────────────────────
// │ 🔔 Notification for : On-Call Engineer
// │ 📝 Message : 🔥 CRITICAL: Production database is DOWN!
// │ 📣 Channels : [ Base ] + Email + SMS + Slack + WhatsApp + Push
// └─────────────────────────────────────
// 📧 EMAIL sent → oncall@company.com
// 📱 SMS sent → +1-555-0199
// 💬 SLACK sent → #critical-alerts
// 🟢 WHATSAPP sent → +1-555-0199
// 🔔 PUSH sent → device-token-xyz-9876

// ━━━ Scenario 5: Dynamic — User Preferences ━━━

// ┌─────────────────────────────────────
// │ 🔔 Notification for : Charlie
// │ 📣 Channels : [ Base ] + Email + Slack + WhatsApp
// └─────────────────────────────────────
// 📧 EMAIL sent → charlie@email.com
// 💬 SLACK sent → #charlie-alerts
// 🟢 WHATSAPP sent → +1-555-0102
// ```

// ---

// ### How Wrapping Works (Visual)
// ```
// allChannels.send("DB is DOWN!")
// │
// └─► PushDecorator.send()
// │
// └─► WhatsAppDecorator.send()
// │
// └─► SlackDecorator.send()
// │
// └─► SmsDecorator.send()
// │
// └─► EmailDecorator.send()
// │
// └─► BaseNotifier.send() ← innermost
// ← 📧 Email sent
// ← 📱 SMS sent
// ← 💬 Slack sent
// ← 🟢 WhatsApp sent
// ← 🔔 Push sent
// ```

// ---

// ### Decorator vs Inheritance

// | | Inheritance | Decorator |
// |---|---|---|
// | **Combinations** | 2³ = 8 subclasses | 3 decorators, stack freely |
// | **Runtime change** | ❌ Fixed at compile time | ✅ Dynamic at runtime |
// | **Add new channel** | Rewrite many classes | Add one new decorator |
// | **User preferences** | ❌ Hard to handle | ✅ if/else wrapping |
// | **Responsibility** | Mixed in one class | Each decorator = one channel |

// ---

// ### When to Use Decorator

// | Situation | Use Decorator? |
// |---|---|
// | Add behavior without modifying original class | ✅ Yes |
// | Combinations of features needed at runtime | ✅ Yes |
// | User-configurable features (preferences) | ✅ Yes |
// | Subclassing causes class explosion | ✅ Yes |
// | Simple single behavior extension | ❌ Use inheritance |

// ### Key Takeaway
// ```
// // The magic — same interface, stackable behavior:

// Notifier n = new BaseNotifier("User"); // just base
// Notifier n = new EmailDecorator(n, "..."); // + email
// Notifier n = new SmsDecorator(n, "..."); // + sms
// Notifier n = new SlackDecorator(n, "..."); // + slack

// // Each layer adds ONE responsibility — Open/Closed Principle ✅
