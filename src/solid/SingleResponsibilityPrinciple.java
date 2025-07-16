package solid;

public class SingleResponsibilityPrinciple {

    /**
     * Single Responsibility Principle (SRP) states that a class should have only one reason to change.
     * In other words, a class should have only one job or responsibility.
     *
     * <p>
     * In the below example, the Report class has three responsibilities:
     * 1. Generate the report content
     * 2. Save the report content to a file
     * 3. Send the report via email
     * <p>
     * To follow the Single Responsibility Principle, we can split the Report class into three separate classes.
     * Each class will have only one responsibility.
     * <p>
     * The Report class will only have the report content.
     * <p>
     * ReportGenerator class will generate the report content.
     * ReportSaver class will save the report content to a file.
     * EmailSender class will send the report via email.
     */

   /*

    class Report {

        private final String content;

        Report(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void generateReport() {
            // Logic to generate the report content
        }

        public void saveToFile() {
            // Logic to save the report content to a file
        }

        public void sendEmail() {
            // Logic to send the report via email
        }
    }

     */

    class Report {
        private final String content;

        public Report(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    class ReportGenerator {
        public void generateReport(Report report) {
            // Logic to generate the report content
        }
    }

    class ReportSaver {
        public void saveToFile(Report report) {
            // Logic to save the report content to a file
        }
    }

    class EmailSender {
        public void sendEmail(Report report) {
            // Logic to send the report via email
        }
    }

}
