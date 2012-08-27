package com.coders.mobimarkt.interaction.intf;



public interface IInteractionManager {
	public void startInteraction(int campaignId, IInteraction interaction,ITimeTable timetable);
	public void terminateInteractions(int campaignId);
	public void pauseInteractions(int campaignId);
	public void terminateInteraction(IInteraction interaction);
	public void pauseInteraction(IInteraction interaction);
}
