# cardPayment
spring-boot, jpa, mustache
<br/>

※ 개발환경 : spring-boot , mysql, mustache, jpa , bootstrap, jquery


※ 테이블 레이아웃 :   Table: cardpaymentmst
                    Columns:
                            mngtNo varchar(20) PK 
                            dataLen varchar(4) 
                            dataFlag varchar(10) 
                            cardNo varchar(20) 
                            miCnt varchar(2) 
                            cardLife varchar(4) 
                            cvc varchar(3) 
                            tradeAmt varchar(10) 
                            vat varchar(10) 
                            originMngtNo varchar(20) 
                            encodeCardInfo varchar(300) 
                            filler varchar(47) 
                            accountAmt varchar(10) 
                            accountVatAmt varchar(10)


※ 빌드 및 실행 : 1. Run As - Spring boot app
                 2. http://localhost:8080/ 호출
                 3. 테스트
                 
                 
※ 개발화면
1. 카드등록
   ![카드등록](https://user-images.githubusercontent.com/87290936/125238051-9fd69c00-e321-11eb-8417-0d914fab1675.PNG)
   
2. 조회 및 취소
   ![조회및취소](https://user-images.githubusercontent.com/87290936/125238113-ba107a00-e321-11eb-9bc5-780edeaf128e.PNG)
   
3. 데이터 저장
   ![저장data](https://user-images.githubusercontent.com/87290936/125238116-bb41a700-e321-11eb-90f0-fc8828da8dbb.PNG)  
