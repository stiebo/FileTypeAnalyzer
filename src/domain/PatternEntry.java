package domain;

public class PatternEntry {
    private int priority;
    private String pattern;
    private String result;

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