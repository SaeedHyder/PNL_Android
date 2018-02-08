package com.ingic.pnl.entities;

import com.google.gson.annotations.SerializedName;
import com.ingic.pnl.helpers.DateHelper;

import java.util.Date;

/**
 * Created on 12/23/2017.
 */

public class NotificationItemEnt {
    public NotificationItemEnt(int userId, String title, String message, boolean isPush, boolean isRead, int id, String createdOn, String updatedOn, boolean isActive) {
        UserId = userId;
        Title = title;
        Message = message;
        IsPush = isPush;
        IsRead = isRead;
        Id = id;
        CreatedOn = createdOn;
        UpdatedOn = updatedOn;
        IsActive = isActive;
    }

    @SerializedName("UserId")
    private int UserId;
    @SerializedName("Title")
    private String Title;
    @SerializedName("Message")
    private String Message;
    @SerializedName("IsPush")
    private boolean IsPush;
    @SerializedName("IsRead")
    private boolean IsRead;
    @SerializedName("Id")
    private int Id;
    @SerializedName("CreatedOn")
    private String CreatedOn;
    @SerializedName("UpdatedOn")
    private String UpdatedOn;
    @SerializedName("IsActive")
    private boolean IsActive;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public boolean getIsPush() {
        return IsPush;
    }

    public void setIsPush(boolean IsPush) {
        this.IsPush = IsPush;
    }

    public boolean getIsRead() {
        return IsRead;
    }

    public void setIsRead(boolean IsRead) {
        this.IsRead = IsRead;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCreatedOn() {
        return DateHelper.getTimeAgo(getParseCreatedOn(CreatedOn).getTime());
    }

    public void setCreatedOn(String CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public Date getParseCreatedOn(String createdOn) {

        return DateHelper.getLocalTimeDate(createdOn);
    }

    public String getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(String UpdatedOn) {
        this.UpdatedOn = UpdatedOn;
    }

    public boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
