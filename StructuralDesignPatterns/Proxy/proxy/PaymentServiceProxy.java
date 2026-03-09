package Proxy.proxy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Proxy.real.PaymentService;
import Proxy.real.RealPaymentService;

public class PaymentServiceProxy implements PaymentService {

    private final RealPaymentService realService;
    private Map<String, Integer> requestCount;
    private Map<String, Long> windowStart;
    private Set<String> validApiKeys;

    private static final int MAX_REQUESTS_PER_MINUTE = 3; // rate limit
    private static final long ONE_MINUTE_MS = 60_000L;

    public PaymentServiceProxy() {
        this.realService = new RealPaymentService();
        this.requestCount = new HashMap<>();
        this.windowStart = new HashMap<>();
        this.validApiKeys = new HashSet<>(
                Arrays.asList("KEY-ADMIN-001", "KEY-USER-002", "KEY-USER-003"));
    }

    private boolean isRateLimited(String userId) {
        long now = System.currentTimeMillis();
        windowStart.putIfAbsent(userId, now);
        if (now - windowStart.get(userId) > ONE_MINUTE_MS) {
            requestCount.put(userId, 0);
            windowStart.put(userId, now);
        }
        int count = requestCount.getOrDefault(userId, 0);
        if (count >= MAX_REQUESTS_PER_MINUTE) {
            System.out.println("[Proxy - RateLimit] ✘ Rate limit exceeded for user: "
                    + userId + " (" + count + "/" + MAX_REQUESTS_PER_MINUTE + " requests)");
            return true;
        }

        requestCount.put(userId, count + 1);
        System.out.println("[Proxy - RateLimit] ✔ Request allowed: "
                + (count + 1) + "/" + MAX_REQUESTS_PER_MINUTE);
        return false;
    }

    private boolean isAuthenticated(String apiKey) {
        if (!validApiKeys.contains(apiKey)) {
            System.out.println("[Proxy - Auth] ✘ Invalid API key: " + apiKey);
            return false;
        }
        System.out.println("[Proxy - Auth] ✔ Authenticated: " + apiKey);
        return true;
    }

    // ── Step 3: Logging ───────────────────────────────────────
    private void logRequest(String method, String userId) {
        System.out.println("[Proxy - Log] Request → method: "
                + method + " | user: " + userId
                + " | time: " + new java.util.Date());
    }

    // ── Combined Guard ────────────────────────────────────────
    private boolean canProceed(String apiKey, String userId, String method) {
        System.out.println("\n--- Proxy Intercepting: " + method + " ---");
        if (!isAuthenticated(apiKey))
            return false;
        if (isRateLimited(userId))
            return false;
        logRequest(method, userId);
        return true;
    }

    @Override
    public String processPayment(String userId, double amount) {
        String apiKey = resolveApiKey(userId);
        if (!canProceed(apiKey, userId, "processPayment")) {
            return "ERROR: Request blocked by API Gateway";
        }
        return realService.processPayment(userId, amount);
    }

    @Override
    public String refundPayment(String userId, String transactionId) {
        String apiKey = resolveApiKey(userId);
        if (!canProceed(apiKey, userId, "refundPayment")) {
            return "ERROR: Request blocked by API Gateway";
        }
        return realService.refundPayment(userId, transactionId);
    }

    @Override
    public String getPaymentStatus(String transactionId) {
        System.out.println("\n--- Proxy Intercepting: getPaymentStatus ---");
        logRequest("getPaymentStatus", "anonymous");
        return realService.getPaymentStatus(transactionId);
    }

    private String resolveApiKey(String userId) {
        Map<String, String> userKeyMap = new HashMap<>();
        userKeyMap.put("USR-001", "KEY-ADMIN-001");
        userKeyMap.put("USR-002", "KEY-USER-002");
        userKeyMap.put("USR-999", "KEY-INVALID"); // bad actor
        return userKeyMap.getOrDefault(userId, "KEY-UNKNOWN");
    }
}
