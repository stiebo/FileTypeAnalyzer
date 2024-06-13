package domain;

public class PatternEntry {
    private final int priority;
    private final String pattern;
    private final String result;

    public PatternEntry(int priority, String pattern, String result) {
        this.priority = priority;
        this.pattern = pattern;
        this.result = result;
    }

    public int getPriority() {
        return priority;
    }

    public byte[] getPatternBytes() {
        return pattern.getBytes();
    }

    public String getResult() {
        return result;
    }
}