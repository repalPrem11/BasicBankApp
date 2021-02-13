package com.example.basicbankapp;

public class model {
    String id ,name, email, phone, account_no, IFSC_code, balance;
    String trans_id,date,sender_id,receiver_id,amount,status;

    public model( String id, String name, String phone, String balance) {
        this.id= id;
        this.name = name;
        this.phone = phone;
        this.balance = balance;
    }

    public model(String id, String name, String email, String phone, String account_no, String IFSC_code, String balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.account_no = account_no;
        this.IFSC_code = IFSC_code;
        this.balance = balance;
    }

    public model(String trans_id, String date, String sender_id, String receiver_id, String amount, String status) {
        this.trans_id = trans_id;
        this.date = date;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.amount = amount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getIFSC_code() {
        return IFSC_code;
    }

    public void setIFSC_code(String IFSC_code) {
        this.IFSC_code = IFSC_code;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}