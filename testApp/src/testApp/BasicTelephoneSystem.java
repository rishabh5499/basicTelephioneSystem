package testApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BasicTelephoneSystem {
    static class Phone {
        private String phoneNumber;
        private boolean isRinging = false;
        
        public Phone(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
        
        public String getPhoneNumber() {
            return phoneNumber;
        }
        
        public void ring(String callerId) {
            isRinging = true;
            System.out.println("Phone " + phoneNumber + " is ringing... Caller ID: " + callerId);
        }
        
        public void answer() {
            if (isRinging) {
                isRinging = false;
                System.out.println("Phone " + phoneNumber + " is now answered.");
            } else {
                System.out.println("Phone " + phoneNumber + " is not ringing.");
            }
        }
        
        public void hangUp() {
            if (isRinging) {
                isRinging = false;
            }
            System.out.println("Phone " + phoneNumber + " has hung up.");
        }
        
        public boolean isRinging() {
            return isRinging;
        }
    }

    static class Exchange {
        private Map<String, Phone> phoneDirectory = new HashMap<>();
        
        public void addPhone(Phone phone) {
            phoneDirectory.put(phone.getPhoneNumber(), phone);
        }
        
        public void makeCall(String fromNumber, String toNumber) {
            Phone fromPhone = phoneDirectory.get(fromNumber);
            Phone toPhone = phoneDirectory.get(toNumber);
            
            if (fromPhone == null || toPhone == null) {
                System.out.println("Invalid phone number.");
                return;
            }
            
            System.out.println("Making call from " + fromNumber + " to " + toNumber);
            toPhone.ring(fromNumber);
        }
        
        public void answerCall(String phoneNumber) {
            Phone phone = phoneDirectory.get(phoneNumber);
            if (phone != null) {
                phone.answer();
            } else {
                System.out.println("Phone number not found.");
            }
        }
        
        public void hangUpCall(String phoneNumber) {
            Phone phone = phoneDirectory.get(phoneNumber);
            if (phone != null) {
                phone.hangUp();
            } else {
                System.out.println("Phone number not found.");
            }
        }
        
        public void checkRingingPhones() {
            for (Phone phone : phoneDirectory.values()) {
                if (phone.isRinging()) {
                    System.out.println("Phone " + phone.getPhoneNumber() + " is still ringing.");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exchange exchange = new Exchange();
        
        Phone phone1 = new Phone("101");
        Phone phone2 = new Phone("102");
        Phone phone3 = new Phone("103");
        
        exchange.addPhone(phone1);
        exchange.addPhone(phone2);
        exchange.addPhone(phone3);
        
        System.out.println("Telephone System Initialized.");
        
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Make a Call");
            System.out.println("2. Answer a Call");
            System.out.println("3. Hang Up");
            System.out.println("4. Check Ringing Phones");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            if (option == 1) {
                System.out.print("Enter your phone number: ");
                String fromNumber = scanner.nextLine();
                System.out.print("Enter the phone number to call: ");
                String toNumber = scanner.nextLine();
                exchange.makeCall(fromNumber, toNumber);
            } else if (option == 2) {
                System.out.print("Enter phone number to answer: ");
                String phoneNumber = scanner.nextLine();
                exchange.answerCall(phoneNumber);
            } else if (option == 3) {
                System.out.print("Enter phone number to hang up: ");
                String phoneNumber = scanner.nextLine();
                exchange.hangUpCall(phoneNumber);
            } else if (option == 4) {
                exchange.checkRingingPhones();
            } else if (option == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
        
        scanner.close();
    }
}
