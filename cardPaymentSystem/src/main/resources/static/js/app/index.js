
function validCheck(_flag)
{
	var result = true; 
	
	// 결제 등록
	if(_flag == "1")
	{
		if($("#cardNo").val() == null || $("#cardNo").val().trim().length == 0 || $("#cardNo").val().trim().length < 10)
		{
			alert("카드번호를 10자리 이상 입력하세요.");
			$("#cardNo").focus();
			result = false;
		}
		else if($("#cardLife").val() == null || $("#cardLife").val().trim().length == 0 || $("#cardLife").val().trim().length <4)
		{
			alert("카드 유효기간 4자리를 입력하세요.");
			$("#cardLife").focus();
			result = false;
		}
		else if($("#cvc").val() == null || $("#cvc").val().trim().length == 0 || $("#cvc").val().trim().length < 3)
		{
			alert("cvc 3자리를 입력하세요.");
			$("#cvc").focus();
			result = false;
		}
		else if($("#miCnt").val() == null || $("#miCnt").val().trim().length == 0)
		{
			alert("할부개월수를 입력하세요.");
			$("#miCnt").focus();
			result = false;
		}
		else if($("#tradeAmt").val() == null || $("#tradeAmt").val().trim().length == 0)
		{
			alert("결제금액을 입력하세요.");
			$("#tradeAmt").focus();
			result = false;
		}
	}
	else if(_flag == "2") // 단건 조회
	{
		if($("#mngtNo").val() == null || $("#mngtNo").val().trim().length == 0 || $("#mngtNo").val().trim().length != 20)
		{
			alert("관리번호 20자리를 정확히 입력하세요.");
			$("#mngtNo").focus();
			result = false;
		}
		
	} 
	else if(_flag == "3") // 취소 등록
	{
		if($("#cnlMngtNo").val() == null || $("#cnlMngtNo").val().trim().length == 0 || $("#cnlMngtNo").val().trim().length != 20)
		{
			alert("취소요청 관리번호 20자리를 정확히 입력하세요.");
			$("#cnlMngtNo").focus();
			result = false;
		}
		else if($("#cnlTradeAmt").val() == null || $("#cnlTradeAmt").val().trim().length == 0)
		{
			alert("취소요청 금액을 입력하세요.");
			$("#cnlTradeAmt").focus();
			result = false;
		}
	}
	
	return result;
}

var main = {
   init : function () {
       var _this = this;
       $('#btn-save').on('click', function () {
           _this.save();
       }),
	   $('#btn-get').on('click', function () {
           _this.get();
       }),
	   $('#btn-cancel').on('click', function () {
           _this.cancel();
       })
   },
   save : function () {
       var data = {
    		cardNo : $("#cardNo").val(),
    		cardLife : $("#cardLife").val(),
    		cvc : $("#cvc").val(),
    		miCnt : $("#miCnt").val(),
    		tradeAmt : $("#tradeAmt").val(),
    		vat : $("#vat").val(),
			vatFlag : "ORG",
			mngtNo : $("#mngtNo").val()
       };
	   if($("#vat").val() == null  || $("#vat").val().trim().length == 0)
	   {
	   		data.vat = Math.round($("#tradeAmt").val() / 11);
			data.vatFlag = "SET";
	   }	
	   if(validCheck("1")){
		 	$.ajax({
	           type : 'POST',
	           url : '/card/regst',
	           dataType : 'json',
	           contentType : 'application/json; charset=utf-8',
	           data : JSON.stringify(data)
	       }).done(function (resp) {
	           alert(resp);
	           window.location.href = '/';
	       }).fail(function (error) {
	           alert(JSON.stringify(error));
	       })
	   }
      
   },
	get : function () {
       var data = {
			mngtNo : $("#mngtNo").val()
       };
	   if(validCheck("2")){
	       $.ajax({
	           type : 'POST',
	           url : '/card/getMngtNo',
	           dataType : 'json',
	           contentType : 'application/json; charset=utf-8',
	           data : JSON.stringify(data)
	       }).done(function (resp) {
				if(resp == null)
				{
					alert("결제된 내역이 없습니다.");	
				}
				else
				{
					alert(resp);
				}
	           window.location.href = '/';
	       }).fail(function (error) {
	           alert(JSON.stringify(error));
	       })
	  }
   },
	cancel : function () {
       var data = {
			cnlMngtNo : $("#cnlMngtNo").val(),
			cnlTradeAmt : $("#cnlTradeAmt").val(),
			cnlVat : $("#cnlVat").val(),
			cnlVatFlag : "ORG"
       };
 	   if($("#cnlVat").val() == null  || $("#cnlVat").val().trim().length == 0)
	   {
	   		data.cnlVat = Math.round($("#cnlTradeAmt").val() / 11);
			data.cnlVatFlag = "SET";
	   }
	   if(validCheck("3")){
	       $.ajax({
	           type : 'POST',
	           url : '/card/cancel',
	           dataType : 'json',
	           contentType : 'application/json; charset=utf-8',
	           data : JSON.stringify(data)
	       }).done(function (resp) {
				if(resp == null)
				{
					alert("결제된 내역이 없습니다.");	
				}
				else
				{
					alert(resp);
				}
	           window.location.href = '/';
	       }).fail(function (error) {
	           alert(JSON.stringify(error));
	       })
   		}
	}
};

main.init();


// 데이터 정합성 : 문자체크 / 숫자체크 / 길이