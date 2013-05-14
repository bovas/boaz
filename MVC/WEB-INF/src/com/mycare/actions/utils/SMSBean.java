package com.mycare.actions.utils;

import java.io.Serializable;

public class SMSBean extends MessageBean
  implements Serializable
{
  private static final long serialVersionUID = -7444463560943630432L;
  private String phoneNo = null;
  private String content = null;

  public SMSBean(String phoneNo, String content, String accountId, int userId, long transactionId) {
    super(2, accountId, userId, transactionId);
    setPhoneNo(phoneNo);
    setContent(content);
  }

  public String getContent() {
    return this.content;
  }

  private void setContent(String content) {
    this.content = content;
  }

  public String getPhoneNo() {
    return this.phoneNo;
  }

  private void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }
}