package model;

import domain.PatternEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PatternReader {
    public static List<PatternEntry> readPatterns(String patternsFile) {
        List<PatternEntry> patterns = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(patternsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int priority = Integer.parseInt(parts[0]);
                String pattern = parts[1].replace("\"", "");
                String result = parts[2].replace("\"", "");
                patterns.add(new PatternEntry(priority, pattern, result));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read patterns.");
        }
        patterns.sort(Comparator.comparingInt(PatternEntry::getPriority).reversed());
        return patterns;
    }


}
