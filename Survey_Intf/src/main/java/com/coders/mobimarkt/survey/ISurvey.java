package com.coders.mobimarkt.survey;

import com.coders.mobimarkt.data.intf.IData;
import com.coders.mobimarkt.interaction.intf.IInteraction;


public interface ISurvey {
	public IInteraction getInteraction(IData data);
}
