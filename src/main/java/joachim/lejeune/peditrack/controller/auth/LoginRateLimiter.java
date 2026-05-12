package joachim.lejeune.peditrack.controller.auth;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginRateLimiter {

    private static final int MAX_ATTEMPTS = 5;
    private static final long WINDOW_MS = 15 * 60 * 1000L;

    private final ConcurrentHashMap<String, AttemptInfo> attempts = new ConcurrentHashMap<>();

    public boolean isBlocked(String key) {
        AttemptInfo info = attempts.get(key);
        if (info == null) return false;
        if (System.currentTimeMillis() - info.windowStart > WINDOW_MS) {
            attempts.remove(key);
            return false;
        }
        return info.count >= MAX_ATTEMPTS;
    }

    public void recordFailure(String key) {
        attempts.compute(key, (k, info) -> {
            long now = System.currentTimeMillis();
            if (info == null || now - info.windowStart > WINDOW_MS) {
                return new AttemptInfo(now, 1);
            }
            info.count++;
            return info;
        });
    }

    public void recordSuccess(String key) {
        attempts.remove(key);
    }

    private static class AttemptInfo {
        long windowStart;
        int count;

        AttemptInfo(long windowStart, int count) {
            this.windowStart = windowStart;
            this.count = count;
        }
    }
}
