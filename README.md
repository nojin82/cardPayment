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
                 
                 
