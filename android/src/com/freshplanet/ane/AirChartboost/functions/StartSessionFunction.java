//////////////////////////////////////////////////////////////////////////////////////
//
//  Copyright 2012 Freshplanet (http://freshplanet.com | opensource@freshplanet.com)
//  
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//  
//    http://www.apache.org/licenses/LICENSE-2.0
//  
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//  
//////////////////////////////////////////////////////////////////////////////////////

package com.freshplanet.ane.AirChartboost.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.chartboost.sdk.Chartboost;
import com.freshplanet.ane.AirChartboost.AirChartboostDelegate;
import com.freshplanet.ane.AirChartboost.AirChartboostExtension;

public class StartSessionFunction implements FREFunction 
{	
	public FREObject call(FREContext context, FREObject[] args) 
	{
		// Retrieve the parameters
		String appId = null;
		String appSignature = null;
		try
		{
			appId = args[0].getAsString();
			appSignature = args[1].getAsString();
		}
		catch (Exception e)
		{
			AirChartboostExtension.log(e.getMessage());
			return null;
		}
		
		// Start Chartboost session
		Chartboost cb = Chartboost.sharedChartboost();
		cb.onCreate(context.getActivity(), appId, appSignature, new AirChartboostDelegate());
		cb.setImpressionsUseActivities(true);
		cb.startSession();
		cb.onStart(context.getActivity()); //aggggrrrr, nothing show up without this line

		return null;
	}
}
