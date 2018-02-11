package com.beamofsoul.springboot.entity.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserMessageDTO {

	String senderNickname;
	String senderPhoto;
	String messageTitle;
	String messageContent;
	Date createDate;
}
