# File Type Analyzer - Command-line Tool demonstrating Sub-string searching

## About

Here's the link to the project: https://hyperskill.org/projects/64

Check out my profile: https://hyperskill.org/profile/500961738

All documentation retrieved from https://hyperskill.org/projects/64, provided by JetBrains Academy.

The project demos how to make a tool for determining different file types by searching for byte sequences within a file. This approach is widely used in many different applications. For example, the Unix “file” tool relies on a sophisticated “magic” database (it consists of a pattern set written in a specific language), antivirus and malware-detection tools search the malicious signatures inside user’s files, and firewalls do the same with a system’s network traffic (as well as DPI systems).

### Usage
```text
java Main <algorithm> <folder_path> <patterns_file>
```

### Algorithm
Search algorithms to choose from include:
```text
--NAIVE (a simple pattern search algorithm)
--KMP (using a Knuth-Morris-Pratt algorithm)
--RK (Rabin-Karp algorithm)
```
### Folder-Path
Path to a folder with various files. The program will analyze all files in folder_path in parallel using several "workers" in a multithreaded environment. Each "worker" is executed using a Callable, returning a Future result to the Controller when the search is finished.

### Patterns-file
File containing various patterns to allow the program check several patterns against each file. Here we define pattern as a pair of two strings: {P, R}, where P is a pattern itself and R is a resulting file type which corresponds to pattern P. If the string P is found in the file then your program should return R as file type. For example, for the following pattern
```text
{"%PDF-", "PDF document"}
```
A patterns_file (e.g. patterns.db, included in the repo) must consist of patterns with their priorities. You can download it [here](https://stepik.org/media/attachments/lesson/210127/patterns.db). For example:
```text
4;"PK";"Zip archive"
7;"word/_rels";"MS Office Word 2007+"
7;"ppt/_rels";"MS Office PowerPoint 2007+"
7;"xl/_rels";"MS Office Excel 2007+"
```
A higher value means higher priority. In this example, the "Zip archive" is a container for multiple files. However, Microsoft Office files are also stored as Zip archives, you can clearly see this if you rename file "file.docx" to "file.zip". If you unzip it, you'll see that it contains a bunch of folders and a bunch of XMLs. So, a Word file contains both "PK" indicating that this is a Zip archive and "word/_rels" indicating that this is a Word document. In this situation, you should choose a pattern with higher priority, which is "MS Office Word 2007+".

