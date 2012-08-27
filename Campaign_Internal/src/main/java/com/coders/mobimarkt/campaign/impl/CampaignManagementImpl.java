package com.coders.mobimarkt.campaign.impl;

import java.util.Iterator;
import java.util.List;

import com.coders.mobimarkt.campaign.intf.ICampaign;
import com.coders.mobimarkt.campaign.intf.ICampaignManagement;
import com.coders.mobimarkt.data.intf.IData;
import com.coders.mobimarkt.interaction.intf.IInteraction;
import com.coders.mobimarkt.interaction.intf.IInteractionManager;
import com.coders.mobimarkt.interaction.intf.ITimeTable;
import com.coders.mobimarkt.survey.ISurvey;


public class CampaignManagementImpl implements ICampaignManagement{
	private ICampaign campaign = null;
	private IInteractionManager interactionManager = null;
	private ITimeTable timeTable = null;
	
	public void setCampaign(ICampaign campaign) {
		this.campaign = campaign;
	}
	
	public ICampaign getCampaign() {
		return campaign;
	}
	
	
	public void start() {
		List<IData> datas = getCampaign().getDataSet().getDatas();
		ISurvey survey = getCampaign().getSurvey();
		IInteractionManager interactionManager = getInteractionManager();
		ITimeTable timetable = getTimeTable();
		int campaignId = campaign.getId();
		for (Iterator<IData> iterator = datas.iterator(); iterator.hasNext();) {
			IData iData = iterator.next();
			IInteraction interaction = survey.getInteraction(iData);
			interactionManager.startInteraction(campaignId, interaction, timetable);			
		}
	}

	public void setInteractionManager(IInteractionManager interactionManager) {
		this.interactionManager = interactionManager;
	}

	public IInteractionManager getInteractionManager() {
		return interactionManager;
	}

	public void setTimeTable(ITimeTable timeTable) {
		this.timeTable = timeTable;
	}

	public ITimeTable getTimeTable() {
		return timeTable;
	}

	public void pause() {
		getInteractionManager().pauseInteractions(getCampaign().getId());
		
	}

	public void terminate() {
		getInteractionManager().terminateInteractions(getCampaign().getId());
	}

}
