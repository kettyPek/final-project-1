package javabootcamp.actions;

import java.time.LocalDateTime;

public class ActivityData {
	
	protected ActivityName activityName;
	protected double balanceChange;
	protected LocalDateTime timeStamp;
	protected String additionalInfo;
	
	public ActivityData(ActivityName activityName,double balanceChange,String additionalInfo) {
		this.activityName = activityName;
		this.balanceChange = balanceChange;
		this.timeStamp = LocalDateTime.now();
		this.additionalInfo = additionalInfo;
	}
	
	public LocalDateTime getTimeStemp() {
		return timeStamp;
	}

	@Override
	public String toString() {
		return "Activity: "+ activityName + ", balance change: " + balanceChange + ", time stamp: "
				+ timeStamp + ", additiona info: " + additionalInfo;
	}
	
	

}
