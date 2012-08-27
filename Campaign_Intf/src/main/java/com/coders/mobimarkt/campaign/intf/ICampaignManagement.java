package com.coders.mobimarkt.campaign.intf;

import com.coders.mobimarkt.interaction.intf.IInteractionManager;
import com.coders.mobimarkt.interaction.intf.ITimeTable;


public interface ICampaignManagement {
	public void setCampaign(ICampaign campaign);
	public ICampaign getCampaign();
	public void setInteractionManager(IInteractionManager interactionManager);
	public IInteractionManager getInteractionManager();
	public void setTimeTable(ITimeTable timeTable);
	public ITimeTable getTimeTable();
	public void start();
	public void pause();
	public void terminate();
}
