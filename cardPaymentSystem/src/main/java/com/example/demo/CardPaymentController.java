package com.example.demo;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/card", method = { RequestMethod.GET, RequestMethod.POST })
public class CardPaymentController {

	@Autowired
	private CardPaymentRepository cardPaymentRepository;

	// 1) 전체데이터 조회
	@GetMapping(value = "/get")
	public List<CardPaymentEntity> find() {
		return cardPaymentRepository.findAll();
	}

	// 2) 개별데이터 조회 : 전문번호
	@GetMapping(value = "/getMngtNo")
	public void findByMngtNo(HttpServletRequest request, Model model, HttpServletResponse response) {
		StringBuffer json = new StringBuffer();
	    String line = null;
	    
	    CardPaymentEntity cardInfo;
	    String mngtNo = "";
	    String decodeCardInfo = null;
	    String[] cardInfoArr;
	    
	    response.setCharacterEncoding("UTF-8"); 
    	response.setContentType("text/html; charset=UTF-8");
    	
    	
	    try {
		        BufferedReader reader = request.getReader();
		        while((line = reader.readLine()) != null) {
		            json.append(line);
		        }
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode node = mapper.readTree(json.toString());
		        
		        mngtNo = node.get("mngtNo").toString().replaceAll("\\\"", "");;
		    	
		        Optional<CardPaymentEntity> optional = cardPaymentRepository.findById(mngtNo);
		        
		        if(optional.isPresent())
		        {
		        	String dataLen = optional.get().getDataLen();
			        String dataFlag = optional.get().getDataFlag();
			        String cardNo = optional.get().getCardNo();
			        String miCnt = optional.get().getMiCnt();
			        String cardLife = optional.get().getCardLife();
			        String cvc = optional.get().getCvc();
			        String tradeAmt = optional.get().getTradeAmt();
			        String vat = optional.get().getVat();
			        String originMngtNo = optional.get().getOriginMngtNo();
			        String encodeCardInfo = optional.get().getEncodeCardInfo();
			        String filler = optional.get().getFiller();
			        String maskCardNo = "";
			        
			        if(encodeCardInfo != null)
			        {
			        	decodeCardInfo = StringConverter.decrypt(encodeCardInfo);
			        	System.out.println("decodeCardInfo : " + decodeCardInfo);
			        	
			        	cardInfoArr = decodeCardInfo.split(","); 
			        			
			        	System.out.println("cardInfoArr : " + cardInfoArr[0]);
			        	
			        	
			        	char wrd;
			        	
			        	if(cardInfoArr[0].length() >= 10)
			        	{
			        		for(int i=0; i<cardInfoArr[0].length(); i++)
			        		{
			        			wrd = cardInfoArr[0].charAt(i);
			        			
			        			if(i > 5 && i < cardInfoArr[0].length()-3)
			        			{
			        				wrd = '*';
			        			}
			        			
			        			maskCardNo += String.valueOf(wrd);
			        		}
			        	}
			        }
			        
			        System.out.println("dataLen : " + dataLen);
			        System.out.println("dataFlag : " + dataFlag);
			        System.out.println("cardNo : " + cardNo);
			        System.out.println("maskCardNo : " + maskCardNo);
			        System.out.println("miCnt : " + miCnt);
			        System.out.println("cardLife : " + cardLife);
			        System.out.println("cvc : " + cvc);
			        System.out.println("tradeAmt : " + tradeAmt);
			        System.out.println("vat : " + vat);
			        System.out.println("originMngtNo : " + originMngtNo);
			        System.out.println("encodeCardInfo : " + encodeCardInfo);
			        System.out.println("filler : " + filler);
			        
			        // output
			        model.addAttribute("mngtNo", mngtNo);
			        model.addAttribute("cardNo", maskCardNo);
			        model.addAttribute("cardLife", cardLife);
			        model.addAttribute("cvc", cvc);
			        model.addAttribute("dataFlag", dataFlag);
			        model.addAttribute("tradeAmt", tradeAmt);
			        model.addAttribute("vat", vat);
		        }
		        else
		        {
		        	model.addAttribute("errorCode"	, "0201");
		        	model.addAttribute("errorMsg"	, "관리번호 정보가 없습니다.");
		        }
		        
		        response.getWriter().println(model);		        
		    	
		    }catch(Exception e) {
		        System.out.println("Error reading JSON string: " + e.toString());
		    }
	    	
	}
		


	
	// 3) 개별데이터 등록
	@PostMapping(value="/regst")
	public void cardInfoRegst(HttpServletRequest request, Model model, HttpServletResponse response){ 
		
		StringBuffer json = new StringBuffer();
	    String line = null;
	    String msgData = null;
	    try {
		        BufferedReader reader = request.getReader();
		        while((line = reader.readLine()) != null) {
		            json.append(line);
		        }
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode node = mapper.readTree(json.toString());
		        
		        String dataLen = "446";
		        String dataFlag = "PAYMENT";
		        String cardNo = node.get("cardNo").toString().replaceAll("\\\"", "");
		        String miCnt = node.get("miCnt").toString().replaceAll("\\\"", "");
		        String cardLife = node.get("cardLife").toString().replaceAll("\\\"", "");
		        String cvc = node.get("cvc").toString().replaceAll("\\\"", "");
		        String tradeAmt = node.get("tradeAmt").toString().replaceAll("\\\"", "");
		        String vat = node.get("vat").toString().replaceAll("\\\"", "");
		        String originMngtNo = "";
		        String encodeCardInfo = "";
		        String filler = "";
		        String accountAmt = tradeAmt;
		        String accountVatAmt = vat;
		        
		        // 결제정보 등록
		        // 1.관리번호 생성
		        String mngtNo = StringConverter.setMngtNo();
		        
		        // 2. 암호화정보 생성
		        encodeCardInfo = StringConverter.ecryptCardInfo(cardNo, cardLife, cvc);
		        
		        // 3. db저장
		        CardPaymentEntity card = new CardPaymentEntity(mngtNo, dataLen, dataFlag, cardNo, miCnt, cardLife, cvc, tradeAmt, vat, originMngtNo, encodeCardInfo, filler, accountAmt, accountVatAmt);
		        System.out.println("getMngtNo : [" + card.getMngtNo() + "]");
		        System.out.println("getDataLen : [" + card.getDataLen() + "]");
		        System.out.println("getDataFlag : [" + card.getDataFlag() + "]");
		        System.out.println("getCardNo : [" + card.getCardNo() + "]");
		        System.out.println("getMiCnt : [" + card.getMiCnt() + "]");
		        System.out.println("getCardLife : [" + card.getCardLife() + "]");
		        System.out.println("getCvc : [" + card.getCvc() + "]");
		        System.out.println("getTradeAmt : [" + card.getTradeAmt() + "]");
		        System.out.println("getVat : [" + card.getVat() + "]");
		        System.out.println("getOriginMngtNo : [" + card.getOriginMngtNo() + "]");
		        System.out.println("getEncodeCardInfo : [" + card.getEncodeCardInfo() + "]");
		        System.out.println("getFiller : [" + card.getFiller() + "]");
		        System.out.println("getAccountAmt : [" + card.getAccountAmt() + "]");
		        System.out.println("getAccountVatAmt : [" + card.getAccountVatAmt() + "]");
		        
		        
		        cardPaymentRepository.save(card);
		        
		        // 전문데이터
		        msgData = cardMsgInfo(card);
		
		        
		        // output 
		        model.addAttribute("mngtNo", mngtNo);
				model.addAttribute("msgData", msgData);
				response.getWriter().println(model);
				
		    }catch(Exception e) {
		        System.out.println("Error reading JSON string: " + e.toString());
		    }
	}
	
		// 4) 결제취소
		@PostMapping(value="/cancel")
		public void cardInfoCancel(HttpServletRequest request, Model model, HttpServletResponse response){ 
			
			StringBuffer json = new StringBuffer();
		    String line = null;
		    String msgData = null;
		    String originMngtNo = null;
		    
		    response.setCharacterEncoding("UTF-8"); 
        	response.setContentType("text/html; charset=UTF-8");
        	
		    try {
			        BufferedReader reader = request.getReader();
			        while((line = reader.readLine()) != null) {
			            json.append(line);
			        }
			        ObjectMapper mapper = new ObjectMapper();
			        JsonNode node = mapper.readTree(json.toString());
			        System.out.println("[cardInfoCancel] node : " + node);
			        
			        // 1. 원 거래번호 조회
			        originMngtNo = node.get("cnlMngtNo").toString().replaceAll("\\\"", "");
			        
			        String cnlVatFlag = node.get("cnlVatFlag").toString().replaceAll("\\\"", "");
			        
			        Optional<CardPaymentEntity> optional = cardPaymentRepository.findById(originMngtNo);
			        if(optional.isPresent())
			        {
			        	System.out.println(optional.get().getCardNo());

			        	// 2.기본정보 set
				        String mngtNo = StringConverter.setMngtNo();
			        	String dataLen 		= "446";
				        String dataFlag 	= "CANCEL";
				        String cardNo 		= optional.get().getCardNo();
				        String miCnt 		= "00";	//일시불
				        String cardLife 	= optional.get().getCardLife();
				        String cvc 			= optional.get().getCvc();
				        String tradeAmt 	= node.get("cnlTradeAmt").toString().replaceAll("\\\"", "");
				        String vat			= node.get("cnlVat").toString().replaceAll("\\\"", "");
//				        originMngtNo 		= "";
				        String encodeCardInfo = optional.get().getEncodeCardInfo();
				        String filler = "";
				        String accountAmt = optional.get().getAccountAmt();
				        String accountVatAmt = optional.get().getAccountVatAmt();
			        	
			        	
			        	
			        	
			        	// 3. 금액정합성
				        
				        BigDecimal bAccountAmt = new BigDecimal(accountAmt);
						BigDecimal bAccountVatAmt = new BigDecimal(accountVatAmt);
						BigDecimal bTradeAmt = new BigDecimal(tradeAmt);
						BigDecimal bVat = new BigDecimal(vat);
						
				        if(bTradeAmt.compareTo(bAccountAmt) > 0)
				        {
				        	model.addAttribute("errorCode"	, "0301");
				        	model.addAttribute("errorMsg"	, "계좌잔액보다 취소요청금액이 큽니다.");
				        	
				        	response.getWriter().println(model);
				        	return;
				        }
			        	
				        System.out.println("cnlVatFlag : " + cnlVatFlag);
				        
				        if(bVat.compareTo(bAccountVatAmt) > 0 && "SET".equals(cnlVatFlag) == false)
				        {
				        	System.out.println("정합성2");
				        	model.addAttribute("errorCode"	, "0302");
				        	model.addAttribute("errorMsg"	, "계좌부가세잔액보다 취소요청 부가세금액이 큽니다.");
				        	
				        	response.getWriter().println(model);
				        	return;
				        }
				        
				        // 입력받은 부가세가 없으면 계좌 부가세 잔액으로 치환
				        if("SET".equals(cnlVatFlag))
				        {
				        	if(bVat.compareTo(bAccountVatAmt) > 0)
				        	{
				        		bVat = bAccountVatAmt;
				        	}
				        }
				        
				        if((bAccountVatAmt.subtract(bVat)).compareTo(bAccountAmt.subtract(bTradeAmt)) > 0)
				        {
				        	System.out.println("정합성3");
				        	model.addAttribute("errorCode"	, "0303");
				        	model.addAttribute("errorMsg"	, "계좌잔액보다 부가세금액이 큽니다.");
				        	
				        	response.getWriter().println(model);
				        	return;
				        }
				        
				        bAccountAmt 	= bAccountAmt.subtract(bTradeAmt);
				        bAccountVatAmt 	= bAccountVatAmt.subtract(bVat);
				        
				        // 3. db저장
				        accountAmt 		= null;
				        accountVatAmt 	= null;
				        
				        CardPaymentEntity card = new CardPaymentEntity(mngtNo, dataLen, dataFlag, cardNo, miCnt, cardLife, cvc, tradeAmt, vat, originMngtNo, encodeCardInfo, filler, accountAmt, accountVatAmt);
				        System.out.println("getMngtNo : [" + card.getMngtNo() + "]");
				        System.out.println("getDataLen : [" + card.getDataLen() + "]");
				        System.out.println("getDataFlag : [" + card.getDataFlag() + "]");
				        System.out.println("getCardNo : [" + card.getCardNo() + "]");
				        System.out.println("getMiCnt : [" + card.getMiCnt() + "]");
				        System.out.println("getCardLife : [" + card.getCardLife() + "]");
				        System.out.println("getCvc : [" + card.getCvc() + "]");
				        System.out.println("getTradeAmt : [" + card.getTradeAmt() + "]");
				        System.out.println("getVat : [" + card.getVat() + "]");
				        System.out.println("getOriginMngtNo : [" + card.getOriginMngtNo() + "]");
				        System.out.println("getEncodeCardInfo : [" + card.getEncodeCardInfo() + "]");
				        System.out.println("getFiller : [" + card.getFiller() + "]");
				        System.out.println("getAccountAmt : [" + card.getAccountAmt() + "]");
				        System.out.println("getAccountVatAmt : [" + card.getAccountVatAmt() + "]");
				        cardPaymentRepository.save(card);
				        
				        // 4. 원거래 업데이트
				        accountAmt 		= bAccountAmt.toString();
				        accountVatAmt 	= bAccountVatAmt.toString();
				        CardPaymentEntity originCard = new CardPaymentEntity(originMngtNo, optional.get().getDataLen(), optional.get().getDataFlag(), 
				        		optional.get().getCardNo(), optional.get().getMiCnt(), optional.get().getCardLife(), optional.get().getCvc(),
				        		optional.get().getTradeAmt(), optional.get().getVat(),optional.get().getOriginMngtNo(), optional.get().getEncodeCardInfo(), optional.get().getFiller(), accountAmt, accountVatAmt);
				        System.out.println("getMngtNo : [" + originCard.getMngtNo() + "]");
				        System.out.println("getDataLen : [" + originCard.getDataLen() + "]");
				        System.out.println("getDataFlag : [" + originCard.getDataFlag() + "]");
				        System.out.println("getCardNo : [" + originCard.getCardNo() + "]");
				        System.out.println("getMiCnt : [" + originCard.getMiCnt() + "]");
				        System.out.println("getCardLife : [" + originCard.getCardLife() + "]");
				        System.out.println("getCvc : [" + originCard.getCvc() + "]");
				        System.out.println("getTradeAmt : [" + originCard.getTradeAmt() + "]");
				        System.out.println("getVat : [" + originCard.getVat() + "]");
				        System.out.println("getOriginMngtNo : [" + originCard.getOriginMngtNo() + "]");
				        System.out.println("getEncodeCardInfo : [" + originCard.getEncodeCardInfo() + "]");
				        System.out.println("getFiller : [" + originCard.getFiller() + "]");
				        System.out.println("getAccountAmt : [" + originCard.getAccountAmt() + "]");
				        System.out.println("getAccountVatAmt : [" + originCard.getAccountVatAmt() + "]");
				        cardPaymentRepository.save(originCard);
				        
				        // 전문데이터
				        msgData = cardMsgInfo(card);
				        
				        // output 
				        model.addAttribute("mngtNo", mngtNo);
						model.addAttribute("msgData", msgData);

			        }
			        else
			        {
			        	model.addAttribute("errorCode"	, "0304");
			        	model.addAttribute("errorMsg"	, "관리번호 정보가 없습니다.");
			        }
			        System.out.println("출력");
			        
					response.getWriter().println(model);			        
			        
			    }catch(Exception e) {
			        System.out.println("Error reading JSON string: " + e.toString());
			    }
		}
		
	
	public String cardMsgInfo(CardPaymentEntity card)
	{
		String msgInfo = "";
		
		String dataLen 	= StringConverter.setMsgFormat("dataLen", card.getDataLen());
		String dataFlag = StringConverter.setMsgFormat("dataFlag", card.getDataFlag());
		String cardNo 	= StringConverter.setMsgFormat("cardNo", card.getCardNo());
		String miCnt 	= StringConverter.setMsgFormat("miCnt", card.getMiCnt());
		String cardLife = StringConverter.setMsgFormat("cardLife", card.getCardLife());
		String cvc 		= StringConverter.setMsgFormat("cvc", card.getCvc());
		String tradeAmt = StringConverter.setMsgFormat("tradeAmt", card.getTradeAmt());
		String vat		= StringConverter.setMsgFormat("vat", card.getVat());
		String originMngtNo = StringConverter.setMsgFormat("originMngtNo", card.getOriginMngtNo());
		String encodeCardInfo = StringConverter.setMsgFormat("encodeCardInfo", card.getEncodeCardInfo());
		String filler 	= StringConverter.setMsgFormat("filler", card.getFiller());
		
		msgInfo = card.getMngtNo() + dataLen + dataFlag + cardNo + miCnt + cardLife + cvc + tradeAmt + vat +
					originMngtNo + encodeCardInfo + filler;
		
		return msgInfo;
	}
		
}
