package designPatterns;


enum LogLevel {
    DEBUG, ERROR, WARNING
}


// Abstract class
abstract class Logger {
    Logger nextLogger;

    Logger setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
        return nextLogger;
    }

    abstract void log(LogLevel level, String log);
}


// Error Logger concrete class
class ErrorLogger extends Logger {

    public void log(LogLevel level, String log) {

        if (level.equals(LogLevel.ERROR)) {
            System.out.println("Logging Error : " + log);

        } else if (nextLogger != null) {
            nextLogger.log(level, log);
        }
    }

}

// Debug Logger concrete class
class DebugLogger extends Logger {

    void log(LogLevel level, String log) {

        if (level.equals(LogLevel.DEBUG)) {
            System.out.println("Logging Debug : " + log);

        } else if (nextLogger != null) {
            nextLogger.log(level, log);
        }
    }

}

// Warning Logger concrete class
class WarningLogger extends Logger {

    void log(LogLevel level, String log) {

        if (level.equals(LogLevel.WARNING)) {
            System.out.println("Logging Error  :" + log);

        } else if (nextLogger != null) {
            nextLogger.log(level, log);
        }
    }

}


public class ChainOfResponsibility {

    public static void main(String[] args) {

        ErrorLogger loggerChain = new ErrorLogger(); // This delegates to next chain of handlers
        loggerChain.setNextLogger(new DebugLogger()).setNextLogger(new WarningLogger());

        loggerChain.log(LogLevel.DEBUG, "Stack OverFlow Error");
    }

}
