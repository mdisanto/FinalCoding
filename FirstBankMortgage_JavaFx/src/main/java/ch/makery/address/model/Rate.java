package ch.makery.address.model;

import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;
import ch.makery.address.view.MortgageController;
import domain.RateDomainModel;

public class Rate extends RateDomainModel {
	
	public static double getPayment(int NumberOfPayments)
	{
		//FinalExam
		//	Normally this kind of method would be in a BLL, but alas...
		
		//	Figure out payment based on:
		//	Interest rate
		//	PV
		//	FV (make FV = 0, unless you want a balloon payment
		//	Compounding = True
		//	Number of Payments (passed in)
		
		
		
		double InterestRate = RateDAL.getRate(MortgageController.CreditScore);
		double FV = 0;
		double Payment = ((MortgageController.HouseCost)*InterestRate)/NumberOfPayments;
		boolean Compounding = true;
		
		double PV = FinanceLib.pv(InterestRate, NumberOfPayments, Payment, FV, Compounding);
		double PMT = FinanceLib.pmt(InterestRate, NumberOfPayments, PV, FV, Compounding);
		return PMT;
	}
}
