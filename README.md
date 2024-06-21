
Trace Command 

Go to traces file path in cmd then run below command 

''sh
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.
args="show-trace trace_files\trace.zip"

