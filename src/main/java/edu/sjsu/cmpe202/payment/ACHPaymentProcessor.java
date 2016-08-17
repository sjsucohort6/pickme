package edu.sjsu.cmpe202.payment;

/**
 * @author rwatsh on 8/17/16.
 */
public class ACHPaymentProcessor implements PaymentProcessor{
    /**
     * A stub calling into the third party bank's web service to process payment.
     *
     * @return
     */
    @Override
    public boolean processPayment() {
        // call into 3rd party bank's web service
        return true;
    }
}
