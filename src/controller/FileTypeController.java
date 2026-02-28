package controller;

import domain.PatternEntry;
import model.FileTypeModel;
import model.PatternReader;
import view.FileTypeView;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileTypeController {
    private final FileTypeView view;
    private final String algorithm;
    private final List<PatternEntry> patterns;

    public FileTypeController(FileTypeView view, String algorithm, String patternsFile) {
        this.view = view;
        this.algorithm = algorithm;
        this.patterns = PatternReader.readPatterns(patternsFile);
    }

    public void checkFileTypes(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) {
            view.displayError("Invalid folder path or empty folder.");
            return;
        }

        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        int taskCount = 0;
        for (File file : files) {
            if (file.isFile()) {
                FileTypeModel model = new FileTypeModel(file.getPath(), patterns, algorithm);
                completionService.submit(model);
                taskCount++;
            }
        }

        for (int i = 0; i < taskCount; i++) {
            try {
                Future<String> future = completionService.take();
                String message = future.get();
                view.displayMessage(message);
            } catch (Exception e) {
                view.displayError("An error occurred: " + e.getMessage());
            }
        }

        executor.shutdown();

        long endTime = System.nanoTime();
        view.displayExecutionTime(endTime - startTime);
    }


}