/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author miend
 */
public class MessageModel {
    private int messageid;
    private int userid;
    private int courseid;
    private String msg;
    private String send;

    public int getMessageid() {
        return messageid;
    }

    public int getUserid() {
        return userid;
    }

    public int getCourseid() {
        return courseid;
    }

    public String getMsg() {
        return msg;
    }

    public String getSend() {
        return send;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSend(String send) {
        this.send = send;
    }
    
    
    
    
}
