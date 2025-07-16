package solid;

public class DependencyOfInversion {

    class EmailMessage {
        public void send() {
            // Logic to send an email
        }
    }

    class TextMessage {
        public void send() {
            // Logic to send a text message
        }
    }

    /**
     * The Dependency Inversion Principle (DIP) states that high-level modules should not depend on low-level modules.
     * Both should depend on abstractions. In addition, abstractions should not depend on details.
     * Details should depend on abstractions.
     * <p>
     * In this example, the MessageSender class depends on the EmailMessage and TextMessage classes.
     * If we want to add a new message type, we would need to modify the MessageSender class.
     * This violates the Dependency Inversion Principle.
     */

    class MessageSender {
        private final EmailMessage emailMessage;
        private final TextMessage textMessage;

        public MessageSender(EmailMessage emailMessage, TextMessage textMessage) {
            this.emailMessage = emailMessage;
            this.textMessage = textMessage;
        }

        public void sendEmail() {
            emailMessage.send();
        }

        public void sendTextMessage() {
            textMessage.send();
        }
    }


    // --------------------------------------------------Applying Dependency of inversion principle-------------------------------------------------------------------//

/*
    interface Message {
        void send();
    }

    class EmailMessage implements Message {
        @Override
        public void send() {
            // Logic to send an email
        }
    }

    class TextMessage implements Message {
        @Override
        public void send() {
            // Logic to send a text message
        }
    }

    class MessageSender {
        private final Message message;

        public MessageSender(Message message) {
            this.message = message;
        }

        public void sendMessage() {
            message.send();
        }
    }

 */

}





