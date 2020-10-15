import java.time.LocalDate;
import java.time.Period;
/**
 * Created by Philip Zamayeri
 * Date: 2020-10-11
 * Time: 19:50
 * Project: Gym
 * Copyright: MIT
 */
public class Customer {
    private String idNr;
    private String name;
    private LocalDate dateOfLastPayment;
    private boolean activeMembership;

    public Customer(){}

    public Customer(String idNr, String name, LocalDate dateOfLastPayment) {
        setIdNr(idNr);
        setName(name);
        setDateOfLastPayment(dateOfLastPayment);
        isCustomerMember();
    }

    public void setIdNr(String idNr) {
        this.idNr = idNr;
    }

    public String getIdNr() {
        return idNr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDateOfLastPayment(LocalDate dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public LocalDate getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    public boolean getActiveMembership() {
        return activeMembership;
    }

    public void isCustomerMember() {
        Period period = Period.between(LocalDate.now(), dateOfLastPayment);
        if (period.getYears() <= -1) {
            activeMembership = false;
        } else {
            activeMembership = true;
        }
    }
}
