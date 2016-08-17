package edu.sjsu.cmpe202.payment;

/**
 * @author rwatsh on 8/17/16.
 */
public class CreditCardPaymentProcessor implements PaymentProcessor {
    /**
     * This method is a stub to call into the third party credit agency's web service to process payment.
     *
     * @return
     */
    @Override
    public boolean processPayment() {
        // process payment with third party credit agency's web service
        return true;
    }
}
