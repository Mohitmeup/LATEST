package com.cg.ibs.cardmanagement.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cg.ibs.cardmanagement.bean.CaseIdBean;
import com.cg.ibs.cardmanagement.bean.CreditCardBean;
import com.cg.ibs.cardmanagement.bean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.bean.CustomerBean;
import com.cg.ibs.cardmanagement.bean.DebitCardBean;
import com.cg.ibs.cardmanagement.bean.DebitCardTransaction;

public class CardManagementDaoImpl implements CustomerDao, BankDao {

	CaseIdBean caseIdObj = new CaseIdBean();
	DebitCardBean bean = new DebitCardBean();
	CreditCardBean bean1 = new CreditCardBean();
	CustomerBean bean2 = new CustomerBean();
	private static boolean result = false;
    private static String UCI="7894561239632587";
    private static BigInteger customerAccountNum=new BigInteger("1234567890");
	private static Map<String, DebitCardTransaction> debitCardTransactionDetails = new HashMap<>();
	private static Map<String, CreditCardTransaction> creditCardTransactionDetails = new HashMap<>();
	private static Map<String, CaseIdBean> queryDetails = new HashMap<>();
	private static Map<BigInteger, DebitCardBean> debitCardDetails = new HashMap<>();
	private static Map<BigInteger, CreditCardBean> creditCardDetails = new HashMap<>();
	private static Map<BigInteger, CustomerBean> customerDetails = new HashMap<>();

	
	private static DebitCardBean createDebitCard(BigInteger accountNumber, BigInteger debitCardNumber, String debitCardStatus,
			String nameOnDebitCard, int debitCvvNum, int debitCurrentPin, LocalDate debitDateOfExpiry, String uCI,
			String debitCardType) {
		DebitCardBean dcb = new DebitCardBean();
		dcb.setAccountNumber(accountNumber);
		dcb.setDebitCardNumber(debitCardNumber);
		dcb.setDebitCardStatus(debitCardStatus);
		dcb.setNameOnDebitCard(nameOnDebitCard);
		dcb.setDebitCvvNum(debitCvvNum);
		dcb.setDebitCurrentPin(debitCurrentPin);
		dcb.setDebitDateOfExpiry(debitDateOfExpiry);
		dcb.setUCI(uCI);
		dcb.setDebitCardType(debitCardType);
		return dcb;		
	}
	
	
	static {

		DebitCardBean debit1 = createDebitCard(customerAccountNum, new BigInteger("5234567891012131"),
				"Active", "Mohit Pursnani", 067, 2131, LocalDate.of(2024, Month.JULY, 30),UCI,
				"Platinum");
		DebitCardBean debit2 = createDebitCard(customerAccountNum,new BigInteger("5221562391012233"),
				"Active", "Mohit Pursnani", 057, 2233, LocalDate.of(2022, Month.AUGUST, 30), UCI,
				"Silver");

		debitCardDetails.put(debit1.getDebitCardNumber(), debit1);
		debitCardDetails.put(debit2.getDebitCardNumber(), debit2);

		CreditCardBean credit1 = new CreditCardBean(new BigInteger("5189101213259898"), "Active", "Mohit Pursnani", 623,
				9898, LocalDate.of(2023, Month.JUNE, 30), UCI, "Silver", 200, new BigDecimal("100000.00"),
				690600.0);

		creditCardDetails.put(credit1.getCreditCardNumber(), credit1);

		CustomerBean cust1 = new CustomerBean(customerAccountNum, UCI, "Mohit", "Pursnani",
				"mohit@gmail.com", "201965231351", "9774216545");

		customerDetails.put(cust1.getAccountNumber(), cust1);

		DebitCardTransaction debittrans1 = new DebitCardTransaction("DEB101", UCI,
				customerAccountNum, new BigInteger("5234567891012131"),
				LocalDateTime.of(2019, Month.OCTOBER, 04, 12, 54, 6), new BigDecimal("1563"), "Petrol", "Debit");
		DebitCardTransaction debittrans2 = new DebitCardTransaction("DEB102", UCI,
				customerAccountNum, new BigInteger("5234567891012131"),
				LocalDateTime.of(2019, Month.AUGUST, 8, 18, 32, 8), new BigDecimal("20.45"), "Interest", "Credit");
		debitCardTransactionDetails.put(debittrans1.getTransactionId(), debittrans1);
		debitCardTransactionDetails.put(debittrans2.getTransactionId(), debittrans2);

		CreditCardTransaction credittrans1 = new CreditCardTransaction("CRED101",UCI,
				new BigInteger("5189101213259898"), LocalDateTime.of(2018, Month.OCTOBER, 12, 11, 54, 6),
				new BigDecimal("5000"), "Shopping");
		CreditCardTransaction credittrans2 = new CreditCardTransaction("CRED102", UCI,
				new BigInteger("5189101213259898"), LocalDateTime.of(2018, Month.SEPTEMBER, 25, 23, 21, 6),
				new BigDecimal("5000"), "Movie");
		creditCardTransactionDetails.put(credittrans1.getTransactionid(), credittrans1);
		creditCardTransactionDetails.put(credittrans2.getTransactionid(), credittrans2);

	}

	public CardManagementDaoImpl() {
		super();
	}

	public CardManagementDaoImpl(CaseIdBean caseIdObj) {
		super();

		this.caseIdObj = caseIdObj;

	}

	public boolean verifyAccountNumber(BigInteger accountNumber) {

		

		return customerDetails.containsKey(accountNumber);
	}

	public boolean verifyDebitCardNumber(BigInteger debitCardNumber) {

		 
		return debitCardDetails.containsKey(debitCardNumber); 
	
	}
	public boolean verifyCreditCardNumber(BigInteger creditCardNumber) {

		
		return creditCardDetails.containsKey(creditCardNumber);
	}

	public BigInteger getAccountNumber(BigInteger debitCardNumber) {
		
		return  debitCardDetails.get(debitCardNumber).getAccountNumber();
	}

	@Override
	public void newCreditCard(CaseIdBean caseIdObj) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	@Override
	public void newDebitCard(CaseIdBean caseIdObj, BigInteger accountNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	@Override
	public List<CaseIdBean> viewAllQueries() {
		List<CaseIdBean> query = new ArrayList();

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {
			query.add(entry.getValue());
		}

		return query;

	}

	@Override
	public List<DebitCardBean> viewAllDebitCards() {
		List<DebitCardBean> debitCards = new ArrayList();

		for (Entry<BigInteger, DebitCardBean> entry : debitCardDetails.entrySet()) {
			debitCards.add(entry.getValue());
		}

		return debitCards;

	}

	public List<CreditCardBean> viewAllCreditCards() {
		List<CreditCardBean> creditCards = new ArrayList<>();

		for (Entry<BigInteger, CreditCardBean> entry : creditCardDetails.entrySet()) {
			creditCards.add(entry.getValue());
		}
		return creditCards;

	}

	public void requestDebitCardLost(CaseIdBean caseIdObj, BigInteger debitCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	public void requestCreditCardLost(CaseIdBean caseIdObj, BigInteger creditCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	public String getdebitCardType(BigInteger debitCardNumber) {
		
		return  debitCardDetails.get(debitCardNumber).getDebitCardType();

	}

	public void requestDebitCardUpgrade(CaseIdBean caseIdObj, BigInteger debitCardNumber) {
		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	public int getDebitCardPin(BigInteger debitCardNumber) {
		
		return debitCardDetails.get(debitCardNumber).getDebitCurrentPin();

	}

	public void setNewDebitPin(BigInteger debitCardNumber, int newPin) {
		bean = debitCardDetails.get(debitCardNumber);
		bean.setDebitCurrentPin(newPin);
		debitCardDetails.replace(debitCardNumber, bean);
	}

	public int getCreditCardPin(BigInteger creditCardNumber) {
		
		return  creditCardDetails.get(creditCardNumber).getCreditCurrentPin();

	}

	public void setNewCreditPin(BigInteger creditCardNumber, int newPin) {
		bean1 = creditCardDetails.get(creditCardNumber);
		bean1.setCreditCurrentPin(newPin);
		creditCardDetails.replace(creditCardNumber, bean1);
	}

	public void requestCreditCardUpgrade(CaseIdBean caseIdObj, BigInteger creditCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		

	}

	public String getcreditCardType(BigInteger creditCardNumber) {

	
		return  creditCardDetails.get(creditCardNumber).getCreditCardType();
	}

	@Override
	public boolean verifyDebitTransactionId(String transactionId) {

		 
		return debitCardTransactionDetails.containsKey(transactionId);
	}

	@Override
	public void raiseDebitMismatchTicket(CaseIdBean caseIdObj, String transactionId) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	@Override
	public void raiseCreditMismatchTicket(CaseIdBean caseIdObj, String transactionId) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	public List<DebitCardTransaction> getDebitTrans(int dys, BigInteger debitCardNumber) {

		List<DebitCardTransaction> debitCardsList = new ArrayList();
		LocalDateTime dLocalDateTime = LocalDateTime.now().minusDays(dys);
		for (Entry<String, DebitCardTransaction> entry : debitCardTransactionDetails.entrySet()) {
			if (entry.getValue().getDate().isAfter(dLocalDateTime))
				debitCardsList.add(entry.getValue());
		}

		return debitCardsList;
	}

	@Override
	public List<CreditCardTransaction> getCreditTrans(int days, BigInteger creditCardNumber) {

		List<CreditCardTransaction> creditCardsList = new ArrayList();
		LocalDateTime dLocalDateTime = LocalDateTime.now().minusDays(days);
		for (Entry<String, CreditCardTransaction> entry : creditCardTransactionDetails.entrySet()) {
			if (entry.getValue().getDate().isAfter(dLocalDateTime))
				creditCardsList.add(entry.getValue());
		}

		return creditCardsList;
	}

	@Override
	public boolean verifyQueryId(String queryId) {

		
		return queryDetails.containsKey(queryId);

	}

	@Override
	public void setQueryStatus(String queryId, String newStatus) {

		caseIdObj = queryDetails.get(queryId);
		caseIdObj.setStatusOfQuery(newStatus);
		queryDetails.replace(newStatus, caseIdObj);

	}

	@Override
	public boolean verifyCreditTransactionId(String transactionId) {
		 
		return creditCardTransactionDetails.containsKey(transactionId);
	}

	@Override
	public String getCustomerReferenceId(CaseIdBean caseIdObjService, String customerReferenceId) {

		String BankId = null;

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {
			if (customerReferenceId.equals(entry.getValue().getCustomerReferenceId()))
				BankId = entry.getValue().getCaseIdTotal();
		}

	 

		return queryDetails.get(BankId).getStatusOfQuery();
	}

	@Override
	public void actionANDC(BigInteger debitCardNumber, Integer cvv, Integer pin, String queryId, String status) {
		

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {

			BigInteger accountNumber = entry.getValue().getAccountNumber();
			String uci = entry.getValue().getUCI();
			String type = entry.getValue().getDefineQuery();
			CustomerBean bean = new CustomerBean();
			bean = customerDetails.get(accountNumber);
			String firstName = bean.getFirstName();
			String lastName = bean.getLastName();
			DebitCardBean debit3 = new DebitCardBean(accountNumber, debitCardNumber, status,
					(firstName + " " + lastName), cvv, pin, LocalDate.now().plusYears(5), uci, type);
			debitCardDetails.put(debitCardNumber, debit3);
			queryDetails.remove(queryId);
		}

	}

	@Override
	public void actionANCC(BigInteger creditCardNumber, int cvv, int pin, String queryId, int score, double income,
			String status) {
	

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {

			BigDecimal limit = new BigDecimal("0");
			String uci = entry.getValue().getUCI();
			String type = entry.getValue().getDefineQuery();
			if (type .equals("Silver")) {
				limit = new BigDecimal(50000);
			} else if (type.equals("Gold")) {
				limit = new BigDecimal(100000);
			} else if (type.equals("Platinium")) {
				limit = new BigDecimal(500000);
			}
			uci = entry.getValue().getUCI();
			CustomerBean bean = new CustomerBean();
			CreditCardBean credit3 = new CreditCardBean(creditCardNumber, status, "Mohit Pursnani", cvv, pin,
					LocalDate.now().plusYears(5), uci, type, score, limit, income);
			creditCardDetails.put(creditCardNumber, credit3);
			queryDetails.remove(queryId);
		}
	}

	public void actionBlockDC(String queryId, String status) {

		caseIdObj = queryDetails.get(queryId);
		BigInteger debitCardNumber = caseIdObj.getCardNumber();
		bean = debitCardDetails.get(debitCardNumber);
		bean.setDebitCardStatus(status);
		debitCardDetails.replace(debitCardNumber, bean);
		queryDetails.remove(queryId);
	}

	public void actionBlockCC(String queryId, String status) {

		caseIdObj = queryDetails.get(queryId);
		BigInteger creditCardNumber = caseIdObj.getCardNumber();
		bean1 = creditCardDetails.get(creditCardNumber);
		bean1.setCreditCardStatus(status);
		debitCardDetails.replace(creditCardNumber, bean);
		queryDetails.remove(queryId);

	}

	public void actionUpgradeDC(String queryId) {

		caseIdObj = queryDetails.get(queryId);
		BigInteger debitCardNumber = caseIdObj.getCardNumber();
		String type = caseIdObj.getDefineQuery();
		bean = debitCardDetails.get(debitCardNumber);
		bean.setDebitCardType(type);
		debitCardDetails.replace(debitCardNumber, bean);
		queryDetails.remove(queryId);
	}
	
	public void actionUpgradeCC(String queryId) {

		caseIdObj = queryDetails.get(queryId);
		BigInteger creditCardNumber = caseIdObj.getCardNumber();
		String type = caseIdObj.getDefineQuery();
		bean1 = creditCardDetails.get(creditCardNumber);
		bean1.setCreditCardType(type);
		creditCardDetails.replace(creditCardNumber, bean1);
		queryDetails.remove(queryId);
	}

	public String getDebitCardStatus(BigInteger debitCardNumber) {
		bean = debitCardDetails.get(debitCardNumber);
		 
		return bean.getDebitCardStatus();
	}
	public String getCreditCardStatus(BigInteger creditCardNumber) {
		bean1 = creditCardDetails.get(creditCardNumber);
	
		return  bean1.getCreditCardStatus();
	}
}
