package com.mycare.actions.utils;

import java.io.Serializable;

public class MessageBean
  implements Serializable
{
  private static final long serialVersionUID = 3338239107654799549L;
  private int type = 0;
  private String accountId = null;
  private int userId = -1;
  private long transactionId = -1L;

  public MessageBean(int type, String accountId, int userId, long transactionId) {
    setType(type);
    setAccountId(accountId);
    setUserId(userId);
    setTransactionId(transactionId);
  }

  public int getType() {
    return this.type;
  }

  private void setType(int type) {
    this.type = type;
  }

  public String getAccountId() {
    return this.accountId;
  }

  public long getTransactionId() {
    return this.transactionId;
  }

  public int getUserId() {
    return this.userId;
  }

  private void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  private void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  private void setUserId(int userId) {
    this.userId = userId;
  }
}