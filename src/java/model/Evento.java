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
public class Evento {
    
    private Long eventid;
    private String eventName;
    private String description;
    private String impInformation;
    private int price;
    private String beginDate;
    private String endDate;
    private String beginTime;
    private String endTime;
    private String weekdays;
    private Long fk_usuario_evento;
    private Long fk_local_evento;
    private Long fk_entPromotora_evento;
    private int eventType;

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImpInformation() {
        return impInformation;
    }

    public void setImpInformation(String impInformation) {
        this.impInformation = impInformation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(String weekdays) {
        this.weekdays = weekdays;
    }

    public Long getFk_usuario_evento() {
        return fk_usuario_evento;
    }

    public void setFk_usuario_evento(Long fk_usuario_evento) {
        this.fk_usuario_evento = fk_usuario_evento;
    }

    public Long getFk_local_evento() {
        return fk_local_evento;
    }

    public void setFk_local_evento(Long fk_local_evento) {
        this.fk_local_evento = fk_local_evento;
    }

    public Long getFk_entPromotora_evento() {
        return fk_entPromotora_evento;
    }

    public void setFk_entPromotora_evento(Long fk_entPromotora_evento) {
        this.fk_entPromotora_evento = fk_entPromotora_evento;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
    
    
}
