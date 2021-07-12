package com.example.demo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cardpaymentmst")
public class CardPaymentEntity {

	@Id
	@Column(name="mngtNo")
	private    String    mngtNo;

	@Column(name="dataLen")
	private    String    dataLen;
	
	@Column(name="dataFlag")
	private    String    dataFlag;
	
	@Column(name="cardNo")
	private    String    cardNo;
	
	@Column(name="miCnt")
	private    String    miCnt;
	
	@Column(name="cardLife", length=4)
	private    String    cardLife;
	
	@Column(name="cvc")
	private    String    cvc;
	
	@Column(name="tradeAmt")
	private    String    tradeAmt;
	
	@Column(name="vat")
	private    String    vat;
	
	@Column(name="originMngtNo")
	private    String    originMngtNo;
	
	@Column(name="encodeCardInfo")
	private    String    encodeCardInfo;
	
	@Column(name="filler")
	private    String    filler;

	@Column(name="accountAmt")
	private    String    accountAmt;
	
	@Column(name="accountVatAmt")
	private    String    accountVatAmt;
	
	
	public String getMngtNo() {
		return mngtNo;
	}
	
	public String getDataLen() {
		return dataLen;
	}
	
	public String getDataFlag() {
		return dataFlag;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	
	public String getMiCnt() {
		return miCnt;
	}
	
	public String getCardLife() {
		return cardLife;
	}
	
	public String getCvc() {
		return cvc;
	}
	
	public String getTradeAmt() {
		return tradeAmt;
	}
	
	public String getVat() {
		return vat;
	}
	
	public String getOriginMngtNo() {
		return originMngtNo;
	}
	
	public String getEncodeCardInfo() {
		return encodeCardInfo;
	}
	
	public String getFiller() {
		return filler;
	}
	
	public String getAccountAmt() {
		return accountAmt;
	}
	
	public String getAccountVatAmt() {
		return accountVatAmt;
	}
	
	
	
	public void setMngtNo(String mngtNo) {
		this.mngtNo = mngtNo;
	}
	
	public void setDataLen(String dataLen) {
		this.dataLen = dataLen;
	}
	
	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}
	
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public void setMiCnt(String miCnt) {
		this.miCnt = miCnt;
	}
	
	public void setCardLife(String cardLife) {
		this.cardLife = cardLife;
	}
	
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	
	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	
	public void setVat(String vat) {
		this.vat = vat;
	}
	
	public void setOriginMngtNo(String originMngtNo) {
		this.originMngtNo = originMngtNo;
	}
	
	public void setEncodeCardInfo(String encodeCardInfo) {
		this.encodeCardInfo = encodeCardInfo;
	}
	
	public void setFiller(String filler) {
		this.filler = filler;
	}
	
	public void setAccountAmt(String accountAmt) {
		this.accountAmt = accountAmt;
	}
	
	public void setAccountVatAmt(String accountVatAmt) {
		this.accountVatAmt = accountVatAmt;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	private CardPaymentEntity() {};
	
	public CardPaymentEntity(String mngtNo,
								String dataLen,
								String dataFlag,
								String cardNo,
								String miCnt,
								String cardLife,
								String cvc,
								String tradeAmt,
								String vat,
								String originMngtNo,
								String encodeCardInfo,
								String filler,
								String accountAmt,
								String accountVatAmt
								) {
		super();
		this.mngtNo = mngtNo;
		this.dataLen = dataLen;
		this.dataFlag = dataFlag;
		this.cardNo = cardNo;
		this.miCnt = miCnt;
		this.cardLife = cardLife;
		this.cvc = cvc;
		this.tradeAmt = tradeAmt;
		this.vat = vat;
		this.originMngtNo = originMngtNo;
		this.encodeCardInfo = encodeCardInfo;
		this.filler = filler;
		this.accountAmt = accountAmt;
		this.accountVatAmt = accountVatAmt;
	}
}
