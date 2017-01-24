/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RodolfoSaldanha
 */
public class Inscricao {
    
    private Long fk_uid;
    private Long fk_eventid;
    private String payment;
    private String getToKnowEvent;
    private String descriptiveField;

    public Long getFk_uid() {
        return fk_uid;
    }
    
    public Long getFk_eventid() {
        return fk_eventid;
    }

    public String getPayment() {
        return payment;
    }

    public String getGetToKnowEvent() {
        return getToKnowEvent;
    }

    public String getDescriptiveField() {
        return descriptiveField;
    }

    public void setFk_eventid(Long fk_eventid) {
        this.fk_eventid = fk_eventid;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setGetToKnowEvent(String getToKnowEvent) {
        this.getToKnowEvent = getToKnowEvent;
    }

    public void setDescriptiveField(String descriptiveField) {
        this.descriptiveField = descriptiveField;
    }

    public void setFk_uid(Long fk_uid) {
        this.fk_uid = fk_uid;
    }
    
}
