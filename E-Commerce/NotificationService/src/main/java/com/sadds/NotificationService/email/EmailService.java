package com.sadds.NotificationService.email;

import com.sadds.NotificationService.entity.CustomerResponse;
import com.sadds.NotificationService.entity.OrderConfirmation;
import com.sadds.NotificationService.entity.PaymentConfirmation;
import com.sadds.NotificationService.entity.ProductPurchaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmEmail(OrderConfirmation orderConfirmation) {
        CustomerResponse customer = orderConfirmation.customerResponse();
        List<ProductPurchaseResponse> products = orderConfirmation.productPurchaseResponses();

        String customerName = customer.firstname() + " " + customer.lastname();
        String fullAddress = customer.street() + " " + customer.city() + " " + customer.state() + " " + customer.zipcode() + " " + customer.country();
        String productInfo = "";

        for (ProductPurchaseResponse product : products) {
            String productBody = String.format("""
                    Product: %s
                    Per unit price: %.2f
                    Quantity: %.2f
                    
                    """,
                    product.name(),
                    product.price(),
                    product.quantityPurchased());
            productInfo += productBody;
        }
        Double totalAmount = orderConfirmation.totalAmount();

        String emailBody = String.format("""
                Dear %s,
                
                Thank you for your recent purchase from our store. Here are the details of your order:
                
                Customer Name: %s
                Shipping Address: %s
                
                List of Products purchased: 
                %s
                
                Total Amount: $%.2f
                
                Your order is being processed and will be shipped to your address shortly. If you have any questions, feel free to contact us.
                
                Best regards,
                Devansh
                """,
                customerName,
                customerName,
                fullAddress,
                productInfo,
                totalAmount
        );
        sendEmail(emailBody, "Order Confirmation", customer.email());
    }

    public void sendPaymentConfirmEmail(PaymentConfirmation paymentConfirmation) {
        String emailBody = String.format("""
                Dear Customer,
                
                We have received your payment successfully. Below are the details of your transaction:
                
                Order Reference: %s
                Total Amount: $%.2f
                Payment Method: %s
                
                Thank you for your purchase! If you have any questions, feel free to contact us.
                
                Best regards,
                Devansh
                """,
                paymentConfirmation.orderReference(),
                paymentConfirmation.totalAmount(),
                paymentConfirmation.paymentMethod().toString()
        );
        sendEmail(emailBody, "Payment confirmation", paymentConfirmation.customerEmail());
    }

    public void sendEmail(String emailBody, String subject, String to) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(emailBody);
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println("---------------------Message failed to send----------------------");
            throw new RuntimeException(e);
        }
    }
}
