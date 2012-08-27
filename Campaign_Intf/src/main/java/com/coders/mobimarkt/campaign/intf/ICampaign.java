package com.coders.mobimarkt.campaign.intf;

import com.coders.mobimarkt.data.intf.IDataSet;
import com.coders.mobimarkt.interaction.intf.ITimeTable;
import com.coders.mobimarkt.survey.ISurvey;
import com.coders.mobimarkt.user.ICustomer;


public interface ICampaign {
	public void setDataSet(IDataSet dataSet);
	public IDataSet getDataSet();
	
	public void setName(String name);
	public String getName();

	public void setOwner(ICustomer customer);
	public ICustomer getOwner();
	
	public ISurvey getSurvey();
	public void setSurvey(ISurvey survey);
	
	public int getId();
	public void setId(int id);
	
	public ITimeTable getTimeTable();
	public void setTimeTable(ITimeTable timetable);
}
