package com.xxxx.yeb.pojo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 消息
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ChatMsg对象", description="")
public class ChatMsg {
   @ApiModelProperty(value = "消息发送者")
   private String from;

   @ApiModelProperty(value = "消息接收者")
   private String to;

   @ApiModelProperty(value = "消息内容")
   private String content;

   @ApiModelProperty(value = "发送时间")
   private Date date;

   private String fromNickName;

   public String getFrom() {
      return from;
   }

   public void setFrom(String from) {
      this.from = from;
   }

   public String getTo() {
      return to;
   }

   public void setTo(String to) {
      this.to = to;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getFromNickName() {
      return fromNickName;
   }

   public void setFromNickName(String fromNickName) {
      this.fromNickName = fromNickName;
   }
}